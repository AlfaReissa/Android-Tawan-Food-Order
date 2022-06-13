package com.tawan.java.data.remote.reqres.cart;

import com.google.gson.annotations.SerializedName;

public class OrderedItemItem{

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

	public int getIdMenu(){
		return idMenu;
	}

	public String getNotes(){
		return notes;
	}

	public int getQuantity(){
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

	public int getIdResto(){
		return idResto;
	}

	public int getId(){
		return id;
	}

	public int getIdUser(){
		return idUser;
	}

	public int getPriceMultiplied(){
		return priceMultiplied;
	}

	@Override
 	public String toString(){
		return 
			"OrderedItemItem{" + 
			"id_menu = '" + idMenu + '\'' + 
			",notes = '" + notes + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",price = '" + price + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id_resto = '" + idResto + '\'' + 
			",id = '" + id + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",price_multiplied = '" + priceMultiplied + '\'' + 
			"}";
		}
}