package com.tawan.java.data.remote.reqres.invoice;

import com.google.gson.annotations.SerializedName;

public class OrderedItemItem{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("id_invoice")
	private int idInvoice;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("menu")
	private Object menu;

	@SerializedName("order")
	private Order order;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setIdInvoice(int idInvoice){
		this.idInvoice = idInvoice;
	}

	public int getIdInvoice(){
		return idInvoice;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setMenu(Object menu){
		this.menu = menu;
	}

	public Object getMenu(){
		return menu;
	}

	public void setOrder(Order order){
		this.order = order;
	}

	public Order getOrder(){
		return order;
	}
}