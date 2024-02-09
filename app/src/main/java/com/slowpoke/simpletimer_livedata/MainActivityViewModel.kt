package com.slowpoke.simpletimer_livedata

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private lateinit var timer: CountDownTimer

    private val _seconds = MutableLiveData<Int>()
    val seconds: LiveData<Int>
        get() = _seconds

    private val _isFinished = MutableLiveData<Boolean>()
    val isFinished: LiveData<Boolean>
        get() = _isFinished

    private val _isPaused = MutableLiveData<Boolean>(false)
    val isPaused: LiveData<Boolean>
        get() = _isPaused

    fun startTimer(startingTimeInSeconds: Int) {
        if (::timer.isInitialized) {
            timer.cancel()
        }

        _seconds.value = startingTimeInSeconds
        _isFinished.value = false
        timer = object : CountDownTimer(startingTimeInSeconds * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val currentValue = _seconds.value!!
                _seconds.value = currentValue - 1
            }

            override fun onFinish() {
                _isFinished.value = true
            }

        }.start()
    }

    fun stopTimer() {
        timer.cancel()
        if (_isPaused.value!!) _seconds.value = 0
    }

    fun pauseTimer() {
        if (_isPaused.value!!) {
            startTimer(_seconds.value!!)
            _isPaused.value = false
        } else {
            stopTimer()
            _isPaused.value = true
        }



    }
}