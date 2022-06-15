package com.tawan.java.ui.hometawan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawan.java.data.remote.QumparanResource
import com.tawan.java.data.remote.RemoteDataSource
import com.tawan.java.data.remote.reqres.*
import com.tawan.java.data.remote.reqres.cart.UserCartResponsekt
import com.tawan.java.data.remote.reqres.invoice.InvoicePayload
import com.tawan.java.data.remote.reqres.invoice.InvoicePayloadkt
import com.tawan.java.data.remote.reqres.invoice.UserInvoiceResponsekt
import com.tawan.java.data.remote.reqres.menu.MenuTawanResponsekt
import com.tawan.java.data.remote.reqres.orderitem.OrderItemPayload
import com.tawan.java.data.remote.reqres.orderitem.OrderItemResponse
import kotlinx.coroutines.launch
import org.json.JSONObject

class HomeViewModel(val ds: RemoteDataSource) : ViewModel() {

    var selectedId = MutableLiveData("")

    private var _taskLiveData =
        MutableLiveData<QumparanResource<TasksResponse?>>()
    val searchLiveData get() = _taskLiveData

    private var _menuLiveData =
        MutableLiveData<QumparanResource<MenuTawanResponsekt?>>()
    val menuLiveData get() = _menuLiveData

    private var _checkCartLiveData =
        MutableLiveData<QumparanResource<OrderItemResponse?>>()
    val checkCartLiveData get() = _checkCartLiveData

    private var _saveInvoiceLiveData =
        MutableLiveData<QumparanResource<GeneralApiResponse?>>()
    val saveInvoiceLiveData get() = _saveInvoiceLiveData

    private var _userInvoiceHistoryLiveData =
        MutableLiveData<QumparanResource<UserInvoiceResponsekt?>>()
    val userInvoiceHistoryLiveData get() = _userInvoiceHistoryLiveData

    private var _userCartLiveData =
        MutableLiveData<QumparanResource<UserCartResponsekt?>>()
    val userCartLiveData get() = _userCartLiveData

    private var _deleteCartLiveData =
        MutableLiveData<QumparanResource<GeneralApiResponse?>>()
    val deleteCartLiveData get() = _deleteCartLiveData

    private var _saveCartLiveData =
        MutableLiveData<QumparanResource<GeneralApiResponse?>>()
    val saveCartLiveData get() = _saveCartLiveData

    private var _updateCartLiveData =
        MutableLiveData<QumparanResource<GeneralApiResponse?>>()
    val updateCartLiveData get() = _updateCartLiveData

    private var _saveTaskLiveData =
        MutableLiveData<QumparanResource<SaveTaskResponse?>>()
    val saveTaskLiveData get() = _saveTaskLiveData

    private var _deleteTaskLiveData =
        MutableLiveData<QumparanResource<DeleteResponse?>>()
    val deleteTaskLiveData get() = _deleteTaskLiveData

    private var _detailTaskLiveData =
        MutableLiveData<QumparanResource<DetailTaskResponse?>>()
    val detailTaskLiveData get() = _detailTaskLiveData


    fun getUserCart(id: String) = viewModelScope.launch {
        _userCartLiveData.postValue(QumparanResource.Loading())
        try {
            val res = ds.userCart(id)
            if (res.isSuccessful) {
                _userCartLiveData.postValue(QumparanResource.Success(res.body()))
            } else {
                var message = res.message().toString()
                res.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    message = jsonObj.getString("message")
                }
                _userCartLiveData.postValue(QumparanResource.Error(message))
            }
        } catch (e: Exception) {
            _userCartLiveData.postValue(QumparanResource.Error(e.message.toString()))
        }
    }

    fun deleteCart(id: String) = viewModelScope.launch {
        _deleteCartLiveData.postValue(QumparanResource.Loading())
        try {
            val res = ds.deleteFromCart(id)
            if (res.isSuccessful) {
                _deleteCartLiveData.postValue(QumparanResource.Success(res.body()))
            } else {
                var message = res.message().toString()
                res.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    message = jsonObj.getString("message")
                }
                _deleteCartLiveData.postValue(QumparanResource.Error(message))
            }
        } catch (e: Exception) {
            _deleteCartLiveData.postValue(QumparanResource.Error(e.message.toString()))
        }
    }

    fun saveCartResponse(payload: OrderItemPayload) = viewModelScope.launch {
        _saveCartLiveData.postValue(QumparanResource.Loading())
        try {
            val res = ds.addItemToCart(payload)
            if (res.isSuccessful) {
                _saveCartLiveData.postValue(QumparanResource.Success(res.body()))
            } else {
                var message = res.message().toString()
                res.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    message = jsonObj.getString("message")
                }
                _saveCartLiveData.postValue(QumparanResource.Error(message))
            }
        } catch (e: Exception) {
            _saveCartLiveData.postValue(QumparanResource.Error(e.message.toString()))
        }
    }

    fun updateCart(payload: OrderItemPayload) = viewModelScope.launch {
        _updateCartLiveData.postValue(QumparanResource.Loading())
        try {
            val res = ds.updateItemToCart(payload)
            if (res.isSuccessful) {
                _updateCartLiveData.postValue(QumparanResource.Success(res.body()))
            } else {
                var message = res.message().toString()
                res.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    message = jsonObj.getString("message")
                }
                _updateCartLiveData.postValue(QumparanResource.Error(message))
            }
        } catch (e: Exception) {
            _updateCartLiveData.postValue(QumparanResource.Error(e.message.toString()))
        }
    }


    fun getMenu() = viewModelScope.launch {
        _menuLiveData.postValue(QumparanResource.Loading())
        try {
            val res = ds.getMenu()
            if (res.isSuccessful) {
                _menuLiveData.postValue(QumparanResource.Success(res.body()))
            } else {
                var message = res.message().toString()
                res.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    message = jsonObj.getString("message")
                }
                _menuLiveData.postValue(QumparanResource.Error(message))
            }
        } catch (e: Exception) {
            _menuLiveData.postValue(QumparanResource.Error(e.message.toString()))
        }
    }

    fun checkOrderItem(idUser:String,idMenu:String) = viewModelScope.launch {
        _checkCartLiveData.postValue(QumparanResource.Loading())
        try {
            val res = ds.checkIfAlreadyAddedInCart(userId = idUser,idMenu)
            if (res.isSuccessful) {
                _checkCartLiveData.postValue(QumparanResource.Success(res.body()))
            } else {
                var message = res.message().toString()
                res.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    message = jsonObj.getString("message")
                }
                _checkCartLiveData.postValue(QumparanResource.Error(message))
            }
        } catch (e: Exception) {
            _checkCartLiveData.postValue(QumparanResource.Error(e.message.toString()))
        }
    }

    fun saveInvoice(payload: InvoicePayloadkt) = viewModelScope.launch {
        _saveInvoiceLiveData.postValue(QumparanResource.Loading())
        try {
            val res = ds.addToInvoice(payload)
            if (res.isSuccessful) {
                _saveInvoiceLiveData.postValue(QumparanResource.Success(res.body()))
            } else {
                var message = res.message().toString()
                res.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    message = jsonObj.getString("message")
                }
                _saveInvoiceLiveData.postValue(QumparanResource.Error(message))
            }
        } catch (e: Exception) {
            _saveInvoiceLiveData.postValue(QumparanResource.Error(e.message.toString()))
        }
    }

    fun getUserHistoryInvoice(id: String) = viewModelScope.launch {
        _userInvoiceHistoryLiveData.postValue(QumparanResource.Loading())
        try {
            val res = ds.getInvoiceByUser(id)
            if (res.isSuccessful) {
                _userInvoiceHistoryLiveData.postValue(QumparanResource.Success(res.body()))
            } else {
                var message = res.message().toString()
                res.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    message = jsonObj.getString("message")
                }
                _userInvoiceHistoryLiveData.postValue(QumparanResource.Error(message))
            }
        } catch (e: Exception) {
            _userInvoiceHistoryLiveData.postValue(QumparanResource.Error(e.message.toString()))
        }
    }


    fun getDetailTask(id: String) = viewModelScope.launch {
        _detailTaskLiveData.postValue(QumparanResource.Loading())
        try {
            val res = ds.getDetailTask(id)
            if (res.isSuccessful) {
                _detailTaskLiveData.postValue(QumparanResource.Success(res.body()))
            } else {
                var message = res.message().toString()
                res.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    message = jsonObj.getString("message")
                }
                _detailTaskLiveData.postValue(QumparanResource.Error(message))
            }
        } catch (e: Exception) {
            _detailTaskLiveData.postValue(QumparanResource.Error(e.message.toString()))
        }
    }

    fun deleteTask(id: String) = viewModelScope.launch {
        _deleteTaskLiveData.postValue(QumparanResource.Loading())
        try {
            val res = ds.deleteTask(id)
            if (res.isSuccessful) {
                _deleteTaskLiveData.postValue(QumparanResource.Success(res.body()))
            } else {
                var message = res.message().toString()
                res.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    message = jsonObj.getString("message")
                }
                _deleteTaskLiveData.postValue(QumparanResource.Error(message))
            }
        } catch (e: Exception) {
            _deleteTaskLiveData.postValue(QumparanResource.Error(e.message.toString()))
        }
    }

    fun saveTask(id_user: String, title: String, desc: String, deadline: String) =
        viewModelScope.launch {
            _saveTaskLiveData.postValue(QumparanResource.Loading())
            try {
                val res = ds.saveNewTask(id_user, title, desc, deadline)
                if (res.isSuccessful) {
                    _saveTaskLiveData.postValue(QumparanResource.Success(res.body()))
                } else {
                    var message = res.message().toString()
                    res.errorBody()?.let {
                        val jsonObj = JSONObject(it.charStream().readText())
                        message = jsonObj.getString("message")
                    }
                    _saveTaskLiveData.postValue(QumparanResource.Error(message))
                }
            } catch (e: Exception) {
                _saveTaskLiveData.postValue(QumparanResource.Error(e.message.toString()))
            }
        }

    fun updateTask(id: String, title: String, desc: String, deadline: String) =
        viewModelScope.launch {
            _saveTaskLiveData.postValue(QumparanResource.Loading())
            try {
                val res = ds.updateTask(id, title, desc, deadline)
                if (res.isSuccessful) {
                    _saveTaskLiveData.postValue(QumparanResource.Success(res.body()))
                } else {
                    var message = res.message().toString()
                    res.errorBody()?.let {
                        val jsonObj = JSONObject(it.charStream().readText())
                        message = jsonObj.getString("message")
                    }
                    _saveTaskLiveData.postValue(QumparanResource.Error(message))
                }
            } catch (e: Exception) {
                _saveTaskLiveData.postValue(QumparanResource.Error(e.message.toString()))
            }
        }

}
