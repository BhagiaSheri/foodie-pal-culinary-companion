package com.miu.foodiepal_culinarycompanion

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.miu.foodiepal_culinarycompanion.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_credentials", Context.MODE_PRIVATE)

        loginBinding.loginButton.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val username = loginBinding.usernameEditText.text.toString()
        val password = loginBinding.passwordEditText.text.toString()

        // Validate input fields
        if (username.isEmpty() || password.isEmpty()) {
            // Show appropriate feedback for empty fields
            showToast("Username and password are required.")
            return
        }

        // Fetch stored credentials
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")

        // Check if entered credentials match stored credentials
        if (username == savedUsername && password == savedPassword) {
            // Authentication successful
            navigateToFoodActivity()
        } else {
            // Authentication failed
            showToast("Invalid Username or Password.")
        }
    }

    private fun navigateToFoodActivity() {
        // Intent to start the result activity
        val intent = Intent(this, FoodActivity::class.java)
        startActivity(intent)
        finish() // Close the login activity

        showToast("Login Success")
    }

    private fun showToast(message: String) {
        // Show a toast message
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}