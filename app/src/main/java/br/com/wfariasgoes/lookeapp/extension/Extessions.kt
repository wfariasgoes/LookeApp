package br.com.wfariasgoes.lookeapp.extension

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setDefaultGridSettings(context: Context,
                                            adapter: RecyclerView.Adapter<*>)
        : RecyclerView {

    this.layoutManager = GridLayoutManager(context, 2)
    this.adapter = adapter

    return this
}

fun RecyclerView.setDefaultLinearSettings(
    context: Context,
    adapter: RecyclerView.Adapter<*>
)
        : RecyclerView {

    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    this.adapter = adapter

    return this
}
