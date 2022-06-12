package com.tawan.java.data.remote.reqres.cuisine;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CuisineTawanResponse{

	@SerializedName("CuisineTawanResponse")
	private List<CuisineTawanResponseItem> cuisineTawanResponse;

	public void setCuisineTawanResponse(List<CuisineTawanResponseItem> cuisineTawanResponse){
		this.cuisineTawanResponse = cuisineTawanResponse;
	}

	public List<CuisineTawanResponseItem> getCuisineTawanResponse(){
		return cuisineTawanResponse;
	}

	@Override
 	public String toString(){
		return 
			"CuisineTawanResponse{" + 
			"cuisineTawanResponse = '" + cuisineTawanResponse + '\'' + 
			"}";
		}
}