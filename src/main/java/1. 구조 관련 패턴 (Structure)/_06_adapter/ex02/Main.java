package org._06_adapter.ex02;

/**
 * Main 클래스 - Adapter 패턴 사용 예제 (다중 디스플레이 시스템)
 *
 * 다양한 디스플레이 연결 방식(VGA, HDMI, USB)을 하나의 통합된
 * 인터페이스로 관리하는 Adapter 패턴의 실용적 활용을 보여줍니다.
 *
 * 이 예제는 실제 컴퓨터 시스템에서 볼 수 있는 다양한 디스플레이
 * 연결 방식의 통합 관리 시나리오를 다룹니다.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Adapter 패턴을 활용한 다중 디스플레이 시스템 ===\n");

        System.out.println(" 1. 개별 디스플레이 시스템 직접 사용 ");
        demonstrateDirectUsage();

        System.out.println(" 2. 어댑터를 통한 통합 인터페이스 사용 ");
        demonstrateUnifiedInterface();

        System.out.println(" 3. 다중 디스플레이 관리 시스템 ");
        demonstrateMultiDisplayManagement();

        printPatternBenefits();
    }

    /**
     * 각 디스플레이 시스템의 직접 사용 방식 시연
     *
     * 어댑터 없이 각 디스플레이 시스템을 직접 사용할 때의
     * 복잡성과 일관성 부족을 보여줍니다.
     */
    private static void demonstrateDirectUsage() {
        // VGA 시스템 직접 사용
        VGA vgaSystem = new VGA();
        vgaSystem.connectWithVgaCable(true);
        System.out.println();

        // HDMI 시스템 직접 사용
        HDMI hdmiSystem = new HDMI();
        hdmiSystem.connectHDMIPort("4K");
        System.out.println();

        // USB 시스템 직접 사용
        USB usbSystem = new USB();
        usbSystem.connectUSBDevice("USB-C", true);
        System.out.println();

        /*
         * 직접 사용의 문제점:
         * 1. 각 시스템마다 다른 메서드명과 파라미터
         * 2. 일관성 없는 API 사용법
         * 3. 클라이언트가 모든 시스템의 세부사항을 알아야 함
         * 4. 새로운 연결 방식 추가 시 클라이언트 코드 수정 필요
         */
    }

    /**
     * 어댑터를 통한 통합 인터페이스 사용 시연
     *
     * 각 디스플레이 시스템을 어댑터로 감싸서 일관된
     * 인터페이스로 사용하는 방법을 보여줍니다.
     */
    private static void demonstrateUnifiedInterface() {
        // 각 시스템을 어댑터로 래핑
        DisplayAdapter vgaAdapter = new VGAAdapter(new VGA(), false);
        DisplayAdapter hdmiAdapter = new HDMIAdapter(new HDMI(), "1080p");
        DisplayAdapter usbAdapter = new USBAdapter(new USB(), "USB-3.0", false);

        // 통일된 방식으로 사용
        System.out.println("--- VGA 디스플레이 (어댑터 통해) ---");
        vgaAdapter.display();
        System.out.println();

        System.out.println("--- HDMI 디스플레이 (어댑터 통해) ---");
        hdmiAdapter.display();
        System.out.println();

        System.out.println("--- USB 디스플레이 (어댑터 통해) ---");
        usbAdapter.display();
        System.out.println();

        /*
         * 통합 인터페이스의 장점:
         * 1. 모든 디스플레이 시스템이 동일한 display() 메서드 사용
         * 2. 클라이언트는 구체적인 연결 방식을 몰라도 됨
         * 3. 일관된 사용 패턴으로 코드 가독성 향상
         * 4. 새로운 연결 방식 추가 시 기존 코드 수정 불필요
         */
    }

    /**
     * 다중 디스플레이 관리 시스템 시연
     *
     * 여러 디스플레이를 동시에 관리하는 실용적인
     * 시나리오를 통해 Adapter 패턴의 실제 가치를 보여줍니다.
     */
    private static void demonstrateMultiDisplayManagement() {
        // 다양한 디스플레이 어댑터들을 배열로 관리
        DisplayAdapter[] displays = {
                new VGAAdapter(new VGA(), true),           // 고품질 VGA
                new HDMIAdapter(new HDMI(), "4K"),         // 4K HDMI
                new USBAdapter(new USB(), "USB-C", true),  // USB-C with power delivery
                new VGAAdapter(new VGA(), false),          // 표준 VGA
                new HDMIAdapter(new HDMI(), "1080p")       // 1080p HDMI
        };

        System.out.println("=== 다중 디스플레이 동시 활성화 ===");
        for (int i = 0; i < displays.length; i++) {
            System.out.printf("Display %d 활성화:\n", i + 1);
            displays[i].display();
            System.out.println("---");
        }

        // 선택적 디스플레이 활성화 시연
        System.out.println("=== 특정 디스플레이만 선택적 활성화 ===");
        activateSpecificDisplays(displays, new int[]{0, 2, 4}); // 1번, 3번, 5번 디스플레이

        /*
         * 다중 디스플레이 관리의 장점:
         * 1. 배열이나 컬렉션을 통한 일괄 관리
         * 2. 반복문을 통한 간단한 다중 처리
         * 3. 조건부 선택 및 필터링 용이
         * 4. 동적 디스플레이 추가/제거 가능
         */
    }

    /**
     * 특정 인덱스의 디스플레이들만 선택적으로 활성화
     *
     * @param displays 전체 디스플레이 배열
     * @param indices 활성화할 디스플레이 인덱스들
     */
    private static void activateSpecificDisplays(DisplayAdapter[] displays, int[] indices) {
        for (int index : indices) {
            if (index >= 0 && index < displays.length) {
                System.out.printf("선택된 Display %d 활성화:\n", index + 1);
                displays[index].display();
                System.out.println("---");
            }
        }
    }

    /**
     * Adapter 패턴의 실제 효과와 장점 출력
     */
    private static void printPatternBenefits() {
        System.out.println("=== Adapter 패턴의 실제 효과 ===");
        System.out.println("✅ 다양한 하드웨어 인터페이스의 통합 관리");
        System.out.println("✅ 일관된 API로 인한 코드 복잡성 감소");
        System.out.println("✅ 새로운 연결 방식 추가 시 기존 코드 수정 불필요");
        System.out.println("✅ 다중 디스플레이 관리의 단순화");
        System.out.println("✅ 각 디스플레이 시스템의 특화 기능 보존");

        System.out.println("\n=== 실제 적용 가능한 시나리오 ===");
        System.out.println("🖥️ 멀티모니터 설정: 다양한 연결 방식의 모니터들 통합");
        System.out.println("🎮 게이밍 시스템: 여러 디스플레이 출력 동시 관리");
        System.out.println("💼 프레젠테이션: 다양한 프로젝터와 디스플레이 지원");
        System.out.println("🏢 회의실 시스템: 여러 디스플레이 장치의 원터치 연결");
        System.out.println("📺 디지털 사이니지: 다종류 디스플레이 통합 제어");

        System.out.println("\n=== 확장 가능한 기능들 ===");
        System.out.println("🔧 해상도 자동 최적화: 각 디스플레이별 최적 해상도 설정");
        System.out.println("⚡ 전원 관리: 디스플레이별 전원 상태 통합 관리");
        System.out.println("🎯 프로파일 관리: 사용 환경별 디스플레이 설정 저장");
        System.out.println("📊 상태 모니터링: 모든 디스플레이의 연결 상태 추적");
        System.out.println("🔄 핫스왑 지원: 디스플레이 동적 연결/해제 처리");

        /*
         * Adapter 패턴의 비즈니스 가치:
         *
         * 1. 개발 효율성:
         *    - 통합된 API로 개발 시간 단축
         *    - 일관된 패턴으로 학습 비용 감소
         *
         * 2. 유지보수성:
         *    - 각 어댑터의 독립적 관리
         *    - 새로운 기술 도입 시 점진적 적용
         *
         * 3. 하드웨어 호환성:
         *    - 다양한 하드웨어 벤더 지원
         *    - 레거시 시스템과 최신 기술의 공존
         *
         * 4. 사용자 경험:
         *    - 복잡한 설정 과정의 단순화
         *    - 직관적이고 일관된 사용법
         */
    }
}