package bookmall.vo;

public class CategoryVo {
	private int categoryNo;
	private String name;
	
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CategoryVo [categoryNo=" + categoryNo + ", name=" + name + "]";
	}
	
}
