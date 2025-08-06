package org._03_template_method.ex01;

/**
 * Coffee - 커피 제조 구체 클래스
 *
 * Template Method 패턴의 ConcreteClass 역할을 담당하는 클래스입니다.
 * Beverage 추상 클래스를 상속받아 커피만의 특별한 제조 방법을
 * 구현합니다.
 *
 * 전체적인 제조 흐름(prepareRecipe)은 상위 클래스에서 정의된 대로 따르되,
 * 커피 특유의 "드립" 과정과 "설탕과 크림 추가" 과정을 구현합니다.
 */
class Coffee extends Beverage {

    /**
     * 커피 우리기 구현
     *
     * 상위 클래스의 추상 메서드 brew()를 구현합니다.
     * 커피는 뜨거운 물을 통해 원두에서 추출(드립)하는 방식으로 우립니다.
     *
     * 이 메서드는 Template Method(prepareRecipe)의 2단계에서 호출됩니다.
     */
    @Override
    void brew() {
        System.out.println("Dripping Coffee through filter");
        // 실제 구현에서는 다음과 같은 세부 과정들이 포함될 수 있습니다:
        // - 커피 원두 분쇄
        // - 필터 준비
        // - 물 온도 조절 (90-96도)
        // - 추출 시간 조절 (4-6분)
        // - 추출량 조절
    }

    /**
     * 커피 첨가물 넣기 구현
     *
     * 상위 클래스의 추상 메서드 addCondiments()를 구현합니다.
     * 일반적으로 커피에는 설탕과 크림을 첨가합니다.
     *
     * 이 메서드는 Template Method(prepareRecipe)의 4단계에서 호출됩니다.
     */
    @Override
    void addCondiments() {
        System.out.println("Adding Sugar and Cream");
        // 실제 구현에서는 다음과 같은 옵션들이 포함될 수 있습니다:
        // - 설탕 종류 선택 (백설탕, 흑설탕, 인공감미료)
        // - 크림 종류 선택 (우유, 생크림, 식물성 크림)
        // - 첨가량 조절
        // - 추가 향신료 (계피, 바닐라 등)
    }
}