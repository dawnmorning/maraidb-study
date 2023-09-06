package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		CategoryInsertTest();
		CategoryFindAllTest();
	}

	public static void CategoryFindAllTest() {
		CategoryDao categoryDao = new CategoryDao();
		
		List<CategoryVo> category_lists =  categoryDao.categoryFindAll();
		for (CategoryVo category_list : category_lists) {
			System.out.println("카테고리 번호: "+ category_list.getCategoryNo()+ ","  + " 카테고리 이름: " + category_list.getName());
		}
	}

	public static void CategoryInsertTest() {
		// 1. Vo 먼저 생성
		CategoryVo categoryVo = new CategoryVo();
		
		// 2. Vo 만들고 Dao에 담기
		CategoryDao categoryDao = new CategoryDao();
		
		categoryVo.setName("역사/문화");
		categoryDao.categoryInsert(categoryVo);
		
		categoryVo.setName("IT");
		categoryDao.categoryInsert(categoryVo);
		
		categoryVo.setName("인문");
		categoryDao.categoryInsert(categoryVo);
	}
	
}
