package com.tawan.java.ui.hometawan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tawan.java.data.remote.QumparanResource
import com.tawan.java.data.remote.RemoteDataSource
import com.tawan.java.data.remote.reqres.*
import com.tawan.java.data.remote.reqres.menu.MenuTawanResponsekt
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


    private var _saveTaskLiveData =
        MutableLiveData<QumparanResource<SaveTaskResponse?>>()
    val saveTaskLiveData get() = _saveTaskLiveData

    private var _deleteTaskLiveData =
        MutableLiveData<QumparanResource<DeleteResponse?>>()
    val deleteTaskLiveData get() = _deleteTaskLiveData

    private var _detailTaskLiveData =
        MutableLiveData<QumparanResource<DetailTaskResponse?>>()
    val detailTaskLiveData get() = _detailTaskLiveData

    fun getUserTask(id: String) = viewModelScope.launch {
        _taskLiveData.postValue(QumparanResource.Loading())
        try {
            val res = ds.getTask(id)
            if (res.isSuccessful) {
                _taskLiveData.postValue(QumparanResource.Success(res.body()))
            } else {
                var message = res.message().toString()
                res.errorBody()?.let {
                    val jsonObj = JSONObject(it.charStream().readText())
                    message = jsonObj.getString("message")
                }
                _taskLiveData.postValue(QumparanResource.Error(message))
            }
        } catch (e: Exception) {
            _taskLiveData.postValue(QumparanResource.Error(e.message.toString()))
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
