package org._02_strategy.ex02;

/**
 * Compressor - 압축기 컨텍스트 클래스
 *
 * Strategy 패턴의 Context 역할을 담당하는 클래스입니다.
 * 압축 전략(CompressionStrategy)을 사용하여 데이터 압축을 수행하며,
 * 런타임에 압축 알고리즘을 동적으로 변경할 수 있습니다.
 *
 * 이 클래스는 구체적인 압축 알고리즘을 직접 구현하지 않고,
 * 설정된 전략 객체에 압축 작업을 위임합니다. 이를 통해
 * 압축 알고리즘의 추가나 변경이 Compressor에 영향을 주지 않습니다.
 */
class Compressor {
    private CompressionStrategy strategy;

    /**
     * 압축 전략을 설정하는 메서드
     *
     * Strategy 패턴의 핵심으로, 사용할 압축 알고리즘을
     * 런타임에 동적으로 선택하고 변경할 수 있게 해줍니다.
     *
     * @param strategy 사용할 압축 전략 객체
     */
    public void setCompressionStrategy(CompressionStrategy strategy) {
        this.strategy = strategy;
        System.out.println("압축 전략이 " + strategy.getClass().getSimpleName() + "로 변경되었습니다.");
    }

    /**
     * 데이터를 압축하는 메서드
     *
     * 설정된 압축 전략을 사용하여 실제 데이터 압축을 수행합니다.
     * 어떤 압축 알고리즘이 사용되는지는 전략 객체에 위임하고,
     * Compressor는 압축 방식의 세부사항을 알 필요가 없습니다.
     *
     * @param data 압축할 원본 데이터
     * @return 압축된 데이터, 전략이 설정되지 않은 경우 원본 데이터 반환
     */
    public String compressData(String data) {
        if (strategy == null) {
            System.out.println("압축 전략이 설정되지 않았습니다. 원본 데이터를 반환합니다.");
            return data;
        }

        System.out.println("데이터 압축을 시작합니다...");
        // 실제 압축 작업은 설정된 전략에 위임
        String compressedData = strategy.compress(data);

        // 압축 효과 분석
        double compressionRatio = (double) compressedData.length() / data.length();
        System.out.println("압축 완료! 압축률: " + String.format("%.2f", compressionRatio) +
                " (원본: " + data.length() + "자 -> 압축: " + compressedData.length() + "자)");
        System.out.println();

        /*
         * Strategy 패턴의 장점을 보여주는 부분:
         * 1. Compressor는 압축 알고리즘의 구체적인 구현을 몰라도 됨
         * 2. 새로운 압축 방식 추가 시 이 코드는 변경되지 않음
         * 3. 데이터 특성에 따라 최적의 압축 방식을 동적으로 선택 가능
         * 4. 각 압축 알고리즘은 독립적으로 개발하고 테스트 가능
         */

        return compressedData;
    }

    /**
     * 현재 설정된 압축 전략 정보를 반환하는 메서드
     *
     * @return 현재 압축 전략의 클래스명, 설정되지 않은 경우 "None"
     */
    public String getCurrentStrategy() {
        return strategy != null ? strategy.getClass().getSimpleName() : "None";
    }
}