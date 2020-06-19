package coffeeshop.dto;

public class UserDTO {
    int id;
    String email;
    String password;
    String firstname;
    String lastname;
    String sex;
    String address;
    String mobilephone;
    int groupid;
    String groupname;
	public UserDTO() {
		super();
	}
	public void generateEmptyObjectForJSP() {
		this.email = "";
		this.password = "";
		this.firstname = "";
		this.lastname = "";
		this.sex = "";
		this.address = "";
		this.mobilephone = "";
		this.groupid = 0;
	}
	
	public UserDTO(int id, String email, String password, String firstname, String lastname, String sex, String address,
			String mobilephone, int groupid, String groupname) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.sex = sex;
		this.address = address;
		this.mobilephone = mobilephone;
		this.groupid = groupid;
		this.groupname = groupname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}
