package com.bignerdranch.android.geoquiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "CheatViewModel"
const val IS_ANSWER_SHOWN_KEY = "IS_ANSWER_SHOWN_KEY"

class CheatViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    init {
        Log.d(TAG, "ViewModel Instance created")
    }
    var isCheater: Boolean = false
    var isAnswerShown: Boolean
        get() = savedStateHandle.get(IS_ANSWER_SHOWN_KEY) ?: false
        set(value) = savedStateHandle.set(IS_ANSWER_SHOWN_KEY, value)

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }
}