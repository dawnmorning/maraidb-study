package emaillist.dao.test;

import java.lang.invoke.VolatileCallSite;
import java.util.List;

import emaillist.dao.EmailListDao;
import emaillist.vo.EmailListVo;

public class EmailListDaoTest {
	
	// 1. Test에서 Vo객체 만들자!
	public static void main(String[] args) {
		// 2. vo만들기
		EmailListVo vo = new EmailListVo();
		vo.setFirstName("둘");
		vo.setLastName("리");
		vo.setEmail("2ly@gmail.com");
		
		// 3. method 만들기
		testInsert(vo);
		tsetFindAll();
		testDeleteByEmail("2ly@gmail.com");
		tsetFindAll();
	}

	private static void testDeleteByEmail(String email) {
		new EmailListDao().deleteByEmail(email);
	}
	
	private static void tsetFindAll() {
		// list 구현
		List<EmailListVo> list = new EmailListDao().findAll();
		for (EmailListVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void testInsert(EmailListVo vo) {
		new EmailListDao().insert(vo);
	}

}
