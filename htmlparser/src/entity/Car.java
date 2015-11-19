package entity;

public class Car {
	private int id;
	private String carname;
	private String carcompany;
	private String carbrand;
	private int total_sales;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public String getCarcompany() {
		return carcompany;
	}
	public void setCarcompany(String carcompany) {
		this.carcompany = carcompany;
	}
	public String getCarbrand() {
		return carbrand;
	}
	public void setCarbrand(String carbrand) {
		this.carbrand = carbrand;
	}
	public int getTotal_sales() {
		return total_sales;
	}
	public void setTotal_sales(int total_sales) {
		this.total_sales = total_sales;
	}
	
	@Override
	public String toString(){
		return carname+", "+carcompany+", "+carbrand+", "+total_sales+";";	
	}
}
