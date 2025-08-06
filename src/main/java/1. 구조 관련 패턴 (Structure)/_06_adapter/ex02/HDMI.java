package org._06_adapter.ex02;

/**
 * HDMI - 현대적 HDMI 디스플레이 시스템 (Adaptee)
 *
 * High-Definition Multimedia Interface를 나타내는 클래스로,
 * 현대적인 디지털 디스플레이 연결 방식을 시뮬레이션합니다.
 *
 * HDMI의 특징:
 * - 디지털 신호 방식 (고화질)
 * - 오디오와 비디오 통합 전송
 * - 다양한 해상도 지원 (4K, 8K 등)
 * - 빠른 연결 및 핫플러그 지원
 */
class HDMI {

    /**
     * HDMI 포트를 통한 고해상도 디스플레이 연결
     *
     * HDMI의 현대적이고 고성능 연결 방식을 구현합니다.
     * 디지털 신호의 장점을 활용하여 빠르고 고품질의
     * 디스플레이 출력을 제공합니다.
     *
     * @param resolution 원하는 해상도 설정
     *                   예: "1080p", "4K", "8K"
     */
    public void connectHDMIPort(String resolution) {
        System.out.println("HDMI System: Initiating HDMI handshake...");

        // HDMI 특유의 빠른 디지털 연결 과정
        simulateDigitalHandshake();

        // 해상도별 최적화된 설정
        optimizeForResolution(resolution);

        System.out.println("HDMI System: Digital signal optimized for " + resolution);
        System.out.println("HDMI System: Audio/Video channels established");
        System.out.println("HDMI System: Display active through HDMI port");

        /*
         * HDMI 시스템의 현대적 특징:
         * 1. 디지털 신호로 손실 없는 고품질 전송
         * 2. 오디오와 비디오 동시 전송
         * 3. 다양한 해상도 동적 지원
         * 4. 빠른 연결 속도 (핫플러그)
         * 5. 자동 최적화 기능
         */
    }

    /**
     * HDMI 디지털 핸드셰이크 시뮬레이션
     *
     * HDMI의 특징인 빠른 디지털 협상 과정을 시뮬레이션합니다.
     * VGA에 비해 훨씬 빠른 연결 과정을 보여줍니다.
     */
    private void simulateDigitalHandshake() {
        try {
            System.out.println("HDMI System: EDID information exchange...");
            Thread.sleep(50); // 매우 빠른 디지털 협상
            System.out.println("HDMI System: Display capabilities detected");
        } catch (InterruptedException e) {
            System.out.println("HDMI System: Handshake interrupted");
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 해상도별 최적화 설정
     *
     * 요청된 해상도에 따라 HDMI 시스템을 최적화합니다.
     * 현대적 디스플레이 시스템의 지능적 적응 능력을 보여줍니다.
     *
     * @param resolution 목표 해상도
     */
    private void optimizeForResolution(String resolution) {
        switch (resolution.toLowerCase()) {
            case "4k":
                System.out.println("HDMI System: 4K optimization - HDR enabled, 60fps");
                break;
            case "8k":
                System.out.println("HDMI System: 8K optimization - HDR+ enabled, 30fps");
                break;
            case "1080p":
            default:
                System.out.println("HDMI System: 1080p optimization - Standard HDR, 60fps");
                break;
        }
    }
}