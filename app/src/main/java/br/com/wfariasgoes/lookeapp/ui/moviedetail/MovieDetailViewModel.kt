package br.com.wfariasgoes.lookeapp.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.wfariasgoes.lookeapp.base.BaseViewModel
import br.com.wfariasgoes.network.response.Objects

class MovieDetailViewModel(var nomeVideo: Objects): BaseViewModel() {

    private val textName = MutableLiveData<String>().apply {
        value = nomeVideo.name
    }

    private val imageUrl = MutableLiveData<String>().apply {
        value = nomeVideo.im
    }

    val text: LiveData<String> = textName
    val urlString : LiveData<String> = imageUrl
}