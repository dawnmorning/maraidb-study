package bookmall.dao.test;

import java.util.List;

import bookmall.dao.OrdersDao;
import bookmall.vo.OrdersBookVo;
import bookmall.vo.OrdersVo;

public class OrdersDaoTest {

	public static void main(String[] args) {
		ordersInsertTest();
		ordersFindAllTest();
		ordersBookFindAllTest();
	}

	public static void ordersBookFindAllTest() {
		OrdersDao OD = new OrdersDao();
		List<OrdersBookVo> OBVLists = OD.ordersBookFindAll();	
		for(OrdersBookVo OBVList : OBVLists) {
			System.out.println("책 제목: " + OBVList.getBookName() + "," + " 책 수량: " + OBVList.getQuantity() + "," + " 책 가격:" + OBVList.getBookPrice());
		}
	}

	public static void ordersFindAllTest() {
		OrdersDao OD = new OrdersDao();
		List<OrdersVo> oVLists = OD.ordersFindAll();

		for (OrdersVo oVList : oVLists) {
			System.out.println(
					"주문자 이름 :" + oVList.getUserName() + "," + " 전화번호: " + oVList.getUserPhoneNumber()+ "," + " 주문 번호: "
							+ oVList.getOrderNo()+ "," + " 총 가격: " + oVList.getPrice()+ "," + " 주소: " + oVList.getAddress());
		}

	}

	public static void ordersInsertTest() {
		OrdersVo OV = new OrdersVo();
		OrdersBookVo OBV = new OrdersBookVo();
		OrdersDao OD = new OrdersDao();

		OV.setNo(1);
		OV.setOrderNo(2023090601);
		OV.setPrice(70000);
		OV.setAddress("비트교육센터");
		OV.setUserNo(1);
		OD.ordersInsert(OV);

		OBV.setBook_bookNo(1);
		OBV.setQuantity(1);
		
		OBV.setBook_bookNo(2);
		OBV.setQuantity(1);
		OD.insert(OBV);

	}

}
