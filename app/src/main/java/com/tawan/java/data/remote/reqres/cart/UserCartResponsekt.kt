package com.tawan.java.data.remote.reqres.cart


import com.google.gson.annotations.SerializedName

data class UserCartResponsekt(
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
        @SerializedName("ordered_item")
        val orderedItem: List<OrderedItem>,
        @SerializedName("total_price")
        val totalPrice: Int,
        @SerializedName("total_price_rupiah_format")
        val totalPriceRupiahFormat: String,
        @SerializedName("total_quantity")
        val totalQuantity: Int
    ) {
        data class OrderedItem(
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("id_menu")
            val idMenu: Int,
            @SerializedName("id_resto")
            val idResto: Int,
            @SerializedName("id_user")
            val idUser: Int,
            @SerializedName("notes")
            val notes: String,
            @SerializedName("price")
            val price: String,
            @SerializedName("price_multiplied")
            val priceMultiplied: Int,
            @SerializedName("quantity")
            val quantity: Int,
            @SerializedName("menu_name")
            val menuName: String,
            @SerializedName("updated_at")
            val updatedAt: String
        )
    }
}