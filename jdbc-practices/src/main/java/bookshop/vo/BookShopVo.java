package bookshop.vo;

public class BookShopVo {
	private long bookNo;
	private String title;
	private String rent;
	private String authorNo;
	private String authorName;
	
	public long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getAuthorNo() {
		return authorNo;
	}
	public void setAuthorNo(String authorNo) {
		this.authorNo = authorNo;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	@Override
	public String toString() {
		return "BookVo [bookNo=" + bookNo + ", title=" + title + ", rent=" + rent + ", authorNo=" + authorNo
				+ ", authorName=" + authorName + "]";
	}

}
