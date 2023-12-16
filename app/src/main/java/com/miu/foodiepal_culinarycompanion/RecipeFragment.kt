package com.miu.foodiepal_culinarycompanion

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miu.foodiepal_culinarycompanion.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class RecipeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddRecipe: FloatingActionButton
    private lateinit var recipeAdapter: MyRecipeRecyclerViewAdapter

    private val recipeList = mutableListOf<Recipe>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewRecipes)
        fabAddRecipe = view.findViewById(R.id.fabAddRecipe)

        // Set up RecyclerView
        recipeAdapter = MyRecipeRecyclerViewAdapter(recipeList)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = recipeAdapter

        // Set up FloatingActionButton click listener
        fabAddRecipe.setOnClickListener {
            // Handle adding a new recipe (you can show a dialog, navigate to a new activity, etc.)
            // For simplicity, let's just add a dummy recipe for demonstration purposes
            addDummyRecipe()
        }

        return view
    }

    // Function to add a dummy recipe
    private fun addDummyRecipe() {
        val dummyRecipe = Recipe(
            "New Recipe",
            "Description for the new recipe",
            R.drawable.ic_launcher_foreground, // Replace with the actual image resource
            "30 minutes",
            4.5
        )
        recipeList.add(dummyRecipe)
        recipeAdapter.notifyDataSetChanged()
    }

}