package org._06_adapter.ex01;

/**
 * MessageAdapter - 메시지 시스템 어댑터 (Adapter)
 *
 * Adapter 패턴의 핵심 클래스로, 기존 레거시 시스템(OldMessageSender)과
 * 새로운 현대적 인터페이스(ModernMessageSender) 사이의 다리 역할을 합니다.
 *
 * 이 어댑터는 다음과 같은 변환을 수행합니다:
 * 1. 개별 파라미터 → 배열 형태로 변환
 * 2. void 반환형 → int 반환형 처리 및 예외 변환
 * 3. 현대적 에러 처리 → 레거시 상태 코드 처리
 *
 * Object Adapter 패턴을 사용하여 컴포지션을 통해 레거시 시스템을 감쌉니다.
 */
class MessageAdapter implements ModernMessageSender {
    /**
     * 어댑터가 감싸고 있는 레거시 시스템
     *
     * Object Adapter 패턴의 핵심: 상속이 아닌 컴포지션을 사용
     * 이를 통해 다음과 같은 장점을 얻습니다:
     * - 런타임에 어댑터 교체 가능
     * - 다중 상속 문제 없음
     * - 레거시 시스템의 구현에 덜 의존적
     */
    private OldMessageSender oldSystem;

    /**
     * 어댑터 생성자
     *
     * 의존성 주입을 통해 레거시 시스템을 받습니다.
     * 이를 통해 다양한 OldMessageSender 구현체를 사용할 수 있고,
     * 테스트 시에는 Mock 객체를 주입할 수도 있습니다.
     *
     * @param oldSystem 감쌀 레거시 메시지 시스템
     */
    public MessageAdapter(OldMessageSender oldSystem) {
        this.oldSystem = oldSystem;
    }

    /**
     * 현대적 인터페이스를 레거시 인터페이스로 변환
     *
     * 이 메서드는 Adapter 패턴의 핵심으로, 두 개의 서로 다른
     * 인터페이스 사이의 변환을 담당합니다.
     *
     * 변환 과정:
     * 1. 개별 파라미터를 배열로 패키징
     * 2. 레거시 시스템 호출
     * 3. 반환값을 현대적 방식으로 해석 (예외 처리)
     *
     * @param message 전송할 메시지
     * @param recipient 수신자
     * @throws RuntimeException 전송 실패 시
     */
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("Adapter: Converting modern call to legacy format...");

        // 1단계: 현대적 파라미터를 레거시 형식으로 변환
        String[] messageData = {message, recipient};

        // 2단계: 레거시 시스템 호출
        int result = oldSystem.send(messageData);

        // 3단계: 레거시 반환값을 현대적 방식으로 변환
        if (result != 1) {
            // 레거시 시스템의 다양한 에러 코드를 현대적 예외로 변환
            String errorMessage = switch (result) {
                case 0 -> "Invalid message format";
                case -1 -> "Empty message not allowed";
                case -2 -> "Invalid recipient";
                case -3 -> "Transmission interrupted";
                default -> "Unknown error occurred (code: " + result + ")";
            };

            System.out.println("Adapter: Converting error code " + result + " to exception");
            throw new RuntimeException("Message sending failed: " + errorMessage);
        }

        System.out.println("Adapter: Successfully adapted legacy system call");

        /*
         * 어댑터의 책임과 역할:
         *
         * 1. 인터페이스 변환:
         *    - 파라미터 형식 변환 (개별 → 배열)
         *    - 반환 방식 변환 (상태코드 → 예외)
         *
         * 2. 데이터 변환:
         *    - 현대적 데이터 형식을 레거시 형식으로
         *    - 필요시 데이터 검증 및 정제
         *
         * 3. 에러 처리 변환:
         *    - 레거시 에러 코드를 현대적 예외로
         *    - 적절한 에러 메시지 생성
         *
         * 4. 로깅 및 모니터링:
         *    - 변환 과정의 추적 가능성
         *    - 디버깅을 위한 정보 제공
         *
         * 이러한 어댑터를 통해 클라이언트는 레거시 시스템의
         * 복잡성을 몰라도 되고, 레거시 시스템은 수정 없이
         * 새로운 인터페이스에서 사용될 수 있습니다.
         */
    }
}