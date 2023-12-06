package com.slowpoke.simpletimer_livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.slowpoke.simpletimer_livedata.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.seconds.observe(this) {
            binding.tvTimeLeft.text = viewModel.seconds.value.toString()
        }

        viewModel.isFinished.observe(this) {
            if (it) {
                Toast.makeText(this, "Countdown done!", Toast.LENGTH_SHORT).show()
            }
        }


        binding.btnStartTimer.setOnClickListener {
            val userInput = binding.etTimeInput.text
            if (userInput.isEmpty() || userInput.length < 4) {
                Toast.makeText(this@MainActivity, "Invalid input", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.changeTimerValue(userInput.toString().toLong())
                viewModel.startTimer()
            }
        }

        binding.btnStopTimer.setOnClickListener {
            binding.tvTimeLeft.text = "0"
            viewModel.stopTimer()
        }
    }

}

