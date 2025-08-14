package org._07_bridge.ex01;

/**
 * TV - TV 장치의 구체적 구현 (ConcreteImplementor)
 *
 * Bridge 패턴에서 ConcreteImplementor 역할을 담당하는 클래스입니다.
 * Device 인터페이스를 TV 장치의 특성에 맞게 구체적으로 구현합니다.
 *
 * Bridge 패턴의 효과:
 * - Remote 클래스는 TV의 구체적인 구현을 몰라도 Device 인터페이스를 통해 제어
 * - TV만의 고유한 특성(채널, 화면 밝기 등)은 내부적으로 관리
 * - 새로운 TV 기능 추가 시 Remote 코드에 영향 없음
 */
class TV implements Device {
    private boolean on = false;
    private int volume = 30;
    private int channel = 1;

    /**
     * TV 전원 켜기
     * TV 특화된 초기화 과정을 포함할 수 있습니다.
     */
    @Override
    public void turnOn() {
        on = true;
        System.out.println("TV: Power ON");
        System.out.println("TV: Displaying channel " + channel);
    }

    /**
     * TV 전원 끄기
     * TV 특화된 종료 과정을 포함할 수 있습니다.
     */
    @Override
    public void turnOff() {
        on = false;
        System.out.println("TV: Power OFF");
        System.out.println("TV: Screen turned black");
    }

    /**
     * TV 볼륨 설정
     * TV는 시각적 볼륨 표시 등의 고유 기능을 가질 수 있습니다.
     */
    @Override
    public void setVolume(int volume) {
        if (volume < 0) volume = 0;
        if (volume > 100) volume = 100;

        this.volume = volume;
        System.out.println("TV: Volume set to " + volume + "%");

        // TV 특화 기능: 화면에 볼륨 표시
        if (on) {
            System.out.println("TV: Volume indicator displayed on screen");
        }
    }

    /**
     * TV 전원 상태 확인
     */
    @Override
    public boolean isEnabled() {
        return on;
    }

    /**
     * TV 전용 기능: 채널 변경
     * 이는 Device 인터페이스에 없는 TV만의 고유 기능입니다.
     * Remote에서 직접 호출하지 않고 내부적으로 관리됩니다.
     */
    public void setChannel(int channel) {
        if (on && channel > 0) {
            this.channel = channel;
            System.out.println("TV: Channel changed to " + channel);
        }
    }
}