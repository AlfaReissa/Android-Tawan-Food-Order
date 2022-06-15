package com.tawan.java.data.remote.reqres.invoice


import com.google.gson.annotations.SerializedName

class UserInvoiceResponsekt : ArrayList<UserInvoiceResponsekt.UserInvoiceResponsektItem>(){
    data class UserInvoiceResponsektItem(
        @SerializedName("address")
        val address: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("id_user")
        val idUser: Int,
        @SerializedName("lat")
        val lat: Any,
        @SerializedName("long")
        val long: Any,
        @SerializedName("metode_bayar")
        val metodeBayar: Any,
        @SerializedName("notes")
        val notes: String,
        @SerializedName("ordered_item")
        val orderedItem: List<OrderedItem>,
        @SerializedName("ordered_item_names")
        val orderedItemNames: List<String>,
        @SerializedName("photo_payment_path")
        val photoPaymentPath: Any,
        @SerializedName("status")
        val status: String,
        @SerializedName("total_price")
        val totalPrice: Int,
        @SerializedName("total_price_rupiah_format")
        val totalPriceRupiahFormat: String,
        @SerializedName("updated_at")
        val updatedAt: String
    ) {
        data class OrderedItem(
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("id_invoice")
            val idInvoice: Int,
            @SerializedName("menu")
            val menu: Any,
            @SerializedName("order")
            val order: Order,
            @SerializedName("updated_at")
            val updatedAt: String
        ) {
            data class Order(
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
                @SerializedName("menu")
                val menu: Menu,
                @SerializedName("menu_name")
                val menuName: String,
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
            ) {
                data class Menu(
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
        }
    }
}