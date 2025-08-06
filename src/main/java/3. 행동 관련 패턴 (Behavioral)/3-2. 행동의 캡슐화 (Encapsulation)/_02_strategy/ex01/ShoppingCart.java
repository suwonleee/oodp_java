package org._02_strategy.ex01;

/**
 * ShoppingCart - 쇼핑카트 컨텍스트 클래스
 *
 * Strategy 패턴의 Context 역할을 담당하는 클래스입니다.
 * 결제 전략(PaymentStrategy)을 사용하여 결제를 처리하며,
 * 런타임에 결제 방식을 동적으로 변경할 수 있습니다.
 *
 * 이 클래스는 구체적인 결제 방법을 직접 알지 못하고,
 * PaymentStrategy 인터페이스를 통해서만 결제 기능을 사용합니다.
 * 이를 통해 결제 방식의 추가나 변경이 ShoppingCart에 영향을 주지 않습니다.
 */
class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    /**
     * 결제 전략을 설정하는 메서드
     *
     * Strategy 패턴의 핵심으로, 런타임에 사용할 결제 방식을
     * 동적으로 변경할 수 있게 해줍니다.
     *
     * @param paymentStrategy 사용할 결제 전략 객체
     */
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    /**
     * 결제를 수행하는 메서드
     *
     * 설정된 결제 전략을 사용하여 실제 결제를 처리합니다.
     * 어떤 결제 방식이 사용되는지는 전략 객체에 위임하고,
     * ShoppingCart는 결제 방식의 세부사항을 알 필요가 없습니다.
     *
     * @param amount 결제할 금액
     */
    public void processPayment(int amount) {
        if (paymentStrategy == null) {
            System.out.println("결제 방법이 설정되지 않았습니다.");
            return;
        }

        System.out.println("Processing payment...");
        // 실제 결제 처리는 설정된 전략에 위임
        paymentStrategy.pay(amount);
        System.out.println("Payment completed successfully!\n");

        /*
         * Strategy 패턴의 장점을 보여주는 부분:
         * 1. ShoppingCart는 결제 방식의 구체적인 구현을 몰라도 됨
         * 2. 새로운 결제 방식 추가 시 이 코드는 변경되지 않음
         * 3. 런타임에 결제 방식을 자유롭게 변경 가능
         * 4. 각 결제 방식은 독립적으로 테스트 가능
         */
    }
}