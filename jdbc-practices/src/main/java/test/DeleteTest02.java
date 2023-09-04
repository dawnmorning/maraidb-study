package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteTest02 {

	public static void main(String[] args) {
		boolean result = deleteDepartmentByNo(2L);
		System.out.println(result ? "성공" : "실패");
	}

	private static boolean deleteDepartmentByNo(long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
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
			String sql = 
					"delete "
					+ "from dept" +    
					" where no=" + no;
			pstmt = conn.prepareStatement(sql);
			// 4. binding
			pstmt.setLong(1, no);
			
			// 5. sql 실행
			
			int count = pstmt.executeUpdate();
			// rs =  stmt.executeQuery(sql);
			
			// 6. 결과처리
			result = count == 1;
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 7. 자원 처리
				if (pstmt != null) {
					pstmt.close();
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
