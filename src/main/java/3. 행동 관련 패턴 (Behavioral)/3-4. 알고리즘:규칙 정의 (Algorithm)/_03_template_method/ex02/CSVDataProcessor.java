package org._03_template_method.ex02;

import java.util.Arrays;
import java.util.List;

/**
 * CSVDataProcessor - CSV 데이터 처리 구체 클래스
 *
 * Template Method 패턴의 ConcreteClass 역할을 담당하는 클래스입니다.
 * DataProcessor 추상 클래스를 상속받아 CSV 형식의 데이터를 처리하는
 * 구체적인 구현을 제공합니다.
 *
 * CSV(Comma-Separated Values)는 콤마로 구분된 값들로 이루어진
 * 간단한 테이블 형태의 데이터 형식으로, 주로 스프레드시트나
 * 데이터베이스에서 데이터를 내보낼 때 사용됩니다.
 */
class CSVDataProcessor extends DataProcessor {

    /**
     * CSV 데이터 읽기 구현
     *
     * CSV 파일의 특성에 맞는 읽기 방식을 구현합니다.
     * CSV는 텍스트 기반이므로 간단한 문자열 읽기 방식을 사용합니다.
     *
     * @param filePath CSV 파일 경로
     * @return 읽어온 CSV 데이터 문자열
     */
    @Override
    String readData(String filePath) {
        System.out.println("📂 CSV 파일 읽기: " + filePath);

        // 실제 구현에서는 다음과 같은 처리가 포함될 수 있습니다:
        // - 파일 인코딩 확인 (UTF-8, EUC-KR 등)
        // - 큰 파일의 경우 스트리밍 읽기
        // - BOM(Byte Order Mark) 처리
        // - 개행 문자 정규화

        // 예시 CSV 데이터 반환 (실제로는 파일에서 읽어옴)
        return "name,age,city\nJohn,25,New York\nJane,30,Los Angeles\nBob,35,Chicago";
    }

    /**
     * CSV 데이터 파싱 구현
     *
     * CSV 형식의 문자열을 구조화된 데이터로 변환합니다.
     * 콤마를 구분자로 사용하여 각 행과 열을 분리합니다.
     *
     * @param rawData 원본 CSV 문자열
     * @return 파싱된 CSV 데이터 (List of String arrays)
     */
    @Override
    Object parseData(String rawData) {
        System.out.println("🔍 CSV 데이터 파싱 중...");

        // CSV 파싱 로직 구현
        String[] lines = rawData.split("\n");
        List<String[]> parsedData = Arrays.stream(lines)
                .map(line -> line.split(","))
                .toList();

        System.out.println("✅ CSV 파싱 완료: " + parsedData.size() + "개 행");

        // 실제 구현에서는 다음과 같은 고급 처리가 포함될 수 있습니다:
        // - 따옴표 안의 콤마 처리 ("Smith, John" 형태)
        // - 이스케이프 문자 처리
        // - 다양한 구분자 지원 (세미콜론, 탭 등)
        // - 헤더 행 별도 처리
        // - 빈 셀 처리
        // - 데이터 타입 추론 및 변환

        return parsedData;
    }

    /**
     * CSV 데이터 처리 구현
     *
     * 파싱된 CSV 데이터에 대해 비즈니스 로직을 적용합니다.
     * 이 예제에서는 간단한 통계 정보를 계산합니다.
     *
     * @param parsedData 파싱된 CSV 데이터
     * @return 처리된 결과 데이터
     */
    @Override
    Object processData(Object parsedData) {
        System.out.println("⚙️ CSV 데이터 처리 중...");

        @SuppressWarnings("unchecked")
        List<String[]> csvData = (List<String[]>) parsedData;

        // CSV 데이터 처리 예시: 행 수 계산 및 첫 번째 행 정보 추출
        int totalRows = csvData.size();
        String[] headers = totalRows > 0 ? csvData.get(0) : new String[0];
        int dataRows = Math.max(0, totalRows - 1); // 헤더 제외

        String result = String.format(
                "CSV 처리 결과 - 총 행: %d, 데이터 행: %d, 컬럼: %s",
                totalRows, dataRows, Arrays.toString(headers)
        );

        System.out.println("✅ CSV 처리 완료");

        // 실제 구현에서는 다음과 같은 처리들이 포함될 수 있습니다:
        // - 데이터 타입 변환 (문자열 → 숫자, 날짜 등)
        // - 데이터 정제 (공백 제거, 대소문자 통일 등)
        // - 집계 연산 (합계, 평균, 최대/최소값 등)
        // - 데이터 변환 (정규화, 인코딩 등)
        // - 중복 제거 및 데이터 품질 검증

        return result;
    }

    /**
     * CSV 특화 데이터 검증 (Hook Method 활용)
     *
     * 상위 클래스의 기본 검증에 더해 CSV 특화된 추가 검증을 수행합니다.
     * 이는 Hook Method의 예시로, 기본 검증을 확장하는 방식입니다.
     *
     * @param data 검증할 데이터
     * @return 검증 통과 여부
     */
    @Override
    boolean validateData(Object data) {
        // 상위 클래스의 기본 검증 먼저 수행
        if (!super.validateData(data)) {
            return false;
        }

        System.out.println("🔍 CSV 특화 검증 중...");

        @SuppressWarnings("unchecked")
        List<String[]> csvData = (List<String[]>) data;

        // CSV 특화 검증 로직
        if (csvData.isEmpty()) {
            System.out.println("❌ CSV 데이터가 비어있습니다.");
            return false;
        }

        // 모든 행의 컬럼 수가 일치하는지 확인
        int expectedColumns = csvData.get(0).length;
        for (int i = 1; i < csvData.size(); i++) {
            if (csvData.get(i).length != expectedColumns) {
                System.out.println("❌ CSV 행별 컬럼 수가 일치하지 않습니다. (행 " + (i+1) + ")");
                return false;
            }
        }

        System.out.println("✅ CSV 특화 검증 통과");
        return true;
    }
}