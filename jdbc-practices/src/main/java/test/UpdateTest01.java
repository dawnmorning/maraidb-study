package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest01 {

	public static void main(String[] args) {
		boolean result =  updateDepartment(2L, "인사");
		System.out.println(result ? "성공" : "실패");
	}

	private static boolean updateDepartment(long no, String name) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		// ResultSet rs = null;
		try {
			// 1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.174:3307/webdb?chraset=utf8";
			// getConnection (url, 계정이름, 비밀번호)
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("연결 성공");
			// 3. Statement 객체 생성
			stmt = conn.createStatement();
			// 4. sql 실행
			String sql = 
					"update dept" +
					"set name='" + name + "'"   +
					" where no=" + no;
			int count = stmt.executeUpdate(sql);
			// rs =  stmt.executeQuery(sql);
			
			// 5. 결과처리
			result = count == 1;
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 6. 자원 처리
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null)
				{
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
