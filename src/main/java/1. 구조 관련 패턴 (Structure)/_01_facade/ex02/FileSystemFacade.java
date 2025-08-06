package org._01_facade.ex02;

import java.io.IOException;

/**
 * FileSystemFacade - 파일 시스템 Facade 패턴의 핵심 클래스
 *
 * 복잡한 파일 시스템 작업(읽기, 쓰기, 삭제)을 간단한 인터페이스로 통합하여 제공합니다.
 * 각각의 파일 작업 클래스들을 캡슐화하고, 예외 처리를 통합하여
 * 클라이언트가 복잡한 예외 처리나 개별 클래스 사용법을 알 필요 없게 만듭니다.
 *
 * 이 클래스는 파일 작업의 복잡성을 숨기고 간단한 boolean/String 반환값으로
 * 작업 결과를 제공하여 사용성을 크게 향상시킵니다.
 */
class FileSystemFacade {
    // 파일 시스템 서브시스템 컴포넌트들을 private으로 캡슐화
    private FileReader fileReader;
    private FileWriter fileWriter;
    private FileDeleter fileDeleter;

    /**
     * 생성자 - 모든 파일 작업 서브시스템 컴포넌트들을 초기화
     * 각 파일 작업을 담당하는 개별 클래스들의 인스턴스를 생성하여
     * Facade가 이들을 통합 관리할 수 있도록 준비합니다.
     */
    public FileSystemFacade() {
        this.fileReader = new FileReader();
        this.fileWriter = new FileWriter();
        this.fileDeleter = new FileDeleter();
    }

    /**
     * 파일 읽기 - 예외 처리가 통합된 간편한 파일 읽기 메서드
     *
     * FileReader의 복잡한 IOException 처리를 내부에서 처리하고,
     * 클라이언트에게는 간단한 String 반환 또는 null로 결과를 제공합니다.
     *
     * @param filePath 읽을 파일의 경로
     * @return 파일 내용 문자열, 오류 시 null 반환
     */
    public String readFile(String filePath) {
        try {
            // 서브시스템(FileReader)에 실제 작업 위임
            return fileReader.readFile(filePath);
        } catch (IOException e) {
            // 복잡한 예외를 간단한 에러 메시지와 null 반환으로 처리
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    /**
     * 파일 쓰기 - 예외 처리가 통합된 간편한 파일 쓰기 메서드
     *
     * FileWriter의 복잡한 IOException 처리를 내부에서 처리하고,
     * 클라이언트에게는 간단한 boolean 값으로 성공/실패를 알려줍니다.
     *
     * @param filePath 쓸 파일의 경로
     * @param content 파일에 쓸 내용
     * @return 성공 시 true, 실패 시 false
     */
    public boolean writeFile(String filePath, String content) {
        try {
            // 서브시스템(FileWriter)에 실제 작업 위임
            fileWriter.writeFile(filePath, content);
            return true; // 성공 시 true 반환
        } catch (IOException e) {
            // 복잡한 예외를 간단한 에러 메시지와 false 반환으로 처리
            System.err.println("Error writing file: " + e.getMessage());
            return false;
        }
    }

    /**
     * 파일 삭제 - 예외 처리가 통합된 간편한 파일 삭제 메서드
     *
     * FileDeleter의 복잡한 IOException 처리를 내부에서 처리하고,
     * 클라이언트에게는 간단한 boolean 값으로 성공/실패를 알려줍니다.
     *
     * @param filePath 삭제할 파일의 경로
     * @return 성공 시 true, 실패 시 false
     */
    public boolean deleteFile(String filePath) {
        try {
            // 서브시스템(FileDeleter)에 실제 작업 위임
            fileDeleter.deleteFile(filePath);
            return true; // 성공 시 true 반환
        } catch (IOException e) {
            // 복잡한 예외를 간단한 에러 메시지와 false 반환으로 처리
            System.err.println("Error deleting file: " + e.getMessage());
            return false;
        }
    }
}