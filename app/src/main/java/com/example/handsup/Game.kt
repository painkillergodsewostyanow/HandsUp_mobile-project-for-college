package com.example.handsup

import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class Game : AppCompatActivityWithHideSystemUI() {
    lateinit var category: Category
    companion object{
        const val CATEGORY = "category"
    }
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        category = intent.getSerializableExtra(CATEGORY) as Category
        setContentView(R.layout.activity_game)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.gameFragments, CategoryDescriptionFragment.newInstance(category)).commit()
        hideSystemUI()
    }
}