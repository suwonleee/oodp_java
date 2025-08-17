package org._11_flyweight.ex02;

import java.util.ArrayList;
import java.util.List;

/**
 * TextDocument - 텍스트 문서 클래스 (Context/Client)
 *
 * Flyweight 패턴에서 Context 역할을 담당하는 클래스입니다.
 * Character Flyweight들을 사용하여 실제 텍스트 문서를 구현하며,
 * 각 문자의 외재적 상태(위치, 컨텍스트)를 관리합니다.
 *
 * 텍스트 에디터의 핵심 기능:
 * - 문자들을 순서대로 배치하여 문서 구성
 * - 각 문자의 위치와 스타일 정보 관리
 * - Flyweight를 활용한 메모리 효율적 문서 저장
 *
 * 외재적 상태 관리:
 * - 위치 정보 (line, column, position)
 * - 컨텍스트 정보 (문서명, 섹션 등)
 */
class TextDocument {
    /**
     * 문서의 제목
     */
    private String title;

    /**
     * 문서를 구성하는 문자 요소들의 리스트
     *
     * 각 CharacterElement는 다음을 포함합니다:
     * - Character Flyweight에 대한 참조 (내재적 상태)
     * - 외재적 상태 (위치, 줄번호, 컬럼번호 등)
     */
    private List<CharacterElement> characters;

    /**
     * Character Flyweight를 제공하는 팩토리
     */
    private CharacterFactory factory;

    /**
     * 문서 생성자
     *
     * @param title 문서 제목
     */
    public TextDocument(String title) {
        this.title = title;
        this.characters = new ArrayList<>();
        this.factory = CharacterFactory.getInstance();
    }

    /**
     * 문서에 문자 추가 (기본 스타일)
     *
     * @param symbol 추가할 문자
     */
    public void addCharacter(char symbol) {
        addCharacter(symbol, "Arial", 12, "Black");
    }

    /**
     * 문서에 문자 추가 (스타일 지정)
     *
     * @param symbol 추가할 문자
     * @param font 폰트명
     * @param size 폰트 크기
     * @param color 문자 색상
     */
    public void addCharacter(char symbol, String font, int size, String color) {
        // 1. Factory에서 Character Flyweight 획득
        Character character = factory.getCharacter(symbol, font, size, color);

        // 2. 현재 위치 계산 (외재적 상태)
        int position = characters.size();
        int line = calculateLine(position);
        int column = calculateColumn(position);

        // 3. 외재적 상태와 함께 문서에 추가
        CharacterElement element = new CharacterElement(character, position, line, column);
        characters.add(element);

        System.out.printf("Added '%c' at position %d (Line %d, Col %d)\n",
                symbol, position, line, column);
    }

    /**
     * 미리 정의된 스타일로 문자 추가
     *
     * @param symbol 추가할 문자
     * @param style 스타일명 ("title", "heading", "body", "caption", "highlight")
     */
    public void addCharacterWithStyle(char symbol, String style) {
        Character character = factory.getCharacterWithStyle(symbol, style);

        int position = characters.size();
        int line = calculateLine(position);
        int column = calculateColumn(position);

        CharacterElement element = new CharacterElement(character, position, line, column);
        characters.add(element);

        System.out.printf("Added '%c' with style '%s' at position %d\n",
                symbol, style, position);
    }

    /**
     * 문자열을 문서에 추가
     *
     * @param text 추가할 문자열
     * @param font 폰트명
     * @param size 폰트 크기
     * @param color 문자 색상
     */
    public void addText(String text, String font, int size, String color) {
        System.out.printf("Adding text: \"%s\" [%s %dpt %s]\n", text, font, size, color);

        for (char c : text.toCharArray()) {
            if (c != ' ') { // 공백은 건너뛰고 실제 문자만 추가
                addCharacter(c, font, size, color);
            } else {
                // 공백도 위치 계산에 포함
                int position = characters.size();
                characters.add(null); // 공백을 나타내는 플레이스홀더
            }
        }

        System.out.println("Text addition completed.");
    }

    /**
     * 전체 문서 렌더링
     *
     * 모든 문자들을 순서대로 렌더링하여 문서를 화면에 표시합니다.
     * 각 Character Flyweight의 render 메서드를 외재적 상태와 함께 호출합니다.
     */
    public void renderDocument() {
        System.out.println("=== Rendering Document: " + title + " ===");

        if (characters.isEmpty()) {
            System.out.println("Document is empty.");
        } else {
            for (CharacterElement element : characters) {
                if (element != null) { // null은 공백
                    // 화면 좌표 계산 (외재적 상태)
                    int x = element.column * 10; // 가정: 문자당 10픽셀 너비
                    int y = element.line * 20;    // 가정: 줄당 20픽셀 높이

                    // Character Flyweight의 render 메서드 호출
                    element.character.render(x, y, title);
                }
            }
        }

        System.out.println("======================================");

        /*
         * 렌더링 과정에서의 Flyweight 활용:
         * 1. 각 CharacterElement가 Character Flyweight 참조
         * 2. 동일한 문자들은 같은 Flyweight 인스턴스 공유
         * 3. 외재적 상태(위치)는 개별적으로 관리
         * 4. 렌더링 시 내재적 상태 + 외재적 상태 조합 사용
         */
    }

    /**
     * 문서의 특정 범위 렌더링
     *
     * @param startPos 시작 위치
     * @param endPos 끝 위치
     */
    public void renderRange(int startPos, int endPos) {
        System.out.printf("=== Rendering Document Range: %d to %d ===\n", startPos, endPos);

        for (int i = Math.max(0, startPos); i <= Math.min(characters.size() - 1, endPos); i++) {
            CharacterElement element = characters.get(i);
            if (element != null) {
                int x = element.column * 10;
                int y = element.line * 20;
                element.character.render(x, y, title + " (partial)");
            }
        }

        System.out.println("=======================================");
    }

    /**
     * 문서 통계 정보 출력
     */
    public void displayStatistics() {
        System.out.println("=== Document Statistics ===");
        System.out.println("Title: " + title);
        System.out.println("Total characters: " + characters.size());
        System.out.println("Unique character flyweights: " + factory.getFlyweightCount());

        if (characters.size() > 0) {
            double efficiency = (double) factory.getFlyweightCount() / characters.size();
            System.out.printf("Memory efficiency ratio: %.3f (lower is better)\n", efficiency);

            int savedInstances = characters.size() - factory.getFlyweightCount();
            System.out.println("Character instances saved: " + savedInstances);

            // 메모리 절약 퍼센트 계산
            double savedPercent = (double) savedInstances / characters.size() * 100;
            System.out.printf("Memory saved: %.1f%%\n", savedPercent);
        }

        System.out.println("===========================");
    }

    /**
     * 특정 문자의 모든 출현 위치 찾기
     *
     * @param symbol 찾을 문자
     */
    public void findCharacterPositions(char symbol) {
        System.out.printf("=== Positions of character '%c' ===\n", symbol);

        boolean found = false;
        for (int i = 0; i < characters.size(); i++) {
            CharacterElement element = characters.get(i);
            if (element != null && element.character.getSymbol() == symbol) {
                System.out.printf("Position %d: Line %d, Column %d | %s\n",
                        i, element.line, element.column,
                        element.character.getStyleInfo());
                found = true;
            }
        }

        if (!found) {
            System.out.printf("Character '%c' not found in document.\n", symbol);
        }

        System.out.println("===================================");
    }

    /**
     * 줄 번호 계산 (외재적 상태)
     *
     * @param position 문서 내 위치
     * @return 줄 번호
     */
    private int calculateLine(int position) {
        // 간단한 구현: 80자마다 새 줄로 가정
        return position / 80;
    }

    /**
     * 컬럼 번호 계산 (외재적 상태)
     *
     * @param position 문서 내 위치
     * @return 컬럼 번호
     */
    private int calculateColumn(int position) {
        // 간단한 구현: 줄 내에서의 위치
        return position % 80;
    }

    /**
     * 문서의 텍스트 내용을 문자열로 반환
     *
     * @return 문서의 전체 텍스트 내용
     */
    public String getTextContent() {
        StringBuilder content = new StringBuilder();

        for (CharacterElement element : characters) {
            if (element != null) {
                content.append(element.character.getSymbol());
            } else {
                content.append(' '); // 공백 추가
            }
        }

        return content.toString();
    }

    /**
     * CharacterElement 내부 클래스
     *
     * 외재적 상태를 관리하는 클래스로, Character Flyweight에 대한 참조와
     * 함께 각 문자의 위치 정보를 저장합니다.
     */
    private static class CharacterElement {
        /**
         * Character Flyweight에 대한 참조 (내재적 상태)
         */
        private Character character;

        /**
         * 외재적 상태들
         */
        private int position;    // 문서 내 절대 위치
        private int line;        // 줄 번호
        private int column;      // 컬럼 번호

        /**
         * CharacterElement 생성자
         *
         * @param character Character Flyweight 인스턴스
         * @param position 문서 내 위치 (외재적 상태)
         * @param line 줄 번호 (외재적 상태)
         * @param column 컬럼 번호 (외재적 상태)
         */
        public CharacterElement(Character character, int position, int line, int column) {
            this.character = character;
            this.position = position;
            this.line = line;
            this.column = column;
        }

        /**
         * 요소 정보 반환
         *
         * @return 문자 요소의 상세 정보
         */
        public String getElementInfo() {
            return String.format("Element[pos=%d, line=%d, col=%d]: %s",
                    position, line, column, character.getIdentity());
        }
    }
}