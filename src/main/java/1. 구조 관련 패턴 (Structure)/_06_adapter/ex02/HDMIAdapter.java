package org._06_adapter.ex02;

/**
 * HDMIAdapter - HDMI 디스플레이 어댑터 (Adapter)
 *
 * HDMI 시스템을 통합 DisplayAdapter 인터페이스에 맞춰주는 어댑터입니다.
 * HDMI의 고급 해상도 기능을 간단한 display() 인터페이스로 추상화합니다.
 *
 * HDMI는 현대적 시스템이지만 여전히 특화된 API를 가지고 있어
 * 통합 인터페이스를 위해 어댑터가 필요합니다.
 */
class HDMIAdapter implements DisplayAdapter {
    private HDMI hdmi;
    private String preferredResolution;

    /**
     * HDMI 어댑터 생성자
     *
     * HDMI 객체와 선호 해상도를 받아 어댑터를 초기화합니다.
     * 생성 시점에 해상도를 설정함으로써 매번 해상도를
     * 결정하는 복잡성을 제거합니다.
     *
     * @param hdmi HDMI 시스템 인스턴스
     * @param preferredResolution 선호하는 해상도 ("1080p", "4K", "8K")
     */
    public HDMIAdapter(HDMI hdmi, String preferredResolution) {
        this.hdmi = hdmi;
        this.preferredResolution = preferredResolution;
        System.out.println("HDMIAdapter: Configured for " + preferredResolution + " resolution");
    }

    /**
     * HDMI 디스플레이 출력 구현
     *
     * DisplayAdapter의 단순한 display() 메서드를 HDMI의
     * 고급 connectHDMIPort() 메서드로 변환합니다.
     *
     * 변환 과정:
     * 1. 간단한 display() 요청 수신
     * 2. 미리 설정된 해상도 옵션 적용
     * 3. HDMI 특화 연결 메서드 호출
     */
    @Override
    public void display() {
        System.out.println("HDMIAdapter: Processing display request for HDMI...");
        hdmi.connectHDMIPort(preferredResolution);
        System.out.println("HDMIAdapter: HDMI display activation successful");

        /*
         * HDMI 어댑터의 특별한 역할:
         *
         * 1. 고급 기능의 단순화:
         *    - 복잡한 해상도 선택을 미리 구성
         *    - 다양한 HDMI 옵션을 기본값으로 설정
         *
         * 2. 성능 최적화:
         *    - 디지털 신호의 빠른 특성 활용
         *    - 적절한 해상도 자동 선택
         *
         * 3. 호환성 보장:
         *    - 다양한 HDMI 버전 대응
         *    - 레거시 시스템과의 통합 인터페이스 제공
         *
         * 4. 미래 확장성:
         *    - 새로운 HDMI 기능 추가 시 어댑터만 수정
         *    - 클라이언트 코드는 변경 불필요
         */
    }

    /**
     * 해상도 동적 변경 메서드
     *
     * 런타임에 해상도를 변경할 수 있는 추가 기능을 제공합니다.
     * 이는 어댑터가 단순한 변환 이상의 부가 기능을 제공할 수 있음을 보여줍니다.
     *
     * @param newResolution 새로운 해상도 설정
     */
    public void changeResolution(String newResolution) {
        System.out.println("HDMIAdapter: Changing resolution from " +
                preferredResolution + " to " + newResolution);
        this.preferredResolution = newResolution;
    }
}