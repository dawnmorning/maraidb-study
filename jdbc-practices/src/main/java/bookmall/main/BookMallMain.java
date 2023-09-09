package bookmall.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import bookmall.dao.test.BookDaoTest;
import bookmall.dao.test.CartDaoTest;
import bookmall.dao.test.CategoryDaoTest;
import bookmall.dao.test.OrdersDaoTest;
import bookmall.dao.test.UserDaoTest;

public class BookMallMain {
	
	// 이 부분 ip 수정하셔서 사용하시면 됩니다.
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String URL = "jdbc:mariadb://192.168.0.174:3307/bookmall?charset=utf8";
    private static final String USER = "bookmall";
    private static final String PASSWORD = "bookmall";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
	public static void main(String[] args) {
		
		CategoryDaoTest.CategoryInsertTest(); // category 삽입
		BookDaoTest.bookInsertTest(); // book 삽입
		UserDaoTest.memberInsertTest(); // user 삽입
		CartDaoTest.cartInsertTest(); // cart 삽입
		OrdersDaoTest.ordersInsertTest(); // 주문내역 삽입

		System.out.println("## 회원 리스트 ##");
		UserDaoTest.memberFindAllTest();
		System.out.println("");

		System.out.println("## 카테고리 리스트 ##");
		CategoryDaoTest.CategoryFindAllTest();
		System.out.println("");

		System.out.println("## 도서 리스트 ##");
		BookDaoTest.bookFindAllTest();
		System.out.println("");

		System.out.println("## 카트 리스트 ##");
		CartDaoTest.cartFindAllTest();
		System.out.println("");

		System.out.println("## 주문 리스트 ##");
		OrdersDaoTest.ordersFindAllTest();
		System.out.println("");
		System.out.println("## 주문 도서 리스트 ##");
		OrdersDaoTest.ordersBookFindAllTest();
	}
}
