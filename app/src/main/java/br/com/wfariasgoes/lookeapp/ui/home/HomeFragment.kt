package br.com.wfariasgoes.lookeapp.ui.home

import android.content.Context
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.wfariasgoes.lookeapp.R
import br.com.wfariasgoes.lookeapp.base.BaseFragment
import br.com.wfariasgoes.lookeapp.extension.setDefaultGridSettings
import br.com.wfariasgoes.lookeapp.ui.adapter.HomeAdapter
import br.com.wfariasgoes.network.response.ResponseData
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var contextHome: Context
    private lateinit var adapter: HomeAdapter

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
//            for (resp in it.objects){
//                Log.i("TAG", resp.name)
//            }

            adapter =  HomeAdapter(it.objects, contextHome, this)
            recycler.setDefaultGridSettings(contextHome, adapter)

        }

        viewModel.homeRepositoryResponse().observe(this, homeResponse)
        viewModel.loadMovies()
    }
}