package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bookmall.vo.CartVo;

public class CartDao {

	public void cartInsert(CartVo cartVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.174:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
			
			String sql = "insert into cart (user_no, book_bookNo, quantity) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cartVo.getUserNo());
			pstmt.setInt(2, cartVo.getBookNo());
			pstmt.setInt(3, cartVo.getQuantity());
			pstmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<CartVo> cartFindAll() {
		List<CartVo> cartList = new ArrayList<CartVo>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.174:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

			String sql = "SELECT"
					+ "    u.name, b.title, c.quantity, c.quantity * b.price"
					+ " FROM"
					+ "    book b"
					+ "        JOIN"
					+ "    cart c ON b.bookNo = c.book_bookNo"
					+ "        JOIN"
					+ "    user u ON c.user_no = u.no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String userName = rs.getString(1);
				String bookTitle = rs.getString(2);
				int quantity = rs.getInt(3);
				int price = rs.getInt(4);
				

				CartVo cartVo = new CartVo();
				cartVo.setUserName(userName);
				cartVo.setBookTitle(bookTitle);
				cartVo.setQuantity(quantity);
				cartVo.setPrice(price);

				cartList.add(cartVo);
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
		return cartList;
	}
}
