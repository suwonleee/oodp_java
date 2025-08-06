package org._06_adapter.ex02;

/**
 * USB - USB 디스플레이 시스템 (Adaptee)
 *
 * USB(Universal Serial Bus) 기반의 디스플레이 연결 시스템을 나타냅니다.
 * 최신 기술로 다양한 디바이스 연결과 플러그 앤 플레이를 지원하는
 * 범용 연결 방식입니다.
 *
 * USB 디스플레이의 특징:
 * - 범용 연결성 (다양한 기기 지원)
 * - 플러그 앤 플레이
 * - 전원 공급 동시 지원
 * - 다양한 USB 버전별 성능 차이
 */
class USB {

    /**
     * USB를 통한 범용 디스플레이 연결
     *
     * USB의 범용성과 편의성을 활용한 디스플레이 연결을 구현합니다.
     * USB 버전에 따른 성능 차이와 플러그 앤 플레이 특성을 보여줍니다.
     *
     * @param usbVersion USB 버전 ("USB-C", "USB-3.0", "USB-2.0")
     * @param powerDelivery 전원 공급 기능 사용 여부
     */
    public void connectUSBDevice(String usbVersion, boolean powerDelivery) {
        System.out.println("USB System: Detecting " + usbVersion + " connection...");

        // USB 버전별 성능 및 기능 차이 처리
        handleUSBVersionCapabilities(usbVersion);

        // 플러그 앤 플레이 자동 인식 과정
        performPlugAndPlayDetection();

        // 전원 공급 기능 설정
        if (powerDelivery) {
            enablePowerDelivery(usbVersion);
        }

        System.out.println("USB System: Display device ready via " + usbVersion);

        /*
         * USB 디스플레이 시스템의 특징:
         * 1. 범용성: 다양한 기기와 호환
         * 2. 편의성: 플러그 앤 플레이 지원
         * 3. 버전별 차이: 속도와 기능의 차이
         * 4. 전원 공급: 별도 전원 없이 디스플레이 구동
         * 5. 양방향 통신: 디스플레이 정보 피드백
         */
    }

    /**
     * USB 버전별 성능 및 기능 처리
     *
     * 각 USB 버전의 특성에 맞는 최적화를 수행합니다.
     * 실제 하드웨어의 버전별 차이를 시뮬레이션합니다.
     *
     * @param usbVersion USB 버전
     */
    private void handleUSBVersionCapabilities(String usbVersion) {
        switch (usbVersion.toUpperCase()) {
            case "USB-C":
                System.out.println("USB System: USB-C detected - 4K@60Hz supported, 100W power delivery");
                break;
            case "USB-3.0":
                System.out.println("USB System: USB 3.0 detected - 1080p@60Hz supported, 4.5W power");
                break;
            case "USB-2.0":
                System.out.println("USB System: USB 2.0 detected - 1080p@30Hz supported, 2.5W power");
                break;
            default:
                System.out.println("USB System: Unknown USB version - using basic compatibility mode");
                break;
        }
    }

    /**
     * 플러그 앤 플레이 자동 인식 수행
     *
     * USB의 핵심 특징인 자동 인식 기능을 시뮬레이션합니다.
     * 사용자의 추가 설정 없이 자동으로 디바이스를 인식하고 설정합니다.
     */
    private void performPlugAndPlayDetection() {
        try {
            System.out.println("USB System: Plug and play detection in progress...");
            Thread.sleep(100); // 빠른 자동 인식
            System.out.println("USB System: Display device auto-configured");
        } catch (InterruptedException e) {
            System.out.println("USB System: Detection interrupted");
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 전원 공급 기능 활성화
     *
     * USB의 전원 공급 기능을 활성화합니다.
     * 이는 USB 디스플레이의 중요한 편의 기능 중 하나입니다.
     *
     * @param usbVersion USB 버전 (전원 공급 용량 결정)
     */
    private void enablePowerDelivery(String usbVersion) {
        System.out.println("USB System: Enabling power delivery...");

        String powerCapacity = switch (usbVersion.toUpperCase()) {
            case "USB-C" -> "up to 100W";
            case "USB-3.0" -> "up to 4.5W";
            case "USB-2.0" -> "up to 2.5W";
            default -> "standard USB power";
        };

        System.out.println("USB System: Power delivery active - " + powerCapacity);
        System.out.println("USB System: No external power adapter required");
    }
}