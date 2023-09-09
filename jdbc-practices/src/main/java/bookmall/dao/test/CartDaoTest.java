package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		cartInsertTest();
		cartFindAllTest();
	}

	public static void cartFindAllTest() {
		CartDao cartDao = new CartDao();
		
		List<CartVo> cartLists = cartDao.cartFindAll();
		for(CartVo cartList : cartLists) {
			System.out.println("도서 명: " + cartList.getBookTitle()+ "|" + "수량: " + cartList.getQuantity()+ "|"  + "가격: " + cartList.getPrice()+"원");
		}
	}

	public static void cartInsertTest() {
		CartVo cartVo = new CartVo();
		CartDao cartDao = new CartDao();
		
		cartVo.setBookNo(1);
		cartVo.setUserNo(1);
		cartVo.setQuantity(1);
		
		cartDao.cartInsert(cartVo);
		
		cartVo.setBookNo(2);
		cartVo.setUserNo(1);
		cartVo.setQuantity(1);
		cartDao.cartInsert(cartVo);
		
	}


}
