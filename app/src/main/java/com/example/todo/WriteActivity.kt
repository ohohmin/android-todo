package com.example.todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todo.databinding.ActivityWriteBinding
import com.example.todo.obj.Todo
import com.google.android.material.snackbar.Snackbar


class WriteActivity : AppCompatActivity() {

    // binding 변수 선언
    private lateinit var binding: ActivityWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding 변수에 layout 을 연결
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_write)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setTitle("")
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        // 등록버튼 눌렀을때 동작 정의
        binding.writeButton.setOnClickListener {

            // 유효성검사
            if (binding.summary.text!!.isEmpty()) {
                Snackbar.make(it, "요약을 입력해주세요", 300).show()
            } else if (binding.date.text!!.isEmpty()) {
                Snackbar.make(it, "날짜를 입력해주세요", 300).show()
            } else if (binding.place.text!!.isEmpty()) {
                Snackbar.make(it, "장소을 입력해주세요", 300).show()
            }else if(binding.content.text!!.isEmpty()) {
                Snackbar.make(it,"내용을 입력해주세요",300).show()
            }

            /**
             * 내용을 입력하지 않았을 때 체크하는 코드를 수정하세요
             */

            else {
                val intent = Intent()

                /**
                 * 내용이 Todo객체에 들어가도록 코드를 수정하세요
                 */
                intent.putExtra(
                    "data",
                    Todo(
                        binding.summary.text.toString(),
                        binding.date.text.toString(),
                        binding.place.text.toString(),
                        binding.content.text.toString()
                    )
                )
                // 액티비티 성공 및 데이터 전달
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }


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