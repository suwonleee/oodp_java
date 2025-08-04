package org._01_facade.ex02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * FileWriter - 파일 쓰기 서브시스템
 *
 * Facade 패턴에서 서브시스템 역할을 담당하는 클래스입니다.
 * 파일 쓰기라는 특정한 책임만을 가지며,
 * 실제 파일 시스템에 데이터를 저장하는 저수준 작업을 담당합니다.
 *
 * Java NIO API를 사용하여 문자열을 바이트 배열로 변환하고
 * 파일에 쓰는 복잡한 작업을 수행합니다.
 */
class FileWriter {
    /**
     * 문자열 내용을 파일에 쓰는 메서드
     *
     * 주어진 내용을 바이트 배열로 변환하여 지정된 경로에 파일로 저장합니다.
     * 파일이 이미 존재하면 덮어쓰고, 존재하지 않으면 새로 생성합니다.
     *
     * @param filePath 생성하거나 덮어쓸 파일의 경로
     * @param content 파일에 쓸 문자열 내용
     * @throws IOException 파일 생성 권한이 없거나, 디스크 공간이 부족하거나,
     *                     기타 파일 시스템 오류가 발생한 경우
     */
    public void writeFile(String filePath, String content) throws IOException {
        // content.getBytes(): 문자열을 UTF-8 기본 인코딩으로 바이트 배열 변환
        // Files.write(): 바이트 배열을 지정된 경로의 파일에 쓰기
        // Paths.get(): 문자열 경로를 Path 객체로 변환
        Files.write(Paths.get(filePath), content.getBytes());
    }
}