package org._07_bridge.ex02;

/**
 * MessageSender - 메시지 전송 인터페이스 (Implementor)
 *
 * Bridge 패턴에서 Implementor 역할을 담당하는 인터페이스입니다.
 * SMS, Email 등 다양한 메시지 전송 방식이 구현해야 하는
 * 기본적인 전송 기능을 정의합니다.
 *
 * Bridge 패턴의 핵심:
 * - Message(Abstraction)는 이 인터페이스를 통해 실제 전송을 수행
 * - 구체적인 전송 방식(SMS/Email)에 대한 지식 없이 전송 가능
 * - 새로운 전송 방식 추가 시 Message 코드 수정 불필요
 *
 * 이 인터페이스는 메시지 전송에 필요한 원시적(primitive) 연산만 정의하며,
 * 메시지 가공이나 포맷팅은 Message 계층에서 담당합니다.
 */
interface MessageSender {
    /**
     * 메시지 전송 수행
     *
     * 각 구현체별로 전송 방식은 다르지만 동일한 시그니처를 제공합니다.
     *
     * @param recipient 수신자 정보 (전송 방식에 따라 전화번호, 이메일 주소 등)
     * @param content 전송할 메시지 내용
     * @return 전송 성공 여부
     */
    boolean sendMessage(String recipient, String content);

    /**
     * 전송 방식 정보 반환
     *
     * 디버깅이나 로깅 목적으로 현재 전송 방식을 식별할 수 있게 합니다.
     *
     * @return 전송 방식 이름 (예: "SMS", "Email")
     */
    String getSenderType();
}