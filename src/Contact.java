
public class Contact {
	private String First_Name;
	private String Last_Name;
	private String Address;
	private String City;
	private String County;
	private String Phone_Num;
	private String Mobile_Num;
	private String Birth_Date;
	
	// Contact Constructor
	public Contact (String First_Name,String Last_Name,String Address,String City,String County,String Phone_Num,String Mobile_Num,String Birth_Date){
		this.First_Name=First_Name;
		this.Last_Name=Last_Name;
		this.Address=Address;
		this.City=City;
		this.County=County;
		this.Phone_Num=Phone_Num;
		this.Mobile_Num=Mobile_Num;
		this.Birth_Date=Birth_Date;
	}
	// Getters and Setters for Contact Fields
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getCounty() {
		return County;
	}
	public void setCounty(String county) {
		County = county;
	}
	public String getPhone_Num() {
		return Phone_Num;
	}
	public void setPhone_Num(String phone_Num) {
		Phone_Num = phone_Num;
	}
	public String getMobile_Num() {
		return Mobile_Num;
	}
	public void setMobile_Num(String mobile_Num) {
		Mobile_Num = mobile_Num;
	}
	public String getBirth_Date() {
		return Birth_Date;
	}
	public void setBirth_Date(String birth_Date) {
		Birth_Date = birth_Date;
	}
	
	public void printContact(){
		System.out.println("Name: "+ getFirst_Name() + " " + getLast_Name());
		System.out.println("Date of Birth: " +getBirth_Date());
		System.out.println("Address: " + getAddress());
		System.out.println("City: " + getCity());
		System.out.println("County:  "+ getCounty());
		System.out.println("Phone Num.: " + getPhone_Num());
		System.out.println("Mobile Num.: " + getMobile_Num());
		
	}
	
}
