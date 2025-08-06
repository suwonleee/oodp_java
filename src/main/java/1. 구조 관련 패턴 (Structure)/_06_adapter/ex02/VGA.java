package org._06_adapter.ex02;

/**
 * VGA - 레거시 VGA 디스플레이 시스템 (Adaptee)
 *
 * Adapter 패턴에서 Adaptee 역할을 담당하는 클래스입니다.
 * 오래된 VGA(Video Graphics Array) 연결 방식을 나타내며,
 * 기존의 레거시 시스템에서 사용되던 인터페이스를 모방합니다.
 *
 * VGA의 특징:
 * - 아날로그 신호 방식
 * - 해상도와 화질에 제한
 * - 오래된 하드웨어와의 호환성
 * - 특별한 케이블 연결 방식
 */
class VGA {

    /**
     * VGA 케이블을 통한 디스플레이 연결
     *
     * VGA 시스템 특유의 연결 방법을 구현합니다.
     * 아날로그 신호 방식과 품질 옵션을 제공하는
     * 레거시 시스템의 전형적인 API 설계를 보여줍니다.
     *
     * @param highQuality 고화질 모드 여부
     *                    true: 고해상도 모드 (더 많은 처리 시간 필요)
     *                    false: 표준 해상도 모드 (빠른 처리)
     */
    public void connectWithVgaCable(boolean highQuality) {
        System.out.println("VGA System: Initializing VGA connection...");

        if (highQuality) {
            System.out.println("VGA System: Setting high quality mode (1024x768@60Hz)");
            // 고화질 모드 설정 시뮬레이션
            simulateConnectionDelay(300);
        } else {
            System.out.println("VGA System: Setting standard quality mode (800x600@60Hz)");
            // 표준 모드 설정 시뮬레이션
            simulateConnectionDelay(150);
        }

        System.out.println("VGA System: Analog signal established");
        System.out.println("VGA System: Display active through VGA cable");

        /*
         * VGA 시스템의 특징:
         * 1. 아날로그 신호 방식으로 디지털 대비 화질 한계
         * 2. 해상도 옵션이 제한적 (고정된 몇 가지 모드)
         * 3. 연결 설정에 상대적으로 긴 시간 소요
         * 4. 레거시 하드웨어와의 뛰어난 호환성
         * 5. 케이블 연결 방식에 대한 구체적인 제어 필요
         */
    }

    /**
     * VGA 연결 지연 시뮬레이션
     *
     * 실제 VGA 하드웨어의 연결 지연을 시뮬레이션합니다.
     * 아날로그 신호의 특성상 디지털 방식보다 연결 시간이 더 걸립니다.
     *
     * @param milliseconds 지연 시간 (밀리초)
     */
    private void simulateConnectionDelay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("VGA System: Connection interrupted");
            Thread.currentThread().interrupt();
        }
    }
}