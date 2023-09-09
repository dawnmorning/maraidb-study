package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.main.BookMallMain;
import bookmall.vo.BookmallUserVo;

public class UserDao {

	public boolean memberInsert(BookmallUserVo userVo) {
		boolean result = false;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = BookMallMain.getConnection();

			String sql = "Insert into user values(null,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userVo.getUserName());
			pstmt.setString(2, userVo.getUserPhoneNumber());
			pstmt.setString(3, userVo.getUserEmail());
			pstmt.setString(4, userVo.getUserPassWord());

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<BookmallUserVo> memberFindAll() {
		List<BookmallUserVo> result = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = BookMallMain.getConnection();

			String sql = "select * from user";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int no = rs.getInt(1);
				String userName = rs.getString(2);
				String userPhoneNumber = rs.getString(3);
				String userEmail = rs.getString(4);
				String userPassWord = rs.getString(5);

				BookmallUserVo vo = new BookmallUserVo();
				vo.setUserNum(no);
				vo.setUserName(userName);
				vo.setUserPhoneNumber(userPhoneNumber);
				vo.setUserEmail(userEmail);
				vo.setUserPassWord(userPassWord);

				result.add(vo);
//				System.out.println(empNo + " : " +  firstName + lastName);

			}

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
