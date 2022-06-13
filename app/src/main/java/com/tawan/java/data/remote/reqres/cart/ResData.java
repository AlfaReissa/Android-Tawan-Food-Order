package com.tawan.java.data.remote.reqres.cart;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResData{

	@SerializedName("total_price")
	private int totalPrice;

	@SerializedName("total_quantity")
	private int totalQuantity;

	@SerializedName("total_price_rupiah_format")
	private String totalPriceRupiahFormat;

	@SerializedName("ordered_item")
	private List<OrderedItemItem> orderedItem;

	public int getTotalPrice(){
		return totalPrice;
	}

	public int getTotalQuantity(){
		return totalQuantity;
	}

	public String getTotalPriceRupiahFormat(){
		return totalPriceRupiahFormat;
	}

	public List<OrderedItemItem> getOrderedItem(){
		return orderedItem;
	}

	@Override
 	public String toString(){
		return 
			"ResData{" + 
			"total_price = '" + totalPrice + '\'' + 
			",total_quantity = '" + totalQuantity + '\'' + 
			",total_price_rupiah_format = '" + totalPriceRupiahFormat + '\'' + 
			",ordered_item = '" + orderedItem + '\'' + 
			"}";
		}
}