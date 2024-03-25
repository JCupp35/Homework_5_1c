package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()

    private val cheatLauncher = registerForActivityResult(

        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        // get results

        if (result.resultCode == Activity.RESULT_OK) {
            val isCheater = result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
            quizViewModel.setCheaterForCurrentQuestion(isCheater)
        }


    }
/*    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,  "onCreate (Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Got a QuizViewModel:$quizViewModel")



        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }
        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }
        binding.cheatButton.setOnClickListener {
         //val intent = Intent(this, CheatActivity::class.java)
          val answerIsTrue = quizViewModel.currentQuestionAnswer
          val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
           // startActivity(intent)
            cheatLauncher.launch(intent)
        }
        binding.nextButton.setOnClickListener {
          //  currentIndex = (currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()
    }

        private fun updateQuestion() {
           // val questionTextResId = questionBank[currentIndex].textResId
            val questionTextResId = quizViewModel.currentQuestionText
            binding.questionTextView.setText(questionTextResId)
        }
private fun checkAnswer(userAnswer: Boolean) {
    // val correctAnswer = questionBank[currentIndex].answer
    val correctAnswer = quizViewModel.currentQuestionAnswer
    val messageResId = when {
        quizViewModel.isCheaterForCurrentQuestion() -> "Cheating is wrong."
        userAnswer == correctAnswer -> "Correct!"
        else -> "Incorrect!"
    }
    Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
}

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart() called" )

    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume() called" )

    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause() called" )

    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop() called" )

    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy() called" )

    }

    }




