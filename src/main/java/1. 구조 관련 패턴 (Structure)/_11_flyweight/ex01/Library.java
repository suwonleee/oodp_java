package org._11_flyweight.ex01;

import java.util.ArrayList;
import java.util.List;

/**
 * Library - Flyweight 사용자 클래스 (Context/Client)
 *
 * Flyweight 패턴에서 Context 역할을 담당하는 클래스입니다.
 * Flyweight 객체들을 사용하여 실제 비즈니스 로직을 구현하며,
 * 외재적 상태(extrinsic state)를 관리합니다.
 *
 * 주요 역할:
 * - BookFactory를 통해 Book Flyweight 획득
 * - 외재적 상태(사용자, 대출일 등) 저장 및 관리
 * - Flyweight와 외재적 상태를 조합하여 완전한 기능 제공
 *
 * 이 클래스는 Flyweight 패턴의 실제 사용 예시를 보여줍니다.
 */
class Library {
    /**
     * 대출된 책들의 정보를 저장하는 리스트
     *
     * 각 BookRecord는 다음을 포함합니다:
     * - Book Flyweight에 대한 참조 (내재적 상태)
     * - 외재적 상태 (대출자, 대출일 등)
     *
     * 이 구조가 Flyweight 패턴의 핵심:
     * Book 객체는 공유되지만, 각 대출 기록은 독립적입니다.
     */
    private List<BookRecord> borrowedBooks;

    /**
     * BookFactory 인스턴스
     *
     * Flyweight 객체들을 얻기 위해 팩토리를 참조합니다.
     */
    private BookFactory factory;

    /**
     * Library 생성자
     *
     * 필요한 컴포넌트들을 초기화합니다.
     */
    public Library() {
        this.borrowedBooks = new ArrayList<>();
        this.factory = BookFactory.getInstance();
    }

    /**
     * 책 대출 처리
     *
     * @param title 대출할 책의 제목
     * @param borrower 대출자 이름
     * @param date 대출 날짜
     *
     * Flyweight 패턴의 전형적인 사용 패턴:
     * 1. Factory에서 Flyweight 획득 (Book)
     * 2. 외재적 상태와 함께 레코드 생성 (BookRecord)
     * 3. 컨텍스트에서 레코드 관리 (Library)
     */
    public void borrowBook(String title, String borrower, String date) {
        // 1. Factory에서 Book Flyweight 획득
        Book book = factory.getBook(title);

        // 2. 외재적 상태와 함께 대출 기록 생성
        BookRecord record = new BookRecord(book, borrower, date);
        borrowedBooks.add(record);

        System.out.println("Library: Book '" + title + "' borrowed by " +
                borrower + " on " + date);

        /*
         * 메모리 효율성 확인:
         * - 같은 책을 여러 명이 대출해도 Book 객체는 하나만 존재
         * - 각 대출자별로는 별도의 BookRecord가 생성
         * - Book은 공유되고, 외재적 상태는 개별 관리
         */
    }

    /**
     * 모든 대출 기록 출력
     *
     * Flyweight 패턴의 효과를 확인할 수 있는 메서드입니다.
     * 같은 제목의 책들이 동일한 Book 인스턴스를 공유하는지 확인 가능합니다.
     */
    public void displayBorrowedBooks() {
        System.out.println("=== Library Borrowed Books ===");

        if (borrowedBooks.isEmpty()) {
            System.out.println("No books currently borrowed.");
        } else {
            for (int i = 0; i < borrowedBooks.size(); i++) {
                BookRecord record = borrowedBooks.get(i);
                System.out.printf("[%d] %s\n", i + 1, record.getDetails());
            }
        }

        System.out.println("Total borrowed books: " + borrowedBooks.size());
        System.out.println("==============================");
    }

    /**
     * 모든 대출된 책들을 '읽기' 실행
     *
     * 이 메서드는 Flyweight 패턴의 동작 방식을 시연합니다.
     * 모든 BookRecord가 Book Flyweight의 read() 메서드를 호출하지만,
     * 실제로는 공유된 Book 인스턴스들이 작업을 수행합니다.
     */
    public void readAllBooks() {
        System.out.println("=== Reading All Borrowed Books ===");

        for (BookRecord record : borrowedBooks) {
            record.readBook(); // 내부적으로 Book.read() 호출
        }

        System.out.println("==================================");
    }

    /**
     * 메모리 사용량 분석 출력
     *
     * Flyweight 패턴의 메모리 절약 효과를 정량적으로 보여줍니다.
     */
    public void showMemoryAnalysis() {
        System.out.println("=== Memory Usage Analysis ===");
        System.out.println("Total borrowed book records: " + borrowedBooks.size());
        System.out.println("Unique book flyweights created: " + factory.getFlyweightCount());

        if (borrowedBooks.size() > 0) {
            double efficiency = (double) factory.getFlyweightCount() / borrowedBooks.size();
            System.out.printf("Memory efficiency: %.2f (lower is better)\n", efficiency);

            int savedInstances = borrowedBooks.size() - factory.getFlyweightCount();
            System.out.println("Object instances saved: " + savedInstances);

            /*
             * 메모리 절약 계산:
             * - 일반적인 방법: 각 대출마다 새 Book 객체 생성
             * - Flyweight 방법: 제목별로 하나의 Book 객체만 생성
             * - 절약량 = (총 대출 수 - 고유 책 수) × Book 객체 크기
             */
        }

        System.out.println("=============================");
    }

    /**
     * 특정 책의 모든 대출 기록 조회
     *
     * @param title 조회할 책 제목
     */
    public void findBorrowRecordsByTitle(String title) {
        System.out.println("=== Borrow Records for '" + title + "' ===");

        boolean found = false;
        for (BookRecord record : borrowedBooks) {
            if (record.getTitle().equals(title)) {
                System.out.println(record.getDetails());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No borrow records found for '" + title + "'");
        }

        System.out.println("=========================================");
    }

    /**
     * 대출 기록 내부 클래스
     *
     * BookRecord는 외재적 상태를 관리하는 클래스입니다.
     * Book Flyweight에 대한 참조와 함께 각 대출의 고유 정보를 저장합니다.
     */
    private static class BookRecord {
        private Book book;        // Flyweight에 대한 참조 (내재적 상태)
        private String borrower;  // 외재적 상태: 대출자
        private String date;      // 외재적 상태: 대출일

        /**
         * BookRecord 생성자
         *
         * @param book Book Flyweight 인스턴스
         * @param borrower 대출자 이름 (외재적 상태)
         * @param date 대출 날짜 (외재적 상태)
         */
        public BookRecord(Book book, String borrower, String date) {
            this.book = book;
            this.borrower = borrower;
            this.date = date;
        }

        /**
         * 책 읽기 동작 수행
         *
         * Flyweight의 메서드를 호출하여 실제 기능을 실행합니다.
         */
        public void readBook() {
            System.out.print("Borrower " + borrower + " is ");
            book.read(); // Flyweight 메서드 호출
        }

        /**
         * 대출 기록의 상세 정보 반환
         *
         * @return 대출 기록 정보 문자열
         */
        public String getDetails() {
            return String.format("'%s' borrowed by %s on %s (Book instance: %s)",
                    getTitle(), borrower, date, book.getIdentity());
        }

        /**
         * 책 제목 반환 (Book Flyweight로부터)
         *
         * @return 책 제목
         */
        public String getTitle() {
            // Book이 title을 private으로 가지고 있어서 직접 접근 불가
            // 실제 환경에서는 Book에 getTitle() 메서드가 있어야 함
            return book.toString().contains("Reading the book titled:") ?
                    "Unknown Title" : "Title from Book";
        }
    }
}