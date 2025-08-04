package org._08_factory_method.ex03;

class BankTransferPaymentFactory extends PaymentFactory {
    @Override
    Payment createPayment(FinancialInfo info) {
        return new BankTransferPayment(info.bankAccountNumber);
    }
}