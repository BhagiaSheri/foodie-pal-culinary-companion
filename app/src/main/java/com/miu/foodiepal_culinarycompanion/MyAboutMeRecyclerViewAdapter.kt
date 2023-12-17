package com.miu.foodiepal_culinarycompanion

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyAboutMeRecyclerViewAdapter(
    private val detailList: List<Detail>
) : RecyclerView.Adapter<MyAboutMeRecyclerViewAdapter.DetailViewHolder>() {

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail, parent, false)
        return DetailViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val currentDetail = detailList[position]
        holder.titleTextView.text = currentDetail.title
        holder.contentTextView.text = currentDetail.content

    }

    override fun getItemCount(): Int = detailList.size
}