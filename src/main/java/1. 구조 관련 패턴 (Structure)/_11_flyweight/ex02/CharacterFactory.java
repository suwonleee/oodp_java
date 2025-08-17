package org._11_flyweight.ex02;

import java.util.HashMap;
import java.util.Map;

/**
 * CharacterFactory - 문자 Flyweight 팩토리 (FlyweightFactory)
 *
 * Flyweight 패턴에서 Factory 역할을 담당하는 클래스입니다.
 * Character Flyweight들의 생성과 공유를 관리하여 텍스트 에디터의
 * 메모리 효율성을 극대화합니다.
 *
 * 텍스트 에디터에서의 중요성:
 * - 문서에 수만 개의 문자가 있을 수 있음
 * - 각 문자마다 객체를 생성하면 메모리 부족
 * - 동일한 스타일의 문자들은 하나의 객체로 공유
 * - 예: "안녕하세요"에서 '안', '녕', '하', '세', '요' 각각 하나의 Flyweight
 *
 * 캐시 키 전략:
 * 내재적 상태들(symbol, font, size, color)을 조합하여 고유 키 생성
 */
class CharacterFactory {
    /**
     * Character Flyweight 인스턴스들을 저장하는 캐시
     *
     * - Key: 내재적 상태 조합을 나타내는 문자열 키
     * - Value: Character Flyweight 인스턴스
     *
     * 키 형식: "symbol|font|size|color" (예: "A|Arial|12|Black")
     */
    private static final Map<String, Character> characters = new HashMap<>();

    /**
     * 싱글톤 인스턴스
     *
     * 애플리케이션 전체에서 하나의 팩토리만 존재하여
     * 모든 Character Flyweight들을 중앙 집중식으로 관리합니다.
     */
    private static CharacterFactory instance;

    /**
     * private 생성자 (싱글톤 패턴)
     */
    private CharacterFactory() {}

    /**
     * 싱글톤 인스턴스 획득
     *
     * @return CharacterFactory의 유일한 인스턴스
     */
    public static CharacterFactory getInstance() {
        if (instance == null) {
            instance = new CharacterFactory();
        }
        return instance;
    }

    /**
     * Character Flyweight 인스턴스 획득
     *
     * @param symbol 문자
     * @param font 폰트명
     * @param size 폰트 크기
     * @param color 문자 색상
     * @return 해당 속성의 Character 인스턴스
     *
     * Flyweight 패턴의 핵심 로직:
     * 1. 내재적 상태 조합으로 키 생성
     * 2. 캐시에서 기존 인스턴스 검색
     * 3. 존재하면 기존 인스턴스 반환 (메모리 절약)
     * 4. 존재하지 않으면 새 인스턴스 생성 후 캐시에 저장
     */
    public Character getCharacter(char symbol, String font, int size, String color) {
        // 내재적 상태들을 조합하여 고유 키 생성
        String key = createKey(symbol, font, size, color);

        // 캐시에서 기존 인스턴스 확인
        Character character = characters.get(key);

        if (character == null) {
            // 기존 인스턴스가 없으면 새로 생성
            character = new Character(symbol, font, size, color);
            characters.put(key, character);

            System.out.printf("Factory: Created new flyweight for key: %s\n", key);
            System.out.println("Factory: Total character flyweights: " + characters.size());
        } else {
            System.out.printf("Factory: Reusing existing flyweight for: '%c'\n", symbol);
        }

        return character;

        /*
         * 메모리 효율성 예시:
         *
         * 1000자 문서에서 'A' 문자가 100번 나타나는 경우:
         * - 일반적인 방법: 100개의 Character 객체 생성
         * - Flyweight 방법: 1개의 Character 객체만 생성하여 공유
         *
         * 메모리 절약량 = 99 × Character_객체_크기
         */
    }

    /**
     * 내재적 상태들로부터 캐시 키 생성
     *
     * @param symbol 문자
     * @param font 폰트명
     * @param size 폰트 크기
     * @param color 문자 색상
     * @return 고유 캐시 키
     */
    private String createKey(char symbol, String font, int size, String color) {
        return symbol + "|" + font + "|" + size + "|" + color;
    }

    /**
     * 특정 스타일의 Character Flyweight 획득 (편의 메서드)
     *
     * @param symbol 문자
     * @param style 미리 정의된 스타일명
     * @return 해당 스타일의 Character 인스턴스
     */
    public Character getCharacterWithStyle(char symbol, String style) {
        return switch (style.toLowerCase()) {
            case "title" -> getCharacter(symbol, "Arial", 18, "Black");
            case "heading" -> getCharacter(symbol, "Times New Roman", 14, "Blue");
            case "body" -> getCharacter(symbol, "Arial", 12, "Black");
            case "caption" -> getCharacter(symbol, "Arial", 10, "Gray");
            case "highlight" -> getCharacter(symbol, "Arial", 12, "Red");
            default -> getCharacter(symbol, "Arial", 12, "Black"); // 기본 스타일
        };
    }

    /**
     * 현재 캐시된 Flyweight 개수 반환
     *
     * @return 생성된 Character Flyweight의 총 개수
     */
    public int getFlyweightCount() {
        return characters.size();
    }

    /**
     * 메모리 사용 통계 출력
     */
    public void displayMemoryStatistics() {
        System.out.println("=== Character Flyweight Memory Statistics ===");
        System.out.println("Total unique character flyweights: " + characters.size());

        // 폰트별 통계
        Map<String, Integer> fontStats = new HashMap<>();
        Map<String, Integer> colorStats = new HashMap<>();
        Map<Integer, Integer> sizeStats = new HashMap<>();

        for (Character ch : characters.values()) {
            fontStats.merge(ch.getFont(), 1, Integer::sum);
            colorStats.merge(ch.getColor(), 1, Integer::sum);
            sizeStats.merge(ch.getSize(), 1, Integer::sum);
        }

        System.out.println("Font distribution: " + fontStats);
        System.out.println("Color distribution: " + colorStats);
        System.out.println("Size distribution: " + sizeStats);
        System.out.println("============================================");
    }

    /**
     * 캐시에 저장된 모든 Character 정보 출력 (디버깅용)
     */
    public void displayCachedCharacters() {
        System.out.println("=== Cached Character Flyweights ===");

        if (characters.isEmpty()) {
            System.out.println("No characters cached yet.");
        } else {
            for (Map.Entry<String, Character> entry : characters.entrySet()) {
                String key = entry.getKey();
                Character character = entry.getValue();
                System.out.println("Key: " + key + " | " + character.getIdentity());
            }
        }

        System.out.println("Total flyweights: " + characters.size());
        System.out.println("==================================");
    }

    /**
     * 특정 조건의 Character들 검색
     *
     * @param font 검색할 폰트명
     * @param size 검색할 폰트 크기
     * @return 조건에 맞는 Character 개수
     */
    public int countCharactersWithStyle(String font, int size) {
        int count = 0;
        for (Character ch : characters.values()) {
            if (ch.getFont().equals(font) && ch.getSize() == size) {
                count++;
            }
        }
        return count;
    }

    /**
     * 캐시 최적화 - 사용되지 않는 Flyweight 정리
     *
     * 실제 환경에서는 메모리 압박 시 사용빈도가 낮은
     * Flyweight들을 제거할 수 있습니다.
     *
     * 주의: 이 메서드는 신중하게 사용해야 합니다.
     * 아직 참조되고 있는 Flyweight를 제거하면 문제가 발생할 수 있습니다.
     */
    public void optimizeCache() {
        int sizeBefore = characters.size();

        // 예시: 특정 조건의 Flyweight들 제거
        // 실제로는 사용 빈도, 마지막 접근 시간 등을 고려해야 함
        characters.entrySet().removeIf(entry -> {
            Character ch = entry.getValue();
            // 예: 크기가 너무 크거나 특별한 색상인 문자들 제거
            return ch.getSize() > 20 || "Yellow".equals(ch.getColor());
        });

        int sizeAfter = characters.size();
        System.out.printf("Cache optimized: %d -> %d flyweights (removed %d)\n",
                sizeBefore, sizeAfter, sizeBefore - sizeAfter);
    }

    /**
     * 전체 캐시 클리어
     *
     * 모든 Character Flyweight를 제거합니다.
     * 메모리 정리나 애플리케이션 재시작 시 사용할 수 있습니다.
     */
    public void clearCache() {
        characters.clear();
        System.out.println("Factory: All character flyweights cleared from cache");
    }
}