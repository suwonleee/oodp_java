package org._02_strategy.ex01;

/**
 * CreditCardPayment - 신용카드 결제 전략
 *
 * PaymentStrategy 인터페이스를 구현하여 신용카드 결제 로직을 제공하는
 * 구체적인 전략(Concrete Strategy) 클래스입니다.
 *
 * Strategy 패턴에서 이 클래스는 특정 알고리즘(신용카드 결제)을
 * 캡슐화하여 제공하며, 다른 결제 방식과 독립적으로 동작합니다.
 */
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;

    /**
     * 신용카드 결제 전략 생성자
     *
     * @param cardNumber 신용카드 번호
     * @param cardHolderName 카드 소유자 이름
     */
    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    /**
     * 신용카드를 통한 결제 수행
     *
     * 신용카드 특유의 결제 프로세스를 구현합니다.
     * 실제 환경에서는 카드 유효성 검증, 한도 확인,
     * 결제 게이트웨이 연동 등의 복잡한 로직이 포함될 수 있습니다.
     *
     * @param amount 결제할 금액
     */
    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount +
                " using Credit Card: " + cardNumber +
                " (Holder: " + cardHolderName + ")");

        // 실제 구현에서는 다음과 같은 로직들이 포함될 수 있습니다:
        // - 카드 번호 유효성 검증
        // - 카드 한도 확인
        // - 결제 게이트웨이 API 호출
        // - 거래 내역 저장
        // - 결제 승인/거절 처리
    }
}