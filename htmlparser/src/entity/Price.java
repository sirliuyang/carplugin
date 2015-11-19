package entity;

import javax.annotation.Resource;

@Resource
public class Price {
	private int id;
	private float price;
	private String month;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	@Override
	public String toString(){
		return this.price + ", "+this.month+";";
	}
	
}
