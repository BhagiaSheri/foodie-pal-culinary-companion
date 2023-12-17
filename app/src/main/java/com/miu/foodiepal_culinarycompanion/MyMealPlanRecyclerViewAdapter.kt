package com.miu.foodiepal_culinarycompanion

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyMealPlanRecyclerViewAdapter(
    private val mealPlans: List<MealPlan>
) : RecyclerView.Adapter<MyMealPlanRecyclerViewAdapter.MealPlanViewHolder>() {

    inner class MealPlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayTextView: TextView = itemView.findViewById(R.id.item_day)
        val planTextView: TextView = itemView.findViewById(R.id.item_plan)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealPlanViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_mean_plan, parent, false)

        return MealPlanViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MealPlanViewHolder, position: Int) {
        val item = mealPlans[position]
        holder.dayTextView.text = item.day
        holder.planTextView.text = item.planOfDay
    }

    override fun getItemCount(): Int = mealPlans.size
}