package coffeeshop.dto;

public class OrderingBillDTO {
	int id;
    int customerId;
    int userId;
    int totalPrice;
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
	public OrderingBillDTO(int id, int customerId, int userId, int totalPrice) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.userId = userId;
		this.totalPrice = totalPrice;
	}
	public OrderingBillDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void generateEmptyObjectForJSP() {
		this.customerId = 0;
		this.userId = 0;
		this.totalPrice = 0;
	}
}
