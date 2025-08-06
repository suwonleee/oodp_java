package org._06_adapter.ex02;

/**
 * USBAdapter - USB 디스플레이 어댑터 (Adapter)
 *
 * USB 시스템을 통합 DisplayAdapter 인터페이스에 맞춰주는 어댑터입니다.
 * USB의 다양한 버전과 전원 공급 옵션을 간단한 display() 메서드로 추상화합니다.
 *
 * USB는 가장 현대적이고 범용적인 연결 방식이지만, 여전히 특화된 설정이
 * 필요하므로 통합 인터페이스를 위해 어댑터를 사용합니다.
 */
class USBAdapter implements DisplayAdapter {
    private USB usb;
    private String usbVersion;
    private boolean powerDeliveryEnabled;

    /**
     * USB 어댑터 생성자
     *
     * USB 객체, 버전, 전원 공급 옵션을 받아 어댑터를 초기화합니다.
     * 복잡한 USB 설정을 생성 시점에 구성하여 사용을 단순화합니다.
     *
     * @param usb USB 시스템 인스턴스
     * @param usbVersion USB 버전 ("USB-C", "USB-3.0", "USB-2.0")
     * @param powerDeliveryEnabled 전원 공급 기능 사용 여부
     */
    public USBAdapter(USB usb, String usbVersion, boolean powerDeliveryEnabled) {
        this.usb = usb;
        this.usbVersion = usbVersion;
        this.powerDeliveryEnabled = powerDeliveryEnabled;

        System.out.println("USBAdapter: Configured for " + usbVersion +
                (powerDeliveryEnabled ? " with power delivery" : " without power delivery"));
    }

    /**
     * USB 디스플레이 출력 구현
     *
     * DisplayAdapter의 단순한 display() 메서드를 USB의
     * 복잡한 connectUSBDevice() 메서드로 변환합니다.
     *
     * 변환 과정:
     * 1. 간단한 display() 요청 수신
     * 2. 미리 설정된 USB 버전과 전원 옵션 적용
     * 3. USB 특화 연결 메서드 호출
     */
    @Override
    public void display() {
        System.out.println("USBAdapter: Adapting display request for USB connection...");
        usb.connectUSBDevice(usbVersion, powerDeliveryEnabled);
        System.out.println("USBAdapter: USB display connection established");

        /*
         * USB 어댑터의 고유한 역할:
         *
         * 1. 버전 관리:
         *    - 다양한 USB 버전별 최적화
         *    - 호환성 보장 및 성능 최적화
         *
         * 2. 기능 통합:
         *    - 디스플레이와 전원 공급의 통합 관리
         *    - 플러그 앤 플레이 자동화
         *
         * 3. 복잡성 은닉:
         *    - USB 특화 설정의 추상화
         *    - 클라이언트의 USB 지식 요구사항 제거
         *
         * 4. 미래 호환성:
         *    - 새로운 USB 버전 추가 시 쉬운 확장
         *    - 기존 코드 수정 없이 업그레이드 지원
         */
    }

    /**
     * USB 설정 동적 변경
     *
     * 런타임에 USB 설정을 변경할 수 있는 기능을 제공합니다.
     * 어댑터가 단순한 인터페이스 변환 이상의 부가 기능을 제공하는 예시입니다.
     *
     * @param newVersion 새로운 USB 버전
     * @param enablePowerDelivery 전원 공급 활성화 여부
     */
    public void reconfigureUSB(String newVersion, boolean enablePowerDelivery) {
        System.out.println("USBAdapter: Reconfiguring from " + usbVersion + " to " + newVersion);
        this.usbVersion = newVersion;
        this.powerDeliveryEnabled = enablePowerDelivery;
        System.out.println("USBAdapter: USB settings updated successfully");
    }

    /**
     * 현재 USB 설정 정보 반환
     *
     * 어댑터의 현재 설정 상태를 조회할 수 있는 메서드입니다.
     * 디버깅이나 모니터링 목적으로 유용합니다.
     *
     * @return USB 설정 정보 문자열
     */
    public String getUSBConfiguration() {
        return String.format("USB Version: %s, Power Delivery: %s",
                usbVersion,
                powerDeliveryEnabled ? "Enabled" : "Disabled");
    }
}