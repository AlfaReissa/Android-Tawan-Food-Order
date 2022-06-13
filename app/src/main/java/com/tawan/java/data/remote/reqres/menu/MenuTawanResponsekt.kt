package com.tawan.java.data.remote.reqres.menu


import com.google.gson.annotations.SerializedName

class MenuTawanResponsekt : ArrayList<MenuTawanResponsekt.MenuTawanResponsektItem>(){
    data class MenuTawanResponsektItem(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("desc")
        val desc: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("id_cuisine")
        val idCuisine: Int,
        @SerializedName("id_resto")
        val idResto: Int,
        @SerializedName("is_available")
        val isAvailable: Int,
        @SerializedName("is_visible")
        val isVisible: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("notes")
        val notes: String,
        @SerializedName("price")
        val price: String,
        @SerializedName("thumbnail")
        val thumbnail: String,
        @SerializedName("thumbnail2")
        val thumbnail2: Any,
        @SerializedName("thumbnail2_url")
        val thumbnail2Url: String,
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String,
        @SerializedName("updated_at")
        val updatedAt: String
    )
}