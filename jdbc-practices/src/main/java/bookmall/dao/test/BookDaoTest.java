package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		bookInsertTest();
		bookFindAllTest();
	}

	public static void bookFindAllTest() {
		BookDao bookDao = new BookDao();
		
		List<BookVo> bookLists = bookDao.bookFindAll();
		for(BookVo bookList : bookLists ) {
			System.out.println("번호: " + bookList.getBookNo() + ":" + " 책 제목: " + bookList.getTitle()+ "," + " 책 가격: " + bookList.getPrice()+ "," + 
					" 카테고리 번호: " + bookList.getCategory_no()+ "," + " 카테고리 이름: " + bookList.getCaetgory_name());
		}
	}

	public static void bookInsertTest() {
		BookVo bookVo =  new BookVo();
		BookDao bookDao = new BookDao();
		
		bookVo.setBookNo(1);
		bookVo.setTitle("총,균,쇠");
		bookVo.setPrice(30000);
		bookVo.setCategory_no(1);
		bookDao.bookInsert(bookVo);
		
		bookVo.setBookNo(2);
		bookVo.setTitle("이것이 Java다");
		bookVo.setPrice(40000);
		bookVo.setCategory_no(2);
		bookDao.bookInsert(bookVo);
		
		bookVo.setBookNo(3);
		bookVo.setTitle("무소유");
		bookVo.setPrice(15000);
		bookVo.setCategory_no(3);
		bookDao.bookInsert(bookVo);
	}

}