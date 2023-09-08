package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // binding 변수 선언
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding 변수에 layout 을 연결
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)

        binding.title // ...

    }
}