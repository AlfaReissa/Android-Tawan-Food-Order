package com.tawan.java.data.remote.reqres.menu;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MenuTawanResponse{

	@SerializedName("MenuTawanResponse")
	private List<MenuTawanResponseItem> menuTawanResponse;

	public void setMenuTawanResponse(List<MenuTawanResponseItem> menuTawanResponse){
		this.menuTawanResponse = menuTawanResponse;
	}

	public List<MenuTawanResponseItem> getMenuTawanResponse(){
		return menuTawanResponse;
	}

	@Override
 	public String toString(){
		return 
			"MenuTawanResponse{" + 
			"menuTawanResponse = '" + menuTawanResponse + '\'' + 
			"}";
		}
}