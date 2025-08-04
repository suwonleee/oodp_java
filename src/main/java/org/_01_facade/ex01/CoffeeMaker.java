package org._01_facade.ex01;

/**
 * CoffeeMaker - 커피머신 서브시스템
 *
 * 스마트홈의 커피 제조를 담당하는 개별 컴포넌트입니다.
 * Facade 패턴에서 서브시스템 역할을 하며,
 * 실제로는 원두 선택, 농도 조절, 물 온도 설정 등의 복잡한 기능을 가질 수 있습니다.
 */
public class CoffeeMaker {
    /**
     * 커피 내리기
     */
    public void brewCoffee() {
        System.out.println("Brewing coffee.");
        // 실제 구현에서는 물 가열, 원두 분쇄, 추출 과정 제어 등의 복잡한 로직이 포함될 수 있음
    }
}