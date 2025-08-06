package org._01_facade.ex02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * FileDeleter - 파일 삭제 서브시스템
 *
 * Facade 패턴에서 서브시스템 역할을 담당하는 클래스입니다.
 * 파일 삭제라는 특정한 책임만을 가지며,
 * 실제 파일 시스템에서 파일을 제거하는 저수준 작업을 담당합니다.
 *
 * Java NIO API를 사용하여 파일 시스템에서 파일을 안전하게 삭제합니다.
 */
class FileDeleter {
    /**
     * 지정된 경로의 파일을 삭제하는 메서드
     *
     * 파일 시스템에서 해당 경로의 파일을 완전히 제거합니다.
     * 파일이 다른 프로세스에 의해 사용 중이거나 권한이 없으면 예외가 발생합니다.
     *
     * @param filePath 삭제할 파일의 경로
     * @throws IOException 파일이 존재하지 않거나, 삭제 권한이 없거나,
     *                     파일이 사용 중이거나, 기타 파일 시스템 오류가 발생한 경우
     */
    public void deleteFile(String filePath) throws IOException {
        // Files.delete(): 지정된 경로의 파일을 삭제
        // 파일이 존재하지 않으면 NoSuchFileException 발생
        // Paths.get(): 문자열 경로를 Path 객체로 변환
        Files.delete(Paths.get(filePath));
    }
}