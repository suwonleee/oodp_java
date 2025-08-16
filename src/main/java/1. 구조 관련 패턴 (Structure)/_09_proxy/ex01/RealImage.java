package org._09_proxy.ex01;

/**
 * RealSubject 클래스
 * - 실제 작업을 수행하는 클래스
 * - 비용이 큰 작업(이미지 로딩)을 포함
 */
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(); // 생성자에서 즉시 디스크에서 이미지를 로드
    }

    /**
     * 실제로 비용이 큰 작업을 수행하는 메소드
     * - 디스크에서 이미지 파일을 읽어오는 작업을 시뮬레이션
     */
    private void loadFromDisk() {
        System.out.println("Loading " + fileName);
    }

    /**
     * 이미지를 화면에 표시하는 실제 구현
     */
    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}