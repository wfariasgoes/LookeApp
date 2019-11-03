package br.com.wfariasgoes.lookeapp.ui.moviedetail

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.wfariasgoes.lookeapp.R
import br.com.wfariasgoes.lookeapp.base.BaseActivity
import br.com.wfariasgoes.network.response.Objects
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : BaseActivity() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var objectsVideo: Objects

    companion object {
        private const val VIDEO: String = "VIDEO"
        fun getIntent(context: Context, video: Objects): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(VIDEO, video)
            return intent
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.activity_movie_detail
    }

    override fun setupView(savedInstanceState: Bundle?) {
        toolbar.setNavigationOnClickListener { onBackPressed() }
        setupToolbar()

        intent.extras?.let {
            this.objectsVideo = it.getSerializable(VIDEO) as Objects
        }

        viewModel = MovieDetailViewModel(objectsVideo)
        setupViewModel()

        viewModel.text.observe(this, Observer {
            textName.text = it
            textSecondName.text = it
        })

        viewModel.urlString.observe(this, Observer {
            Picasso.get()
                .load(it)
                .into(imageBackDrop)

            Picasso.get()
                .load(it)
                .into(imagePath)
        })

    }

    fun setupViewModel() {
        if (!this::viewModel.isInitialized) {
            viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
            setObserveLive(viewModel)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        val upArrow = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp).mutate()
        upArrow.setColorFilter(
            resources.getColor(R.color.colorBottomNavigationTextSelected),
            PorterDuff.Mode.SRC_ATOP
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            supportActionBar!!.setHomeAsUpIndicator(upArrow)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> onBackPressed() // Finaliza a Activity atual e assim volta para a tela anterior
            else -> {
            }
        }
        return true
    }

}
