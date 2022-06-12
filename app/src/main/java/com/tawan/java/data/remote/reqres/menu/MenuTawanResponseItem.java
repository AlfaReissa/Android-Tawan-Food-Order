package com.tawan.java.data.remote.reqres.menu;

import com.google.gson.annotations.SerializedName;

public class MenuTawanResponseItem{

	@SerializedName("thumbnail")
	private String thumbnail;

	@SerializedName("is_visible")
	private int isVisible;

	@SerializedName("notes")
	private String notes;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_resto")
	private int idResto;

	@SerializedName("is_available")
	private int isAvailable;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("price")
	private String price;

	@SerializedName("id_cuisine")
	private int idCuisine;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("thumbnail2")
	private Object thumbnail2;

	@SerializedName("desc")
	private String desc;

	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}

	public String getThumbnail(){
		return thumbnail;
	}

	public void setIsVisible(int isVisible){
		this.isVisible = isVisible;
	}

	public int getIsVisible(){
		return isVisible;
	}

	public void setNotes(String notes){
		this.notes = notes;
	}

	public String getNotes(){
		return notes;
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

	public void setIsAvailable(int isAvailable){
		this.isAvailable = isAvailable;
	}

	public int getIsAvailable(){
		return isAvailable;
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

	public void setIdCuisine(int idCuisine){
		this.idCuisine = idCuisine;
	}

	public int getIdCuisine(){
		return idCuisine;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setThumbnail2(Object thumbnail2){
		this.thumbnail2 = thumbnail2;
	}

	public Object getThumbnail2(){
		return thumbnail2;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}

	@Override
 	public String toString(){
		return 
			"MenuTawanResponseItem{" + 
			"thumbnail = '" + thumbnail + '\'' + 
			",is_visible = '" + isVisible + '\'' + 
			",notes = '" + notes + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id_resto = '" + idResto + '\'' + 
			",is_available = '" + isAvailable + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",price = '" + price + '\'' + 
			",id_cuisine = '" + idCuisine + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",thumbnail2 = '" + thumbnail2 + '\'' + 
			",desc = '" + desc + '\'' + 
			"}";
		}
}