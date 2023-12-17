package com.miu.foodiepal_culinarycompanion

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A fragment representing a list of Items.
 */
class RecipeFragment : Fragment(), RecipeInputDialog.OnRecipeInputListener {

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

        recipeList.add(
            0, Recipe(
                "French Egg Toast",
                """|Add 2 Eggs,
                    |1 Tbl Spoon Black Pepper,
                    |Salt, Red Chilli...
                    """.trimMargin(),
                R.drawable.french_toast_pic,
                "20 Minutes",
                "4.0 Rating"
            )
        )

        // Set up RecyclerView
        recipeAdapter = MyRecipeRecyclerViewAdapter(recipeList)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = recipeAdapter

        // Set up FloatingActionButton click listener
        fabAddRecipe.setOnClickListener {
            addNewRecipe()
        }

        return view
    }

    private fun addNewRecipe() {
        // Show the recipe input dialog
        val recipeInputDialog = RecipeInputDialog(requireContext(), this)
        recipeInputDialog.show()
    }

    override fun onRecipeInput(
        title: String,
        description: String,
        cookingTime: String,
        rating: String
    ) {
        // Handle adding the new recipe to the list
        val images: List<Int> = listOf(R.drawable.biryani, R.drawable.recipe_pic, R.drawable.donut)

        val newRecipe =
            Recipe(title, description, images[(images.indices).random()], cookingTime, rating)
        recipeList.add(newRecipe)
        recipeAdapter.notifyDataSetChanged()

    }

}