package org._07_bridge.ex01;

/**
 * AdvancedRemote - 고급 리모컨 구현 (RefinedAbstraction)
 *
 * Bridge 패턴에서 또 다른 RefinedAbstraction 역할을 담당하는 클래스입니다.
 * BasicRemote와 같은 Remote를 상속하지만, 더 많은 기능을 제공합니다.
 *
 * Bridge 패턴의 확장성 시연:
 * - 동일한 Device 구현체들을 재사용
 * - BasicRemote와는 다른 제어 방식과 추가 기능 제공
 * - 장치 구현 변경 없이 리모컨 기능만 확장
 *
 * 고급 리모컨의 특징:
 * - 더 정교한 제어 옵션
 * - 추가적인 편의 기능
 * - 사용자 정의 설정 지원
 */
class AdvancedRemote extends Remote {

    /**
     * 고급 리모컨 생성자
     *
     * @param device 제어할 장치
     */
    public AdvancedRemote(Device device) {
        super(device);
    }

    /**
     * 고급 리모컨의 전원 제어 구현
     *
     * BasicRemote와는 다른 전원 제어 방식을 보여줍니다.
     * Bridge 패턴의 유연성: 동일한 Device 인터페이스를 사용하되,
     * 상위 제어 로직은 리모컨 타입별로 다르게 구현 가능합니다.
     */
    @Override
    public void power() {
        System.out.println("AdvancedRemote: Smart power control activated");

        if (device.isEnabled()) {
            // 고급 리모컨은 끄기 전에 확인 과정을 거칩니다
            System.out.println("AdvancedRemote: Preparing for safe shutdown...");
            device.turnOff();
            System.out.println("AdvancedRemote: Device safely powered off");
        } else {
            // 고급 리모컨은 켤 때 최적화된 설정을 적용합니다
            System.out.println("AdvancedRemote: Optimizing device settings...");
            device.turnOn();

            // 켜진 후 추가 설정 (고급 리모컨만의 기능)
            setOptimalVolume();
        }
    }

    /**
     * 음소거 기능 - 고급 리모컨만의 추가 기능
     *
     * RefinedAbstraction이 제공하는 확장 기능의 예시입니다.
     * Device의 기본 기능을 조합하여 더 고수준의 기능을 구현합니다.
     */
    public void mute() {
        System.out.println("AdvancedRemote: Mute activated");
        if (device.isEnabled()) {
            device.setVolume(0);
            System.out.println("AdvancedRemote: Device is now muted");
        } else {
            System.out.println("AdvancedRemote: Cannot mute - device is off");
        }
    }

    /**
     * 최적 볼륨 설정 - 내부 헬퍼 메서드
     *
     * 고급 리모컨이 제공하는 지능적 기능의 예시입니다.
     * Device의 원시적 기능을 활용하여 더 스마트한 동작을 구현합니다.
     */
    private void setOptimalVolume() {
        System.out.println("AdvancedRemote: Setting optimal volume level...");
        device.setVolume(75); // 최적화된 기본 볼륨
    }

    /**
     * 채널/주파수 스캔 - 고급 기능
     *
     * Device 인터페이스에 없는 기능이지만, 고급 리모컨이 제공하는
     * 지능적 기능의 예시입니다. 실제로는 장치별로 다른 스캔 방식을
     * 구현할 수 있습니다.
     */
    public void scanChannels() {
        System.out.println("AdvancedRemote: Auto-scanning available channels/frequencies...");

        if (!device.isEnabled()) {
            System.out.println("AdvancedRemote: Please turn on the device first");
            return;
        }

        // 장치 타입에 따른 다른 스캔 방식 (예시)
        String deviceType = device.getClass().getSimpleName();
        switch (deviceType) {
            case "TV":
                System.out.println("AdvancedRemote: Scanning TV channels...");
                break;
            case "Radio":
                System.out.println("AdvancedRemote: Scanning FM frequencies...");
                break;
            default:
                System.out.println("AdvancedRemote: Generic scan completed");
        }

        System.out.println("AdvancedRemote: Scan complete");

        /*
         * Bridge 패턴의 확장성을 보여주는 부분:
         *
         * 1. 기능의 독립적 진화:
         *    - 고급 리모컨의 새 기능이 Device 구현에 영향 없음
         *    - Device의 새로운 구현체도 기존 리모컨과 호환
         *
         * 2. 다형성 활용:
         *    - 동일한 Device 인터페이스를 통해 다양한 장치 제어
         *    - 런타임에 장치 타입 확인 후 적절한 동작 수행
         *
         * 3. 조합의 유연성:
         *    - AdvancedRemote + TV, AdvancedRemote + Radio 등
         *    - 상속 기반이라면 각 조합마다 별도 클래스 필요
         */
    }

    /**
     * 고급 상태 표시
     *
     * BasicRemote보다 더 자세한 정보를 제공하는 고급 기능입니다.
     */
    public void displayDetailedStatus() {
        System.out.println("AdvancedRemote Detailed Status:");
        System.out.println("  Remote Type: Advanced");
        System.out.println("  Device: " + device.getClass().getSimpleName());
        System.out.println("  Power: " + (device.isEnabled() ? "ON" : "OFF"));
        System.out.println("  Features: Mute, Auto-scan, Smart power control");
    }
}