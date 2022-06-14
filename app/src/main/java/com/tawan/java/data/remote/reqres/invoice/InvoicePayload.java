package com.tawan.java.data.remote.reqres.invoice;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class InvoicePayload{

	@SerializedName("cart_ids")
	private List<String> cartIds;

	@SerializedName("address")
	private String address;

	@SerializedName("notes")
	private String notes;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("id_resto")
	private String idResto;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("longitude")
	private String longitude;

	public List<String> getCartIds(){
		return cartIds;
	}

	public String getAddress(){
		return address;
	}

	public String getNotes(){
		return notes;
	}

	public String getLatitude(){
		return latitude;
	}

	public String getIdResto(){
		return idResto;
	}

	public String getIdUser(){
		return idUser;
	}

	public String getLongitude(){
		return longitude;
	}
}