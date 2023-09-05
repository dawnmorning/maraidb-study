package emaillist.main;

import java.util.List;
import java.util.Scanner;

import emaillist.dao.EmailListDao;
import emaillist.vo.EmailListVo;

public class EmailListApp {
	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.println("(l)ist (i)nsert (d)elete (q)uit > ");
			String command = scanner.nextLine();
			if ("l".equals(command)) {
				doList();
			} else if ("i".equals(command)){
				doInsert();
			} else if ("d".equals(command)) {
				doDelete();
			} else if ("q".equals(command)) {
				System.out.println("You are out");
				scanner.close();
				break;
			}
		}
	}

	private static void doDelete() {
		System.out.println("Please type email what you delete >>");
		String email = scanner.nextLine();
		new EmailListDao().deleteByEmail(email);
		System.out.println("----업데이트 된 목록 ----");
		doList();
	}

	private static void doInsert() {
		System.out.println("성: ");
		String firstName = scanner.nextLine();
		
		System.out.println("이름 : ");
		String lastName = scanner.nextLine();
		
		System.out.println("이메일 : ");
		String emailString = scanner.nextLine();
		
		EmailListVo vo = new EmailListVo();
		vo.setFirstName(firstName);
		vo.setLastName(lastName);
		vo.setEmail(emailString);
		new EmailListDao().insert(vo);
		System.out.println("----업데이트 된 목록 ----");
		doList();
	}

	private static void doList() {
		// list 구현
		List<EmailListVo> list = new EmailListDao().findAll();
		for (EmailListVo vo : list) {
			System.out.println("이름 : " + vo.getFirstName() + vo.getLastName() + ", 이메일 : " + vo.getEmail());
		}
	}

}
