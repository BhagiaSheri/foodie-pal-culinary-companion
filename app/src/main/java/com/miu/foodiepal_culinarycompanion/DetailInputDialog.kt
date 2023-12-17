package com.miu.foodiepal_culinarycompanion

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DetailInputDialog(context: Context, private val listener: OnDetailInputListener) {
    private val dialog: Dialog

    init {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.dialog_detail_input, null)

        val editTextTitle = view.findViewById<EditText>(R.id.editTextDetailTitle)
        val editTextContent = view.findViewById<EditText>(R.id.editTextDetailContent)
        val buttonAddBlog = view.findViewById<Button>(R.id.buttonAddDetail)

        dialog = MaterialAlertDialogBuilder(context)
            .setView(view)
            .setTitle("Add New Fun Fact")
            .create()

        buttonAddBlog.setOnClickListener {
            val title = editTextTitle.text.toString()
            val content = editTextContent.text.toString()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                listener.onDetailInput(title, content)
                dialog.dismiss()
            } else {
                Toast.makeText(
                    context,
                    "Enter All Fields to add fun fact!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun show() {
        dialog.show()
    }

    interface OnDetailInputListener {
        fun onDetailInput(title: String, content: String)
    }
}
