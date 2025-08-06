package org._08_factory_method.ex03;

class CreditCardPaymentFactory extends PaymentFactory {
    @Override
    Payment createPayment(FinancialInfo info) {
        return new CreditCardPayment(info.creditCardNumber);
    }
}