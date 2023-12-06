package com.slowpoke.simpletimer_livedata

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private lateinit var timer: CountDownTimer
    private val _seconds = MutableLiveData<Int>()
    val seconds: LiveData<Int>
        get() = _seconds

    fun startTimer(){
        timer = object: CountDownTimer(10000, 1000){

            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                _seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {
            }

        }.start()
    }

    fun stopTimer(){
        timer.cancel()
    }
}