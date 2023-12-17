package com.miu.foodiepal_culinarycompanion

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    val fragments: List<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        //Write the necessary code
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        //Write the necessary code
        return when (position) {
            0 -> RecipeFragment()
            1 -> MealPlanFragment()
            else -> Fragment()
        }
    }

}