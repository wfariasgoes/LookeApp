package br.com.wfariasgoes.lookeapp.ui.ui.storage

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import br.com.wfariasgoes.lookeapp.R
import br.com.wfariasgoes.lookeapp.base.BaseFragment
import br.com.wfariasgoes.lookeapp.extension.setDefaultGridSettings
import br.com.wfariasgoes.lookeapp.ui.adapter.VideoAdapter
import br.com.wfariasgoes.model.VideoModel
import kotlinx.android.synthetic.main.fragment_dashboard.*

class MediaStorageFragment : BaseFragment() {
    private lateinit var listVideos: ArrayList<VideoModel>
    private lateinit var adapter: VideoAdapter
    private lateinit var contexMedia: Context

    override fun getRootLayoutId(): Int {
        return R.layout.fragment_dashboard
    }

    override fun setupViewModel() {
    }

    override fun setupData(view: View) {
        listVideos = ArrayList()
        contexMedia = view.context
        fetchVideosFromGallery()

    }

    private fun fetchVideosFromGallery() {
        var uri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        var cursor: Cursor
        var columIndexData: Int
        var thum: Int
        var absoluteImagePath: String
        var projection = arrayOf(
            MediaStore.MediaColumns.DATA,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media._ID,
            MediaStore.Video.Thumbnails.DATA
        )

        var orderBy: String = MediaStore.Images.Media.DATE_TAKEN
        cursor = activity!!.applicationContext.applicationContext.contentResolver
            .query(uri, projection, null, null, orderBy +" DESC" )

        columIndexData = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        thum = cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA)

        while (cursor.moveToNext()) {
            absoluteImagePath = cursor.getString(columIndexData)
            var videoModel =  VideoModel()
            videoModel.booleanSelected = false
            videoModel.strPath = absoluteImagePath
            videoModel.strThumb = cursor.getString(thum)

            listVideos.add(videoModel)
        }

        adapter = VideoAdapter(contexMedia,listVideos)
        recyclerStore.setDefaultGridSettings(contexMedia, adapter)
    }

}