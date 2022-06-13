package com.tawan.java.data.remote

import com.tawan.java.data.remote.reqres.orderitem.OrderItemPayload
import com.tawan.java.data.remote.service.ApiService

class RemoteDataSource(
    private val commonService: ApiService,
) {

    suspend fun login(email: String, password: String) =
        commonService.login(email = email, password = password)

    suspend fun register(name: String, email: String, password: String) =
        commonService.register(name = name, email = email, password = password)

    suspend fun getCuisine() =
        commonService.getCuisine()

    suspend fun getMenu() =
        commonService.getMenu()

    suspend fun checkIfAlreadyAddedInCart(userId: String, menuId: String) =
        commonService.checkIfInChart(idUser = userId, menuId)

    suspend fun addItemToCart(payload:OrderItemPayload) = commonService.addItemToCart(
        payload
    )

    suspend fun updateItemToCart(payload:OrderItemPayload) = commonService.updateItemToCart(
        id = payload.id.toString(), payload = payload
    )

    suspend fun deleteFromCart(id: String) =
        commonService.deleteFromCart(id)


    suspend fun getTask(id: String) =
        commonService.getTaskUserById(id)

    suspend fun getDetailTask(id: String) =
        commonService.detailTaskTask(id)

    suspend fun deleteTask(id: String) =
        commonService.deleteTask(id)

    suspend fun saveNewTask(id_user: String, title: String, desc: String, deadline: String) =
        commonService.saveTask(id_user, title, desc, deadline)

    suspend fun updateTask(id: String, title: String, desc: String, deadline: String) =
        commonService.updateTask(id, title, desc, deadline)


}