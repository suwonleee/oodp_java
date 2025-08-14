package org._07_bridge.ex01;

/**
 * Device - 장치 제어 인터페이스 (Implementor)
 *
 * Bridge 패턴에서 Implementor 역할을 담당하는 인터페이스입니다.
 * TV, Radio 등 실제 장치들이 구현해야 하는 기본적인 제어 기능들을 정의합니다.
 *
 * Bridge 패턴의 핵심:
 * - Remote(Abstraction)는 이 인터페이스를 통해 실제 장치를 제어
 * - 구체적인 장치 타입(TV/Radio)에 대한 지식 없이 제어 가능
 * - 새로운 장치 타입 추가 시 Remote 코드 수정 불필요
 *
 * 이 인터페이스는 장치 제어에 필요한 원시적(primitive) 연산들만 정의하며,
 * 고수준의 제어 로직은 Remote에서 이 연산들을 조합하여 구현합니다.
 */
interface Device {
    /**
     * 장치 전원 켜기
     * 각 장치별로 전원을 켜는 구체적인 방법은 다를 수 있습니다.
     */
    void turnOn();

    /**
     * 장치 전원 끄기
     * 각 장치별로 전원을 끄는 구체적인 방법은 다를 수 있습니다.
     */
    void turnOff();

    /**
     * 볼륨 설정
     * @param volume 설정할 볼륨 값 (0-100)
     */
    void setVolume(int volume);

    /**
     * 장치 전원 상태 확인
     * @return 전원이 켜져있으면 true, 꺼져있으면 false
     */
    boolean isEnabled();
}