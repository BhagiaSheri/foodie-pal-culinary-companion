package com.miu.foodiepal_culinarycompanion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BlogFragment : Fragment(), BlogInputDialog.OnBlogInputListener {

    private lateinit var webView: WebView
    private lateinit var fabContribute: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var blogAdapter: MyBlogRecyclerViewAdapter

    // Replace this URL with the actual blog URL you want to display
    private val blogUrl = "https://pinchofyum.com/"
    private val blogs = mutableListOf<Blog>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blog_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewBlogs)
        webView = view.findViewById(R.id.webView)
        fabContribute = view.findViewById(R.id.fabContribute)

        val content = """
            Preheat Your Oven: Preheat your oven to 350°F (175°C) and line a baking sheet with parchment paper.
            Cream Butter and Sugars: In a large mixing bowl, cream together the softened butter, granulated sugar, and brown sugar until light and fluffy.
            Add Eggs and Vanilla: Beat in the eggs one at a time, ensuring each is fully incorporated. Add the vanilla extract and mix until smooth.
            Combine Dry Ingredients: In a separate bowl, whisk together the flour, baking soda, baking powder, and salt. Gradually add this mixture to the wet ingredients, mixing until just combined.
            Fold in Chocolate Chips: Gently fold in the chocolate chips, distributing them evenly throughout the cookie dough.
            Scoop and Bake: Drop rounded tablespoons of cookie dough onto the prepared baking sheet, leaving space between each cookie. Bake for 10-12 minutes or until the edges are golden brown.
            Cool and Enjoy: Allow the cookies to cool on the baking sheet for a few minutes before transferring them to a wire rack to cool completely. Once cooled, indulge in the irresistible goodness of homemade chocolate chip cookies!
            These cookies are perfect for sharing with friends and family or enjoying as a well-deserved treat for yourself. Get ready to fill your home with the delightful aroma of freshly baked cookies and savor the deliciousness in every bite.
            
            Happy baking! :)
        """.trimIndent()
        blogs.add(0, Blog("Delicious Homemade Chocolate Donuts", content))

        // Set up RecyclerView
        blogAdapter = MyBlogRecyclerViewAdapter(blogs, requireContext())
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = blogAdapter

        setupWebView()

        fabContribute.setOnClickListener {
            addNewBlog()
        }

        return view
    }

    private fun addNewBlog() {
        val blogInputDialog = BlogInputDialog(requireContext(), this)
        blogInputDialog.show()
    }

    private fun setupWebView() {
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()
        webView.loadUrl(blogUrl)
    }

    override fun onBlogInput(blogTitle: String, blogContent: String) {
        val newBlog = Blog(blogTitle, blogContent)
        blogs.add(newBlog)
        blogAdapter.notifyDataSetChanged()
    }
}