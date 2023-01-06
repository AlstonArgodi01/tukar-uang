package com.example.tukaruang2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.tukaruang2.databinding.ActivityMainBinding
import com.example.tukaruang2.repo.Mainviewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  val viewmodel: Mainviewmodel by viewModels()
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
            viewmodel.konversion.collect { event ->
                when(event){
                    is Mainviewmodel.currevent.berhasil ->{
                        binding.tvResult.setTextColor(Color.BLACK)
                        binding.tvResult.text = event.sukses
                    }
                    is Mainviewmodel.currevent.gagal ->{
                        binding.tvResult.setTextColor(Color.RED)
                        binding.tvResult.text = event.fail
                    }
                    else -> Unit
                }
            }
        }
    }
}