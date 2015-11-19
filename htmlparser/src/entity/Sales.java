package entity;

import java.sql.Date;

public class Sales {
	private int id;
	private String carname;
	private int sale_num;
	private Date month;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSale_num() {
		return sale_num;
	}
	public void setSale_num(int sale_num) {
		this.sale_num = sale_num;
	}
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	
	@Override
	public String toString(){
		return id+", "+carname+", "+sale_num+", "+month.toString()+";";	
	}
}
