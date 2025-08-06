package org._03_template_method.ex02;

/**
 * DataProcessor - 데이터 처리 템플릿 클래스
 *
 * Template Method 패턴의 AbstractClass 역할을 담당하는 추상 클래스입니다.
 * 모든 데이터 처리 시스템의 기본 알고리즘(읽기→파싱→검증→처리→저장)을
 * 정의하고, 데이터 형식별로 달라지는 세부 단계들은 하위 클래스에서
 * 구현하도록 합니다.
 *
 * 이 클래스는 다양한 데이터 형식(CSV, JSON, XML 등)을 처리할 때
 * 공통된 처리 흐름을 제공하면서도, 각 형식의 특성을 반영할 수 있도록
 * 설계되었습니다.
 */
abstract class DataProcessor {

    /**
     * Template Method - 데이터 처리의 전체 알고리즘을 정의
     *
     * 이 메서드는 Template Method 패턴의 핵심으로, 모든 데이터 처리의
     * 전체적인 흐름을 정의합니다. final로 선언하여 하위 클래스에서
     * 이 메서드를 오버라이드할 수 없도록 하여 알고리즘 구조를 보호합니다.
     *
     * 데이터 처리의 5단계 알고리즘:
     * 1. readData() - 데이터 읽기 (형식별로 다름)
     * 2. parseData() - 데이터 파싱 (형식별로 다름)
     * 3. validateData() - 데이터 검증 (공통 로직)
     * 4. processData() - 데이터 처리 (형식별로 다름)
     * 5. saveResult() - 결과 저장 (공통 로직)
     *
     * @param filePath 처리할 데이터 파일 경로
     */
    final void processDataFile(String filePath) {
        System.out.println("=== 데이터 처리 시작: " + filePath + " ===");

        // 1단계: 데이터 읽기 (형식별 구현)
        String rawData = readData(filePath);

        // 2단계: 데이터 파싱 (형식별 구현)
        Object parsedData = parseData(rawData);

        // 3단계: 데이터 검증 (공통 구현)
        if (validateData(parsedData)) {
            // 4단계: 데이터 처리 (형식별 구현)
            Object processedData = processData(parsedData);

            // 5단계: 결과 저장 (공통 구현)
            saveResult(processedData);
        } else {
            System.out.println("❌ 데이터 검증 실패: 처리 중단");
        }

        System.out.println("=== 데이터 처리 완료 ===\n");
    }

    /**
     * 데이터 읽기 - 형식별로 다른 추상 메서드
     *
     * 각 데이터 형식마다 파일을 읽는 방법이 다를 수 있으므로
     * (인코딩, 스트림 방식, 청크 단위 등) 추상 메서드로 정의하여
     * 하위 클래스에서 최적화된 읽기 방식을 구현하도록 합니다.
     *
     * @param filePath 읽을 파일 경로
     * @return 원본 데이터 문자열
     */
    abstract String readData(String filePath);

    /**
     * 데이터 파싱 - 형식별로 다른 추상 메서드
     *
     * 각 데이터 형식마다 파싱 방법이 완전히 다르므로
     * (CSV는 구분자 기반, JSON은 트리 구조 등) 추상 메서드로 정의하여
     * 하위 클래스에서 형식에 맞는 파서를 구현하도록 합니다.
     *
     * @param rawData 원본 데이터 문자열
     * @return 파싱된 데이터 객체
     */
    abstract Object parseData(String rawData);

    /**
     * 데이터 처리 - 형식별로 다른 추상 메서드
     *
     * 파싱된 데이터를 실제 비즈니스 로직에 따라 처리하는 단계입니다.
     * 데이터 형식에 따라 처리 방식이 다를 수 있으므로 추상 메서드로
     * 정의합니다.
     *
     * @param parsedData 파싱된 데이터
     * @return 처리된 데이터
     */
    abstract Object processData(Object parsedData);

    /**
     * 데이터 검증 - 모든 형식에 공통된 검증 로직
     *
     * 모든 데이터 형식에 적용될 수 있는 공통 검증 규칙을 구현합니다.
     * 하위 클래스에서 이 메서드를 오버라이드하여 추가 검증을
     * 수행할 수도 있습니다 (Hook Method의 성격도 가짐).
     *
     * @param data 검증할 데이터
     * @return 검증 통과 여부
     */
    boolean validateData(Object data) {
        System.out.println("📋 데이터 검증 중...");

        // 기본적인 공통 검증 로직
        if (data == null) {
            System.out.println("❌ 데이터가 null입니다.");
            return false;
        }

        // 실제 구현에서는 다음과 같은 공통 검증들이 포함될 수 있습니다:
        // - 데이터 크기 검증
        // - 필수 필드 존재 여부 확인
        // - 데이터 타입 검증
        // - 비즈니스 규칙 검증

        System.out.println("✅ 기본 검증 통과");
        return true;
    }

    /**
     * 결과 저장 - 모든 형식에 공통된 저장 로직
     *
     * 처리된 데이터를 저장하는 공통 로직을 구현합니다.
     * 모든 형식의 데이터가 동일한 방식으로 저장되므로
     * 상위 클래스에서 구현을 제공합니다.
     *
     * @param processedData 저장할 처리된 데이터
     */
    void saveResult(Object processedData) {
        System.out.println("💾 처리 결과 저장 중...");
        // 실제 구현에서는 다음과 같은 저장 로직들이 포함될 수 있습니다:
        // - 데이터베이스 저장
        // - 파일 시스템 저장
        // - 캐시 저장
        // - 원격 스토리지 저장
        // - 로그 기록
        System.out.println("✅ 저장 완료: " + processedData.getClass().getSimpleName());
    }
}