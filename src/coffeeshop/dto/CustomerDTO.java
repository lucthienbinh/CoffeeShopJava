package coffeeshop.dto;

public class CustomerDTO {
    int id;
    String name;
    String address;
    String mobilephone;
    String email;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public CustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerDTO(int id, String name, String address, String mobilephone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.mobilephone = mobilephone;
		this.email = email;
	}
	
	public void generateEmptyObjectForJSP() {
		this.email = "";
		this.name = "";
		this.address = "";
		this.mobilephone = "";
	}
}
