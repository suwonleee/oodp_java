package org._03_template_method.ex02;

/**
 * Main 클래스 - Template Method 패턴 사용 예제 (데이터 처리)
 *
 * Template Method 패턴이 어떻게 복잡한 데이터 처리 파이프라인에서
 * 공통된 처리 흐름을 유지하면서도 각 데이터 형식별로 다른 세부 처리를
 * 허용하는지 보여주는 클라이언트 코드입니다.
 *
 * 이 예제는 실제 데이터 처리 시스템에서 자주 마주치는 시나리오로,
 * Template Method 패턴의 실용적 가치를 잘 보여줍니다.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Template Method 패턴을 활용한 데이터 처리 시스템 ===\n");

        // CSV 데이터 처리
        System.out.println("🔸 CSV 데이터 처리 시작 🔸");
        DataProcessor csvProcessor = new CSVDataProcessor();
        csvProcessor.processDataFile("sample_data.csv");

        // JSON 데이터 처리
        System.out.println("🔹 JSON 데이터 처리 시작 🔹");
        DataProcessor jsonProcessor = new JSONDataProcessor();
        jsonProcessor.processDataFile("sample_data.json");

        /*
         * Template Method 패턴의 실제 동작 분석:
         *
         * 1. 동일한 처리 파이프라인:
         *    - 모든 데이터 형식이 동일한 5단계 과정을 거침
         *    - processDataFile()이 전체 흐름을 제어
         *    - 읽기 → 파싱 → 검증 → 처리 → 저장 순서가 일관됨
         *
         * 2. 형식별 특화 구현:
         *    - readData(): CSV는 단순 텍스트, JSON은 UTF-8 보장
         *    - parseData(): CSV는 콤마 분할, JSON은 객체 구조 파싱
         *    - processData(): 각 형식의 특성에 맞는 비즈니스 로직
         *    - validateData(): 형식별 추가 검증 로직
         *
         * 3. 공통 로직 재사용:
         *    - validateData(): 기본 null 체크 등 공통 검증
         *    - saveResult(): 모든 형식에 동일한 저장 로직
         *
         * 4. 확장성과 일관성:
         *    - 새로운 데이터 형식 추가 시 일관된 처리 흐름 보장
         *    - 처리 단계 변경 시 모든 형식에 자동 적용
         */

        System.out.println("=== 패턴의 실제 효과 ===");
        System.out.println("✅ 모든 데이터 형식이 동일한 5단계 처리 과정을 따름");
        System.out.println("✅ 공통 로직(검증, 저장)은 중복 없이 재사용됨");
        System.out.println("✅ 각 형식의 고유한 특성(파싱, 처리)은 개별 최적화됨");
        System.out.println("✅ 새로운 형식(XML, YAML 등) 추가가 용이함");
        System.out.println("✅ 처리 파이프라인 변경 시 상위 클래스만 수정");

        /*
         * 실제 활용 사례와 확장 가능성:
         *
         * 1. 추가 가능한 데이터 형식:
         *    - XMLDataProcessor: XML 파싱 및 XPath 활용
         *    - YAMLDataProcessor: YAML 계층 구조 처리
         *    - ExcelDataProcessor: 스프레드시트 데이터 처리
         *    - ParquetDataProcessor: 컬럼형 데이터 최적 처리
         *
         * 2. 처리 파이프라인 확장:
         *    - 데이터 품질 검증 단계 추가
         *    - 실시간 모니터링 및 로깅
         *    - 에러 복구 및 재처리 로직
         *    - 병렬 처리 및 배치 처리 지원
         *
         * 3. 실제 시스템에서의 응용:
         *    - ETL(Extract, Transform, Load) 파이프라인
         *    - 데이터 마이그레이션 시스템
         *    - 로그 분석 시스템
         *    - API 데이터 통합 시스템
         *
         * Template Method vs Strategy 비교:
         * - Template Method: 전체 알고리즘 구조가 고정되어 있을 때
         * - Strategy: 전체 알고리즘을 교체하고 싶을 때
         *
         * 이 데이터 처리 예제에서는 "처리 파이프라인"이라는
         * 고정된 구조가 있으므로 Template Method가 적합합니다.
         */

        System.out.println("\n=== 확장성 시연 ===");
        System.out.println("💡 새로운 데이터 형식 추가 시나리오:");
        System.out.println("   1. DataProcessor를 상속받는 새 클래스 생성");
        System.out.println("   2. readData(), parseData(), processData() 구현");
        System.out.println("   3. 선택적으로 validateData() 오버라이드");
        System.out.println("   4. 기존 코드 수정 없이 동일한 파이프라인 적용!");
    }
}