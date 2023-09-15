package com.example.todo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.example.todo.databinding.ActivityUpdateBinding
import com.example.todo.obj.Todo
import com.google.android.material.snackbar.Snackbar

class UpdateActivity : AppCompatActivity() {

    // binding 변수 선언
    private lateinit var binding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding 변수에 layout 을 연결
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_update)

        // 툴바 설정
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setTitle("")
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        // 메인에서 넘겨준 데이터를 저장
        val todo = intent.getParcelableExtra<Todo>("data")
        val index = intent.getIntExtra("index", -1)

        if(todo != null) {
            // 메인에서 넘겨준 데이터를 뷰에 설정
            binding.summary.setText(todo.summary)
            binding.date.setText(todo.date)
            binding.place.setText(todo.place)

            /**
             * 메인에서 넘겨준 todo중 content의 값을 뷰에 설정하는 코드를 만들어주세요
             */
        }

        binding.updateButton.setOnClickListener{

            if (binding.summary.text!!.isEmpty()) {
                Snackbar.make(it, "요약을 입력해주세요", 300).show()
            } else if (binding.date.text!!.isEmpty()) {
                Snackbar.make(it, "날짜를 입력해주세요", 300).show()
            } else {
                val intent = Intent()
                intent.putExtra(
                    "data",

                    /**
                     * 내용을 메인으로 넘겨줄 수 있도록 코드를 수정해주세요
                     */
                    Todo(
                        binding.summary.text.toString(),
                        binding.date.text.toString(),
                        binding.place.text.toString()
                    )
                )
                intent.putExtra("index", index)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }


    /**
     * 툴바 뒤로가기 눌렀을 때 설정
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //toolbar의 back키 눌렀을 때 동작
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}