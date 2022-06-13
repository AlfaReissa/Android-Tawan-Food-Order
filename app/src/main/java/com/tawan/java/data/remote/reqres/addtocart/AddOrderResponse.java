package com.tawan.java.data.remote.reqres.addtocart;

import com.google.gson.annotations.SerializedName;

public class AddOrderResponse{

	@SerializedName("http_response")
	private int httpResponse;

	@SerializedName("api_code")
	private int apiCode;

	@SerializedName("status_code")
	private int statusCode;

	@SerializedName("message_en")
	private String messageEn;

	@SerializedName("message_id")
	private String messageId;

	@SerializedName("res_data")
	private ResData resData;

	@SerializedName("message")
	private String message;

	public int getHttpResponse(){
		return httpResponse;
	}

	public int getApiCode(){
		return apiCode;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public String getMessageEn(){
		return messageEn;
	}

	public String getMessageId(){
		return messageId;
	}

	public ResData getResData(){
		return resData;
	}

	public String getMessage(){
		return message;
	}
}