package com.slowpoke.simpletimer_livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.slowpoke.simpletimer_livedata.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var timer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timer = object: CountDownTimer(24000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvNumber.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Countdown is done!", Toast.LENGTH_SHORT).show()
            }

        }.start()
    }

}

