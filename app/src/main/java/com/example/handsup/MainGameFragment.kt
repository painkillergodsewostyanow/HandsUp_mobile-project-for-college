package com.example.handsup

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.handsup.databinding.FragmentMainGameBinding

private lateinit var category: Category

class MainGameFragment : Fragment() {
    private lateinit var binding: FragmentMainGameBinding
    private lateinit var categoryWords: List<String>
    private lateinit var categoryDescription: String
    private lateinit var content: TextView
    lateinit var timerView: TextView
    private var preparationTimerIsOverFlag = false
    private var animationIsOverFlag = 0 // - wasn't started
    var guessedAndNotGuessedWords: MutableList<Word> = mutableListOf()
    private lateinit var gameTimer: CountDownTimer
    private lateinit var preparetionTimer: CountDownTimer
    private lateinit var animationTimer: CountDownTimer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainGameBinding.inflate(inflater, container, false)
        categoryDescription = category.description
        categoryWords = category.words.toMutableList()
        content = binding.wordView
        timerView = binding.timerView
        binding.TestRight.setOnClickListener(::right)
        binding.TestMiss.setOnClickListener(::miss)
        startGame()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(arg1: Category): MainGameFragment {
            val fragment = MainGameFragment()
            category = arg1
            return fragment
        }

    }
    private fun showResult(){

        content.text = "RESULT"

    }

    private fun ifWordIsOver(array: MutableList<String>){

        if (array.isEmpty()){

            gameTimer.cancel()
            showResult()

        }

    }

    private fun getRandomWord():String{

        val word:String
        if (categoryWords.isNotEmpty()){

            val randomWordIndex = (categoryWords.indices).random()
            word = categoryWords[(randomWordIndex)]

            categoryWords = categoryWords.filterIndexed { index , _ ->

                index != randomWordIndex

            }

            return word

        }else {
            ifWordIsOver(categoryWords as MutableList<String>)
        }

        return ""

    }

    private fun game(){

        guessedAndNotGuessedWords.clear()
        val word = getRandomWord()
        content.text = word

        gameTimer = object: CountDownTimer(((playTime+1)*1000).toLong(), 1){
            override fun onTick(millisUntilFinished: Long) {
                timerView.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {

                showResult()

            }
        }.start()

    }

    private fun startGame(){
        content.text = "Приготовьтесь"
        preparetionTimer = object: CountDownTimer(((preparationTime+1)*1000).toLong(), 1){
            override fun onTick(millisUntilFinished: Long) {
                timerView.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                preparationTimerIsOverFlag = true
                game()
            }
        }.start()
    }
    private fun animation(isMiss: Boolean, startContent:String, defaultBackground: Int){
        val screen = binding.Screen
        val topImage = binding.top
        val backgroundColor: Int
        val message: String
        if (isMiss) {

            backgroundColor = Color.rgb(230, 0, 126)
            message = "Неверно"

        }else{

            backgroundColor = Color.rgb(202, 211, 93)
            message = "Правильно"

        }

        screen.setBackgroundColor(backgroundColor)
        topImage.visibility = View.GONE
        content.text = message
        animationIsOverFlag = 2 // - not over

        animationTimer = object: CountDownTimer(((answerAnimationTime)*1000).toLong(), 1){
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {

                screen.setBackgroundColor(defaultBackground)
                topImage.visibility = View.VISIBLE
                content.text = startContent
                animationIsOverFlag = 1 // - is over

            }
        }.start()
    }

    fun right(view:View) {

        if (preparationTimerIsOverFlag){
            if(animationIsOverFlag in arrayOf(1, 0)){
                val word = content.text.toString()
                guessedAndNotGuessedWords.add(Word(word, true))
                animation(false, getRandomWord(),  Color.rgb(202, 211, 93))
                ifWordIsOver(categoryWords as MutableList<String>)

            }
        }
    }

    fun miss(view:View) {
        if (preparationTimerIsOverFlag){
            if(animationIsOverFlag in arrayOf(1, 0)){
                val word = content.text.toString()
                guessedAndNotGuessedWords.add(Word(word, false))
                animation(true, getRandomWord(),  Color.rgb(202, 211, 93))
                ifWordIsOver(categoryWords as MutableList<String>)

            }
        }
    }

}