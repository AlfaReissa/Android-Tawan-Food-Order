package com.tawan.java.data.remote.reqres.addtocart;

import com.google.gson.annotations.SerializedName;

public class ResData{

	@SerializedName("id_menu")
	private String idMenu;

	@SerializedName("notes")
	private String notes;

	@SerializedName("quantity")
	private String quantity;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("price")
	private String price;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_resto")
	private String idResto;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("id")
	private int id;

	@SerializedName("price_multiplied")
	private int priceMultiplied;

	public String getIdMenu(){
		return idMenu;
	}

	public String getNotes(){
		return notes;
	}

	public String getQuantity(){
		return quantity;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getPrice(){
		return price;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getIdResto(){
		return idResto;
	}

	public String getIdUser(){
		return idUser;
	}

	public int getId(){
		return id;
	}

	public int getPriceMultiplied(){
		return priceMultiplied;
	}
}