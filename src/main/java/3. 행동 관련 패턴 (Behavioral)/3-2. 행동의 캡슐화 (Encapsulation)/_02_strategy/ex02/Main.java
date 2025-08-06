package org._02_strategy.ex02;

/**
 * Main 클래스 - Strategy 패턴 사용 예제 (압축 시스템)
 *
 * Compressor가 다양한 압축 전략을 런타임에 동적으로 선택하고
 * 사용하는 방법을 보여주는 클라이언트 코드입니다.
 *
 * 이 예제는 데이터의 특성에 따라 최적의 압축 알고리즘을 선택하는
 * 실제적인 시나리오를 통해 Strategy 패턴의 유용성을 보여줍니다.
 */
public class Main {
    public static void main(String[] args) {
        // Context 객체 생성
        Compressor compressor = new Compressor();

        // 테스트용 데이터들 - 각각 다른 특성을 가짐
        String repetitiveData = "aaabbbcccdddeeefffggghhhiiijjjkkklllmmm";
        String textData = "the quick brown fox jumps over the lazy dog and the cat";
        String mixedData = "aaa bbb the and running jumping";

        System.out.println("=== Strategy 패턴을 활용한 데이터 압축 시스템 ===\n");

        // 첫 번째 전략: RunLength Encoding
        // 반복되는 문자가 많은 데이터에 적합
        System.out.println("=== RunLength Encoding 전략 사용 ===");
        CompressionStrategy rleStrategy = new RunLengthEncoding();
        compressor.setCompressionStrategy(rleStrategy);

        System.out.println("반복 패턴이 많은 데이터 압축:");
        compressor.compressData(repetitiveData);

        System.out.println("일반 텍스트 데이터 압축:");
        compressor.compressData(textData);

        // 두 번째 전략: Simple Replacement Compression
        // 자주 사용되는 단어가 많은 텍스트에 적합
        System.out.println("=== Simple Replacement 전략 사용 ===");
        CompressionStrategy replacementStrategy = new SimpleReplacementCompression();
        compressor.setCompressionStrategy(replacementStrategy);

        System.out.println("영어 텍스트 데이터 압축:");
        compressor.compressData(textData);

        System.out.println("혼합 데이터 압축:");
        compressor.compressData(mixedData);

        // 런타임에 전략 변경 시연
        System.out.println("=== 런타임 전략 변경 시연 ===");
        System.out.println("현재 전략: " + compressor.getCurrentStrategy());

        // 데이터 특성에 따른 최적 전략 선택
        if (hasRepeatedPatterns(repetitiveData)) {
            System.out.println("반복 패턴 감지 -> RLE 전략으로 변경");
            compressor.setCompressionStrategy(rleStrategy);
        } else {
            System.out.println("텍스트 패턴 감지 -> Replacement 전략으로 변경");
            compressor.setCompressionStrategy(replacementStrategy);
        }

        compressor.compressData(repetitiveData);

        /*
         * Strategy 패턴의 실제 활용 효과:
         *
         * 1. 알고리즘 선택의 유연성:
         *    - 데이터 특성에 따라 최적의 압축 방식 선택
         *    - 런타임에 압축 전략을 동적으로 변경 가능
         *
         * 2. 확장성:
         *    - 새로운 압축 알고리즘 추가 용이 (허프만 인코딩, LZ77 등)
         *    - 기존 코드 수정 없이 새로운 전략 추가 가능
         *
         * 3. 성능 최적화:
         *    - 압축률과 속도를 고려한 전략 선택
         *    - A/B 테스트를 통한 최적 전략 탐색
         *
         * 4. 유지보수성:
         *    - 각 압축 알고리즘이 독립적으로 관리됨
         *    - 알고리즘별 성능 측정 및 개선 용이
         *
         * 실제 사용 사례:
         * - 파일 압축 소프트웨어 (ZIP, RAR 등)
         * - 이미지 압축 (JPEG, PNG의 다양한 압축 레벨)
         * - 네트워크 데이터 압축 (gzip, deflate 등)
         * - 데이터베이스 압축 (행/열 기반 압축)
         */
    }

    /**
     * 데이터에 반복 패턴이 있는지 확인하는 헬퍼 메서드
     * 실제 구현에서는 더 정교한 패턴 분석 알고리즘을 사용할 수 있습니다.
     *
     * @param data 분석할 데이터
     * @return 반복 패턴이 있으면 true, 없으면 false
     */
    private static boolean hasRepeatedPatterns(String data) {
        // 간단한 휴리스틱: 연속된 같은 문자가 3개 이상 있으면 반복 패턴으로 판단
        for (int i = 0; i < data.length() - 2; i++) {
            if (data.charAt(i) == data.charAt(i + 1) &&
                    data.charAt(i) == data.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }
}