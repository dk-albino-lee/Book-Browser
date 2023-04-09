package com.movingroot.flowassignment.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.movingroot.flowassignment.databinding.ActivityMainBinding
import com.movingroot.flowassignment.presentation.base.BaseApplication
import com.movingroot.flowassignment.presentation.di.main.MainComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainComponent: MainComponent
    @Inject lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (applicationContext as BaseApplication)
            .applicationComponent.mainComponent().create()
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
