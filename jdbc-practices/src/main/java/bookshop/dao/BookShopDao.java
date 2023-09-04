package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bookshop.vo.BookShopVo;

public class BookShopDao {

	public List<BookShopVo> findAll() {
		List<BookShopVo> result = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.0.172:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			String sql = "select *" + "from book";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long bookNo = rs.getLong(1);
				String title = rs.getString(2);
				String rent = rs.getString(3);

				BookShopVo vo = new BookShopVo();
				vo.setBookNo(bookNo);
				vo.setTitle(title);
				vo.setRent(rent);
				result.add(vo);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean updateRent(BookShopVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.172:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL 준비
			String sql = "update book" + " set rent=?" + "where no=?";
			pstmt = conn.prepareStatement(sql);

			// 4. binding
			pstmt.setString(1, vo.getRent());
			pstmt.setLong(2, vo.getBookNo());

			// 5. SQL 실행
			int count = pstmt.executeUpdate();

			// 6. 결과 처리
			result = count == 1;

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				// 6. 자원정리
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public String checkRentStatus(long bookNo) {
	    String rentStatus = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        Class.forName("org.mariadb.jdbc.Driver");
	        String url = "jdbc:mariadb://192.168.0.172:3307/webdb?charset=utf8";
	        conn = DriverManager.getConnection(url, "webdb", "webdb");
	        
	        String sql = "select rent from book where no=?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setLong(1, bookNo);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            rentStatus = rs.getString(1);
	        }
	    } catch (ClassNotFoundException e) {
	        System.out.println("드라이버 로딩 실패:" + e);
	    } catch (SQLException e) {
	        System.out.println("error:" + e);
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (pstmt != null) {
	                pstmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return rentStatus;
	}
}
