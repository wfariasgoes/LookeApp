package br.com.wfariasgoes.lookeapp.ui.videoplayer

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.core.content.ContextCompat
import br.com.wfariasgoes.lookeapp.R
import br.com.wfariasgoes.lookeapp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : BaseActivity() {

    private lateinit var objectsVideo: String
    private lateinit var objectsAudio: String
    private var mediaController: MediaController? = null

    companion object {
        private const val VIDEO: String = "VIDEO"
        private const val AUDIO: String = "AUDIO"
        private const val PERMISSION_STORAGE_CODE = 1000
        fun getIntent(context: Context, videoBg: String, audioSg: String): Intent {
            val intent = Intent(context, VideoActivity::class.java)
            intent.putExtra(VIDEO, videoBg)
            intent.putExtra(AUDIO, audioSg)
            return intent
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.activity_video
    }

    override fun setupView(savedInstanceState: Bundle?) {
        intent.extras?.let {
            this.objectsVideo = it.getString(VIDEO)
            this.objectsAudio = it.getString(AUDIO)
        }
        configureVideoView()

        imageDownload.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    == PackageManager.PERMISSION_DENIED
                ) {
                    var permissions = arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    requestPermissions(permissions, PERMISSION_STORAGE_CODE)
                } else {
                    startDownloading()
                }

            } else {
                startDownloading()
            }
        }

    }

    private fun startDownloading() {
        var urlString: String = objectsVideo
        var request: DownloadManager.Request = DownloadManager.Request(Uri.parse(urlString))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)

        request.setTitle(getString(R.string.title_download))
        request.setDescription(getString(R.string.description_download))
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "" + System.currentTimeMillis()
        )
        Toast.makeText(this, "Download iniciado", Toast.LENGTH_SHORT).show()
        var downloadManager: DownloadManager =  getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    private fun configureVideoView() {
        var uri = Uri.parse(objectsVideo)
        videoView.setVideoURI(uri)

        videoView.setOnPreparedListener {
            progressBar.visibility = View.GONE
            videoView.requestFocus()
            videoAudio.setVideoPath(objectsAudio)
            videoAudio.requestFocus()

            it.isLooping = true
            mediaController = MediaController(this)
            mediaController?.setAnchorView(videoView)
            videoView.setMediaController(mediaController)
            videoView.start()
            videoAudio.start()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_STORAGE_CODE -> if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                startDownloading()
            } else {
                Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
