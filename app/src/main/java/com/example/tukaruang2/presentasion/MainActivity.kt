package com.example.tukaruang2.presentasion

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tukaruang2.R
import com.example.tukaruang2.databinding.ActivityMainBinding
import com.example.tukaruang2.util.RemoteResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  val viewmodel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //data binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        //click button convert
        binding.btnKonvert.setOnClickListener {
            viewmodel.convert(
                binding.etFrom.text.toString(),
                binding.spfrom.selectedItem.toString(),
                binding.spto.selectedItem.toString()
            )
        }
        lifecycleScope.launchWhenStarted {
            viewmodel.conversion.collect { event ->
                when(event){
                    is RemoteResponse.Succes ->{
                        binding.tvResult.setTextColor(Color.BLACK)
                        binding.tvResult.text = event.sukses
                    }
                    is RemoteResponse.Failed ->{
                        binding.tvResult.setTextColor(Color.RED)
                        binding.tvResult.text = event.fail
                    }
                    else -> Unit
                }
            }
        }
    }
}