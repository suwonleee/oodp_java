
package org._07_bridge.ex01;

/**
 * Radio - 라디오 장치의 구체적 구현 (ConcreteImplementor)
 *
 * Bridge 패턴에서 또 다른 ConcreteImplementor 역할을 담당하는 클래스입니다.
 * TV와는 완전히 다른 방식이지만 동일한 Device 인터페이스를 구현하여,
 * Remote가 TV와 Radio를 동일한 방식으로 제어할 수 있게 합니다.
 *
 * Bridge 패턴의 유연성:
 * - 동일한 Remote 코드로 TV와 Radio를 모두 제어 가능
 * - 각 장치의 고유 특성은 보존하면서도 공통 인터페이스 제공
 * - 새로운 라디오 기능 추가 시 다른 장치나 Remote에 영향 없음
 */
class Radio implements Device {
    private boolean on = false;
    private int volume = 50;
    private float frequency = 88.5f; // FM 주파수

    /**
     * 라디오 전원 켜기
     * 라디오 특화된 초기화 과정을 수행합니다.
     */
    @Override
    public void turnOn() {
        on = true;
        System.out.println("Radio: Power ON");
        System.out.println("Radio: Tuned to " + frequency + " FM");
        System.out.println("Radio: Playing audio...");
    }

    /**
     * 라디오 전원 끄기
     * 라디오 특화된 종료 과정을 수행합니다.
     */
    @Override
    public void turnOff() {
        on = false;
        System.out.println("Radio: Power OFF");
        System.out.println("Radio: Audio stopped");
    }

    /**
     * 라디오 볼륨 설정
     * 라디오는 오디오 중심의 피드백을 제공합니다.
     */
    @Override
    public void setVolume(int volume) {
        if (volume < 0) volume = 0;
        if (volume > 100) volume = 100;

        this.volume = volume;
        System.out.println("Radio: Volume set to " + volume + "%");

        // 라디오 특화 기능: 오디오 레벨 조정
        if (on) {
            if (volume == 0) {
                System.out.println("Radio: Muted");
            } else {
                System.out.println("Radio: Audio level adjusted");
            }
        }
    }

    /**
     * 라디오 전원 상태 확인
     */
    @Override
    public boolean isEnabled() {
        return on;
    }

    /**
     * 라디오 전용 기능: 주파수 튜닝
     * TV의 채널과 유사하지만 라디오만의 고유 기능입니다.
     * 이 역시 Device 인터페이스에 없는 라디오 특화 기능입니다.
     */
    public void setFrequency(float frequency) {
        if (on && frequency >= 88.0f && frequency <= 108.0f) {
            this.frequency = frequency;
            System.out.println("Radio: Tuned to " + frequency + " FM");
        }
    }
}