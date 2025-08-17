package org._11_flyweight.ex01;

/**
 * Book - Flyweight 패턴의 핵심 클래스 (Flyweight)
 *
 * Flyweight 패턴에서 실제 Flyweight 역할을 담당하는 클래스입니다.
 * 메모리 효율성을 위해 내재적 상태(intrinsic state)만을 저장하고,
 * 외재적 상태(extrinsic state)는 매개변수로 받아 처리합니다.
 *
 * Flyweight 패턴의 핵심 개념:
 * - 내재적 상태(intrinsic): 여러 객체가 공유할 수 있는 불변 데이터 (제목)
 * - 외재적 상태(extrinsic): 각 사용 맥락마다 다른 가변 데이터 (사용자, 위치 등)
 *
 * 이 클래스의 인스턴스들은 Factory에 의해 관리되며,
 * 동일한 제목을 가진 책은 하나의 인스턴스만 생성되어 공유됩니다.
 */
class Book {
    /**
     * 내재적 상태 (Intrinsic State)
     *
     * - final로 선언하여 불변성 보장
     * - 여러 컨텍스트에서 공유 가능한 데이터
     * - 책의 제목은 변하지 않으므로 내재적 상태로 적합
     * - 메모리에서 한 번만 저장되어 공유됨
     */
    private final String title;

    /**
     * Book 생성자
     *
     * @param title 책 제목 (내재적 상태)
     *
     * 주의: 이 생성자는 일반적으로 클라이언트가 직접 호출하지 않습니다.
     * 대신 BookFactory를 통해 인스턴스를 얻어야 합니다.
     * Factory가 인스턴스 생성과 공유를 관리합니다.
     */
    public Book(String title) {
        this.title = title;
        // 실제 환경에서는 여기에 책 로딩 등의 무거운 작업이 있을 수 있음
        System.out.println("Creating book flyweight for: " + title);
    }

    /**
     * 책 읽기 동작 수행
     *
     * Flyweight 패턴의 핵심 메서드입니다.
     * 내재적 상태(title)는 객체 내부에서 사용하고,
     * 외재적 상태는 매개변수로 받아 처리해야 합니다.
     *
     * 현재 예제에서는 외재적 상태를 사용하지 않지만,
     * 실제 환경에서는 사용자 정보, 읽기 위치, 폰트 크기 등을
     * 매개변수로 받을 수 있습니다.
     */
    public void read() {
        System.out.println("Reading the book titled: " + title);

        /*
         * Flyweight의 메모리 효율성:
         *
         * 만약 같은 제목의 책을 1000명이 읽는다면:
         * - 일반적인 방법: 1000개의 Book 객체 생성 (메모리 낭비)
         * - Flyweight 방법: 1개의 Book 객체만 생성하여 공유 (메모리 절약)
         *
         * 외재적 상태 예시 (매개변수로 전달):
         * - 현재 읽고 있는 페이지 번호
         * - 읽고 있는 사용자 정보
         * - 읽기 설정 (폰트 크기, 테마 등)
         */
    }

    /**
     * 외재적 상태를 매개변수로 받는 읽기 메서드 (확장 예시)
     *
     * @param user 읽고 있는 사용자 (외재적 상태)
     * @param page 현재 페이지 (외재적 상태)
     */
    public void readWithContext(String user, int page) {
        System.out.println("User " + user + " is reading '" + title +
                "' at page " + page);

        /*
         * 이 메서드는 Flyweight 패턴의 올바른 사용법을 보여줍니다:
         * 1. 내재적 상태(title)는 객체 내부에서 사용
         * 2. 외재적 상태(user, page)는 매개변수로 받아 사용
         * 3. 동일한 Book 객체가 다양한 컨텍스트에서 재사용됨
         */
    }

    /**
     * 객체 식별을 위한 디버깅 메서드
     *
     * Flyweight가 제대로 공유되고 있는지 확인할 때 유용합니다.
     *
     * @return 객체의 해시코드와 제목
     */
    public String getIdentity() {
        return "Book@" + Integer.toHexString(hashCode()) + "[" + title + "]";
    }
}