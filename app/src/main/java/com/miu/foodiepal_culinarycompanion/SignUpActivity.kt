package com.miu.foodiepal_culinarycompanion

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.miu.foodiepal_culinarycompanion.databinding.ActivityLoginBinding
import com.miu.foodiepal_culinarycompanion.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_credentials", Context.MODE_PRIVATE)

        // Fetch stored credentials
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")

        if(savedUsername?.isNotEmpty() == true && savedPassword?.isNotEmpty() == true){
            navigateToLoginActivity()
        }

        binding.btnChooseFile.setOnClickListener {
            openFileChooser()
        }

        binding.registerButton.setOnClickListener {
            signupUser()
        }
    }

    private fun openFileChooser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri: Uri = data.data!!
            binding.imageView.setImageURI(imageUri)

            // Save the image URI to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("profile_picture_uri", imageUri.toString())
            editor.apply()
        }
    }

    private fun signupUser() {
        // Fetch other user input fields (fullName, username, password)
        val fullName = binding.fullNameEditText.text.toString()
        val username = binding.usernameEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (isValidateInput(fullName, username, password)) {
            // Store the signup information securely using SharedPreferences

            val editor = sharedPreferences.edit()
            editor.putString("full_name", fullName)
            editor.putString("username", username)
            editor.putString("password", password)
            editor.apply()

            // Navigate to the login screen after successful signup
            navigateToLoginActivity()
        }
    }

    private fun isValidateInput(name: String, username: String, pass: String): Boolean {
        if (name.isEmpty() || username.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please Enter All Field!", Toast.LENGTH_LONG).show()
            return false;
        }
        if (pass.length < 8) {
            Toast.makeText(this, "Password Length should be >=8", Toast.LENGTH_LONG).show()
            return false;
        }
        return true;
    }

    private fun navigateToLoginActivity() {
        // Intent to start the login activity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Close the signup activity
    }
}