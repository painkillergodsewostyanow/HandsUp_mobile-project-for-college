package com.example.handsup


import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.handsup.databinding.ActivityGameResultBinding


class GameResultActivity : AppCompatActivity() {
    private lateinit var bindingClass: ActivityGameResultBinding
    companion object{

        const val GUESSED_AND_NOT_GUESSED_WORDS = "list of pair<word(STRING), guess or not guess (bool)>"

    }
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityGameResultBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        hideSystemUI()
        val wordList = mutableListOf<ResultWord>()
        val guessedAndNotGuessedWords = intent.getParcelableArrayListExtra<Word>(GUESSED_AND_NOT_GUESSED_WORDS) as ArrayList<Word>
        var count = 1
        var countRightAnswers = 0

        for (word in guessedAndNotGuessedWords){
            val image: Int
            if (word.isGuessed){
                image = R.drawable.guessed
                countRightAnswers++
            }else {image = R.drawable.not_guessed}
            wordList.add(ResultWord("$count.", word.word, image))
            count++

        }

        bindingClass.nubmerRightAnswwers.text = countRightAnswers.toString()
        bindingClass.Retry.setOnClickListener(::retry)
        bindingClass.Next.setOnClickListener(::goToSelectCategoryActivity)

        val myListView = findViewById<ListView>(R.id.my_list_view)
        myListView.adapter = AdapterForListView(this, wordList)
    }

    private fun retry(view: View){
        Toast.makeText(this, "НЕ РАБОТАЕТ!",
            Toast.LENGTH_LONG).show();

    }
    private fun goToSelectCategoryActivity(view:View){

        val intent = Intent(this, BeforeGame::class.java)
        startActivity(intent)

    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window,
            window.decorView.findViewById(android.R.id.content)).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

}