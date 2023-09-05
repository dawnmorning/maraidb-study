package bookmall.vo;

public class BookVo {
	private int bookNo;
	private String title;
	private int price;
	private int category_no;
	private String caetgory_name;

	public String getCaetgory_name() {
		return caetgory_name;
	}
	public void setCaetgory_name(String caetgory_name) {
		this.caetgory_name = caetgory_name;
	}
	public int getCategory_no() {
		return category_no;
	}
	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	@Override
	public String toString() {
		return "BookmallVo [bookNo=" + bookNo + ", title=" + title + ", price=" + price + ", category_no=" + category_no
				+ "]";
	}
}
