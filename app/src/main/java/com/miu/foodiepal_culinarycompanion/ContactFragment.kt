package com.miu.foodiepal_culinarycompanion

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast

class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_contact, container, false)


        val callButton: Button = view.findViewById(R.id.callButton)
        val emailButton: Button = view.findViewById(R.id.emailButton)
        val phoneIcon: ImageView = view.findViewById(R.id.phoneIcon)
        val emailIcon: ImageView = view.findViewById(R.id.emailIcon)

        // Set up fade-in animations
        val fadeInAnimation = AnimatorSet()
        val fadeInCallButton = ObjectAnimator.ofFloat(callButton, "alpha", 0f, 1f)
        val fadeInEmailButton = ObjectAnimator.ofFloat(emailButton, "alpha", 0f, 1f)
        fadeInAnimation.playTogether(fadeInCallButton, fadeInEmailButton)
        fadeInAnimation.interpolator = AccelerateDecelerateInterpolator()
        fadeInAnimation.duration = 1000

        // Set up rotation animations
        val rotatePhoneIcon = ObjectAnimator.ofFloat(phoneIcon, "rotation", 0f, 360f)
        val rotateEmailIcon = ObjectAnimator.ofFloat(emailIcon, "rotation", 0f, 360f)
        val rotationAnimation = AnimatorSet()
        rotationAnimation.playTogether(rotatePhoneIcon, rotateEmailIcon)
        rotationAnimation.interpolator = AccelerateDecelerateInterpolator()
        rotationAnimation.duration = 2000

        // Start animations
        fadeInAnimation.start()
        rotationAnimation.start()


        callButton.setOnClickListener {
            val phoneNumber = "+1 (641) 233-7690"
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(dialIntent)
        }

        emailButton.setOnClickListener {
            val email = arrayOf("bhagiasheri24@gmail.com")
            val subject = "Inquiry Regarding Recent Recipe"
            val message = """|Dear Chef, 
                | I hope you are doing well.
                | 
                | I wanted to discuss regarding recent recipe...
            """.trimMargin()

            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:")
            emailIntent.putExtra(Intent.EXTRA_EMAIL, email)
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            emailIntent.putExtra(Intent.EXTRA_TEXT, message)
            emailIntent.type = "message/rfc822"
            // Set the package to Gmail
            emailIntent.setPackage("com.google.android.gm")

            if (emailIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(emailIntent)
            } else {
                // If Gmail app is not installed, you can handle it differently, e.g., show a toast
                // or provide a fallback action.
                // Optionally, you can open the user's preferred email client with ACTION_SEND.
                // This may show an app chooser.
                emailIntent.action = Intent.ACTION_SEND
                startActivity(Intent.createChooser(emailIntent, "Choose an Email client"))
            }
        }

        return view;
    }
}