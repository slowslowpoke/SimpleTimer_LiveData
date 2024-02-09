package com.slowpoke.simpletimer_livedata

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.slowpoke.simpletimer_livedata.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnPauseTimer.isEnabled = false
        binding.btnStopTimer.isEnabled = false


        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.seconds.observe(this) {
            binding.tvTimeLeft.text = it.toString()
        }

        viewModel.isFinished.observe(this) {
            if (it) {
                Toast.makeText(this, "Countdown done!", Toast.LENGTH_SHORT).show()
             }
        }

        viewModel.isPaused.observe(this) {
            if (it) {
                binding.btnPauseTimer.text = getString(R.string.resume)
            } else {
                binding.btnPauseTimer.text = getString(R.string.pause)
            }
        }


        binding.btnStartTimer.setOnClickListener {
            val userInputInSeconds = binding.etTimeInput.text.toString().toIntOrNull()
            if (userInputInSeconds == null || userInputInSeconds < 0) {
                Toast.makeText(this, getString(R.string.invalid_input), Toast.LENGTH_SHORT).show()
            } else {
                viewModel.startTimer(userInputInSeconds)
                binding.btnStopTimer.isEnabled = true
                binding.btnPauseTimer.isEnabled = true
            }
        }

        binding.btnStopTimer.setOnClickListener {
            binding.tvTimeLeft.text = "0"
            viewModel.stopTimer()
            binding.btnStopTimer.isEnabled = false
            binding.btnPauseTimer.isEnabled = false
        }

        binding.btnPauseTimer.setOnClickListener {

            viewModel.pauseTimer()
        }
    }

}

