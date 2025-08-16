package org._09_proxy.ex01;

/**
 * Proxy 패턴 데모 클래스
 * - Virtual Proxy를 통한 지연 로딩 예제
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Proxy 패턴 - 이미지 지연 로딩 예제 ===");

        // ProxyImage 객체들 생성 (실제 이미지 로딩은 아직 발생하지 않음)
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        System.out.println("\n1. 프록시 객체 생성 완료 (실제 로딩은 아직 안됨)");

        // 첫 번째 이미지 표시 (이때 실제 로딩 발생)
        System.out.println("\n2. 첫 번째 이미지 첫 표시:");
        image1.display();

        // 같은 이미지 재표시 (이미 로딩되어 있어서 로딩 과정 생략)
        System.out.println("\n3. 첫 번째 이미지 재표시:");
        image1.display();

        // 두 번째 이미지 표시 (새로운 이미지라서 로딩 발생)
        System.out.println("\n4. 두 번째 이미지 표시:");
        image2.display();

        // 파일명 조회 (실제 객체 생성 없이도 가능)
        System.out.println("\n5. 파일명 조회:");
        System.out.println("Image1 파일명: " + image1.getFileName());
        System.out.println("Image2 파일명: " + image2.getFileName());
    }
}