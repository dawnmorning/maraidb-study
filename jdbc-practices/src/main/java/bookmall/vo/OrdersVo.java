package bookmall.vo;

public class OrdersVo {
	private int no;
	private int orderNo;
	private int quantity;
	private int price;
	private String address;
	private int userNo;
	private String userName;
	private String userPhoneNumber;
	public int getNo() {
		return no;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "OrdersVo [no=" + no + ", orderNo=" + orderNo + ", quantity=" + quantity + ", price=" + price
				+ ", address=" + address + ", userNo=" + userNo + ", userName=" + userName + ", userPhoneNumber="
				+ userPhoneNumber + "]";
	}
}
