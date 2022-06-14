package com.tawan.java.data.remote.reqres.invoice

import com.google.gson.annotations.SerializedName

data class InvoicePayloadkt(
    @SerializedName("address")
    val address: String,
    @SerializedName("cart_ids")
    val cartIds: List<String>,
    @SerializedName("id_resto")
    val idResto: String,
    @SerializedName("id_user")
    val idUser: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("notes")
    val notes: String
) {

}