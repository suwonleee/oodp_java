package org._07_bridge.ex02;

/**
 * SMSSender - SMS 전송 구현체 (ConcreteImplementor)
 *
 * Bridge 패턴에서 ConcreteImplementor 역할을 담당하는 클래스입니다.
 * MessageSender 인터페이스를 SMS 전송 방식에 맞게 구체적으로 구현합니다.
 *
 * SMS 전송의 특징:
 * - 짧은 텍스트 메시지 (일반적으로 160자 제한)
 * - 전화번호를 수신자로 사용
 * - 즉시 전송, 높은 도달률
 * - 네트워크 요금 발생
 */
class SMSSender implements MessageSender {

    /**
     * SMS 메시지 전송 구현
     *
     * SMS 특화된 전송 로직을 구현합니다.
     * Bridge 패턴에 의해 Message 계층은 이 구현 세부사항을 알 필요가 없습니다.
     *
     * @param recipient 수신자 전화번호
     * @param content 전송할 SMS 내용
     * @return 전송 성공 여부
     */
    @Override
    public boolean sendMessage(String recipient, String content) {
        // SMS 전송 전 유효성 검증
        if (!isValidPhoneNumber(recipient)) {
            System.out.println("SMS Error: Invalid phone number format");
            return false;
        }

        if (content.length() > 160) {
            System.out.println("SMS Warning: Message truncated to 160 characters");
            content = content.substring(0, 160);
        }

        // SMS 전송 시뮬레이션
        System.out.println("SMS Sender: Connecting to SMS gateway...");
        simulateNetworkDelay(100);

        System.out.println("SMS Sender: Sending SMS");
        System.out.println("  To: " + recipient);
        System.out.println("  Content: " + content);
        System.out.println("  Length: " + content.length() + " characters");

        // SMS 전송 완료
        System.out.println("SMS Sender: Message delivered successfully");

        /*
         * SMS 전송의 실제 구현에서 고려사항:
         * 1. 통신사별 게이트웨이 연동
         * 2. 국가별 전화번호 형식 검증
         * 3. 메시지 길이 제한 및 분할 전송
         * 4. 전송 상태 추적 (전송 중, 전송 완료, 실패)
         * 5. 재전송 로직 및 실패 처리
         */

        return true;
    }

    /**
     * 전송 방식 정보 반환
     */
    @Override
    public String getSenderType() {
        return "SMS";
    }

    /**
     * 전화번호 유효성 검증
     *
     * SMS 전송에 특화된 유효성 검증 로직입니다.
     *
     * @param phoneNumber 검증할 전화번호
     * @return 유효한 전화번호 형식이면 true
     */
    private boolean isValidPhoneNumber(String phoneNumber) {
        // 간단한 전화번호 형식 검증 (실제로는 더 복잡한 검증 필요)
        return phoneNumber != null &&
                phoneNumber.matches("^[+]?[0-9-\\s]{10,}$");
    }

    /**
     * 네트워크 지연 시뮬레이션
     *
     * @param milliseconds 지연 시간
     */
    private void simulateNetworkDelay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("SMS: Network interrupted");
            Thread.currentThread().interrupt();
        }
    }
}