package br.com.wfariasgoes.lookeapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


open class BaseViewModel : ViewModel() {
    val eventLoading = MutableLiveData<Event<Boolean>>()
    val eventFailure = MutableLiveData<Event<Throwable>>()

    fun showLoading(value: Boolean) {
        eventLoading.value = Event(value)
    }

    fun showFailure(throwable: Throwable) {
        eventFailure.value = Event(throwable)
    }
}