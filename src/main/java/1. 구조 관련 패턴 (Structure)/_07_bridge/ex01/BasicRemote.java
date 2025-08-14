
package org._07_bridge.ex01;

/**
 * BasicRemote - 기본 리모컨 구현 (RefinedAbstraction)
 *
 * Bridge 패턴에서 RefinedAbstraction 역할을 담당하는 클래스입니다.
 * Remote의 추상적 정의를 구체화하여 기본적인 리모컨 기능을 구현합니다.
 *
 * Bridge 패턴의 확장성:
 * - Remote(Abstraction) 계층의 확장: BasicRemote, AdvancedRemote 등
 * - Device(Implementor) 계층의 확장: TV, Radio, Projector 등
 * - 두 계층이 독립적으로 확장 가능 (N × M 조합을 상속 없이 지원)
 *
 * 기본 리모컨의 특징:
 * - 단순하고 직관적인 조작
 * - 전원 토글 방식 (켜져있으면 끄고, 꺼져있으면 킴)
 * - 필수 기능만 제공
 */
class BasicRemote extends Remote {

    /**
     * 기본 리모컨 생성자
     *
     * @param device 제어할 장치
     *
     * 부모 클래스인 Remote의 생성자를 호출하여
     * Device 객체를 초기화합니다.
     */
    public BasicRemote(Device device) {
        super(device);
    }

    /**
     * 기본 리모컨의 전원 제어 구현
     *
     * Bridge 패턴의 핵심 동작:
     * 1. 고수준 로직: 현재 상태 확인 후 토글 결정 (Abstraction의 책임)
     * 2. 저수준 구현: 실제 전원 on/off 동작 (Implementor에 위임)
     *
     * 이 방식으로 리모컨의 제어 로직과 장치의 구현 세부사항이 분리됩니다.
     */
    @Override
    public void power() {
        System.out.println("BasicRemote: Power button pressed");

        // 현재 장치 상태 확인 (Device 인터페이스를 통한 추상적 접근)
        if (device.isEnabled()) {
            // 켜져있으면 끄기 (구체적 구현은 Device에 위임)
            device.turnOff();
        } else {
            // 꺼져있으면 켜기 (구체적 구현은 Device에 위임)
            device.turnOn();
        }

        /*
         * Bridge 패턴의 이점을 보여주는 부분:
         *
         * 1. 추상화와 구현의 분리:
         *    - BasicRemote는 "토글" 로직만 담당
         *    - TV/Radio는 각자의 방식으로 전원 제어 수행
         *
         * 2. 독립적 확장:
         *    - 새로운 리모컨 타입 추가 시 Device 코드 수정 불필요
         *    - 새로운 장치 추가 시 Remote 코드 수정 불필요
         *
         * 3. 런타임 유연성:
         *    - 동일한 BasicRemote로 TV ↔ Radio 전환 가능
         *    - setDevice()로 제어 대상 동적 변경
         */
    }

    /**
     * 기본 리모컨만의 단순한 상태 표시
     *
     * RefinedAbstraction이 제공하는 추가 기능의 예시입니다.
     * 기본 리모컨은 현재 제어 중인 장치의 간단한 상태만 표시합니다.
     */
    public void displayStatus() {
        System.out.println("BasicRemote Status:");
        System.out.println("  Device: " + device.getClass().getSimpleName());
        System.out.println("  Power: " + (device.isEnabled() ? "ON" : "OFF"));
    }
}