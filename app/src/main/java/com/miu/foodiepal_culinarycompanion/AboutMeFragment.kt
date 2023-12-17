package com.miu.foodiepal_culinarycompanion

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AboutMeFragment : Fragment(), DetailInputDialog.OnDetailInputListener {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddDetail: FloatingActionButton
    private lateinit var aboutMeAdapter: MyAboutMeRecyclerViewAdapter
    private val detailList = mutableListOf<Detail>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_me, container, false)

        // Retrieve data from SharedPreferences
        sharedPreferences =
            requireContext().getSharedPreferences("user_credentials", Context.MODE_PRIVATE)
        val fullName = sharedPreferences.getString("full_name", "")
        view.findViewById<TextView>(R.id.fullNameTextView).text = fullName


        recyclerView = view.findViewById(R.id.detailsRecyclerView)
        fabAddDetail = view.findViewById(R.id.fabAddDetails)

        // Set up RecyclerView
        aboutMeAdapter = MyAboutMeRecyclerViewAdapter(detailList)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = aboutMeAdapter

        detailList.add(0, Detail("Cooking Journey", "Started in Year 2023"))
        detailList.add(1, Detail("Favorite Food", "Pakistani Biryani"))
        detailList.add(2, Detail("Favorite Desert", "Gulab Jamun"))
        detailList.add(3, Detail("Cooking Philosophy", "Cook with Care :)"))

        // Set up FloatingActionButton click listener
        fabAddDetail.setOnClickListener {
            addDetail()
        }
        return view
    }

    private fun addDetail() {
        val detailInputDialog = DetailInputDialog(requireContext(), this)
        detailInputDialog.show()
    }

    override fun onDetailInput(title: String, content: String) {
        val funFact = Detail(title, content)
        detailList.add(funFact)
        aboutMeAdapter.notifyDataSetChanged()
    }

}