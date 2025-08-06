package org._03_template_method.ex02;

import java.util.HashMap;
import java.util.Map;

/**
 * JSONDataProcessor - JSON 데이터 처리 구체 클래스
 *
 * Template Method 패턴의 ConcreteClass 역할을 담당하는 클래스입니다.
 * DataProcessor 추상 클래스를 상속받아 JSON 형식의 데이터를 처리하는
 * 구체적인 구현을 제공합니다.
 *
 * JSON(JavaScript Object Notation)은 경량의 데이터 교환 형식으로,
 * 키-값 쌍으로 이루어진 객체나 배열 구조를 표현하며, 웹 API나
 * 설정 파일에서 널리 사용됩니다.
 */
class JSONDataProcessor extends DataProcessor {

    /**
     * JSON 데이터 읽기 구현
     *
     * JSON 파일의 특성에 맞는 읽기 방식을 구현합니다.
     * JSON은 UTF-8 인코딩을 기본으로 하며, 구조화된 텍스트 형태입니다.
     *
     * @param filePath JSON 파일 경로
     * @return 읽어온 JSON 데이터 문자열
     */
    @Override
    String readData(String filePath) {
        System.out.println("📂 JSON 파일 읽기: " + filePath);

        // 실제 구현에서는 다음과 같은 처리가 포함될 수 있습니다:
        // - UTF-8 인코딩 보장
        // - 스트리밍 JSON 파서 사용 (큰 파일의 경우)
        // - JSON Lines 형식 지원
        // - 압축된 JSON 파일 지원

        // 예시 JSON 데이터 반환 (실제로는 파일에서 읽어옴)
        return """
            {
                "users": [
                    {"name": "John", "age": 25, "city": "New York"},
                    {"name": "Jane", "age": 30, "city": "Los Angeles"},
                    {"name": "Bob", "age": 35, "city": "Chicago"}
                ],
                "metadata": {
                    "version": "1.0",
                    "created": "2024-01-01"
                }
            }
            """;
    }

    /**
     * JSON 데이터 파싱 구현
     *
     * JSON 형식의 문자열을 구조화된 데이터 객체로 변환합니다.
     * 실제로는 Jackson, Gson 등의 JSON 라이브러리를 사용하지만,
     * 여기서는 간단한 시뮬레이션을 구현합니다.
     *
     * @param rawData 원본 JSON 문자열
     * @return 파싱된 JSON 데이터 (Map 구조)
     */
    @Override
    Object parseData(String rawData) {
        System.out.println("🔍 JSON 데이터 파싱 중...");

        // 간단한 JSON 파싱 시뮬레이션 (실제로는 JSON 라이브러리 사용)
        Map<String, Object> parsedData = new HashMap<>();

        // 실제 구현을 시뮬레이션하여 구조화된 데이터 생성
        parsedData.put("userCount", 3);
        parsedData.put("version", "1.0");
        parsedData.put("dataType", "users");

        System.out.println("✅ JSON 파싱 완료: " + parsedData.size() + "개 키");

        // 실제 구현에서는 다음과 같은 JSON 파싱 기능들이 포함됩니다:
        // - 중첩된 객체 및 배열 처리
        // - 다양한 데이터 타입 지원 (문자열, 숫자, 불린, null)
        // - 스키마 검증
        // - 순환 참조 검증
        // - 대용량 JSON 스트리밍 파싱
        // - JSON Path를 통한 선택적 파싱

        return parsedData;
    }

    /**
     * JSON 데이터 처리 구현
     *
     * 파싱된 JSON 데이터에 대해 비즈니스 로직을 적용합니다.
     * JSON의 중첩 구조를 활용한 복잡한 데이터 처리를 수행할 수 있습니다.
     *
     * @param parsedData 파싱된 JSON 데이터
     * @return 처리된 결과 데이터
     */
    @Override
    Object processData(Object parsedData) {
        System.out.println("⚙️ JSON 데이터 처리 중...");

        @SuppressWarnings("unchecked")
        Map<String, Object> jsonData = (Map<String, Object>) parsedData;

        // JSON 데이터 처리 예시: 메타데이터 추출 및 통계 생성
        String result = String.format(
                "JSON 처리 결과 - 사용자 수: %d, 버전: %s, 데이터 타입: %s",
                jsonData.get("userCount"),
                jsonData.get("version"),
                jsonData.get("dataType")
        );

        System.out.println("✅ JSON 처리 완료");

        // 실제 구현에서는 다음과 같은 처리들이 포함될 수 있습니다:
        // - 중첩된 객체 평면화 (flattening)
        // - JSON Path를 이용한 데이터 추출
        // - 스키마 기반 데이터 변환
        // - JSON 배열 요소별 처리
        // - 조건부 필드 처리
        // - JSON 병합 및 분할

        return result;
    }

    /**
     * JSON 특화 데이터 검증 (Hook Method 활용)
     *
     * 상위 클래스의 기본 검증에 더해 JSON 특화된 추가 검증을 수행합니다.
     * JSON의 구조적 특성을 고려한 검증 로직을 구현합니다.
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

        System.out.println("🔍 JSON 특화 검증 중...");

        // JSON 데이터가 Map 형태인지 확인
        if (!(data instanceof Map)) {
            System.out.println("❌ JSON 데이터가 올바른 형식이 아닙니다.");
            return false;
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> jsonData = (Map<String, Object>) data;

        // JSON 특화 검증 로직
        if (jsonData.isEmpty()) {
            System.out.println("❌ JSON 데이터가 비어있습니다.");
            return false;
        }

        // 필수 필드 존재 여부 확인 (예시)
        if (!jsonData.containsKey("userCount")) {
            System.out.println("❌ 필수 필드 'userCount'가 없습니다.");
            return false;
        }

        // 데이터 타입 검증 (예시)
        Object userCount = jsonData.get("userCount");
        if (!(userCount instanceof Integer) || (Integer) userCount < 0) {
            System.out.println("❌ userCount 필드가 유효하지 않습니다.");
            return false;
        }

        System.out.println("✅ JSON 특화 검증 통과");

        // 실제 구현에서는 다음과 같은 검증들이 포함될 수 있습니다:
        // - JSON 스키마 검증
        // - 필수 필드 존재 여부 확인
        // - 데이터 타입 검증
        // - 값의 범위 검증
        // - 중첩 구조 검증
        // - 순환 참조 검증

        return true;
    }
}