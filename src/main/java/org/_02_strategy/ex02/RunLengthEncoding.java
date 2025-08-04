package org._02_strategy.ex02;

/**
 * RunLengthEncoding - 런길이 인코딩 압축 전략
 *
 * CompressionStrategy 인터페이스를 구현하여 런길이 인코딩(RLE)
 * 압축 알고리즘을 제공하는 구체적인 전략 클래스입니다.
 *
 * 런길이 인코딩은 연속된 동일한 문자를 [문자][개수] 형태로 압축하는
 * 알고리즘으로, 반복되는 패턴이 많은 데이터에 효과적입니다.
 * 예: "aaabbbcc" -> "a3b3c2"
 */
class RunLengthEncoding implements CompressionStrategy {

    /**
     * 런길이 인코딩을 사용한 데이터 압축
     *
     * 연속된 동일한 문자들을 찾아서 문자와 반복 횟수로 압축합니다.
     * 이 알고리즘은 같은 문자가 연속으로 나타나는 데이터에 특히 효과적입니다.
     *
     * @param data 압축할 원본 데이터
     * @return RLE로 압축된 데이터
     */
    @Override
    public String compress(String data) {
        if (data == null || data.isEmpty()) {
            return data;
        }

        StringBuilder compressed = new StringBuilder();
        char currentChar = data.charAt(0);
        int count = 1;

        // 문자열을 순회하면서 연속된 같은 문자의 개수를 셉니다
        for (int i = 1; i < data.length(); i++) {
            if (data.charAt(i) == currentChar) {
                count++; // 같은 문자면 카운트 증가
            } else {
                // 다른 문자가 나오면 이전 문자와 개수를 압축 결과에 추가
                compressed.append(currentChar).append(count);
                currentChar = data.charAt(i);
                count = 1;
            }
        }

        // 마지막 문자 그룹 처리
        compressed.append(currentChar).append(count);

        String result = compressed.toString();
        System.out.println("RLE Compression: \"" + data + "\" -> \"" + result + "\"");

        /*
         * RLE 압축의 특징:
         * - 장점: 반복 패턴이 많은 데이터에 매우 효과적
         * - 단점: 반복이 적은 데이터는 오히려 크기가 증가할 수 있음
         * - 적용 분야: 이미지 압축, 팩스 전송, 게임 맵 데이터 등
         */

        return result;
    }
}