package com.example.quizlet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizlet.OnBoardData
import com.example.quizlet.R
import com.squareup.picasso.Picasso

class ViewPagerAdapter(
    private val context: Context,
    private val data: ArrayList<OnBoardData>
) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivOnboard = itemView.findViewById<ImageView>(R.id.iv_onboard)!!
        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_viewpager2_onboard, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        Picasso.get().load(item.image).into(holder.ivOnboard)
        holder.tvTitle.text = context.getString(item.title)
    }

}