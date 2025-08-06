
package org._01_facade.ex01;

/**
 * SmartHomeFacade - Facade 패턴의 핵심 클래스
 *
 * 여러 스마트홈 기기들(온도조절기, 조명, 커피머신)을 통합하여
 * 복잡한 개별 조작을 간단한 시나리오 기반 메서드로 제공합니다.
 *
 * 이 클래스는 클라이언트가 각 기기의 세부적인 조작법을 알 필요 없이
 * 'wakeUp()', 'leaveHome()' 같은 직관적인 메서드를 통해
 * 여러 기기를 한번에 제어할 수 있게 해줍니다.
 */
public class SmartHomeFacade {
    // 서브시스템 컴포넌트들을 private으로 캡슐화
    // private : ex) 클래스 안에서만 사용할 수 있는 내부 부품 -> 외부로부터 보호하는 것을 캡슐화(Encapsulation)
    private Thermostat thermostat;
    private Lights lights;
    private CoffeeMaker coffeeMaker;

    /**
     * 생성자 - 모든 서브시스템 컴포넌트들을 주입받아 초기화
     *
     * @param thermostat 온도조절기 객체
     * @param lights 조명 제어 객체
     * @param coffeeMaker 커피머신 객체
     */

    // 생성자(Constructor) : 이 클래스의 객체(인스턴스)를 처음 만들 때 딱 한 번 호출되는 '조립 설명서' 역할
    public SmartHomeFacade(
            Thermostat thermostat, Lights lights, CoffeeMaker coffeeMaker
    ) {
        // 일종의 '부품들'을 받아서 클래스 내부의 제 위치(this.thermostat 등)에 장착하는 역할을 합니다.
        this.thermostat = thermostat;
        this.lights = lights;
        this.coffeeMaker = coffeeMaker;
    }

    /**
     * 기상 시나리오 - 여러 기기들을 아침에 적합한 상태로 일괄 설정
     * 온도를 22도로 설정하고, 조명을 켜고, 커피를 준비합니다.
     */
    public void wakeUp() {
        System.out.println("Waking up...");
        thermostat.setTemperature(22);  // 쾌적한 온도로 설정
        lights.on();                    // 조명 켜기
        coffeeMaker.brewCoffee();       // 커피 내리기
    }

    /**
     * 외출 시나리오 - 에너지 절약을 위해 기기들을 외출 모드로 설정
     * 온도를 낮추고 조명을 끕니다.
     */
    public void leaveHome() {
        System.out.println("Leaving home...");
        thermostat.setTemperature(18);  // 절약 온도로 설정
        lights.off();                   // 조명 끄기
    }
}