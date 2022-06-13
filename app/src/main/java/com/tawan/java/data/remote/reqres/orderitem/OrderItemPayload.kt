package com.tawan.java.data.remote.reqres.orderitem


import com.google.gson.annotations.SerializedName

data class OrderItemPayload(
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
    @SerializedName("updated_at")
    val updatedAt: String
)