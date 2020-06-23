package coffeeshop.dto;

public class MaterialBilllDTO {
	int id;
    int userId;
    int totalPrice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public MaterialBilllDTO(int id, int userId, int totalPrice) {
		super();
		this.id = id;
		this.userId = userId;
		this.totalPrice = totalPrice;
	}
	public MaterialBilllDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void generateEmptyObjectForJSP() {
		this.userId = 0;
		this.totalPrice = 0;
	}
}
