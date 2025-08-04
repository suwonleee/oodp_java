package org._08_factory_method.ex03;

class PayPalPaymentFactory extends PaymentFactory {
    @Override
    Payment createPayment(FinancialInfo info) {
        return new PayPalPayment(info.payPalEmail);
    }
}