package org._11_flyweight.ex02;

/**
 * Character - 문자 Flyweight 클래스 (Flyweight)
 *
 * Flyweight 패턴에서 실제 Flyweight 역할을 담당하는 클래스입니다.
 * 텍스트 에디터에서 문자를 표현하는 객체로, 메모리 효율성을 위해
 * 문자의 내재적 속성(글자, 폰트, 크기 등)만을 저장합니다.
 *
 * 텍스트 에디터의 메모리 최적화:
 * - 문서에 같은 문자가 수천 번 나타날 수 있음
 * - 각 문자마다 객체를 생성하면 메모리 낭비
 * - 동일한 속성을 가진 문자들은 하나의 객체를 공유
 *
 * 내재적 상태 vs 외재적 상태:
 * - 내재적: 문자, 폰트, 크기, 색상 (Character 객체에 저장)
 * - 외재적: 위치, 문맥 (매개변수로 전달)
 */
class Character {
    /**
     * 내재적 상태들 (Intrinsic State)
     *
     * 이 속성들은 여러 컨텍스트에서 공유될 수 있는 불변 데이터입니다.
     * final로 선언하여 생성 후 변경되지 않음을 보장합니다.
     */
    private final char symbol;      // 실제 문자 ('A', 'B', '가', '나' 등)
    private final String font;      // 폰트명 ("Arial", "Times New Roman" 등)
    private final int size;         // 폰트 크기 (12, 14, 16 등)
    private final String color;     // 문자 색상 ("Black", "Red", "Blue" 등)

    /**
     * Character Flyweight 생성자
     *
     * @param symbol 문자
     * @param font 폰트명
     * @param size 폰트 크기
     * @param color 문자 색상
     *
     * 주의: 이 생성자는 CharacterFactory에서만 호출되어야 합니다.
     * 클라이언트는 팩토리를 통해서만 인스턴스를 얻어야 합니다.
     */
    public Character(char symbol, String font, int size, String color) {
        this.symbol = symbol;
        this.font = font;
        this.size = size;
        this.color = color;

        // 실제 환경에서는 폰트 로딩, 렌더링 준비 등의 무거운 작업이 있을 수 있음
        System.out.printf("Creating character flyweight: '%c' [%s, %dpt, %s]\n",
                symbol, font, size, color);
    }

    /**
     * 문자 렌더링 수행 (외재적 상태를 매개변수로 받음)
     *
     * @param x X 좌표 (외재적 상태)
     * @param y Y 좌표 (외재적 상태)
     * @param context 렌더링 컨텍스트 (외재적 상태)
     *
     * Flyweight 패턴의 핵심 메서드입니다.
     * 내재적 상태는 객체 내부에서 사용하고,
     * 외재적 상태는 매개변수로 받아 처리합니다.
     */
    public void render(int x, int y, String context) {
        System.out.printf("Rendering '%c' at (%d, %d) | Font: %s %dpt %s | Context: %s\n",
                symbol, x, y, font, size, color, context);

        /*
         * 실제 텍스트 에디터에서는 여기서:
         * 1. 화면 좌표 계산
         * 2. 폰트 렌더링
         * 3. 안티앨리어싱 적용
         * 4. 화면에 픽셀 그리기
         *
         * 내재적 상태 활용:
         * - symbol: 렌더링할 실제 문자
         * - font, size, color: 렌더링 스타일 적용
         *
         * 외재적 상태 활용:
         * - x, y: 화면상 렌더링 위치
         * - context: 문서명, 페이지, 단락 등 컨텍스트 정보
         */
    }

    /**
     * 문자의 크기 정보 계산 (외재적 상태 고려)
     *
     * @param zoom 확대/축소 비율 (외재적 상태)
     * @return 계산된 문자 크기 정보
     */
    public String calculateSize(double zoom) {
        int actualSize = (int) (size * zoom);
        return String.format("'%c' will be rendered at %dpt (base: %dpt, zoom: %.1fx)",
                symbol, actualSize, size, zoom);
    }

    /**
     * 문자 스타일 정보 반환
     *
     * @return 내재적 상태 정보 문자열
     */
    public String getStyleInfo() {
        return String.format("Character '%c': %s %dpt %s", symbol, font, size, color);
    }

    /**
     * 특정 외재적 상태와 함께 문자 정보 출력
     *
     * @param position 문서 내 위치 (외재적 상태)
     * @param line 줄 번호 (외재적 상태)
     * @param column 열 번호 (외재적 상태)
     */
    public void displayWithPosition(int position, int line, int column) {
        System.out.printf("Position %d (Line %d, Col %d): %s\n",
                position, line, column, getStyleInfo());

        /*
         * 이 메서드는 Flyweight가 다양한 외재적 상태와 함께
         * 어떻게 사용될 수 있는지 보여줍니다:
         *
         * 동일한 Character 객체가:
         * - 문서의 서로 다른 위치에서 사용
         * - 각각 독립적인 위치 정보 유지
         * - 하나의 공유 객체로 모든 위치 처리
         */
    }

    /**
     * 객체 식별 정보 반환 (디버깅용)
     *
     * @return 객체 해시코드와 내재적 상태 정보
     */
    public String getIdentity() {
        return String.format("Character@%s['%c',%s,%d,%s]",
                Integer.toHexString(hashCode()), symbol, font, size, color);
    }

    // Getter 메서드들 (필요시 외부에서 내재적 상태 접근용)
    public char getSymbol() { return symbol; }
    public String getFont() { return font; }
    public int getSize() { return size; }
    public String getColor() { return color; }

    /**
     * 동일성 검사를 위한 equals 구현
     *
     * 동일한 내재적 상태를 가진 Character들이 같은 객체로 인식되도록 합니다.
     * Factory에서 중복 생성을 방지하는 데 사용됩니다.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Character character = (Character) obj;
        return symbol == character.symbol &&
                size == character.size &&
                font.equals(character.font) &&
                color.equals(character.color);
    }

    /**
     * 해시코드 구현
     *
     * equals와 함께 사용되어 HashMap 등에서 올바른 키 비교를 보장합니다.
     */
    @Override
    public int hashCode() {
        int result = symbol;
        result = 31 * result + font.hashCode();
        result = 31 * result + size;
        result = 31 * result + color.hashCode();
        return result;
    }
}