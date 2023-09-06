package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bookmall.vo.BookVo;

public class BookDao {
	public boolean bookInsert(BookVo bookVo) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		boolean result = false;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.174:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

			String sql = "insert into book values(null,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bookVo.getTitle());
			pstmt.setInt(2, bookVo.getPrice());
			pstmt.setLong(3, bookVo.getCategory_no());

			int count = pstmt.executeUpdate();

			result = (count == 1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
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

	public List<BookVo> bookFindAll() {
		List<BookVo> category_lists = new ArrayList<BookVo>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.174:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

			String sql = "select b.bookNo, b.title, b.price, b.bookNo, c.name" + " from book b " 
					+ "join category c on b.bookNo = c.no" + " order by b.bookNo";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int bookNo = rs.getInt(1);
				String bookTitle = rs.getString(2);
				int bookPrice = rs.getInt(3);
				int categoryNo = rs.getInt(4);
				String categoryName = rs.getString(5);

				BookVo bookVo = new BookVo();
				bookVo.setBookNo(bookNo);
				bookVo.setTitle(bookTitle);
				bookVo.setPrice(bookPrice);
				bookVo.setCategory_no(categoryNo);
				bookVo.setCaetgory_name(categoryName);

				category_lists.add(bookVo);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
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
		return category_lists;
	}
}