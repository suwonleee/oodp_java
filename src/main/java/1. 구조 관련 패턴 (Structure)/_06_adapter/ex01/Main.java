package org._06_adapter.ex01;

/**
 * Main 클래스 - Adapter 패턴 사용 예제 (메시지 시스템)
 *
 * Adapter 패턴이 어떻게 레거시 시스템을 새로운 인터페이스에
 * 통합하는지 보여주는 클라이언트 코드입니다.
 *
 * 이 예제는 실제 기업 환경에서 자주 발생하는 시나리오인
 * "레거시 시스템과 새로운 시스템의 통합"을 다룹니다.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Adapter 패턴을 활용한 메시지 시스템 통합 ===\n");

        // 레거시 시스템 인스턴스 생성
        OldMessageSender legacySystem = new OldMessageSystem();

        System.out.println(" 1. 레거시 시스템 직접 사용 ");
        demonstrateLegacySystem(legacySystem);

        System.out.println(" 2. 어댑터를 통한 현대적 인터페이스 사용 ");
        demonstrateModernInterface(legacySystem);

        System.out.println(" 3. 에러 처리 변환 시연 ");
        demonstrateErrorHandling(legacySystem);

        printPatternBenefits();
    }

    /**
     * 레거시 시스템의 직접 사용 시연
     *
     * 어댑터 없이 레거시 시스템을 직접 사용할 때의
     * 불편함과 복잡성을 보여줍니다.
     *
     * @param legacySystem 레거시 메시지 시스템
     */
    private static void demonstrateLegacySystem(OldMessageSender legacySystem) {
        // 레거시 시스템의 복잡한 사용법
        String[] messageData = {"Hello World!", "user@example.com"};
        int result = legacySystem.send(messageData);

        // 복잡한 상태 코드 처리
        if (result == 1) {
            System.out.println("✅ Legacy system: Message sent successfully");
        } else {
            System.out.println("❌ Legacy system: Failed with code " + result);
        }
        System.out.println();

        /*
         * 레거시 시스템 직접 사용의 문제점:
         * 1. 배열 형태의 복잡한 파라미터 준비
         * 2. 정수형 상태 코드의 복잡한 해석
         * 3. 에러 처리의 일관성 부족
         * 4. 직관적이지 않은 API
         */
    }

    /**
     * 어댑터를 통한 현대적 인터페이스 사용 시연
     *
     * MessageAdapter를 사용하여 레거시 시스템을
     * 현대적인 방식으로 사용하는 방법을 보여줍니다.
     *
     * @param legacySystem 레거시 메시지 시스템
     */
    private static void demonstrateModernInterface(OldMessageSender legacySystem) {
        // 어댑터를 통한 레거시 시스템 래핑
        ModernMessageSender modernInterface = new MessageAdapter(legacySystem);

        try {
            // 현대적이고 직관적인 API 사용
            modernInterface.sendMessage("Hello from Modern Interface!", "modern@example.com");
            System.out.println("✅ Modern interface: Message sent successfully");
        } catch (RuntimeException e) {
            System.out.println("❌ Modern interface: " + e.getMessage());
        }
        System.out.println();

        /*
         * 어댑터 사용의 장점:
         * 1. 직관적이고 명확한 파라미터
         * 2. 현대적인 예외 기반 에러 처리
         * 3. 일관된 API 디자인
         * 4. 레거시 시스템의 복잡성 은닉
         */
    }

    /**
     * 에러 처리 변환 시연
     *
     * 어댑터가 레거시 시스템의 다양한 에러 코드를
     * 현대적인 예외로 어떻게 변환하는지 보여줍니다.
     *
     * @param legacySystem 레거시 메시지 시스템
     */
    private static void demonstrateErrorHandling(OldMessageSender legacySystem) {
        ModernMessageSender modernInterface = new MessageAdapter(legacySystem);

        // 다양한 에러 상황 테스트
        String[] testCases = {
                null,                    // null 메시지
                "",                      // 빈 메시지
                "Valid message"          // 유효한 메시지
        };

        String[] recipients = {
                "valid@example.com",
                null,                    // null 수신자
                ""                       // 빈 수신자
        };

        for (int i = 0; i < testCases.length; i++) {
            try {
                System.out.println("Testing case " + (i + 1) + ":");
                String message = testCases[i];
                String recipient = i < recipients.length ? recipients[i] : "test@example.com";

                modernInterface.sendMessage(message, recipient);
                System.out.println("✅ Success: Message sent");
            } catch (RuntimeException e) {
                System.out.println("❌ Error caught: " + e.getMessage());
            }
            System.out.println();
        }

        /*
         * 에러 처리 변환의 가치:
         * 1. 통일된 예외 처리 메커니즘
         * 2. 의미있는 에러 메시지 제공
         * 3. 현대적 에러 처리 패턴 적용
         * 4. 디버깅과 로깅의 용이성
         */
    }

    /**
     * Adapter 패턴의 실제 효과와 장점 출력
     */
    private static void printPatternBenefits() {
        System.out.println("=== Adapter 패턴의 실제 효과 ===");
        System.out.println("✅ 레거시 코드 수정 없이 새로운 인터페이스 사용 가능");
        System.out.println("✅ 복잡한 레거시 API를 현대적이고 직관적인 API로 변환");
        System.out.println("✅ 에러 처리 방식의 현대화 (상태코드 → 예외)");
        System.out.println("✅ 클라이언트 코드의 가독성과 유지보수성 향상");
        System.out.println("✅ 레거시 시스템의 안정성을 그대로 활용");

        System.out.println("\n=== 실제 적용 사례 ===");
        System.out.println("📧 이메일 시스템: SMTP → REST API 변환");
        System.out.println("💾 데이터베이스: JDBC → ORM 프레임워크 연동");
        System.out.println("🔐 인증 시스템: 레거시 로그인 → OAuth2 통합");
        System.out.println("📊 리포팅 시스템: 오래된 리포트 엔진 → 모던 대시보드");
        System.out.println("🌐 웹 서비스: SOAP → REST API 변환");

        /*
         * Adapter 패턴의 비즈니스 가치:
         *
         * 1. 개발 비용 절감:
         *    - 레거시 시스템 재작성 불필요
         *    - 점진적 마이그레이션 가능
         *
         * 2. 위험도 감소:
         *    - 검증된 레거시 로직 유지
         *    - 새로운 시스템과의 안전한 통합
         *
         * 3. 개발 생산성 향상:
         *    - 현대적 API로 개발 편의성 증대
         *    - 일관된 인터페이스로 학습 비용 감소
         *
         * 4. 시스템 진화 지원:
         *    - 레거시와 모던 시스템의 공존
         *    - 단계적 시스템 현대화
         */
    }
}