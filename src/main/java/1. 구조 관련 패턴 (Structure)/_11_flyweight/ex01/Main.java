package org._11_flyweight.ex01;

/**
 * Main 클래스 - Flyweight 패턴 사용 예제 (도서관 시스템)
 *
 * Flyweight 패턴이 어떻게 메모리 효율성을 달성하는지 보여주는 데모 코드입니다.
 * 도서관에서 같은 책을 여러 명이 대출하는 시나리오를 통해
 * 객체 공유로 인한 메모리 절약 효과를 실증합니다.
 *
 * 이 예제는 Flyweight 패턴의 핵심 개념인 "내재적 상태와 외재적 상태의 분리"와
 * "객체 공유를 통한 메모리 최적화"를 명확히 보여줍니다.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Flyweight 패턴을 활용한 도서관 시스템 ===\n");

        // 도서관과 팩토리 인스턴스 생성
        Library library = new Library();
        BookFactory factory = BookFactory.getInstance();

        System.out.println(" 1. 다양한 책들의 대출 시뮬레이션 ");
        simulateBookBorrowing(library);

        System.out.println(" 2. 동일한 책의 중복 대출 시뮬레이션 ");
        simulateDuplicateBorrowing(library);

        System.out.println(" 3. Flyweight 패턴의 메모리 효율성 분석 ");
        analyzeMemoryEfficiency(library, factory);

        System.out.println(" 4. 모든 대출된 책 읽기 시연 ");
        demonstrateBookReading(library);

        System.out.println(" 5. Factory 캐시 상태 확인 ");
        factory.displayCachedBooks();

        printPatternBenefits();
    }

    /**
     * 다양한 책들의 대출 시뮬레이션
     *
     * 서로 다른 책들이 대출되는 과정에서 각각 새로운 Flyweight가
     * 생성되는 과정을 보여줍니다.
     */
    private static void simulateBookBorrowing(Library library) {
        // 서로 다른 책들을 다양한 사용자가 대출
        library.borrowBook("해리포터와 마법사의 돌", "김철수", "2024-01-15");
        library.borrowBook("반지의 제왕", "이영희", "2024-01-16");
        library.borrowBook("어린왕자", "박민수", "2024-01-17");
        library.borrowBook("1984", "정미영", "2024-01-18");

        System.out.println("서로 다른 책 4권이 대출되었습니다.");
        System.out.println("각 책마다 새로운 Flyweight가 생성되어야 합니다.\n");
    }

    /**
     * 동일한 책의 중복 대출 시뮬레이션
     *
     * 같은 책을 여러 명이 대출할 때 Flyweight가 재사용되는 과정을 보여줍니다.
     * 이것이 Flyweight 패턴의 핵심 메모리 절약 메커니즘입니다.
     */
    private static void simulateDuplicateBorrowing(Library library) {
        // 같은 책을 여러 명이 대출 (Flyweight 재사용)
        library.borrowBook("해리포터와 마법사의 돌", "최진욱", "2024-01-19");
        library.borrowBook("해리포터와 마법사의 돌", "한소영", "2024-01-20");
        library.borrowBook("반지의 제왕", "김동준", "2024-01-21");

        System.out.println("같은 책들이 추가로 대출되었습니다.");
        System.out.println("새로운 Flyweight 생성 없이 기존 객체가 재사용됩니다.\n");

        // 현재 대출 상황 출력
        library.displayBorrowedBooks();

        /*
         * 여기서 확인할 수 있는 것:
         * 1. "해리포터와 마법사의 돌"이 3번 대출되었지만 Book 객체는 하나만 존재
         * 2. "반지의 제왕"이 2번 대출되었지만 Book 객체는 하나만 존재
         * 3. 각 대출 기록(BookRecord)은 별도로 관리되어 독립적인 외재적 상태 유지
         */
    }

    /**
     * Flyweight 패턴의 메모리 효율성 분석
     *
     * 실제 메모리 절약 효과를 정량적으로 분석하여 보여줍니다.
     */
    private static void analyzeMemoryEfficiency(Library library, BookFactory factory) {
        library.showMemoryAnalysis();

        System.out.println("=== 메모리 절약 효과 시뮬레이션 ===");

        // 더 극단적인 예시로 효과 시연
        System.out.println("추가 대출 시뮬레이션 (같은 책 대량 대출):");

        // 인기 책 대량 대출 시뮬레이션
        String[] borrowers = {"학생1", "학생2", "학생3", "학생4", "학생5"};
        String[] dates = {"2024-01-22", "2024-01-23", "2024-01-24", "2024-01-25", "2024-01-26"};

        for (int i = 0; i < borrowers.length; i++) {
            library.borrowBook("해리포터와 마법사의 돌", borrowers[i], dates[i]);
        }

        System.out.println("\n대출 완료 후 메모리 분석:");
        library.showMemoryAnalysis();

        /*
         * 메모리 효율성 계산 예시:
         *
         * 현재 상황: "해리포터" 8번 대출, 총 12개 대출 기록, 4개 고유 책
         *
         * 일반적인 방법:
         * - 12개의 Book 객체 생성 필요
         * - 메모리 사용량: 12 × Book_크기
         *
         * Flyweight 방법:
         * - 4개의 Book 객체만 생성
         * - 메모리 사용량: 4 × Book_크기 + 12 × BookRecord_크기
         *
         * 절약량: 8 × Book_크기 (66.7% 메모리 절약)
         */

        System.out.println("===============================\n");
    }

    /**
     * 모든 대출된 책 읽기 시연
     *
     * Flyweight 객체들이 어떻게 공유되면서도 각각의 컨텍스트에서
     * 독립적으로 동작하는지 보여줍니다.
     */
    private static void demonstrateBookReading(Library library) {
        library.readAllBooks();

        /*
         * 읽기 동작에서 관찰할 수 있는 것:
         * 1. 같은 제목의 책들은 동일한 Book 인스턴스의 read() 메서드 호출
         * 2. 각 BookRecord는 자신만의 외재적 상태(borrower)를 유지
         * 3. 내재적 상태(title)는 모든 관련 대출에서 공유
         */

        System.out.println("특정 책의 대출 기록 조회:");
        library.findBorrowRecordsByTitle("해리포터와 마법사의 돌");
    }

    /**
     * Flyweight 패턴의 실제 효과와 장점 출력
     */
    private static void printPatternBenefits() {
        System.out.println("=== Flyweight 패턴의 실제 효과 ===");
        System.out.println("✅ 메모리 사용량 대폭 절약 (객체 공유를 통한)");
        System.out.println("✅ 객체 생성 비용 감소 (중복 생성 방지)");
        System.out.println("✅ 캐시 효과로 인한 성능 향상");
        System.out.println("✅ 대량의 유사 객체 처리에 최적화");
        System.out.println("✅ 메모리 부족 상황에서의 안정성 향상");

        System.out.println("\n=== 적용 가능한 실제 시나리오 ===");
        System.out.println("️ 게임: 동일한 텍스처/스프라이트를 사용하는 다수의 게임 객체");
        System.out.println(" 문서 편집기: 같은 폰트/스타일을 사용하는 다수의 문자");
        System.out.println("️ 웹 브라우저: 동일한 아이콘/이미지를 사용하는 다수의 UI 요소");
        System.out.println(" 지도 응용: 같은 타입의 마커를 표시하는 다수의 위치");
        System.out.println(" 채팅 응용: 동일한 사용자가 보낸 다수의 메시지");

        System.out.println("\n=== 사용 시 고려사항 ===");
        System.out.println("⚠️  내재적/외재적 상태 구분이 명확해야 함");
        System.out.println("⚠️  객체의 수가 충분히 많아야 효과적");
        System.out.println("⚠️  공유 객체는 불변(immutable)이어야 함");
        System.out.println("⚠️  외재적 상태 전달로 인한 메서드 호출 비용 고려");

        /*
         * Flyweight 패턴의 비즈니스 가치:
         *
         * 1. 비용 절감:
         *    - 서버 메모리 사용량 감소로 하드웨어 비용 절약
         *    - 가비지 컬렉션 빈도 감소로 성능 향상
         *
         * 2. 확장성:
         *    - 대량의 객체를 다루는 시스템에서 안정적 동작
         *    - 메모리 제한 환경에서의 서비스 연속성 보장
         *
         * 3. 사용자 경험:
         *    - 메모리 부족으로 인한 시스템 다운 방지
         *    - 응답 속도 개선으로 사용자 만족도 향상
         *
         * 4. 환경 친화성:
         *    - 서버 자원 효율적 사용으로 에너지 절약
         *    - 클라우드 비용 최적화
         */
    }
}