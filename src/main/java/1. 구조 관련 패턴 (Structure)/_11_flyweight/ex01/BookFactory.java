package org._11_flyweight.ex01;

import java.util.HashMap;
import java.util.Map;

/**
 * BookFactory - Flyweight 팩토리 클래스 (FlyweightFactory)
 *
 * Flyweight 패턴에서 Factory 역할을 담당하는 클래스입니다.
 * Flyweight 객체들의 생성과 공유를 관리하여 메모리 효율성을 달성합니다.
 *
 * 주요 책임:
 * - 기존 Flyweight 인스턴스의 존재 여부 확인
 * - 새로운 Flyweight 인스턴스 생성 (필요시에만)
 * - 생성된 인스턴스들의 저장 및 관리
 * - 클라이언트에게 적절한 Flyweight 인스턴스 제공
 *
 * 이 팩토리는 싱글톤 패턴과 함께 사용되어 전역적으로 하나의
 * 팩토리만 존재하도록 보장합니다.
 */
class BookFactory {
    /**
     * Flyweight 인스턴스들을 저장하는 캐시
     *
     * - Key: 책 제목 (내재적 상태를 식별하는 키)
     * - Value: Book 인스턴스 (실제 Flyweight 객체)
     *
     * 이 Map이 Flyweight 패턴의 핵심으로, 동일한 내재적 상태를
     * 가진 객체들이 중복 생성되지 않도록 보장합니다.
     */
    private static final Map<String, Book> books = new HashMap<>();

    /**
     * 싱글톤 인스턴스
     *
     * 애플리케이션 전체에서 하나의 BookFactory만 존재하도록 보장합니다.
     * 이를 통해 모든 Book Flyweight들이 중앙집중식으로 관리됩니다.
     */
    private static BookFactory instance;

    /**
     * private 생성자 (싱글톤 패턴)
     *
     * 외부에서 직접 인스턴스를 생성할 수 없도록 막아,
     * getInstance() 메서드를 통해서만 인스턴스에 접근하도록 합니다.
     */
    private BookFactory() {}

    /**
     * 싱글톤 인스턴스 획득
     *
     * @return BookFactory의 유일한 인스턴스
     *
     * Thread-safe하지 않은 간단한 구현입니다.
     * 프로덕션에서는 동기화 처리가 필요할 수 있습니다.
     */
    public static BookFactory getInstance() {
        if (instance == null) {
            instance = new BookFactory();
        }
        return instance;
    }

    /**
     * Book Flyweight 인스턴스 획득 (Flyweight Factory의 핵심 메서드)
     *
     * @param title 책 제목 (내재적 상태)
     * @return 해당 제목의 Book 인스턴스 (새로 생성되거나 기존 인스턴스)
     *
     * Flyweight 패턴의 핵심 로직:
     * 1. 캐시에서 기존 인스턴스 검색
     * 2. 존재하면 기존 인스턴스 반환 (메모리 절약)
     * 3. 존재하지 않으면 새 인스턴스 생성 후 캐시에 저장
     */
    public Book getBook(String title) {
        // 캐시에서 기존 인스턴스 확인
        Book book = books.get(title);

        if (book == null) {
            // 기존 인스턴스가 없으면 새로 생성
            book = new Book(title);
            books.put(title, book);

            System.out.println("Factory: Created new flyweight for '" + title + "'");
            System.out.println("Factory: Total flyweights created: " + books.size());
        } else {
            System.out.println("Factory: Reusing existing flyweight for '" + title + "'");
        }

        return book;

        /*
         * 메모리 효율성 분석:
         *
         * 예시: "해리포터"를 1000명이 읽는 경우
         * - 일반적인 방법: 1000개 객체 생성 (1000 * Book_객체_크기)
         * - Flyweight 방법: 1개 객체만 생성 (1 * Book_객체_크기)
         *
         * 메모리 절약량 = 999 * Book_객체_크기
         */
    }

    /**
     * 현재 캐시된 Flyweight 개수 반환
     *
     * @return 생성된 Book Flyweight의 총 개수
     *
     * 디버깅이나 메모리 사용량 모니터링에 유용합니다.
     */
    public int getFlyweightCount() {
        return books.size();
    }

    /**
     * 캐시에 저장된 모든 책 제목 출력 (디버깅용)
     *
     * 현재 메모리에 로드된 Flyweight들의 상태를 확인할 때 사용합니다.
     */
    public void displayCachedBooks() {
        System.out.println("=== Cached Books (Flyweights) ===");
        if (books.isEmpty()) {
            System.out.println("No books cached yet.");
        } else {
            for (Map.Entry<String, Book> entry : books.entrySet()) {
                String title = entry.getKey();
                Book book = entry.getValue();
                System.out.println("Title: " + title +
                                 " | Instance: " + book.getIdentity());
            }
        }
        System.out.println("Total flyweights: " + books.size());
        System.out.println("================================");
    }

    /**
     * 캐시 클리어 (메모리 정리용)
     *
     * 필요시 모든 Flyweight 인스턴스를 제거하여 메모리를 확보할 수 있습니다.
     * 일반적으로는 사용하지 않지만, 특정 상황에서 유용할 수 있습니다.
     */
    public void clearCache() {
        books.clear();
        System.out.println("Factory: All flyweights cleared from cache");
    }
}