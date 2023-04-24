package com.example.handsup

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.handsup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.navigationBarColor = resources.getColor(R.color.pink)
        binding.playButton.setOnClickListener(::goToSelectCategoryActivity)
    }

    private fun goToSelectCategoryActivity(view:View){

        val intent = Intent(this, RulesActivity::class.java)
        startActivity(intent)

    }
}