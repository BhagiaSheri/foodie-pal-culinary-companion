package com.miu.foodiepal_culinarycompanion

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RecipeInputDialog(context: Context, private val listener: OnRecipeInputListener) {

    private val dialog: Dialog

    init {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.dialog_recipe_input, null)

        val editTextTitle = view.findViewById<EditText>(R.id.editTextTitle)
        val editTextDescription = view.findViewById<EditText>(R.id.editTextDescription)
        val editTextCookingTime = view.findViewById<EditText>(R.id.editTextCookingTime)
        val editTextRating = view.findViewById<EditText>(R.id.editTextRating)
        val buttonAdd = view.findViewById<Button>(R.id.buttonAddRecipe)

        dialog = MaterialAlertDialogBuilder(context)
            .setView(view)
            .setTitle("Add Recipe")
            .create()

        buttonAdd.setOnClickListener {
            val title = editTextTitle.text.toString()
            val description = editTextDescription.text.toString()
            val cookingTime = editTextCookingTime.text.toString()
            val rating = editTextRating.text.toString().toDoubleOrNull()

            if (title.isNotEmpty() && description.isNotEmpty() && cookingTime.isNotEmpty() && (rating != null && rating <= 5)) {
                listener.onRecipeInput(title, description, cookingTime, "$rating Rating")
                dialog.dismiss()
            } else {
                Toast.makeText(
                    context,
                    "Enter All Fields and Rating should be between 1-5",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun show() {
        dialog.show()
    }

    interface OnRecipeInputListener {
        fun onRecipeInput(title: String, description: String, cookingTime: String, rating: String)
    }
}
