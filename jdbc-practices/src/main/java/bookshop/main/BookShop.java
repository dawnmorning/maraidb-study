package bookshop.main;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookShopDao;
import bookshop.vo.BookShopVo;

public class BookShop {

	public static void main(String[] args) {
		displayBookInfo();

		Scanner scanner = new Scanner(System.in);
		while (true) {
			displayBookInfo();
			System.out.println("\n메뉴를 선택하세요: ");
			System.out.println("1. 대여");
			System.out.println("2. 반납");
			System.out.println("3. 종료");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.print("대여 하고 싶은 책의 번호를 입력하세요: ");
				long no = scanner.nextInt();
				updateBookStatus(no, "Y");
				displayBookInfo();
				break;
			case 2:
				System.out.print("반납 하고 싶은 책의 번호를 입력하세요: ");
				no = scanner.nextInt();
				updateBookStatus(no, "N");
				displayBookInfo();
				break;
			case 3:
				scanner.close();
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
				displayBookInfo();
				break;
			}
		}
	}

	public static void updateBookStatus(long bookNo, String rentStatus) {
	    BookShopDao dao = new BookShopDao();
	    String currentStatus = dao.checkRentStatus(bookNo);

	    if ("Y".equals(currentStatus) && "Y".equals(rentStatus)) {
	        System.out.println("이미 대여 중인 책입니다. 다시 확인해주세요.");
	        return;
	    } else if ("N".equals(currentStatus) && "N".equals(rentStatus)) {
	        System.out.println("이미 반납된 책입니다. 다시 확인해주세요.");
	        return;
	    }

	    BookShopVo vo = new BookShopVo();
	    vo.setBookNo(bookNo);
	    vo.setRent(rentStatus);
	    dao.updateRent(vo);
	    System.out.println(rentStatus == "Y" ? bookNo + "번 책을 대여하였습니다." : bookNo + "번 책을 반납하였습니다.");
	}

	public static void displayBookInfo() {
		System.out.println("##### 현재 도서 정보입니다. #####");
		List<BookShopVo> list = new BookShopDao().findAll();
		for (BookShopVo vo : list) {
			System.out.println(vo);
		}
	}

}
