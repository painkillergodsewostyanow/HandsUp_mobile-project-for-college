package com.example.handsup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Game : AppCompatActivity() {
    companion object{
        const val CATEGORY = "Category"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }
}