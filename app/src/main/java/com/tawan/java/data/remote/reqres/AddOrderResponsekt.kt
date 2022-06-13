package com.tawan.java.data.remote.reqres


import com.google.gson.annotations.SerializedName

data class AddOrderResponsekt(
    @SerializedName("api_code")
    val apiCode: Int,
    @SerializedName("http_response")
    val httpResponse: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("message_en")
    val messageEn: String,
    @SerializedName("message_id")
    val messageId: String,
    @SerializedName("res_data")
    val resData: ResData,
    @SerializedName("status_code")
    val statusCode: Int
) {
    data class ResData(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("id_menu")
        val idMenu: String,
        @SerializedName("id_resto")
        val idResto: String,
        @SerializedName("id_user")
        val idUser: String,
        @SerializedName("notes")
        val notes: String,
        @SerializedName("price")
        val price: String,
        @SerializedName("price_multiplied")
        val priceMultiplied: Int,
        @SerializedName("quantity")
        val quantity: String,
        @SerializedName("updated_at")
        val updatedAt: String
    )
}