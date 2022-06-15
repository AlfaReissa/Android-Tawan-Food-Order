package com.tawan.java.data.remote.reqres.invoice;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UserInvoiceResponseItem{

	@SerializedName("photo_payment_path")
	private Object photoPaymentPath;

	@SerializedName("address")
	private String address;

	@SerializedName("notes")
	private String notes;

	@SerializedName("total_price")
	private int totalPrice;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("metode_bayar")
	private Object metodeBayar;

	@SerializedName("id_user")
	private int idUser;

	@SerializedName("total_price_rupiah_format")
	private String totalPriceRupiahFormat;

	@SerializedName("long")
	private Object jsonMemberLong;

	@SerializedName("ordered_item_names")
	private List<String> orderedItemNames;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("lat")
	private Object lat;

	@SerializedName("status")
	private String status;

	@SerializedName("ordered_item")
	private List<OrderedItemItem> orderedItem;

	public void setPhotoPaymentPath(Object photoPaymentPath){
		this.photoPaymentPath = photoPaymentPath;
	}

	public Object getPhotoPaymentPath(){
		return photoPaymentPath;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setNotes(String notes){
		this.notes = notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setTotalPrice(int totalPrice){
		this.totalPrice = totalPrice;
	}

	public int getTotalPrice(){
		return totalPrice;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setMetodeBayar(Object metodeBayar){
		this.metodeBayar = metodeBayar;
	}

	public Object getMetodeBayar(){
		return metodeBayar;
	}

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
	}

	public void setTotalPriceRupiahFormat(String totalPriceRupiahFormat){
		this.totalPriceRupiahFormat = totalPriceRupiahFormat;
	}

	public String getTotalPriceRupiahFormat(){
		return totalPriceRupiahFormat;
	}

	public void setJsonMemberLong(Object jsonMemberLong){
		this.jsonMemberLong = jsonMemberLong;
	}

	public Object getJsonMemberLong(){
		return jsonMemberLong;
	}

	public void setOrderedItemNames(List<String> orderedItemNames){
		this.orderedItemNames = orderedItemNames;
	}

	public List<String> getOrderedItemNames(){
		return orderedItemNames;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setLat(Object lat){
		this.lat = lat;
	}

	public Object getLat(){
		return lat;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setOrderedItem(List<OrderedItemItem> orderedItem){
		this.orderedItem = orderedItem;
	}

	public List<OrderedItemItem> getOrderedItem(){
		return orderedItem;
	}
}