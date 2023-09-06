package bookmall.main;

import bookmall.dao.test.BookDaoTest;
import bookmall.dao.test.CartDaoTest;
import bookmall.dao.test.CategoryDaoTest;
import bookmall.dao.test.OrdersDaoTest;
import bookmall.dao.test.UserDaoTest;

public class BookMallMain {

	public static void main(String[] args) {

		CategoryDaoTest.CategoryInsertTest(); // category 삽입
		BookDaoTest.bookInsertTest(); // book 삽입
		UserDaoTest.memberInsertTest(); // user 삽입
		CartDaoTest.cartInsertTest(); // cart 삽입
		OrdersDaoTest.ordersInsertTest(); // 주문내역 삽입

		System.out.println("## 회원리스트 ##");
		UserDaoTest.memberFindAllTest();

		System.out.println("## 카테고리 ##");
		CategoryDaoTest.CategoryFindAllTest();

		System.out.println("## 도서 ##");
		BookDaoTest.bookFindAllTest();

		System.out.println("## 카트 ##");
		CartDaoTest.cartFindAllTest();

		System.out.println("## 주문 ##");
		OrdersDaoTest.ordersFindAllTest();
		OrdersDaoTest.ordersBookFindAllTest();
	}
}
