package com.miu.foodiepal_culinarycompanion

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class BlogInputDialog(context: Context, private val listener: OnBlogInputListener) {

    private val dialog: Dialog

    init {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.dialog_blog_input, null)

        val editTextTitle = view.findViewById<EditText>(R.id.editTextBlogTitle)
        val editTextDescription = view.findViewById<EditText>(R.id.editTextBlogDescription)
        val buttonAddBlog = view.findViewById<Button>(R.id.buttonAddBlog)

        dialog = MaterialAlertDialogBuilder(context)
            .setView(view)
            .setTitle("Add Blog")
            .create()

        buttonAddBlog.setOnClickListener {
            val title = editTextTitle.text.toString()
            val description = editTextDescription.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                listener.onBlogInput(title, description)
                dialog.dismiss()
            } else {
                Toast.makeText(
                    context,
                    "Enter All Fields to add new Blog!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun show() {
        dialog.show()
    }

    interface OnBlogInputListener {
        fun onBlogInput(blogTitle: String,blogContent: String)
    }
}
