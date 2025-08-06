package org._03_template_method.ex01;

/**
 * Tea - 차 제조 구체 클래스
 *
 * Template Method 패턴의 ConcreteClass 역할을 담당하는 클래스입니다.
 * Beverage 추상 클래스를 상속받아 차만의 특별한 제조 방법을 구현합니다.
 *
 * Coffee 클래스와 동일한 제조 흐름을 따르지만, 차 특유의 "우려내기" 과정과
 * "레몬 추가" 과정을 구현하여 각 음료의 고유한 특성을 반영합니다.
 */
class Tea extends Beverage {

    /**
     * 차 우리기 구현
     *
     * 상위 클래스의 추상 메서드 brew()를 구현합니다.
     * 차는 찻잎을 뜨거운 물에 우려내는 방식으로 제조됩니다.
     * 커피의 드립 방식과는 완전히 다른 추출 방법을 사용합니다.
     *
     * 이 메서드는 Template Method(prepareRecipe)의 2단계에서 호출됩니다.
     */
    @Override
    void brew() {
        System.out.println("Steeping the tea");
        // 실제 구현에서는 다음과 같은 세부 과정들이 포함될 수 있습니다:
        // - 찻잎 종류별 우림 시간 조절 (녹차: 2-3분, 홍차: 3-5분)
        // - 물 온도 조절 (녹차: 70-80도, 홍차: 90-100도)
        // - 찻잎 양 조절 (물 200ml당 찻잎 2-3g)
        // - 다중 우림 (좋은 차는 여러 번 우려낼 수 있음)
    }

    /**
     * 차 첨가물 넣기 구현
     *
     * 상위 클래스의 추상 메서드 addCondiments()를 구현합니다.
     * 일반적으로 차에는 레몬을 첨가하여 상큼한 맛을 더합니다.
     * 커피의 설탕/크림과는 완전히 다른 첨가물을 사용합니다.
     *
     * 이 메서드는 Template Method(prepareRecipe)의 4단계에서 호출됩니다.
     */
    @Override
    void addCondiments() {
        System.out.println("Adding Lemon");
        // 실제 구현에서는 다음과 같은 옵션들이 포함될 수 있습니다:
        // - 레몬 형태 선택 (슬라이스, 즙, 껍질)
        // - 꿀이나 설탕 추가 옵션
        // - 허브 추가 (민트, 생강 등)
        // - 차 종류별 최적 첨가물 (얼그레이 + 우유, 녹차 + 꿀 등)
    }
}