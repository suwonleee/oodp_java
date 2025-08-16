package org._09_proxy.ex02;

/**
 * Subject 인터페이스
 * - 은행 계좌의 기본 기능을 정의
 * - Proxy와 RealSubject가 공통으로 구현
 */
public interface BankAccount {
    void deposit(double amount);    // 입금
    void withdraw(double amount);   // 출금
    double getBalance();           // 잔액 조회
}