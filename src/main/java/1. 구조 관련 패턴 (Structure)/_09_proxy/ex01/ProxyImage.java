package org._09_proxy.ex01;

/**
 * Proxy 클래스
 * - RealSubject에 대한 대리자 역할
 * - 실제 객체 생성을 필요한 시점까지 지연시킴 (Lazy Initialization)
 * - 클라이언트의 요청을 제어하고 관리
 */
class ProxyImage implements Image {
    private RealImage realImage;  // 실제 객체에 대한 참조 (지연 생성)
    private String fileName;      // 실제 객체 생성을 위해 필요한 정보

    public ProxyImage(String fileName) {
        this.fileName = fileName;
        // RealImage 객체는 실제 필요한 시점까지 생성하지 않음
    }

    /**
     * display() 호출 시 실제 객체를 생성하고 작업을 위임
     * - 처음 호출 시에만 RealImage 객체를 생성 (Lazy Loading)
     * - 이후 호출에서는 기존 객체를 재사용
     */
    @Override
    public void display() {
        if (realImage == null) {
            // 실제로 필요한 시점에 RealImage 객체 생성
            realImage = new RealImage(fileName);
        }
        // 실제 작업을 RealImage에 위임
        realImage.display();
    }

    /**
     * 파일 이름은 실제 객체 생성 없이도 반환 가능
     * - Proxy가 직접 처리할 수 있는 작업
     */
    @Override
    public String getFileName() {
        return fileName;
    }
}