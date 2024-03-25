package com.bignerdranch.android.geoquiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


private const val TAG ="QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle:SavedStateHandle): ViewModel() {

 /*   init{

        Log.d(TAG, "QuizViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()

        Log.d(TAG, "QuizViewModel instance about to be destroyed")*/


    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex:Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY)?:0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText:Int
        get() = questionBank[currentIndex].textResId

    fun isCheaterForCurrentQuestion(): Boolean {
        val isCheaterArray = savedStateHandle.get<IntArray>(IS_CHEATER_KEY) ?: return false
        return isCheaterArray[currentIndex] != 0
    }

    fun setCheaterForCurrentQuestion(isCheater: Boolean) {
        val isCheaterArray = savedStateHandle.get<IntArray>(IS_CHEATER_KEY) ?: IntArray(questionBank.size)
        isCheaterArray[currentIndex] = if (isCheater) 1 else 0
        savedStateHandle.set(IS_CHEATER_KEY, isCheaterArray)
    }
    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}
