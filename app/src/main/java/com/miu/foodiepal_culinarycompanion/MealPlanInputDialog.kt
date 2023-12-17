package com.miu.foodiepal_culinarycompanion

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MealPlanInputDialog(context: Context, private val listener: OnMealPlanInputListener) {

    private val dialog: Dialog

    init {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.dialog_add_meal_plan, null)

        val editTextPlan: EditText = view.findViewById(R.id.editTextPlan)
        val btnAddMeal: Button = view.findViewById(R.id.btnAddMeal)
        val calendarView: CalendarView = view.findViewById(R.id.calendarView)

        dialog = MaterialAlertDialogBuilder(context)
            .setView(view)
            .setTitle("Add Meal Plan")
            .create()

        var selectedDate = ""
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)

            val dayName = SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.time)
            selectedDate = "${dayName.toUpperCase()}, $year-${month + 1}-$dayOfMonth"
        }

        btnAddMeal.setOnClickListener {
            val day = selectedDate
            val plan = editTextPlan.text.toString()

            if (day.isNotEmpty() && plan.isNotEmpty()) {
                listener.onMealPlanInput(day, plan)
                dialog.dismiss()
            } else {
                Toast.makeText(
                    context,
                    "Enter All Fields",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun show() {
        dialog.show()
    }

    interface OnMealPlanInputListener {
        fun onMealPlanInput(weekDay: String, planOfDay: String)
    }
}