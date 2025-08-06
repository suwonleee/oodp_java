package org._01_facade.ex01;

/**
 * Main 클래스 - Facade 패턴 사용 예제
 *
 * 클라이언트 코드로서 SmartHomeFacade를 통해
 * 복잡한 스마트홈 시스템을 간단하게 제어하는 방법을 보여줍니다.
 */
public class Main {
    public static void main(String[] args) {
        // 각 서브시스템 컴포넌트들을 개별적으로 생성
        Thermostat thermostat = new Thermostat();
        Lights lights = new Lights();
        CoffeeMaker coffeeMaker = new CoffeeMaker();

        // Facade 객체 생성 - 모든 서브시스템을 통합 관리
        SmartHomeFacade smartHome = new SmartHomeFacade(thermostat, lights, coffeeMaker);

        // 복잡한 개별 조작 대신 간단한 시나리오 메서드 사용
        smartHome.wakeUp();      // 기상 시 필요한 모든 기기 설정을 한번에
        smartHome.leaveHome();   // 외출 시 필요한 모든 기기 설정을 한번에

        /*
         * Facade 패턴의 장점:
         * 1. 클라이언트는 각 기기의 세부 사항을 알 필요가 없음
         * 2. 여러 기기를 조작하는 복잡한 로직이 Facade에 캡슐화됨
         * 3. 코드가 더 읽기 쉽고 유지보수하기 편함
         * 4. 새로운 시나리오 추가가 용이함
         */
    }
}