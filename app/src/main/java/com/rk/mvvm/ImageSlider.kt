package com.rk.mvvm

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.ViewSwitcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImageSlider : AppCompatActivity() {

    private lateinit var imageSwitcher: ImageSwitcher
    private lateinit var nextButton: Button
    private var currentIndex = 0
    val delayInMillis = 2000
    private val imageIds = arrayOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_image_slider)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        imageSwitcher = findViewById(R.id.imageSwitcher)
        nextButton = findViewById(R.id.nextButton)

        // Set the ViewFactory for ImageSwitcher
        imageSwitcher.setFactory(ViewSwitcher.ViewFactory {
            val imageView = ImageView(applicationContext)
            imageView.scaleType = ImageView.ScaleType.FIT_CENTER
            imageView
        })

        // Set animations
        imageSwitcher.inAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        imageSwitcher.outAnimation =
            AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)

        // Set initial image
        imageSwitcher.setImageResource(imageIds[currentIndex])


        for (i in 0 until imageIds.size) {
            switchImages()
        } // Duration of the splash screen

        // Set button click listener
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % imageIds.size
            imageSwitcher.setImageResource(imageIds[currentIndex])
        }
    }

    fun switchImages() {
        // Post a delayed task to switch the image
        Handler(Looper.getMainLooper()).postDelayed({
            // Update currentIndex to switch to the next image
            currentIndex = (currentIndex + 1) % imageIds.size
            // Set the next image in the ImageSwitcher
            imageSwitcher.setImageResource(imageIds[currentIndex])
            // Schedule the next image switch
            switchImages()
        }, delayInMillis.toLong())
    }
}