package com.example.handsup

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import com.example.handsup.databinding.FragmentMainGameBinding

private lateinit var category: Category

class MainGameFragment : Fragment() {
    private lateinit var binding: FragmentMainGameBinding
    private lateinit var sm: SensorManager
    private lateinit var s: Sensor
    private lateinit var sensorEventListener: SensorEventListener
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

        categoryWords = category.words.toMutableList()
        binding.TestMiss.setOnClickListener(::missForButton)
        binding.TestRight.setOnClickListener(::rightForButton)
        content = binding.wordView
        timerView = binding.timerView

        if (isUseAccelerometer){
            binding.TestMiss.visibility = View.GONE
            binding.TestRight.visibility = View.GONE
            sm = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            s = sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
            categoryDescription = category.description
            sensorEventListener = object: SensorEventListener {
                @SuppressLint("SetTextI18n")
                override fun onSensorChanged(event: SensorEvent?) {
                    if (event != null) {

                        var g0 = event.values[0]
                        var g1 =  event.values[1]
                        var g2 = event.values[2]

                        val normalizedG = Math.sqrt((g0*g0 + g1*g1 + g2*g2).toDouble())

                        g0 = (g0/normalizedG).toFloat()
                        g1 = (g1/normalizedG).toFloat()
                        g2 = (g2/normalizedG).toFloat()

                        val inclinationG2 = Math.round(Math.toDegrees(Math.acos(g2.toDouble())))
                        val inclinationG1 = Math.round(Math.toDegrees(Math.acos(g1.toDouble())))
                        val inclinationG0 = Math.round(Math.toDegrees(Math.acos(g0.toDouble())))

                        if (inclinationG2 < 15 || inclinationG2 > 150){

                            binding.TESTACCEL.text =
                                "ЛЕЖИТ ЭКРАНОМ В ВЕРХ $inclinationG2,  $inclinationG1,  $inclinationG0"
                            miss()

                        } else if (inclinationG2 in 95..100){

                            binding.TESTACCEL.text = "ЛЕЖИТ ЭКРАНОМ В НИЗ $inclinationG2,  $inclinationG1,  $inclinationG0"
                            right()

                        } else{

                            binding.TESTACCEL.text = "В РУКАХ $inclinationG2,  $inclinationG1,  $inclinationG0"

                        }
                    }
                }

                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

                }

            }
        }
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
        binding.Screen.setBackgroundColor(Color.rgb(249, 183, 107))

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
    private fun rightForButton(view: View){
        right()
    }

    private fun missForButton(view: View){
        miss()
    }
    fun right() {

        if (preparationTimerIsOverFlag){
            if(animationIsOverFlag in arrayOf(1, 0)){
                val word = content.text.toString()
                guessedAndNotGuessedWords.add(Word(word, true))
                animation(false, getRandomWord(),  Color.rgb(202, 211, 93))
                ifWordIsOver(categoryWords as MutableList<String>)

            }
        }
    }

    fun miss() {
        if (preparationTimerIsOverFlag){
            if(animationIsOverFlag in arrayOf(1, 0)){
                val word = content.text.toString()
                guessedAndNotGuessedWords.add(Word(word, false))
                animation(true, getRandomWord(),  Color.rgb(202, 211, 93))
                ifWordIsOver(categoryWords as MutableList<String>)

            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (isUseAccelerometer){
            sm.registerListener(sensorEventListener, s, SensorManager.SENSOR_DELAY_FASTEST)
        }
    }

    override fun onPause() {
        super.onPause()
        if (isUseAccelerometer){
            sm.unregisterListener(sensorEventListener)
        }
    }

}