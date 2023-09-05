package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CategoryVo;

public class CategoryDao {

	public boolean categoryInsert(CategoryVo categoryVo) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		boolean result = false;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.174:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

			String sql = "insert into category values(null,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, categoryVo.getName());

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

	public List<CategoryVo> categoryFindAll() {
		List<CategoryVo> category_List = new ArrayList<CategoryVo>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.174:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

			String sql = "select * from category;";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int categoryNo = rs.getInt(1);
				String categoryName = rs.getString(2);
				
				CategoryVo categoryVo = new CategoryVo();
				categoryVo.setCategoryNo(categoryNo);
				categoryVo.setName(categoryName);
				
				category_List.add(categoryVo);
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
		return category_List;
	}
}
