package coffeeshop.dto;

public class MaterialBillDetailDTO {
	int id;
    int materialId;
	int quantity;
	int amount;
    int price;
	int materialBillId;
	String materialName;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMaterialId() {
		return materialId;
	}
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getMaterialBillId() {
		return materialBillId;
	}
	public void setMaterialBillId(int materialBillId) {
		this.materialBillId = materialBillId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public MaterialBillDetailDTO(int id, int materialId, int quantity, int materialBillId, String materialName, int amount, int price) {
		super();
		this.id = id;
		this.materialId = materialId;
		this.quantity = quantity;
		this.materialBillId = materialBillId;
		this.materialName = materialName;
		this.amount = amount;
		this.price = price;
	}
	public MaterialBillDetailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
