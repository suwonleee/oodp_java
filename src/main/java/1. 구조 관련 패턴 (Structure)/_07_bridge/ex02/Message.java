package org._07_bridge.ex02;

/**
 * Message - 메시지의 추상적 개념 (Abstraction)
 *
 * Bridge 패턴에서 Abstraction 역할을 담당하는 추상 클래스입니다.
 * 메시지의 기본적인 구조와 공통 기능을 정의하며,
 * 실제 전송은 MessageSender 객체에 위임합니다.
 *
 * Bridge 패턴의 핵심 구현:
 * - MessageSender를 상속하지 않고 컴포지션으로 참조
 * - 고수준 메시지 처리 로직을 제공하면서 저수준 전송은 MessageSender에 위임
 * - 메시지 타입(Text/Encrypted)과 전송 방식(SMS/Email)을 독립적으로 확장 가능
 *
 * 이 클래스는 "메시지"라는 추상적 개념을 나타내며,
 * 구체적인 메시지 타입들의 공통 기능을 담당합니다.
 */
abstract class Message {
    /**
     * Bridge 패턴의 핵심: Implementor에 대한 참조
     *
     * 이 참조를 통해 실제 메시지 전송 기능을 위임합니다.
     * 상속이 아닌 컴포지션을 사용하여 느슨한 결합을 달성합니다.
     */
    protected MessageSender sender;

    /**
     * 메시지 생성자
     *
     * @param sender 사용할 메시지 전송자 객체
     *
     * Bridge 패턴의 특징:
     * - 런타임에 전송 방식을 결정
     * - 동일한 메시지 타입으로 다양한 전송 방식 사용 가능
     * - 전송 방식 교체 시 메시지 코드 수정 불필요
     */
    public Message(MessageSender sender) {
        this.sender = sender;
    }

    /**
     * 메시지 전송 - 추상 메서드
     *
     * 각 메시지 타입(Text/Encrypted)이 자신만의 전송 로직을 구현합니다.
     * 실제 전송 수행은 sender 객체에 위임하되,
     * 메시지 가공이나 전처리는 각 메시지 타입별로 다를 수 있습니다.
     *
     * @param recipient 수신자 정보
     * @param content 메시지 내용
     * @return 전송 성공 여부
     */
    public abstract boolean send(String recipient, String content);

    /**
     * 전송자 교체 기능
     *
     * Bridge 패턴의 유연성을 보여주는 메서드로,
     * 런타임에 전송 방식을 변경할 수 있습니다.
     *
     * @param sender 새로운 전송자 객체
     */
    public void setSender(MessageSender sender) {
        this.sender = sender;
        System.out.println("Message: Sender changed to " + sender.getSenderType());
    }

    /**
     * 현재 전송 방식 정보 반환
     *
     * @return 현재 설정된 전송 방식
     */
    public String getCurrentSenderType() {
        return sender.getSenderType();
    }

    /**
     * 메시지 정보 표시 - 공통 기능
     *
     * 모든 메시지 타입이 공통으로 사용하는 정보 표시 기능입니다.
     * 하위 클래스에서 필요시 오버라이드할 수 있습니다.
     */
    public void displayMessageInfo() {
        System.out.println("Message Info:");
        System.out.println("  Type: " + this.getClass().getSimpleName());
        System.out.println("  Sender: " + sender.getSenderType());
    }

    /**
     * 메시지 유효성 검증 - 보호된 메서드
     *
     * 모든 메시지 타입에서 사용할 수 있는 기본적인 검증 로직을 제공합니다.
     * 하위 클래스에서 추가적인 검증을 구현할 때 이를 기반으로 할 수 있습니다.
     *
     * @param recipient 수신자 정보
     * @param content 메시지 내용
     * @return 유효하면 true, 그렇지 않으면 false
     */
    protected boolean validateMessage(String recipient, String content) {
        if (recipient == null || recipient.trim().isEmpty()) {
            System.out.println("Message Error: Recipient is required");
            return false;
        }

        if (content == null || content.trim().isEmpty()) {
            System.out.println("Message Error: Content is required");
            return false;
        }

        return true;
    }
}