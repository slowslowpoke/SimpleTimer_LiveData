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


    private val _timerValue = MutableLiveData<Long>()

    fun changeTimerValue(newTimerValue: Long){
        _timerValue.value = newTimerValue
    }



    private val _isFinished = MutableLiveData<Boolean>()
    val isFinished: LiveData<Boolean>
        get() = _isFinished

    fun startTimer(){
        _isFinished.value = false
        timer = object: CountDownTimer(_timerValue.value!!, 1000){

            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                _seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {
                _isFinished.value = true
            }

        }.start()
    }

    fun stopTimer(){
        timer.cancel()
    }
}