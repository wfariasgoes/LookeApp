package br.com.wfariasgoes.lookeapp.ui.home

import androidx.lifecycle.MutableLiveData
import br.com.wfariasgoes.lookeapp.base.BaseViewModel
import br.com.wfariasgoes.network.ApiBuilder
import br.com.wfariasgoes.network.response.ResponseData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel : BaseViewModel() {

    private var homeResponse =  MutableLiveData<ResponseData>()

    fun homeRepositoryResponse(): MutableLiveData<ResponseData> = homeResponse

    fun loadMovies(){
        ApiBuilder.getWebService().getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doAfterTerminate { showLoading(false) }
            .subscribe({
                homeResponse.value = it
            }, {
                showFailure(it)
            })
    }
}