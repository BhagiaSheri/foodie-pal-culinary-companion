package com.miu.foodiepal_culinarycompanion

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.miu.foodiepal_culinarycompanion.placeholder.PlaceholderContent.PlaceholderItem
import com.miu.foodiepal_culinarycompanion.databinding.FragmentRecipeBinding

class MyRecipeRecyclerViewAdapter(
    private val recipeList: List<Recipe>
) : RecyclerView.Adapter<MyRecipeRecyclerViewAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewRecipe)
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
        val timeTextView: TextView = itemView.findViewById(R.id.textViewTime)
        val ratingTextView: TextView = itemView.findViewById(R.id.textViewRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_recipe, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipeList[position]
        holder.imageView.setImageResource(currentRecipe.imageResource)
        holder.titleTextView.text = currentRecipe.title
        holder.descriptionTextView.text = currentRecipe.description
        holder.timeTextView.text = currentRecipe.cookingTime
        holder.ratingTextView.text = currentRecipe.rating.toString()
    }

    override fun getItemCount(): Int = recipeList.size

}