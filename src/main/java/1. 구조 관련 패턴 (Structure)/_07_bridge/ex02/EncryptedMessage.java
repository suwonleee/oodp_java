package org._07_bridge.ex02;

/**
 * EncryptedMessage - 암호화 메시지 구현 (RefinedAbstraction)
 *
 * Bridge 패턴에서 또 다른 RefinedAbstraction 역할을 담당하는 클래스입니다.
 * TextMessage와 같은 Message를 상속하지만, 암호화 기능을 추가로 제공합니다.
 *
 * Bridge 패턴의 확장성 시연:
 * - 동일한 MessageSender 구현체들을 재사용
 * - TextMessage와는 다른 처리 방식과 추가 기능 제공
 * - 전송 방식 변경 없이 메시지 처리 기능만 확장
 *
 * 암호화 메시지의 특징:
 * - 내용을 암호화하여 보안성 강화
 * - 추가적인 처리 시간 필요
 * - 보안이 중요한 메시지에 적합
 */
class EncryptedMessage extends Message {
    private String encryptionKey;

    /**
     * 암호화 메시지 생성자
     *
     * @param sender 사용할 메시지 전송자
     * @param encryptionKey 암호화에 사용할 키
     */
    public EncryptedMessage(MessageSender sender, String encryptionKey) {
        super(sender);
        this.encryptionKey = encryptionKey != null ? encryptionKey : "default-key";
    }

    /**
     * 암호화 메시지 전송 구현
     *
     * Bridge 패턴의 유연성을 보여줍니다:
     * 동일한 MessageSender 인터페이스를 사용하되,
     * 상위 처리 로직(암호화)은 메시지 타입별로 다르게 구현 가능합니다.
     */
    @Override
    public boolean send(String recipient, String content) {
        System.out.println("EncryptedMessage: Preparing to send encrypted message");

        // 메시지 유효성 검증 (부모 클래스의 공통 검증 사용)
        if (!validateMessage(recipient, content)) {
            return false;
        }

        // 암호화 메시지 특화 처리
        System.out.println("EncryptedMessage: Applying security measures...");
        String encryptedContent = encryptContent(content);

        if (encryptedContent == null) {
            System.out.println("EncryptedMessage: Encryption failed");
            return false;
        }

        System.out.println("EncryptedMessage: Content encrypted successfully");
        System.out.println("EncryptedMessage: Original length: " + content.length() +
                ", Encrypted length: " + encryptedContent.length());

        // 실제 전송은 MessageSender에 위임 (Bridge 패턴의 핵심)
        // 전송자는 암호화된 내용인지 모르고 단순히 전송만 수행
        boolean success = sender.sendMessage(recipient, encryptedContent);

        if (success) {
            System.out.println("EncryptedMessage: Encrypted message delivered successfully");
        } else {
            System.out.println("EncryptedMessage: Failed to send encrypted message");
        }

        return success;

        /*
         * Bridge 패턴의 확장성을 보여주는 부분:
         *
         * 1. 기능의 독립적 진화:
         *    - 암호화 메시지의 새 기능이 MessageSender 구현에 영향 없음
         *    - MessageSender의 새로운 구현체도 기존 메시지 타입과 호환
         *
         * 2. 조합의 유연성:
         *    - EncryptedMessage + SMSSender: 암호화된 SMS
         *    - EncryptedMessage + EmailSender: 암호화된 이메일
         *    - 상속 기반이라면 각 조합마다 별도 클래스 필요
         *
         * 3. 계층별 특화:
         *    - Message 계층: 암호화, 압축, 형식화 등 내용 처리
         *    - Sender 계층: SMS, Email, Push 등 전송 방식
         */
    }

    /**
     * 내용 암호화 처리
     *
     * 실제 프로덕션에서는 AES, RSA 등의 강력한 암호화 알고리즘을 사용하지만,
     * 예시에서는 간단한 시저 암호화를 사용합니다.
     *
     * @param content 암호화할 원본 내용
     * @return 암호화된 내용
     */
    private String encryptContent(String content) {
        try {
            System.out.println("EncryptedMessage: Encrypting with key: " +
                    maskKey(encryptionKey));

            // 간단한 시저 암호화 구현 (실제로는 강력한 암호화 사용)
            StringBuilder encrypted = new StringBuilder();
            int shift = Math.abs(encryptionKey.hashCode()) % 26;

            for (char c : content.toCharArray()) {
                if (Character.isLetter(c)) {
                    char base = Character.isUpperCase(c) ? 'A' : 'a';
                    c = (char) (((c - base + shift) % 26) + base);
                }
                encrypted.append(c);
            }

            // 암호화된 내용에 메타데이터 추가
            return "[ENCRYPTED:" + shift + "]" + encrypted.toString();

        } catch (Exception e) {
            System.out.println("EncryptedMessage: Encryption error - " + e.getMessage());
            return null;
        }
    }

    /**
     * 암호화 키 마스킹 (보안을 위해 일부만 표시)
     */
    private String maskKey(String key) {
        if (key.length() <= 4) return "****";
        return key.substring(0, 2) + "****" + key.substring(key.length() - 2);
    }

    /**
     * 암호화 설정 변경
     *
     * RefinedAbstraction이 제공하는 추가 기능으로,
     * 런타임에 암호화 설정을 변경할 수 있습니다.
     *
     * @param newKey 새로운 암호화 키
     */
    public void changeEncryptionKey(String newKey) {
        if (newKey != null && !newKey.trim().isEmpty()) {
            System.out.println("EncryptedMessage: Encryption key updated");
            this.encryptionKey = newKey;
        } else {
            System.out.println("EncryptedMessage: Invalid encryption key");
        }
    }

    /**
     * 보안 정보 표시
     *
     * 암호화 메시지만의 고유 정보를 제공합니다.
     */
    public void displaySecurityInfo() {
        System.out.println("EncryptedMessage Security Info:");
        System.out.println("  Encryption: Enabled (Caesar Cipher)");
        System.out.println("  Key: " + maskKey(encryptionKey));
        System.out.println("  Security Level: Basic (Demo purposes)");
        System.out.println("  Recommended for: Non-critical secure communications");

        /*
         * 실제 프로덕션 환경에서의 보안 고려사항:
         * 1. 강력한 암호화 알고리즘 사용 (AES-256, RSA)
         * 2. 키 관리 시스템 (Key Management Service)
         * 3. 디지털 서명을 통한 무결성 보장
         * 4. 종단간 암호화 (End-to-End Encryption)
         * 5. 키 순환 (Key Rotation) 정책
         */
    }
}