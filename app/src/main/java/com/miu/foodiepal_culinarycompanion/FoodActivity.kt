package com.miu.foodiepal_culinarycompanion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.miu.foodiepal_culinarycompanion.databinding.ActivityFoodBinding

class FoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout
        val fragments: List<Fragment> = listOf(
            RecipeFragment(),
            MealPlanFragment()
        )

        val viewPagerAdapter = ViewPagerAdapter(
            fragments,
            supportFragmentManager,
            lifecycle
        )
        viewPager.adapter = viewPagerAdapter

        //Write the necessary code
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            when(position){
                0 -> tab.text = "Recipes"
                1 -> tab.text = "Meal Planner"
            }
        }.attach()
    }
}