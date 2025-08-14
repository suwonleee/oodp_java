package org._07_bridge.ex02;

/**
 * Main 클래스 - Bridge 패턴 사용 예제 (메시지 전송 시스템)
 *
 * Bridge 패턴이 어떻게 메시지 타입(Text/Encrypted)과 전송 방식(SMS/Email)을
 * 독립적으로 조합할 수 있게 하는지 보여주는 데모 코드입니다.
 *
 * 이 예제는 현대적인 메시징 시스템에서 볼 수 있는 다양한 메시지 형태와
 * 전송 채널의 조합을 Bridge 패턴으로 해결하는 방법을 보여줍니다.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bridge 패턴을 활용한 메시지 전송 시스템 ===\n");

        // 다양한 전송 방식들 생성
        MessageSender smsSender = new SMSSender();
        MessageSender emailSender = new EmailSender();

        System.out.println(" 1. 텍스트 메시지를 다양한 방식으로 전송 ");
        demonstrateTextMessageWithDifferentSenders(smsSender, emailSender);

        System.out.println(" 2. 암호화 메시지를 다양한 방식으로 전송 ");
        demonstrateEncryptedMessageWithDifferentSenders(smsSender, emailSender);

        System.out.println(" 3. 런타임 전송 방식 변경 시연 ");
        demonstrateRuntimeSenderSwitching(smsSender, emailSender);

        System.out.println(" 4. Bridge 패턴의 조합 유연성 시연 ");
        demonstrateFlexibleCombinations(smsSender, emailSender);

        printPatternBenefits();
    }

    /**
     * 텍스트 메시지를 다양한 전송 방식으로 보내는 시연
     *
     * Bridge 패턴의 기본 동작을 보여줍니다:
     * - TextMessage(Abstraction)가 다양한 MessageSender(Implementor)를 사용
     * - 동일한 메시지 처리 로직이 다른 전송 방식과 결합
     */
    private static void demonstrateTextMessageWithDifferentSenders(
            MessageSender smsSender, MessageSender emailSender) {

        String recipient1 = "010-1234-5678";
        String recipient2 = "user@example.com";
        String content = "안녕하세요! Bridge 패턴을 활용한 메시지입니다.";

        // 텍스트 메시지 + SMS 전송
        System.out.println("--- 텍스트 메시지 + SMS ---");
        Message textSMS = new TextMessage(smsSender);
        textSMS.displayMessageInfo();
        textSMS.send(recipient1, content);
        ((TextMessage) textSMS).displayTextStatistics(content);
        System.out.println();

        // 텍스트 메시지 + Email 전송
        System.out.println("--- 텍스트 메시지 + Email ---");
        Message textEmail = new TextMessage(emailSender);
        textEmail.displayMessageInfo();
        textEmail.send(recipient2, content);
        ((TextMessage) textEmail).displayTextStatistics(content);
        System.out.println();
    }

    /**
     * 암호화 메시지를 다양한 전송 방식으로 보내는 시연
     *
     * Bridge 패턴의 확장성을 보여줍니다:
     * - EncryptedMessage는 동일한 MessageSender들을 재사용
     * - 메시지 처리 방식만 다르고 전송 방식은 그대로 활용
     */
    private static void demonstrateEncryptedMessageWithDifferentSenders(
            MessageSender smsSender, MessageSender emailSender) {

        String recipient1 = "010-9876-5432";
        String recipient2 = "secure@example.com";
        String sensitiveContent = "기밀: 다음 회의는 내일 오후 3시입니다.";
        String encryptionKey = "mySecretKey123";

        // 암호화 메시지 + SMS 전송
        System.out.println("--- 암호화 메시지 + SMS ---");
        EncryptedMessage encryptedSMS = new EncryptedMessage(smsSender, encryptionKey);
        encryptedSMS.displayMessageInfo();
        encryptedSMS.displaySecurityInfo();
        encryptedSMS.send(recipient1, sensitiveContent);
        System.out.println();

        // 암호화 메시지 + Email 전송
        System.out.println("--- 암호화 메시지 + Email ---");
        EncryptedMessage encryptedEmail = new EncryptedMessage(emailSender, encryptionKey);
        encryptedEmail.displayMessageInfo();
        encryptedEmail.send(recipient2, sensitiveContent);
        System.out.println();
    }

    /**
     * 런타임 전송 방식 변경 시연
     *
     * Bridge 패턴의 유연성을 보여줍니다:
     * - 동일한 메시지 객체로 다른 전송 방식들을 순차적으로 사용
     * - 컴파일 타임이 아닌 런타임에 전송 방식 교체
     */
    private static void demonstrateRuntimeSenderSwitching(
            MessageSender smsSender, MessageSender emailSender) {

        // 하나의 메시지 객체로 여러 전송 방식 사용
        Message adaptiveMessage = new TextMessage(smsSender);
        String content = "전송 방식을 동적으로 변경하는 메시지입니다.";

        System.out.println("--- 초기 전송 방식: SMS ---");
        adaptiveMessage.send("010-1111-2222", content);

        // 런타임에 전송 방식 변경
        System.out.println("--- 전송 방식 변경: SMS → Email ---");
        adaptiveMessage.setSender(emailSender);
        adaptiveMessage.send("dynamic@example.com", content);

        // 다시 SMS로 변경
        System.out.println("--- 전송 방식 재변경: Email → SMS ---");
        adaptiveMessage.setSender(smsSender);
        adaptiveMessage.send("010-3333-4444", content);
        System.out.println();

        /*
         * 런타임 변경이 가능한 이유:
         * 1. 컴포지션 기반 설계: Message가 MessageSender를 참조
         * 2. 인터페이스 기반 통신: 구체 타입에 의존하지 않음
         * 3. 느슨한 결합: 메시지와 전송 방식이 독립적
         */
    }

    /**
     * Bridge 패턴의 조합 유연성 시연
     *
     * 다양한 메시지 타입과 전송 방식의 조합을 보여줍니다.
     */
    private static void demonstrateFlexibleCombinations(
            MessageSender smsSender, MessageSender emailSender) {

        String[] recipients = {"010-5555-6666", "combo@example.com"};
        String[] contents = {
                "일반 텍스트 메시지입니다.",
                "암호화가 필요한 보안 메시지입니다."
        };

        // 가능한 모든 조합 시연
        MessageSender[] senders = {smsSender, emailSender};
        String[] senderNames = {"SMS", "Email"};

        System.out.println("=== 모든 조합 매트릭스 ===");

        for (int i = 0; i < senders.length; i++) {
            System.out.printf("--- %s 전송 방식으로 다양한 메시지 타입 전송 ---\n", senderNames[i]);

            // 텍스트 메시지 조합
            Message textMsg = new TextMessage(senders[i]);
            System.out.println("조합 1: TextMessage + " + senderNames[i]);
            textMsg.send(recipients[i], contents[0]);

            // 암호화 메시지 조합
            Message encryptedMsg = new EncryptedMessage(senders[i], "key" + i);
            System.out.println("조합 2: EncryptedMessage + " + senderNames[i]);
            encryptedMsg.send(recipients[i], contents[1]);

            System.out.println();
        }

        /*
         * Bridge 패턴의 조합 매트릭스:
         *
         * 메시지 타입 (2개) × 전송 방식 (2개) = 4가지 조합
         * - TextMessage + SMS
         * - TextMessage + Email
         * - EncryptedMessage + SMS
         * - EncryptedMessage + Email
         *
         * 상속 기반이라면 4개의 서로 다른 클래스가 필요하지만,
         * Bridge 패턴으로는 2+2=4개의 클래스로 모든 조합 지원!
         */
    }

    /**
     * Bridge 패턴의 실제 효과와 장점 출력
     */
    private static void printPatternBenefits() {
        System.out.println("=== Bridge 패턴의 실제 효과 ===");
        System.out.println("✅ 메시지 타입과 전송 방식의 완전한 분리");
        System.out.println("✅ 런타임에 전송 방식 동적 변경 가능");
        System.out.println("✅ 상속 조합 폭발 문제 해결 (N×M → N+M)");
        System.out.println("✅ 각 차원의 독립적 확장 가능");
        System.out.println("✅ 새로운 조합 생성을 위한 코드 수정 불필요");

        System.out.println("\n=== 확장 시나리오 ===");
        System.out.println("새로운 메시지 타입 추가:");
        System.out.println("  - CompressedMessage (압축 메시지)");
        System.out.println("  - MultimediaMessage (멀티미디어 메시지)");
        System.out.println("  → 기존 전송 방식들과 자동으로 조합 가능");

        System.out.println("\n새로운 전송 방식 추가:");
        System.out.println("  - PushNotificationSender (푸시 알림)");
        System.out.println("  - SlackSender (슬랙 메신저)");
        System.out.println("  → 기존 메시지 타입들과 자동으로 조합 가능");

        System.out.println("\n=== 실제 적용 사례 ===");
        System.out.println(" 알림 시스템: 다양한 알림 타입 × 다양한 전송 채널");
        System.out.println("️ 결제 시스템: 다양한 결제 방식 × 다양한 결제 게이트웨이");
        System.out.println(" 로깅 시스템: 다양한 로그 레벨 × 다양한 출력 대상");
        System.out.println(" 데이터 처리: 다양한 데이터 형식 × 다양한 저장소");
        System.out.println("️ 미디어 처리: 다양한 미디어 타입 × 다양한 코덱");

        /*
         * Bridge 패턴의 비즈니스 가치:
         *
         * 1. 개발 효율성:
         *    - 조합 폭발 방지로 개발 및 유지보수 비용 절감
         *    - 재사용 가능한 컴포넌트로 개발 속도 향상
         *
         * 2. 시스템 유연성:
         *    - 비즈니스 요구사항 변경에 빠른 대응
         *    - 새로운 기술 스택 도입의 용이성
         *
         * 3. 확장성:
         *    - 서비스 성장에 따른 기능 확장 지원
         *    - 글로벌 서비스를 위한 지역별 적응
         *
         * 4. 품질 향상:
         *    - 각 컴포넌트의 독립적 테스트
         *    - 관심사 분리로 인한 코드 품질 개선
         */
    }
}