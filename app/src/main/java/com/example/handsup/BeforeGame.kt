package com.example.handsup

import android.R
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.handsup.databinding.ActivityBeforeGameBinding

class BeforeGame : AppCompatActivityWithHideSystemUI() {
    private lateinit var binding: ActivityBeforeGameBinding


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeforeGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(com.example.handsup.R.id.gamePlaceholder, SelectCategoryFragment.newInstance()).commit()
        hideSystemUI()
    }
}