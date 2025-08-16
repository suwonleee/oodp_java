package org._09_proxy.ex02;

/**
 * Protection Proxy 패턴 데모 클래스
 * - 은행 계좌 접근 제어 예제
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Proxy 패턴 - 은행 계좌 접근 제어 예제 ===");

        // 계좌 프록시 생성
        BankAccountProxy accountProxy = new BankAccountProxy("123-456-789", 100000, "1234");

        System.out.println("\n1. 인증 없이 접근 시도:");
        accountProxy.getBalance();  // 인증 안됨 - 접근 거부
        accountProxy.deposit(50000); // 인증 안됨 - 접근 거부

        System.out.println("\n2. 잘못된 비밀번호로 인증 시도:");
        accountProxy.authenticate("0000"); // 실패
        accountProxy.authenticate("9999"); // 실패

        System.out.println("\n3. 올바른 비밀번호로 인증:");
        accountProxy.authenticate("1234"); // 성공

        System.out.println("\n4. 인증 후 정상 거래:");
        accountProxy.getBalance();        // 잔액 조회 성공
        accountProxy.deposit(50000);      // 입금 성공
        accountProxy.withdraw(30000);     // 출금 성공

        System.out.println("\n5. 고액 출금 시도 (보안 제한):");
        accountProxy.withdraw(1500000);   // 고액 출금 제한으로 거부

        System.out.println("\n6. 로그아웃 후 접근 시도:");
        accountProxy.logout();
        accountProxy.getBalance();        // 로그아웃 후 접근 거부

        System.out.println("\n7. 재인증 후 거래:");
        accountProxy.authenticate("1234");
        accountProxy.getBalance();
    }
}