package org._07_bridge.ex01;

/**
 * Remote - 리모컨의 추상적 개념 (Abstraction)
 *
 * Bridge 패턴에서 Abstraction 역할을 담당하는 추상 클래스입니다.
 * 리모컨의 기본적인 구조와 공통 기능을 정의하며,
 * 실제 장치 제어는 Device 객체에 위임합니다.
 *
 * Bridge 패턴의 핵심 구현:
 * - Device를 상속하지 않고 컴포지션으로 참조 (has-a 관계)
 * - 고수준 제어 로직을 제공하면서 저수준 구현은 Device에 위임
 * - 리모컨 종류(Basic/Advanced)와 장치 종류(TV/Radio)를 독립적으로 확장 가능
 *
 * 이 클래스는 "리모컨"이라는 추상적 개념을 나타내며,
 * 구체적인 리모컨 타입들의 공통 기능을 담당합니다.
 */
abstract class Remote {
    /**
     * Bridge 패턴의 핵심: Implementor에 대한 참조
     *
     * 이 참조를 통해 실제 장치 제어 기능을 위임합니다.
     * 상속이 아닌 컴포지션을 사용하여 느슨한 결합을 달성합니다.
     */
    protected Device device;

    /**
     * 리모컨 생성자
     *
     * @param device 제어할 장치 객체
     *
     * Bridge 패턴의 특징:
     * - 런타임에 제어할 장치를 결정
     * - 동일한 리모컨으로 다양한 장치 제어 가능
     * - 장치 교체 시 리모컨 코드 수정 불필요
     */
    public Remote(Device device) {
        this.device = device;
    }

    /**
     * 전원 제어 - 추상 메서드
     *
     * 각 리모컨 타입(Basic/Advanced)이 자신만의 전원 제어 로직을 구현합니다.
     * 실제 장치 제어는 device 객체에 위임하되,
     * 제어 방식(토글/별도 버튼 등)은 리모컨 타입별로 다를 수 있습니다.
     */
    public abstract void power();

    /**
     * 볼륨 조절 - 공통 기능
     *
     * 모든 리모컨이 공통으로 가지는 기능으로 기본 구현을 제공합니다.
     * 하위 클래스에서 필요시 오버라이드할 수 있습니다.
     */
    public void volumeUp() {
        System.out.println("Remote: Volume up pressed");
        // 현재 볼륨을 알기 위해서는 Device 인터페이스를 확장해야 하지만,
        // 예시의 단순화를 위해 고정값으로 증가
        if (device.isEnabled()) {
            // 실제 구현에서는 현재 볼륨을 가져와서 증가시키는 로직 필요
            System.out.println("Remote: Increasing volume...");
        }
    }

    /**
     * 볼륨 감소 - 공통 기능
     */
    public void volumeDown() {
        System.out.println("Remote: Volume down pressed");
        if (device.isEnabled()) {
            System.out.println("Remote: Decreasing volume...");
        }
    }

    /**
     * 장치 교체 기능
     *
     * Bridge 패턴의 유연성을 보여주는 메서드로,
     * 런타임에 제어할 장치를 변경할 수 있습니다.
     *
     * @param device 새로운 제어 대상 장치
     */
    public void setDevice(Device device) {
        this.device = device;
        System.out.println("Remote: Device switched");
    }
}