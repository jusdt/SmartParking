package com.example.android.marsphotos.overview

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.MarsApi
import com.example.android.marsphotos.network.TicketData
import kotlinx.coroutines.launch
import retrofit2.Response

class TicketViewModel : ViewModel() {

    private val _idCard = MutableLiveData<String>()
    val idCard: MutableLiveData<String>
        get() = _idCard

    private val _license = MutableLiveData<String>()
    val license: MutableLiveData<String>
        get() = _license

    private val _type = MutableLiveData<String>()
    val type: MutableLiveData<String>
        get() = _type

    private val _username = MutableLiveData<String>()
    val username: MutableLiveData<String>
        get() = _username

    private val _parkingName = MutableLiveData<String>()
    val parkingName: MutableLiveData<String>
        get() = _parkingName

    private val _time = MutableLiveData<String>()
    val time: MutableLiveData<String>
        get() = _time

    private val _cost = MutableLiveData<Long>()
    val cost: MutableLiveData<String>
        get() = _time

    init {

    }

    fun addTicket(context: Context, ticketMode : TicketData) {
        viewModelScope.launch {
            try {
                Log.d("TAG", "addTicket: " + _type.value)
                val status1: Response<TicketData> =
                    MarsApi.retrofitService.addTicket(ticketMode)
                val jwt = status1.body()
                if (status1.isSuccessful) {
                    Toast.makeText(context, "Add successfully", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(context, "xxxLogin failed", Toast.LENGTH_SHORT).show()
                }
            } catch (e: java.lang.Exception) {
                Log.d("TAG", "send ticket : hhuhuhu " + e)
                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}