package org._01_facade.ex01;

/**
 * Lights - 조명 제어 서브시스템
 *
 * 스마트홈의 조명을 제어하는 개별 컴포넌트입니다.
 * Facade 패턴에서 서브시스템 역할을 하며,
 * 실제로는 여러 방의 조명, 밝기 조절 등의 복잡한 기능을 가질 수 있습니다.
 */
public class Lights {
    /**
     * 조명 켜기
     */
    public void on() {
        System.out.println("Lights are on.");
        // 실제 구현에서는 각 방별 조명 제어, 밝기 조절 등의 로직이 포함될 수 있음
    }

    /**
     * 조명 끄기
     */
    public void off() {
        System.out.println("Lights are off.");
        // 실제 구현에서는 절전 모드 설정, 보안등 유지 등의 로직이 포함될 수 있음
    }
}