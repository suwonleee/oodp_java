package org._06_adapter.ex01;

/**
 * ModernMessageSender - 현대적인 메시지 전송 인터페이스 (Target)
 *
 * Adapter 패턴에서 Target 역할을 담당하는 인터페이스입니다.
 * 클라이언트가 사용하고자 하는 현대적이고 직관적인 메시지 전송
 * 인터페이스를 정의합니다.
 *
 * 현대적 인터페이스의 특징:
 * - 명시적이고 의미있는 파라미터명
 * - void 반환형 (예외를 통한 에러 처리)
 * - 직관적이고 서술적인 메서드명
 * - 깔끔하고 간단한 API 설계
 *
 * 이 인터페이스는 새로운 시스템에서 표준으로 사용하고자 하는
 * 메시지 전송 방식을 나타냅니다.
 */
interface ModernMessageSender {
    /**
     * 현대적인 메시지 전송 메서드
     *
     * 현대적 API 설계 원칙을 따르는 메서드:
     * - 각 파라미터가 명확한 의미를 가짐
     * - 메서드명이 의도를 명확히 표현 (sendMessage)
     * - void 반환형으로 성공/실패는 예외로 처리
     * - 간단하고 직관적인 인터페이스
     *
     * @param message 전송할 메시지 내용 (명확한 파라미터명)
     * @param recipient 수신자 정보 (명확한 파라미터명)
     * @throws RuntimeException 전송 실패 시 예외 발생 (현대적 에러 처리)
     */
    void sendMessage(String message, String recipient);
}