package br.com.wfariasgoes.lookeapp.ui.activitiy.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.wfariasgoes.lookeapp.R
import br.com.wfariasgoes.lookeapp.base.BaseFragment
import br.com.wfariasgoes.network.response.ResponseData

class HomeFragment : BaseFragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var contextHome: Context

    override fun getRootLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun setupViewModel() {
        if (!this::viewModel.isInitialized) {
            viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
            setObserveLive(viewModel)
        }
    }

    override fun setupData(view: View) {
        contextHome = view.context

        val homeResponse = Observer<ResponseData> {
            for (resp in it.objects){
                Log.i("TAG", resp.name)
            }
        }

        viewModel.homeRepositoryResponse().observe(this, homeResponse)
        viewModel.loadMovies()
    }
}