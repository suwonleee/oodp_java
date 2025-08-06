package org._06_adapter.ex01;

/**
 * OldMessageSystem - 기존 메시지 시스템의 구체적 구현체
 *
 * OldMessageSender 인터페이스의 실제 구현체로, 레거시 시스템의
 * 실제 동작을 시뮬레이션합니다. 이 클래스는 이미 프로덕션에서
 * 사용되고 있는 안정적인 시스템을 대표합니다.
 *
 * 레거시 시스템의 특징:
 * - 오래된 코딩 스타일과 관례
 * - 다소 복잡한 파라미터 처리 방식
 * - 정수형 상태 코드 반환
 * - 이미 검증된 안정적인 동작
 */
class OldMessageSystem implements OldMessageSender {

    /**
     * 레거시 메시지 전송 구현
     *
     * 기존 시스템의 실제 메시지 전송 로직을 구현합니다.
     * 배열 형태의 입력을 처리하고 정수형 상태 코드를 반환하는
     * 전형적인 레거시 시스템 패턴을 보여줍니다.
     *
     * @param messageData 메시지 데이터 배열
     * @return 전송 상태 코드 (1: 성공, 0: 실패)
     */
    @Override
    public int send(String[] messageData) {
        // 레거시 시스템 특유의 입력 검증
        if (messageData == null || messageData.length < 2) {
            System.out.println("Old System: Invalid message data format");
            return 0; // 실패 코드
        }

        String message = messageData[0];
        String recipient = messageData[1];

        // 기본적인 유효성 검증 (레거시 방식)
        if (message == null || message.trim().isEmpty()) {
            System.out.println("Old System: Empty message not allowed");
            return -1; // 에러 코드
        }

        if (recipient == null || recipient.trim().isEmpty()) {
            System.out.println("Old System: Invalid recipient");
            return -2; // 에러 코드
        }

        // 실제 메시지 전송 시뮬레이션
        System.out.println("Old System: Sending message...");
        System.out.println("  Message: " + message);
        System.out.println("  To: " + recipient);

        // 레거시 시스템의 전송 과정 시뮬레이션
        try {
            // 네트워크 연결, 인증, 전송 등의 복잡한 과정
            Thread.sleep(100); // 처리 시간 시뮬레이션
            System.out.println("Old System: Message sent successfully!");
            return 1; // 성공 코드
        } catch (InterruptedException e) {
            System.out.println("Old System: Transmission interrupted");
            return -3; // 인터럽트 에러 코드
        }

        /*
         * 레거시 시스템의 특징:
         * 1. 복잡한 에러 코드 시스템 (1, 0, -1, -2, -3...)
         * 2. 배열 기반 파라미터 처리
         * 3. null 체크와 같은 defensive programming
         * 4. 동기적 처리 방식
         * 5. 상세한 로깅과 상태 출력
         *
         * 이러한 특징들이 새로운 시스템의 인터페이스와
         * 맞지 않아 어댑터가 필요한 상황입니다.
         */
    }
}