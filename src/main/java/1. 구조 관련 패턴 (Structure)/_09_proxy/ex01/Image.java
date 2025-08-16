
package org._09_proxy.ex01;

/**
 * Subject 인터페이스
 * - Proxy와 RealSubject가 공통으로 구현할 인터페이스
 * - 클라이언트가 사용할 공통된 메소드들을 정의
 */
public interface Image {
    void display();         // 이미지를 화면에 표시하는 메소드
    String getFileName();   // 파일 이름을 반환하는 메소드
}