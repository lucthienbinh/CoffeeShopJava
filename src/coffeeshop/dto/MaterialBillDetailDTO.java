package coffeeshop.dto;

public class MaterialBillDetailDTO {
	int id;
    int materialId;
    int quantity;
    int materialBillId;
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
	public MaterialBillDetailDTO(int id, int materialId, int quantity, int materialBillId) {
		super();
		this.id = id;
		this.materialId = materialId;
		this.quantity = quantity;
		this.materialBillId = materialBillId;
	}
	public MaterialBillDetailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
