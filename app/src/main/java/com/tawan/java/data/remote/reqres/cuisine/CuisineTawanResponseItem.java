package com.tawan.java.data.remote.reqres.cuisine;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CuisineTawanResponseItem{

	@SerializedName("thumbnail")
	private Object thumbnail;

	@SerializedName("is_visible")
	private int isVisible;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_resto")
	private int idResto;

	@SerializedName("id")
	private int id;

	@SerializedName("menus")
	private List<MenusItem> menus;

	@SerializedName("thumbnail2")
	private Object thumbnail2;

	@SerializedName("desc")
	private String desc;

	public void setThumbnail(Object thumbnail){
		this.thumbnail = thumbnail;
	}

	public Object getThumbnail(){
		return thumbnail;
	}

	public void setIsVisible(int isVisible){
		this.isVisible = isVisible;
	}

	public int getIsVisible(){
		return isVisible;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	public void setMenus(List<MenusItem> menus){
		this.menus = menus;
	}

	public List<MenusItem> getMenus(){
		return menus;
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
			"CuisineTawanResponseItem{" + 
			"thumbnail = '" + thumbnail + '\'' + 
			",is_visible = '" + isVisible + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id_resto = '" + idResto + '\'' + 
			",id = '" + id + '\'' + 
			",menus = '" + menus + '\'' + 
			",thumbnail2 = '" + thumbnail2 + '\'' + 
			",desc = '" + desc + '\'' + 
			"}";
		}
}