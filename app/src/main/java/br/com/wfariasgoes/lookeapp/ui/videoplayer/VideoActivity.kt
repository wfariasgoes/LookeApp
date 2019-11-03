package br.com.wfariasgoes.lookeapp.ui.videoplayer

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import br.com.wfariasgoes.lookeapp.R
import br.com.wfariasgoes.lookeapp.base.BaseActivity
import br.com.wfariasgoes.network.response.Objects
import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : BaseActivity() {

    private lateinit var objectsVideo: Objects
    private var mediaController: MediaController? = null

    companion object {
        private const val VIDEO: String = "VIDEO"
        fun getIntent(context: Context, video: Objects): Intent {
            val intent = Intent(context, VideoActivity::class.java)
            intent.putExtra(VIDEO, video)
            return intent
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.activity_video
    }

    override fun setupView(savedInstanceState: Bundle?) {
        intent.extras?.let {
            this.objectsVideo = it.getSerializable(VIDEO) as Objects
        }
        configureVideoView()

        imageDownload.setOnClickListener {
            Toast.makeText(this, "Donwload em andamento", Toast.LENGTH_SHORT).show()
        }

    }

    private fun configureVideoView() {
        var uri = Uri.parse(objectsVideo.bg)

        videoView.setVideoURI(uri)

        videoView.setOnPreparedListener {
            progressBar.visibility = View.GONE
            videoView.requestFocus()
            videoAudio.setVideoPath(objectsVideo.sg)
            videoAudio.requestFocus()

            it.isLooping = true
            mediaController = MediaController(this)
            mediaController?.setAnchorView(videoView)
            videoView.setMediaController(mediaController)
            videoView.start()
            videoAudio.start()
        }

    }
}
