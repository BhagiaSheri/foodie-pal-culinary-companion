package com.miu.foodiepal_culinarycompanion

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * A fragment representing a list of Items.
 */
class MealPlanFragment : Fragment(), MealPlanInputDialog.OnMealPlanInputListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddMealPlan: FloatingActionButton
    private lateinit var mealPlanAdapter: MyMealPlanRecyclerViewAdapter

    private val mealPlans = mutableListOf<MealPlan>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal_plan_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewMealPlanner)
        fabAddMealPlan = view.findViewById(R.id.fabAddMealPlan)

        // add static meal plan value
        val calendar = Calendar.getInstance()
        val dayName = SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.time)
        val currentDate: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)

        mealPlans.add(0, MealPlan("$dayName, $currentDate", "Breakfast: French Egg Toast"))

        // Set up RecyclerView
        mealPlanAdapter = MyMealPlanRecyclerViewAdapter(mealPlans)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mealPlanAdapter

        // Set up FloatingActionButton click listener
        fabAddMealPlan.setOnClickListener {
            addNewMealPlan()
        }

        return view
    }

    private fun addNewMealPlan() {
        // Show the recipe input dialog
        val mealPlanInputDialog = MealPlanInputDialog(requireContext(), this)
        mealPlanInputDialog.show()
    }

    override fun onMealPlanInput(weekDay: String, planOfDay: String) {
        val newMealPlan = MealPlan(weekDay, planOfDay)
        mealPlans.add(newMealPlan)
        mealPlanAdapter.notifyDataSetChanged()
    }

}