package com.example.tukaruang

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.tukaruang.databinding.ActivityMainBinding
import com.example.tukaruang.main.mainviewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewmodel: mainviewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)    //data binding

        binding.btnkonvert.setOnClickListener {
            viewmodel.convert(
                    binding.etfrom.text.toString(),
                    binding.spfromcur.selectedItem.toString(),
                    binding.sptocur.selectedItem.toString(),
            )
        }
        lifecycleScope.launchWhenStarted {
            viewmodel.konversion.collect { event ->
                when(event){
                    is mainviewmodel.currencyevent.succes -> {
                        binding.progressBar.isVisible = false
                        binding.tvresult.setTextColor(Color.BLACK)
                        binding.tvresult.text = event.resulttext
                    }
                    is mainviewmodel.currencyevent.failure -> {
                        binding.progressBar.isVisible = true
                        binding.tvresult.setTextColor(Color.RED)
                        binding.tvresult.text = event.errortext
                    }
                    is mainviewmodel.currencyevent.loading -> {
                        binding.progressBar.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
    }
}