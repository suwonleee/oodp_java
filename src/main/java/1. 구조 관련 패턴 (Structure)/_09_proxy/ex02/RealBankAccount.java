package org._09_proxy.ex02;

/**
 * RealSubject 클래스
 * - 실제 은행 계좌 기능을 구현
 * - 모든 금융 거래 로직을 담당
 */
public class RealBankAccount implements BankAccount {
    private double balance;         // 계좌 잔액
    private String accountNumber;   // 계좌 번호

    public RealBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        System.out.println("실제 은행 계좌 " + accountNumber + " 개설됨 (초기 잔액: " + initialBalance + "원)");
    }

    /**
     * 입금 기능 구현
     */
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + "원 입금 완료. 현재 잔액: " + balance + "원");
        } else {
            System.out.println("입금액은 0원보다 커야 합니다.");
        }
    }

    /**
     * 출금 기능 구현
     */
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(amount + "원 출금 완료. 현재 잔액: " + balance + "원");
        } else if (amount > balance) {
            System.out.println("잔액이 부족합니다. 현재 잔액: " + balance + "원");
        } else {
            System.out.println("출금액은 0원보다 커야 합니다.");
        }
    }

    /**
     * 잔액 조회 기능 구현
     */
    @Override
    public double getBalance() {
        System.out.println("잔액 조회: " + balance + "원");
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}