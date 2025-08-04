package org._02_strategy.ex02;

/**
 * SimpleReplacementCompression - 단순 치환 압축 전략
 *
 * CompressionStrategy 인터페이스를 구현하여 단순 치환 방식의
 * 압축 알고리즘을 제공하는 구체적인 전략 클래스입니다.
 *
 * 자주 나타나는 문자나 문자열을 더 짧은 기호로 치환하여
 * 전체 데이터 크기를 줄이는 압축 방식입니다.
 * 예: 공백을 '_'로, 'the'를 '#'로 치환
 */
class SimpleReplacementCompression implements CompressionStrategy {

    /**
     * 단순 치환을 사용한 데이터 압축
     *
     * 자주 사용되는 문자나 패턴을 더 짧은 기호로 치환하여 압축합니다.
     * 실제 구현에서는 빈도 분석을 통해 최적의 치환 테이블을 생성할 수 있습니다.
     *
     * @param data 압축할 원본 데이터
     * @return 치환 압축된 데이터
     */
    @Override
    public String compress(String data) {
        if (data == null || data.isEmpty()) {
            return data;
        }

        String compressed = data;

        // 자주 사용되는 패턴들을 더 짧은 기호로 치환
        // 실제 환경에서는 통계 분석을 통해 최적의 치환 테이블을 생성
        compressed = compressed.replace(" ", "_");        // 공백을 언더스코어로
        compressed = compressed.replace("the", "#");      // 'the'를 '#'로
        compressed = compressed.replace("and", "&");      // 'and'를 '&'로
        compressed = compressed.replace("ing", "@");      // 'ing'를 '@'로
        compressed = compressed.replace("tion", "!");     // 'tion'을 '!'로

        System.out.println("Simple Replacement Compression: \"" + data + "\" -> \"" + compressed + "\"");

        /*
         * 단순 치환 압축의 특징:
         * - 장점: 구현이 간단하고 특정 패턴에 효과적
         * - 단점: 치환 테이블 정보를 별도로 저장해야 함
         * - 개선점:
         *   1. 허프만 인코딩으로 발전 가능
         *   2. 동적 딕셔너리 생성으로 효율성 향상
         *   3. 문맥 기반 치환으로 압축률 개선
         */

        return compressed;
    }
}