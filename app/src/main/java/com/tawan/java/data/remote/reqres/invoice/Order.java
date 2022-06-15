package com.tawan.java.data.remote.reqres.invoice;

import com.google.gson.annotations.SerializedName;

public class Order{

	@SerializedName("id_menu")
	private int idMenu;

	@SerializedName("notes")
	private String notes;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("price")
	private String price;

	@SerializedName("menu_name")
	private String menuName;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_resto")
	private int idResto;

	@SerializedName("id")
	private int id;

	@SerializedName("id_user")
	private int idUser;

	@SerializedName("price_multiplied")
	private int priceMultiplied;

	@SerializedName("menu")
	private Menu menu;

	public void setIdMenu(int idMenu){
		this.idMenu = idMenu;
	}

	public int getIdMenu(){
		return idMenu;
	}

	public void setNotes(String notes){
		this.notes = notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setMenuName(String menuName){
		this.menuName = menuName;
	}

	public String getMenuName(){
		return menuName;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setIdResto(int idResto){
		this.idResto = idResto;
	}

	public int getIdResto(){
		return idResto;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
	}

	public void setPriceMultiplied(int priceMultiplied){
		this.priceMultiplied = priceMultiplied;
	}

	public int getPriceMultiplied(){
		return priceMultiplied;
	}

	public void setMenu(Menu menu){
		this.menu = menu;
	}

	public Menu getMenu(){
		return menu;
	}
}