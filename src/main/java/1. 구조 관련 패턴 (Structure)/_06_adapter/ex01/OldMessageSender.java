package org._06_adapter.ex01;

/**
 * OldMessageSender - 기존 메시지 전송 시스템 (Adaptee)
 *
 * Adapter 패턴에서 Adaptee 역할을 담당하는 인터페이스입니다.
 * 이는 이미 존재하는 레거시 메시지 시스템의 인터페이스를 나타내며,
 * 새로운 시스템과는 다른 방식으로 동작합니다.
 *
 * 기존 시스템의 특징:
 * - 배열 형태의 파라미터 사용
 * - 정수형 반환값으로 성공/실패 표시
 * - 레거시 네이밍 컨벤션 사용 (send vs sendMessage)
 *
 * 이 인터페이스는 이미 많은 곳에서 사용되고 있어 직접 수정할 수 없는
 * 상황을 시뮬레이션합니다.
 */
interface OldMessageSender {
    /**
     * 기존 시스템의 메시지 전송 메서드
     *
     * 레거시 시스템의 특징적인 인터페이스 설계:
     * - 파라미터를 배열로 받음 (현대적이지 않은 API 설계)
     * - 반환값이 int형 (성공: 1, 실패: 0 또는 음수)
     * - 메서드명이 간단함 (send vs sendMessage)
     *
     * @param messageData 전송할 데이터 배열
     *                    [0]: 메시지 내용
     *                    [1]: 수신자 정보
     * @return 전송 결과 (1: 성공, 0 이하: 실패)
     */
    int send(String[] messageData);
}