package org._01_facade.ex02;

/**
 * Main 클래스 - 파일 시스템 Facade 패턴 사용 예제
 *
 * FileSystemFacade를 통해 복잡한 파일 시스템 작업을
 * 간단하고 안전하게 수행하는 방법을 보여주는 클라이언트 코드입니다.
 *
 * 각각의 파일 작업 클래스를 직접 사용하지 않고,
 * Facade를 통해 통합된 인터페이스로 파일 작업을 수행합니다.
 */
public class Main {
    public static void main(String[] args) {
        // Facade 객체 생성 - 모든 파일 시스템 서브시스템을 통합 관리
        FileSystemFacade fs = new FileSystemFacade();

        // 파일 쓰기 - 복잡한 예외 처리 없이 간단한 boolean 결과만 확인
        boolean writeSuccess = fs.writeFile(
                "test.txt", "Hello, Facade Pattern!"
        );
        System.out.println("File write success: " + writeSuccess);

        // 파일 읽기 - IOException 걱정 없이 간단하게 내용 읽기
        String content = fs.readFile("test.txt");
        System.out.println("File content: " + content);

        // 파일 삭제 - 복잡한 예외 처리 없이 간단한 boolean 결과만 확인
        boolean deleteSuccess = fs.deleteFile("test.txt");
        System.out.println("File delete success: " + deleteSuccess);

        /*
         * Facade 패턴의 장점 (파일 시스템 예제):
         *
         * 1. 예외 처리 단순화:
         *    - 각 서브시스템의 IOException을 Facade에서 처리
         *    - 클라이언트는 try-catch 블록 없이 간단히 사용 가능
         *
         * 2. 일관된 인터페이스:
         *    - 모든 파일 작업이 비슷한 패턴으로 통일됨
         *    - boolean 반환값으로 성공/실패를 명확히 구분
         *
         * 3. 복잡성 감소:
         *    - NIO API의 복잡한 사용법을 몰라도 됨
         *    - Path 객체 변환, 바이트 배열 처리 등의 세부사항 숨김
         *
         * 4. 유지보수성 향상:
         *    - 파일 처리 로직 변경 시 Facade만 수정하면 됨
         *    - 새로운 파일 작업 추가가 용이함
         */
    }
}