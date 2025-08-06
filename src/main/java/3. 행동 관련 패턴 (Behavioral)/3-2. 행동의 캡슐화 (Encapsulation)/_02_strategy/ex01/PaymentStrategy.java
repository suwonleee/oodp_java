package org._02_strategy.ex01;

/**
 * PaymentStrategy - 결제 전략 인터페이스
 *
 * Strategy 패턴의 핵심 인터페이스로, 모든 결제 방법이 구현해야 하는
 * 공통 계약을 정의합니다. 이 인터페이스를 통해 다양한 결제 방식을
 * 동일한 방법으로 사용할 수 있게 됩니다.
 *
 * Strategy 패턴에서 이 인터페이스는 알고리즘 패밀리의 공통 인터페이스 역할을 하며,
 * 클라이언트(Context)는 구체적인 구현체를 몰라도 이 인터페이스를 통해
 * 결제 기능을 사용할 수 있습니다.
 */
interface PaymentStrategy {
    /**
     * 결제를 수행하는 메서드
     *
     * 각 결제 전략(CreditCard, Paypal 등)이 자신만의 방식으로
     * 이 메서드를 구현하여 결제를 처리합니다.
     *
     * @param amount 결제할 금액
     */
    void pay(int amount);
}