package org._02_strategy.ex01;

/**
 * PaypalPayment - PayPal 결제 전략
 *
 * PaymentStrategy 인터페이스를 구현하여 PayPal 결제 로직을 제공하는
 * 구체적인 전략(Concrete Strategy) 클래스입니다.
 *
 * 신용카드 결제와는 완전히 다른 결제 프로세스를 가지지만,
 * 동일한 인터페이스를 구현함으로써 클라이언트는 결제 방식의 차이를
 * 신경 쓰지 않고 사용할 수 있습니다.
 */
class PaypalPayment implements PaymentStrategy {
    private String email;
    private String password;

    /**
     * PayPal 결제 전략 생성자
     *
     * @param email PayPal 계정 이메일
     * @param password PayPal 계정 비밀번호
     */
    public PaypalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * PayPal을 통한 결제 수행
     *
     * PayPal 특유의 결제 프로세스를 구현합니다.
     * OAuth 인증, API 토큰 획득, PayPal API 호출 등의
     * PayPal 고유 로직이 포함됩니다.
     *
     * @param amount 결제할 금액
     */
    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount +
                " using PayPal account: " + email);

        // 실제 구현에서는 다음과 같은 로직들이 포함될 수 있습니다:
        // - PayPal OAuth 인증
        // - 계정 잔액 확인
        // - PayPal REST API 호출
        // - 환율 변환 (필요시)
        // - 거래 수수료 계산
        // - 결제 상태 추적
    }
}