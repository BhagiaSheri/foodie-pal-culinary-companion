package com.miu.foodiepal_culinarycompanion

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MyBlogRecyclerViewAdapter(
    private val blogList: List<Blog>,
    private val context: Context
) : RecyclerView.Adapter<MyBlogRecyclerViewAdapter.BlogViewHolder>() {

    inner class BlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewBlogTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewBlogDescription)
        val shareButton: Button = itemView.findViewById(R.id.shareButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_blog, parent, false)
        return BlogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val currentRecipe = blogList[position]
        holder.titleTextView.text = currentRecipe.blogTitle
        holder.descriptionTextView.text = currentRecipe.blogContent

        // Set up a click listener for the share button
        holder.shareButton.setOnClickListener {
            shareBlog(currentRecipe.blogTitle, currentRecipe.blogContent)
        }

    }

    override fun getItemCount(): Int = blogList.size

    private fun shareBlog(title: String, description: String) {
        val shareText = "Title: $title\n\nBlog:\n$description"

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)

        try {
            context.startActivity(Intent.createChooser(shareIntent, "Share using"))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(context, "No sharing app installed", Toast.LENGTH_SHORT).show()
        }
    }

}