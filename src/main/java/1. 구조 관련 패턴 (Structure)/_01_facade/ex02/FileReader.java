package org._01_facade.ex02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * FileReader - 파일 읽기 서브시스템
 *
 * Facade 패턴에서 서브시스템 역할을 담당하는 클래스입니다.
 * 파일 읽기라는 특정한 책임만을 가지며,
 * 실제 파일 시스템과의 저수준 상호작용을 담당합니다.
 *
 * Java NIO API를 사용하여 파일을 바이트 배열로 읽고 문자열로 변환하는
 * 복잡한 작업을 수행하지만, IOException을 던져 상위 계층(Facade)에서
 * 예외 처리를 하도록 설계되었습니다.
 */
class FileReader {
    /**
     * 파일 내용을 문자열로 읽어오는 메서드
     *
     * Java NIO Files API를 사용하여 파일의 모든 바이트를 읽고,
     * 이를 문자열로 변환하여 반환합니다.
     *
     * @param filePath 읽을 파일의 경로
     * @return 파일의 전체 내용을 담은 문자열
     * @throws IOException 파일이 존재하지 않거나, 읽기 권한이 없거나,
     *                     기타 파일 시스템 오류가 발생한 경우
     */
    public String readFile(String filePath) throws IOException {
        // Files.readAllBytes(): 파일의 모든 바이트를 byte[] 배열로 읽어옴
        // Paths.get(): 문자열 경로를 Path 객체로 변환
        // new String(): 바이트 배열을 UTF-8 기본 인코딩으로 문자열 변환
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}