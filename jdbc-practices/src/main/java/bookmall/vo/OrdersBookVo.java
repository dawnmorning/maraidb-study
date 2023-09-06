package bookmall.vo;

public class OrdersBookVo {
	private int orders_no;
	private int book_bookNo;
	private String bookName;
	private int quantity;
	private int bookPrice;

	public int getOrders_no() {
		return orders_no;
	}

	public void setOrders_no(int orders_no) {
		this.orders_no = orders_no;
	}

	public int getBook_bookNo() {
		return book_bookNo;
	}

	public void setBook_bookNo(int book_bookNo) {
		this.book_bookNo = book_bookNo;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	@Override
	public String toString() {
		return "OrdersBookVo [orders_no=" + orders_no + ", book_bookNo=" + book_bookNo + ", bookName=" + bookName
				+ ", quantity=" + quantity + ", bookPrice=" + bookPrice + "]";
	}

}
