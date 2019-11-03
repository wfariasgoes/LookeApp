package br.com.wfariasgoes.lookeapp.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.wfariasgoes.lookeapp.R
import br.com.wfariasgoes.lookeapp.ui.home.HomeFragment
import br.com.wfariasgoes.lookeapp.ui.moviedetail.MovieDetailActivity
import br.com.wfariasgoes.network.response.Objects
import com.squareup.picasso.Picasso

class HomeAdapter(
    var objects: List<Objects>,
    var contextHome: Context,
    var homeFragment: HomeFragment
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
            val intent: Intent = MovieDetailActivity.getIntent(contextHome, objectItem)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (NoDualClick(1000)) {
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        homeFragment.activity!!, holder.imageVideo, "root"
                    )
                    contextHome!!.startActivity(intent, options.toBundle())
                }
            } else {
                contextHome!!.startActivity(intent)
            }

        }
    }

    private var clk = 0
    fun NoDualClick(ms: Int): Boolean {
        val handler = Handler()
        val r = Runnable { clk = 0 }

        if (clk == 0) {
            clk = 1
            handler.postDelayed(r, ms.toLong())
            return true
        }
        handler.postDelayed(r, 1000)
        return false
    }
}