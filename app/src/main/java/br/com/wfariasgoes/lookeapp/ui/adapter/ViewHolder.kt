package br.com.wfariasgoes.lookeapp.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.wfariasgoes.lookeapp.R

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var view: View = itemView
    var nameVideo: TextView = itemView.findViewById(R.id.textNameVideo)
    var imageVideo:  ImageView = itemView.findViewById(R.id.imageVideo)
    var imagePlay: ImageView = itemView.findViewById(R.id.imagePlay)

}