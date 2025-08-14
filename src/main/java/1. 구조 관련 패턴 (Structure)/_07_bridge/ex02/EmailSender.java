
package org._07_bridge.ex02;

/**
 * EmailSender - 이메일 전송 구현체 (ConcreteImplementor)
 *
 * Bridge 패턴에서 또 다른 ConcreteImplementor 역할을 담당하는 클래스입니다.
 * SMS와는 완전히 다른 방식이지만 동일한 MessageSender 인터페이스를 구현하여,
 * Message가 SMS와 Email을 동일한 방식으로 사용할 수 있게 합니다.
 *
 * 이메일 전송의 특징:
 * - 긴 텍스트와 첨부파일 지원
 * - 이메일 주소를 수신자로 사용
 * - HTML 포맷 지원
 * - 상대적으로 전송 시간이 길 수 있음
 */
class EmailSender implements MessageSender {

    /**
     * 이메일 메시지 전송 구현
     *
     * 이메일 특화된 전송 로직을 구현합니다.
     * SMS와는 다른 검증 로직과 전송 과정을 가지지만,
     * 동일한 인터페이스를 통해 Message가 투명하게 사용할 수 있습니다.
     *
     * @param recipient 수신자 이메일 주소
     * @param content 전송할 이메일 내용
     * @return 전송 성공 여부
     */
    @Override
    public boolean sendMessage(String recipient, String content) {
        // 이메일 전송 전 유효성 검증
        if (!isValidEmailAddress(recipient)) {
            System.out.println("Email Error: Invalid email address format");
            return false;
        }

        // 이메일 전송 시뮬레이션
        System.out.println("Email Sender: Establishing SMTP connection...");
        simulateConnectionSetup(200);

        System.out.println("Email Sender: Composing email message");
        String formattedContent = formatEmailContent(content);

        System.out.println("Email Sender: Sending email");
        System.out.println("  To: " + recipient);
        System.out.println("  Subject: Message from Application");
        System.out.println("  Content Preview: " + getContentPreview(formattedContent));
        System.out.println("  Content Length: " + content.length() + " characters");

        // SMTP 전송 과정 시뮬레이션
        simulateEmailDelivery();

        System.out.println("Email Sender: Email sent successfully");

        /*
         * 실제 이메일 전송 구현에서 고려사항:
         * 1. SMTP 서버 연결 및 인증
         * 2. 이메일 주소 형식 및 도메인 검증
         * 3. HTML/텍스트 이중 형식 지원
         * 4. 첨부파일 처리
         * 5. 스팸 필터 회피를 위한 헤더 설정
         * 6. 배치 전송 및 대기열 관리
         */

        return true;
    }

    /**
     * 전송 방식 정보 반환
     */
    @Override
    public String getSenderType() {
        return "Email";
    }

    /**
     * 이메일 주소 유효성 검증
     *
     * 이메일 전송에 특화된 유효성 검증 로직입니다.
     * SMS의 전화번호 검증과는 완전히 다른 로직을 사용합니다.
     *
     * @param emailAddress 검증할 이메일 주소
     * @return 유효한 이메일 주소 형식이면 true
     */
    private boolean isValidEmailAddress(String emailAddress) {
        // 간단한 이메일 형식 검증 (실제로는 더 정교한 정규식 필요)
        return emailAddress != null &&
                emailAddress.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    /**
     * 이메일 내용 포맷팅
     *
     * 이메일 특화된 내용 가공 로직입니다.
     * HTML 태그 추가, 줄바꿈 처리 등을 수행합니다.
     *
     * @param rawContent 원본 내용
     * @return 포맷팅된 이메일 내용
     */
    private String formatEmailContent(String rawContent) {
        // 이메일용 HTML 포맷팅 (간단한 예시)
        return "<html><body><p>" +
                rawContent.replace("\n", "</p><p>") +
                "</p></body></html>";
    }

    /**
     * 내용 미리보기 생성
     *
     * @param content 전체 내용
     * @return 미리보기 텍스트 (50자 제한)
     */
    private String getContentPreview(String content) {
        // HTML 태그 제거 후 미리보기 생성
        String plainText = content.replaceAll("<[^>]*>", "");
        return plainText.length() > 50 ?
                plainText.substring(0, 50) + "..." :
                plainText;
    }

    /**
     * SMTP 연결 설정 시뮬레이션
     *
     * @param milliseconds 연결 설정 시간
     */
    private void simulateConnectionSetup(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Email: Connection setup interrupted");
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 이메일 전송 과정 시뮬레이션
     */
    private void simulateEmailDelivery() {
        try {
            System.out.println("Email Sender: Queuing message...");
            Thread.sleep(50);
            System.out.println("Email Sender: Transmitting to SMTP server...");
            Thread.sleep(100);
            System.out.println("Email Sender: Server processing...");
            Thread.sleep(150);
        } catch (InterruptedException e) {
            System.out.println("Email: Delivery process interrupted");
            Thread.currentThread().interrupt();
        }
    }
}