import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class GradeRunner {

    private static final String DB_URL = "jdbc:mysql://localhost:43306/sql_practice";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "7475";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("채점할 레벨 또는 테스트를 입력하세요 (예: lv01 또는 test05): ");
        String input = scanner.nextLine().trim();
        scanner.close();

        if (!input.matches("lv\\d{2}|test\\d{2}")) {
            System.out.println("입력 형식이 올바르지 않습니다. 'lvXX' 또는 'testXX' 형식으로 입력해주세요.");
            return;
        }

        Path levelPath = Paths.get("sql-practice/levels", input);
        if (!Files.exists(levelPath) || !Files.isDirectory(levelPath)) {
            System.out.println("입력한 디렉토리가 존재하지 않습니다: " + levelPath);
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("🔍 채점 중: " + levelPath.getFileName());

            // init/init.sql 실행
            Path initPath = levelPath.resolve("init/init.sql");
            if (Files.exists(initPath)) {
                runSqlScript(conn, initPath);
            } else {
                System.out.println("⚠️ init.sql 파일이 없습니다: " + initPath);
                return;
            }

            // solution 디렉토리 내의 모든 .sql 파일 처리
            Path solutionDir = levelPath.resolve("solution");
            Path answerDir = levelPath.resolve("answer");

            if (!Files.isDirectory(solutionDir) || !Files.isDirectory(answerDir)) {
                System.out.println("⚠️ solution 또는 answer 디렉토리가 없습니다.");
                return;
            }

            List<Path> solutionFiles = Files.list(solutionDir)
                    .filter(path -> path.toString().endsWith(".sql"))
                    .sorted()
                    .collect(Collectors.toList());

            for (Path solutionFile : solutionFiles) {
                String fileName = solutionFile.getFileName().toString();
                Path answerFile = answerDir.resolve(fileName);

                if (!Files.exists(answerFile)) {
                    System.out.println("⚠️ 정답 파일이 없습니다: " + answerFile);
                    continue;
                }

                System.out.println("🔸 문제: " + fileName);

                List<List<Object>> expected = runQuery(conn, answerFile);
                List<List<Object>> actual = runQuery(conn, solutionFile);

                if (expected.equals(actual)) {
                    System.out.println("✅ PASS\n");
                } else {
                    System.out.println("❌ FAIL\n");
                }
            }

        } catch (Exception e) {
            System.out.println("⚠️ 오류 발생: " + e.getMessage() + "\n");
        }
    }

    private static void runSqlScript(Connection conn, Path filePath) throws Exception {
        String sql = Files.readString(filePath);
        try (Statement stmt = conn.createStatement()) {
            for (String statement : sql.split(";")) {
                if (!statement.trim().isEmpty()) {
                    stmt.execute(statement);
                }
            }
        }
    }

    private static List<List<Object>> runQuery(Connection conn, Path filePath) throws Exception {
        String query = Files.readString(filePath);
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            List<List<Object>> results = new ArrayList<>();

            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= cols; i++) {
                    row.add(rs.getObject(i));
                }
                results.add(row);
            }

            return results;
        }
    }
}

