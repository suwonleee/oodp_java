package org._02_strategy.ex01;

/**
 * Main 클래스 - Strategy 패턴 사용 예제 (결제 시스템)
 *
 * ShoppingCart가 다양한 결제 전략을 런타임에 동적으로 선택하고
 * 사용하는 방법을 보여주는 클라이언트 코드입니다.
 *
 * 이 예제는 Strategy 패턴이 어떻게 조건문(if-else)을 제거하고
 * 알고리즘을 캡슐화하여 유연하고 확장 가능한 코드를 만드는지 보여줍니다.
 */
public class Main {
    public static void main(String[] args) {
        // Context 객체 생성
        ShoppingCart cart = new ShoppingCart();

        // 첫 번째 전략: 신용카드 결제
        System.out.println("=== 신용카드 결제 ===");
        PaymentStrategy creditCardStrategy =
                new CreditCardPayment("1234-5678-9012-3456", "홍길동");

        cart.setPaymentStrategy(creditCardStrategy);
        cart.processPayment(100);

        // 두 번째 전략: PayPal 결제
        System.out.println("=== PayPal 결제 ===");
        PaymentStrategy paypalStrategy =
                new PaypalPayment("hong@example.com", "password123");

        cart.setPaymentStrategy(paypalStrategy);
        cart.processPayment(250);

        // 런타임에 전략 변경 가능성 시연
        System.out.println("=== 결제 방식 변경 ===");
        cart.setPaymentStrategy(creditCardStrategy);
        cart.processPayment(75);

        /*
         * Strategy 패턴의 실제 사용 효과:
         *
         * 1. 유연성: 런타임에 결제 방식을 자유롭게 변경
         * 2. 확장성: 새로운 결제 방식 추가 시 기존 코드 수정 불필요
         *    (예: BankTransferPayment, CryptoCurrencyPayment 등)
         * 3. 재사용성: 각 결제 전략을 다른 곳에서도 재사용 가능
         * 4. 테스트 용이성: 각 전략을 독립적으로 테스트 가능
         * 5. 단일 책임: 각 결제 방식이 자신만의 책임을 가짐
         *
         * 만약 Strategy 패턴을 사용하지 않았다면:
         * - processPayment 메서드에 복잡한 if-else 또는 switch 문
         * - 새로운 결제 방식 추가 시마다 기존 코드 수정 필요
         * - 결제 로직이 한 곳에 집중되어 유지보수 어려움
         */
    }
}