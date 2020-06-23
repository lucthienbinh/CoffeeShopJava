package coffeeshop.dto;

public class MaterialDTO {
    int id;
    String name;
    int price;
    int remaining;
    String unit;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRemaining() {
		return remaining;
	}
	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public MaterialDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MaterialDTO(int id, String name, int price, int remaining, String unit) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.remaining = remaining;
		this.unit = unit;
	}
	public void generateEmptyObjectForJSP() {
		this.name = "";
		this.price = 0;
		this.remaining = 0;
		this.unit = "";
	}
}
