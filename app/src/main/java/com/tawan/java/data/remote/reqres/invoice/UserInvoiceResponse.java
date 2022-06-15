package com.tawan.java.data.remote.reqres.invoice;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UserInvoiceResponse{

	@SerializedName("UserInvoiceResponse")
	private List<UserInvoiceResponseItem> userInvoiceResponse;

	public void setUserInvoiceResponse(List<UserInvoiceResponseItem> userInvoiceResponse){
		this.userInvoiceResponse = userInvoiceResponse;
	}

	public List<UserInvoiceResponseItem> getUserInvoiceResponse(){
		return userInvoiceResponse;
	}
}