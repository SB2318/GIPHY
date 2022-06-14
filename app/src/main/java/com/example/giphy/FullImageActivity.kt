package com.example.giphy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class FullImageActivity : AppCompatActivity(){

   private val fullImageView:ImageView
     get()= findViewById(R.id.full_image)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)

        val sourceUrl= intent.getStringExtra("imageUrl")

        Glide.with(this).load(sourceUrl).into(fullImageView)
    }
}