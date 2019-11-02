package br.com.wfariasgoes.lookeapp.ui.activitiy.splash

import android.os.Bundle
import android.os.Handler
import br.com.wfariasgoes.lookeapp.R
import br.com.wfariasgoes.lookeapp.base.BaseActivity
import br.com.wfariasgoes.lookeapp.ui.activitiy.MainActivity

class MainActivity : BaseActivity() {
    private val splashDisplayLength: Long = 3500
    val splashCloseLength: Long = splashDisplayLength + 1000

    override fun getRootLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun setupView(savedInstanceState: Bundle?) {
        Handler().postDelayed({
            startActivity()
        }, 3500)
    }

    private fun startActivity() {
        startActivity(MainActivity.getIntent(this))
        finish()
    }

}
