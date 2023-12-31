package com.miu.foodiepal_culinarycompanion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            MealPlanFragment(),
            BlogFragment(),
            ContactFragment(),
            AboutMeFragment()
        )

        val viewPagerAdapter = ViewPagerAdapter(
            fragments,
            supportFragmentManager,
            lifecycle
        )
        viewPager.adapter = viewPagerAdapter

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Recipes"
                1 -> tab.text = "Meal Planner"
                2 -> tab.text = "Blog"
                3 -> tab.text = "Contact"
                4 -> tab.text = "About Me"
            }
        }.attach()

        setupBottomNavigationView(viewPager);
    }

    private fun setupBottomNavigationView(viewPager: ViewPager2) {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_item1 -> {
                    viewPager.currentItem = 0
                    true
                }

                R.id.menu_item2 -> {
                    viewPager.currentItem = 1
                    true
                }

                R.id.menu_item3 -> {
                    viewPager.currentItem = 2
                    true
                }

                else -> false
            }
        }
    }
}