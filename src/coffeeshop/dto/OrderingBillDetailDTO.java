package coffeeshop.dto;

public class OrderingBillDetailDTO {
	int id;
    int orderMenuId;
    int quantity;
    int orderingBillId;
    int price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderMenuId() {
		return orderMenuId;
	}
	public void setOrderMenuId(int orderMenuId) {
		this.orderMenuId = orderMenuId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getOrderingBillId() {
		return orderingBillId;
	}
	public void setOrderingBillId(int orderingBillId) {
		this.orderingBillId = orderingBillId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public OrderingBillDetailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderingBillDetailDTO(int id, int orderMenuId, int quantity, int orderingBillId, int price) {
		super();
		this.id = id;
		this.orderMenuId = orderMenuId;
		this.quantity = quantity;
		this.orderingBillId = orderingBillId;
		this.price = price;
	}
}
