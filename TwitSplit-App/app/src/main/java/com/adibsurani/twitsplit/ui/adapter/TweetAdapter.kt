package com.adibsurani.twitsplit.ui.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adibsurani.twitsplit.R
import kotlinx.android.synthetic.main.row_tweet.view.*

class TweetAdapter (private var activity: Activity,
                    private var context : Context,
                    private var dataList : List<String>) : RecyclerView.Adapter<TweetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_tweet, parent, false)
        return ViewHolder(context, itemView)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position : Int) {
        holder.bindItems(dataList[position])
    }

    inner class ViewHolder(private val context : Context,
                           itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(data: String) {
            itemView.text_tweet.text = data
        }

    }
}