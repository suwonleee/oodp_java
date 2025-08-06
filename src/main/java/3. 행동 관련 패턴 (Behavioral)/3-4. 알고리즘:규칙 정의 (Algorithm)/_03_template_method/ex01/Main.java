package org._03_template_method.ex01;

/**
 * Main 클래스 - Template Method 패턴 사용 예제 (음료 제조)
 *
 * Template Method 패턴이 어떻게 공통된 알고리즘 구조를 유지하면서도
 * 각 구현체별로 다른 세부 과정을 허용하는지 보여주는 클라이언트 코드입니다.
 *
 * 이 예제를 통해 코드 중복 제거와 알고리즘 구조의 일관성 유지라는
 * Template Method 패턴의 주요 장점을 확인할 수 있습니다.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Template Method 패턴을 활용한 음료 제조 시스템 ===\n");

        // 커피 제조 과정
        System.out.println("=== 커피 제조 과정 ===");
        Beverage coffee = new Coffee();
        coffee.prepareRecipe();  // Template Method 호출
        System.out.println();

        // 차 제조 과정
        System.out.println("=== 차 제조 과정 ===");
        Beverage tea = new Tea();
        tea.prepareRecipe();     // 동일한 Template Method 호출
        System.out.println();

        /*
         * Template Method 패턴의 실제 동작 분석:
         *
         * 1. 알고리즘 구조의 통일성:
         *    - 커피와 차 모두 동일한 4단계 과정을 거침
         *    - prepareRecipe()가 전체 흐름을 제어
         *
         * 2. 세부 구현의 다양성:
         *    - brew(): 커피는 "Dripping", 차는 "Steeping"
         *    - addCondiments(): 커피는 "Sugar and Cream", 차는 "Lemon"
         *
         * 3. 코드 재사용성:
         *    - boilWater()와 pourInCup()은 모든 음료에서 재사용
         *    - 새로운 음료 추가 시에도 공통 로직 재활용 가능
         *
         * 4. 확장성:
         *    - 새로운 음료(예: HotChocolate) 추가 시
         *      brew()와 addCondiments()만 구현하면 됨
         *    - 전체 제조 흐름은 자동으로 상속받음
         *
         * Template Method 패턴 없이 구현했다면:
         * - 각 음료 클래스마다 전체 제조 과정을 중복 구현
         * - 제조 단계 변경 시 모든 클래스를 수정해야 함
         * - 새로운 공통 단계 추가 시 모든 클래스에 반영 필요
         */

        System.out.println("=== 패턴의 효과 ===");
        System.out.println("✅ 모든 음료가 동일한 4단계 제조 과정을 따름");
        System.out.println("✅ 공통 로직(물 끓이기, 컵에 따르기)은 재사용됨");
        System.out.println("✅ 각 음료의 고유한 특성(우리기, 첨가물)은 개별 구현됨");
        System.out.println("✅ 새로운 음료 추가 시 기존 코드 수정 불필요");
        System.out.println("✅ 제조 과정 변경 시 상위 클래스만 수정하면 됨");
    }
}