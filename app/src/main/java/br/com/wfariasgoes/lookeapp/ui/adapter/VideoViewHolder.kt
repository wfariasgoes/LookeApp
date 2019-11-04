package br.com.wfariasgoes.lookeapp.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.wfariasgoes.lookeapp.R

class VideoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var view: View = itemView
    var imageVideo:  ImageView = itemView.findViewById(R.id.imageVideoPath)
}