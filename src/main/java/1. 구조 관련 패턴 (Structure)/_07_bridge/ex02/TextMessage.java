package org._07_bridge.ex02;

/**
 * TextMessage - 일반 텍스트 메시지 구현 (RefinedAbstraction)
 *
 * Bridge 패턴에서 RefinedAbstraction 역할을 담당하는 클래스입니다.
 * Message의 추상적 정의를 구체화하여 일반 텍스트 메시지 기능을 구현합니다.
 *
 * Bridge 패턴의 확장성:
 * - Message(Abstraction) 계층의 확장: TextMessage, EncryptedMessage 등
 * - MessageSender(Implementor) 계층의 확장: SMSSender, EmailSender 등
 * - 두 계층이 독립적으로 확장 가능 (N × M 조합을 상속 없이 지원)
 *
 * 일반 텍스트 메시지의 특징:
 * - 내용을 가공하지 않고 그대로 전송
 * - 간단하고 직접적인 메시지 전송
 * - 빠른 처리 속도
 */
class TextMessage extends Message {

    /**
     * 텍스트 메시지 생성자
     *
     * @param sender 사용할 메시지 전송자
     *
     * 부모 클래스인 Message의 생성자를 호출하여
     * MessageSender 객체를 초기화합니다.
     */
    public TextMessage(MessageSender sender) {
        super(sender);
    }

    /**
     * 일반 텍스트 메시지 전송 구현
     *
     * Bridge 패턴의 핵심 동작:
     * 1. 고수준 로직: 메시지 검증 및 전처리 (Abstraction의 책임)
     * 2. 저수준 구현: 실제 전송 동작 (Implementor에 위임)
     *
     * 텍스트 메시지는 내용을 가공하지 않고 그대로 전송하는 것이 특징입니다.
     */
    @Override
    public boolean send(String recipient, String content) {
        System.out.println("TextMessage: Preparing to send plain text message");

        // 메시지 유효성 검증 (부모 클래스의 공통 검증 사용)
        if (!validateMessage(recipient, content)) {
            return false;
        }

        // 텍스트 메시지 특화 처리
        System.out.println("TextMessage: Content type - Plain Text");
        System.out.println("TextMessage: No encryption or special formatting applied");

        // 실제 전송은 MessageSender에 위임 (Bridge 패턴의 핵심)
        boolean success = sender.sendMessage(recipient, content);

        if (success) {
            System.out.println("TextMessage: Plain text message delivered successfully");
        } else {
            System.out.println("TextMessage: Failed to send plain text message");
        }

        return success;

        /*
         * Bridge 패턴의 이점을 보여주는 부분:
         *
         * 1. 추상화와 구현의 분리:
         *    - TextMessage는 "텍스트 메시지 처리" 로직만 담당
         *    - SMS/Email 전송 방식은 각각의 MessageSender가 담당
         *
         * 2. 독립적 확장:
         *    - 새로운 메시지 타입 추가 시 MessageSender 코드 수정 불필요
         *    - 새로운 전송 방식 추가 시 Message 코드 수정 불필요
         *
         * 3. 런타임 유연성:
         *    - 동일한 TextMessage로 SMS ↔ Email 전환 가능
         *    - setSender()로 전송 방식 동적 변경
         */
    }

    /**
     * 텍스트 메시지 전용 통계 정보
     *
     * RefinedAbstraction이 제공하는 추가 기능의 예시입니다.
     * 일반 텍스트 메시지는 간단한 통계 정보만 제공합니다.
     *
     * @param content 분석할 메시지 내용
     */
    public void displayTextStatistics(String content) {
        if (content == null) {
            System.out.println("TextMessage Stats: No content to analyze");
            return;
        }

        System.out.println("TextMessage Statistics:");
        System.out.println("  Character count: " + content.length());
        System.out.println("  Word count: " + countWords(content));
        System.out.println("  Line count: " + (content.split("\n").length));
        System.out.println("  Estimated " + getCurrentSenderType() + " cost: " +
                estimateTransmissionCost(content));
    }

    /**
     * 단어 수 계산 헬퍼 메서드
     */
    private int countWords(String content) {
        if (content.trim().isEmpty()) return 0;
        return content.trim().split("\\s+").length;
    }

    /**
     * 전송 비용 추정 (전송 방식별로 다른 비용 계산)
     */
    private String estimateTransmissionCost(String content) {
        String senderType = getCurrentSenderType();

        switch (senderType) {
            case "SMS":
                // SMS는 160자당 1건 계산
                int smsCount = (int) Math.ceil((double) content.length() / 160);
                return smsCount + " SMS units";
            case "Email":
                // Email은 고정 비용
                return "1 Email credit";
            default:
                return "Unknown";
        }
    }
}