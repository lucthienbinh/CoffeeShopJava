package coffeeshop.dto;

public class OrderingBillDTO {
	int id;
    int customerId;
    int userId;
    int totalPrice;
    String customerName;
    String userName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public OrderingBillDTO(int id, int customerId, int userId, int totalPrice, String customerName, String userName) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.customerName = customerName;
		this.userName = userName;
	}
	public OrderingBillDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void generateEmptyObjectForJSP() {
		this.customerName = "";
		this.userName = "";
		this.totalPrice = 0;
	}
}
