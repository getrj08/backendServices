package com.rj.library.shopping.models;

public class OrderModel {
	
	public String id;
	public int count;
	public int totalBookPrice;
	
	
	public int getTotalBookPrice() {
		return totalBookPrice;
	}
	public void setTotalBookPrice(int totalBookPrice) {
		this.totalBookPrice = totalBookPrice;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
