package bookmall.dao.test;

import java.util.List;

import bookmall.dao.UserDao;
import bookmall.vo.BookmallUserVo;

public class UserDaoTest {

	public static void main(String[] args) {
		memberInsertTest();
		memberFindAllTest();
	}

	public static void memberInsertTest() {
		BookmallUserVo userVo = new BookmallUserVo();
		UserDao userDao = new UserDao();

		userVo.setUserName("안대혁");
		userVo.setUserPhoneNumber("010-1234-1234");
		userVo.setUserEmail("bit1@bit.com");
		userVo.setUserPassWord("bitbit1");
		userDao.memberInsert(userVo);

		userVo.setUserName("김종혁");
		userVo.setUserPhoneNumber("010-1234-2345");
		userVo.setUserEmail("bit2@bit.com");
		userVo.setUserPassWord("bitbit2");
		userDao.memberInsert(userVo);

		userVo.setUserName("서정권");
		userVo.setUserPhoneNumber("010-1234-3456");
		userVo.setUserEmail("bit3@bit.com");
		userVo.setUserPassWord("bitbit3");
		userDao.memberInsert(userVo);
	}

	public static void memberFindAllTest() {
		UserDao userDao = new UserDao();
		List<BookmallUserVo> user_Lists = userDao.memberFindAll();
		
		for (BookmallUserVo user_list : user_Lists) {
			System.out.println("이름 : " + user_list.getUserName() + " 전화번호 : " + user_list.getUserPhoneNumber() + " 이메일 : " + user_list.getUserEmail());
		}
	}
}
