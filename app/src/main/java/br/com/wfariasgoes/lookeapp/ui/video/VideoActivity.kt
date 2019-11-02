package br.com.wfariasgoes.lookeapp.ui.video

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.MediaController
import br.com.wfariasgoes.lookeapp.R
import br.com.wfariasgoes.lookeapp.base.BaseActivity
import br.com.wfariasgoes.network.response.Objects
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : BaseActivity() {

    private var mediaController: MediaController? = null
    companion object {
        private const val PRODUCT: String = "PRODUCT"
        fun getIntent(context: Context, product: Objects): Intent {
            val intent = Intent(context, VideoActivity::class.java)
//            intent.putExtra(PRODUCT, product)
            return intent
        }
    }
    override fun getRootLayoutId(): Int {
     return R.layout.activity_video
    }

    override fun setupView(savedInstanceState: Bundle?) {
        configureVideoView()
    }

    private fun configureVideoView() {

        videoView.setVideoPath(
            "http://www.ebookfrenzy.com/android_book/movie.mp4")

        mediaController = MediaController(this)
        mediaController?.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.start()
    }
}
