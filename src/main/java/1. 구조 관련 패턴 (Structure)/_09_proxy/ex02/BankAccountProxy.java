package org._09_proxy.ex02;

/**
 * Protection Proxy 클래스
 * - 실제 은행 계좌에 대한 접근을 제어
 * - 인증, 권한 검사, 로깅 등의 부가 기능 제공
 * - 보안이 중요한 금융 서비스에서 활용
 */
public class BankAccountProxy implements BankAccount {
    private RealBankAccount realAccount;    // 실제 계좌 객체 참조
    private String userPassword;            // 사용자 비밀번호
    private String correctPassword;         // 올바른 비밀번호
    private boolean isAuthenticated;        // 인증 상태
    private int failedAttempts;            // 실패 횟수

    public BankAccountProxy(String accountNumber, double initialBalance, String password) {
        this.correctPassword = password;
        this.isAuthenticated = false;
        this.failedAttempts = 0;
        // 실제 계좌는 인증 후 필요시 생성 (Lazy Loading)
        this.realAccount = new RealBankAccount(accountNumber, initialBalance);
        System.out.println("계좌 프록시가 생성되었습니다. 사용하려면 인증이 필요합니다.");
    }

    /**
     * 사용자 인증 메소드
     * - 비밀번호 확인
     * - 실패 횟수 관리
     */
    public boolean authenticate(String password) {
        if (failedAttempts >= 3) {
            System.out.println("❌ 계좌가 잠겼습니다. 고객센터에 문의하세요.");
            return false;
        }

        if (correctPassword.equals(password)) {
            isAuthenticated = true;
            failedAttempts = 0;
            System.out.println("✅ 인증 성공!");
            return true;
        } else {
            failedAttempts++;
            System.out.println("❌ 인증 실패! 남은 시도 횟수: " + (3 - failedAttempts));
            return false;
        }
    }

    /**
     * 인증 상태 확인 후 접근 제어
     */
    private boolean checkAuthentication() {
        if (!isAuthenticated) {
            System.out.println("🔒 접근 거부: 인증이 필요합니다.");
            return false;
        }
        return true;
    }

    /**
     * 로그 기록 메소드
     */
    private void logTransaction(String operation, double amount) {
        System.out.println("📝 거래 로그: " + operation + " - " + amount + "원");
    }

    /**
     * 입금 기능 - 인증 확인 후 실제 객체에 위임
     */
    @Override
    public void deposit(double amount) {
        if (!checkAuthentication()) return;

        logTransaction("입금 시도", amount);
        realAccount.deposit(amount);
    }

    /**
     * 출금 기능 - 인증 확인 및 추가 보안 검사 후 위임
     */
    @Override
    public void withdraw(double amount) {
        if (!checkAuthentication()) return;

        // 추가 보안: 큰 금액 출금 시 추가 확인
        if (amount > 1000000) {
            System.out.println("⚠️ 고액 출금 경고: 100만원 초과 출금은 별도 인증이 필요합니다.");
            return;
        }

        logTransaction("출금 시도", amount);
        realAccount.withdraw(amount);
    }

    /**
     * 잔액 조회 - 인증 확인 후 위임
     */
    @Override
    public double getBalance() {
        if (!checkAuthentication()) return -1;

        logTransaction("잔액 조회", 0);
        return realAccount.getBalance();
    }

    /**
     * 로그아웃 기능
     */
    public void logout() {
        isAuthenticated = false;
        System.out.println("🚪 로그아웃 되었습니다.");
    }
}