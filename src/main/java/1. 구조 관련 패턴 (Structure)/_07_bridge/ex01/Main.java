package org._07_bridge.ex01;

/**
 * Main 클래스 - Bridge 패턴 사용 예제 (리모컨과 장치 제어)
 *
 * Bridge 패턴이 어떻게 추상화(Remote)와 구현(Device)을 분리하여
 * 각각 독립적으로 확장 가능하게 만드는지 보여주는 데모 코드입니다.
 *
 * 이 예제는 실생활에서 흔히 볼 수 있는 "리모컨으로 다양한 장치 제어"
 * 시나리오를 통해 Bridge 패턴의 실용성을 보여줍니다.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bridge 패턴을 활용한 리모컨 시스템 ===\n");

        // 다양한 장치들 생성
        Device tv = new TV();
        Device radio = new Radio();

        System.out.println(" 1. 기본 리모컨으로 TV 제어 ");
        demonstrateBasicRemoteWithTV(tv);

        System.out.println(" 2. 기본 리모컨으로 Radio 제어 ");
        demonstrateBasicRemoteWithRadio(radio);

        System.out.println(" 3. 고급 리모컨으로 TV 제어 ");
        demonstrateAdvancedRemoteWithTV(tv);

        System.out.println(" 4. 런타임 장치 교체 시연 ");
        demonstrateRuntimeDeviceSwitching(tv, radio);

        System.out.println(" 5. Bridge 패턴의 확장성 시연 ");
        demonstrateExtensibility();

        printPatternBenefits();
    }

    /**
     * 기본 리모컨으로 TV를 제어하는 시연
     *
     * Bridge 패턴의 기본 동작을 보여줍니다:
     * - BasicRemote(Abstraction)가 TV(ConcreteImplementor)를 제어
     * - 리모컨은 TV의 구체적 구현을 모르고 Device 인터페이스만 사용
     */
    private static void demonstrateBasicRemoteWithTV(Device tv) {
        Remote basicRemote = new BasicRemote(tv);

        // 리모컨 조작 시뮬레이션
        basicRemote.power();        // TV 켜기
        basicRemote.volumeUp();     // 볼륨 올리기
        basicRemote.power();        // TV 끄기

        // BasicRemote만의 기능
        ((BasicRemote) basicRemote).displayStatus();
        System.out.println();
    }

    /**
     * 기본 리모컨으로 Radio를 제어하는 시연
     *
     * Bridge 패턴의 재사용성을 보여줍니다:
     * - 동일한 BasicRemote 코드로 완전히 다른 장치(Radio) 제어
     * - 장치별 고유 특성은 보존되면서도 공통 인터페이스로 제어
     */
    private static void demonstrateBasicRemoteWithRadio(Device radio) {
        Remote basicRemote = new BasicRemote(radio);

        // 동일한 리모컨 조작이지만 Radio만의 방식으로 동작
        basicRemote.power();        // Radio 켜기 (TV와 다른 방식)
        basicRemote.volumeUp();     // 볼륨 올리기 (Radio만의 오디오 피드백)
        basicRemote.volumeDown();   // 볼륨 내리기
        basicRemote.power();        // Radio 끄기
        System.out.println();
    }

    /**
     * 고급 리모컨으로 TV를 제어하는 시연
     *
     * Bridge 패턴의 확장성을 보여줍니다:
     * - AdvancedRemote는 동일한 Device 인터페이스를 사용하지만 다른 제어 로직
     * - TV 구현 변경 없이 리모컨 기능만 확장
     */
    private static void demonstrateAdvancedRemoteWithTV(Device tv) {
        AdvancedRemote advancedRemote = new AdvancedRemote(tv);

        // 고급 리모컨의 스마트 제어
        advancedRemote.power();           // 최적화된 전원 제어
        advancedRemote.scanChannels();    // 채널 스캔 (고급 기능)
        advancedRemote.mute();            // 음소거 (고급 기능)

        // 고급 상태 표시
        advancedRemote.displayDetailedStatus();
        System.out.println();
    }

    /**
     * 런타임 장치 교체 시연
     *
     * Bridge 패턴의 유연성을 보여줍니다:
     * - 동일한 리모컨 객체로 다른 장치들을 순차적으로 제어
     * - 컴파일 타임이 아닌 런타임에 구현 객체 교체
     */
    private static void demonstrateRuntimeDeviceSwitching(Device tv, Device radio) {
        // 하나의 리모컨으로 여러 장치 제어
        Remote universalRemote = new AdvancedRemote(tv);

        System.out.println("--- TV 제어 ---");
        universalRemote.power();

        // 런타임에 제어 대상 변경
        System.out.println("--- 장치 교체: TV → Radio ---");
        universalRemote.setDevice(radio);
        universalRemote.power();

        // 다시 TV로 교체
        System.out.println("--- 장치 교체: Radio → TV ---");
        universalRemote.setDevice(tv);
        universalRemote.power();
        System.out.println();

        /*
         * 이 시연이 보여주는 Bridge 패턴의 특징:
         * 1. 동적 구성: 런타임에 구현 객체 변경 가능
         * 2. 재사용성: 동일한 Abstraction으로 다양한 Implementor 제어
         * 3. 유연성: 새로운 조합을 자유롭게 만들 수 있음
         */
    }

    /**
     * Bridge 패턴의 확장성 시연
     *
     * 상속 기반 설계와 Bridge 패턴의 차이를 설명합니다.
     */
    private static void demonstrateExtensibility() {
        System.out.println("=== Bridge 패턴 vs 상속 기반 설계 ===");

        System.out.println("상속 기반이라면 필요한 클래스들:");
        System.out.println("  - BasicTVRemote, BasicRadioRemote");
        System.out.println("  - AdvancedTVRemote, AdvancedRadioRemote");
        System.out.println("  → 리모컨 타입 N개 × 장치 타입 M개 = N×M 개 클래스");

        System.out.println("\nBridge 패턴으로는:");
        System.out.println("  - 리모컨 타입: BasicRemote, AdvancedRemote (N개)");
        System.out.println("  - 장치 타입: TV, Radio (M개)");
        System.out.println("  → 총 N+M 개 클래스로 N×M 조합 모두 지원!");

        System.out.println("\n새로운 확장 시나리오:");
        System.out.println("  1. 새 장치(Projector) 추가:");
        System.out.println("     → Device 구현체 1개만 추가하면 모든 리모컨에서 사용 가능");
        System.out.println("  2. 새 리모컨(VoiceRemote) 추가:");
        System.out.println("     → Remote 상속 클래스 1개만 추가하면 모든 장치 제어 가능");
        System.out.println();
    }

    /**
     * Bridge 패턴의 실제 효과와 장점 출력
     */
    private static void printPatternBenefits() {
        System.out.println("=== Bridge 패턴의 실제 효과 ===");
        System.out.println("✅ 추상화와 구현의 완전한 분리");
        System.out.println("✅ 컴파일 타임이 아닌 런타임에 구현 연결");
        System.out.println("✅ 상속 폭발 문제 해결 (N×M → N+M)");
        System.out.println("✅ 각 계층의 독립적 확장 가능");
        System.out.println("✅ 기존 코드 수정 없이 새로운 조합 생성");

        System.out.println("\n=== 실제 적용 사례 ===");
        System.out.println(" GUI 프레임워크: Java Swing의 Look & Feel");
        System.out.println("️ 데이터베이스: JDBC와 각종 DB 드라이버");
        System.out.println(" 그래픽스: OpenGL/DirectX 추상화");
        System.out.println(" 네트워크: 소켓 API와 TCP/UDP 구현");
        System.out.println("⚙️ 장치 드라이버: 하드웨어 추상화 계층");

        /*
         * Bridge 패턴의 비즈니스 가치:
         *
         * 1. 개발 효율성:
         *    - 클래스 폭발 방지로 개발 및 유지보수 비용 절감
         *    - 재사용 가능한 컴포넌트로 개발 속도 향상
         *
         * 2. 시스템 유연성:
         *    - 런타임 구성 변경으로 동적 시스템 구축
         *    - 플랫폼별, 버전별 구현체 쉬운 교체
         *
         * 3. 확장성:
         *    - 새로운 요구사항에 대한 빠른 대응
         *    - 기존 코드 영향 없는 안전한 확장
         *
         * 4. 테스트 용이성:
         *    - Mock 구현체를 통한 단위 테스트
         *    - 각 계층의 독립적 테스트 가능
         */
    }
}