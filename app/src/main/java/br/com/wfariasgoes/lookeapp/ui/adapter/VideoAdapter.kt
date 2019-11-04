package br.com.wfariasgoes.lookeapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.wfariasgoes.lookeapp.R
import br.com.wfariasgoes.lookeapp.ui.videoplayer.VideoActivity
import br.com.wfariasgoes.model.VideoModel
import com.bumptech.glide.Glide
import java.io.File

class VideoAdapter(var context: Context, var listVideos: ArrayList<VideoModel>) :
    RecyclerView.Adapter<VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_video, parent, false)
        return VideoViewHolder(view)
    }


    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        var file = File(Environment.getExternalStoragePublicDirectory
            (Environment.DIRECTORY_DOWNLOADS), listVideos[position].strThumb)
        Glide.with(context)
            .load("file://"+listVideos[position].strThumb)
            .skipMemoryCache(false)
            .into(holder.imageVideo)

        holder.view.setOnClickListener {
            val intent: Intent = VideoActivity.getIntent( context, listVideos[position].strPath,"")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listVideos.size
}
