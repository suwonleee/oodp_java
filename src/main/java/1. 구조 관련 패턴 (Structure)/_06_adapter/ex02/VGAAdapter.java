package org._06_adapter.ex02;

/**
 * VGAAdapter - VGA 디스플레이 어댑터 (Adapter)
 *
 * VGA 시스템을 통합 DisplayAdapter 인터페이스에 맞춰주는 어댑터입니다.
 * VGA의 복잡한 연결 방식을 간단한 display() 메서드로 추상화합니다.
 *
 * 이 어댑터는 VGA 특유의 품질 설정 옵션을 캡슐화하여
 * 클라이언트가 복잡한 VGA API를 알 필요 없게 만듭니다.
 */
class VGAAdapter implements DisplayAdapter {
    private VGA vga;
    private boolean highQuality;

    /**
     * VGA 어댑터 생성자
     *
     * VGA 객체와 품질 설정을 받아 어댑터를 초기화합니다.
     * 생성자에서 품질을 미리 설정함으로써 클라이언트는
     * 매번 품질을 고려할 필요가 없습니다.
     *
     * @param vga VGA 시스템 인스턴스
     * @param highQuality 고화질 모드 사용 여부
     */
    public VGAAdapter(VGA vga, boolean highQuality) {
        this.vga = vga;
        this.highQuality = highQuality;
        System.out.println("VGAAdapter: Initialized with " +
                (highQuality ? "high" : "standard") + " quality mode");
    }

    /**
     * VGA 디스플레이 출력 구현
     *
     * DisplayAdapter 인터페이스의 간단한 display() 메서드를
     * VGA 시스템의 복잡한 connectWithVgaCable() 메서드로 변환합니다.
     *
     * 어댑터의 변환 과정:
     * 1. 단순한 display() 호출 수신
     * 2. 미리 설정된 품질 옵션 적용
     * 3. VGA 특화 연결 메서드 호출
     */
    @Override
    public void display() {
        System.out.println("VGAAdapter: Converting display request to VGA format...");
        vga.connectWithVgaCable(highQuality);
        System.out.println("VGAAdapter: VGA display activation complete");

        /*
         * VGA 어댑터의 책임:
         *
         * 1. 인터페이스 단순화:
         *    display() → connectWithVgaCable(boolean)
         *
         * 2. 설정 관리:
         *    - 품질 설정을 미리 구성
         *    - 클라이언트의 복잡성 감소
         *
         * 3. VGA 특화 처리:
         *    - 아날로그 연결의 특성 고려
         *    - 적절한 해상도 모드 선택
         *
         * 4. 에러 처리:
         *    - VGA 특화 에러를 일반적 에러로 변환
         *    - 연결 실패에 대한 적절한 대응
         */
    }
}