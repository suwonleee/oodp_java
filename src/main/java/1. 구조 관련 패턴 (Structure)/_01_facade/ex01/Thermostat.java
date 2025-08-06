package org._01_facade.ex01;

/**
 * Thermostat - 온도조절기 서브시스템
 *
 * 스마트홈의 온도 제어를 담당하는 개별 컴포넌트입니다.
 * Facade 패턴에서 서브시스템 역할을 하며,
 * 실제로는 더 복잡한 온도 제어 로직을 가질 수 있습니다.
 */
public class Thermostat {
    /**
     * 온도 설정 메서드
     * @param temperature 설정할 온도 (섭씨)
     */
    public void setTemperature(int temperature) {
        System.out.println("Setting thermostat to " + temperature + " degrees.");
        // 실제 구현에서는 센서 확인, 히터/에어컨 제어 등의 복잡한 로직이 포함될 수 있음
    }
}