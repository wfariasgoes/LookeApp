package br.com.wfariasgoes.lookeapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.wfariasgoes.lookeapp.R
import br.com.wfariasgoes.network.response.Objects
import com.squareup.picasso.Picasso

class HomeAdapter(
    var objects: List<Objects>,
    var contextHome: Context
) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return objects.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var objectItem = objects[position]

        holder.nameVideo.text = objectItem.name
        Picasso.get()
            .load(objectItem.im)
            .into(holder.imageVideo)

        holder.view.setOnClickListener {
//            contextHome.startActivity()
        }
    }
}