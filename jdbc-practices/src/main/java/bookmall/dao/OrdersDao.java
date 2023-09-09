package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.main.BookMallMain;
import bookmall.vo.OrdersBookVo;
import bookmall.vo.OrdersVo;

public class OrdersDao {

	public boolean ordersInsert(OrdersVo OV) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = BookMallMain.getConnection();
			String sql = "insert into orders values(null,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, OV.getOrderNo());
			pstmt.setInt(2, OV.getPrice());
			pstmt.setString(3, OV.getAddress());
			pstmt.setInt(4, OV.getUserNo());

			int count = pstmt.executeUpdate();
			result = (count == 1);
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
		return result;
	}

	public boolean insert(OrdersBookVo oBV) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Boolean result = false;
		try {
			conn = BookMallMain.getConnection();

			String sql = "insert into orders_book values(?,?,?) ON DUPLICATE KEY UPDATE quantity=quantity+?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, 1);
			pstmt.setInt(2, oBV.getBook_bookNo());
			pstmt.setInt(3, oBV.getQuantity());
			pstmt.setInt(4, oBV.getQuantity());

			int count = pstmt.executeUpdate();

			result = (count == 1 || count == 2);
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
		return result;
	}

	public List<OrdersVo> ordersFindAll() {
		List<OrdersVo> OrdersList = new ArrayList<OrdersVo>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = BookMallMain.getConnection();

			String sql = "select orders.no, orders.orderNo, user.name, user.phone, orders.price, orders.address \r\n"
					+ "from user join orders on user.no = orders.user_no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt(1);
				int orderNo = rs.getInt(2);
				String name = rs.getString(3);
				String phone = rs.getString(4);
				int price = rs.getInt(5);
				String address = rs.getString(6);

				OrdersVo OV = new OrdersVo();
				OV.setNo(no);
				OV.setOrderNo(orderNo);
				OV.setUserName(name);
				OV.setUserPhoneNumber(phone);
				OV.setPrice(price);
				OV.setAddress(address);

				OrdersList.add(OV);
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
		return OrdersList;
	}

	public List<OrdersBookVo> ordersBookFindAll() {
		List<OrdersBookVo> OrdersBookList = new ArrayList<OrdersBookVo>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = BookMallMain.getConnection();

			String sql = "select book.title, orders_book.quantity, book.price from orders_book join book on orders_book.book_bookNo = book.bookNo";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String title = rs.getString(1);
				int quantity = rs.getInt(2);
				int price = rs.getInt(3);

				OrdersBookVo OBV = new OrdersBookVo();
				OBV.setBookName(title);
				OBV.setQuantity(quantity);
				OBV.setBookPrice(price);

				OrdersBookList.add(OBV);
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
		return OrdersBookList;
	}

}
