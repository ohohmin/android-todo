package com.example.todo

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.todo.adaptor.CustomAdapter
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.obj.Todo

class MainActivity : AppCompatActivity() {

    // binding 변수 선언
    private lateinit var binding: ActivityMainBinding

    /**
     * todo 목록
     */
    private val todoList: ArrayList<Todo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binding 변수에 layout 을 연결
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)

        // todoList 안의 객체의 개수가 없을경우 메세지 표시
        // 등록된게 있을경우 등록내용 표시
        if (todoList.isEmpty()) {
            binding.announce.visibility = View.VISIBLE
            binding.list.visibility = View.GONE
        } else {
            binding.announce.visibility = View.GONE
            binding.list.visibility = View.VISIBLE
        }

        val adapter = CustomAdapter(todoList,
            // 목록중 하나의 todo를 클릭했을 때 동작 정의
            { index ->
                val intent = Intent(this, UpdateActivity::class.java)
                intent.putExtra("data", todoList.get(index))
                intent.putExtra("index", index)
                startActivityForResult(intent, 2000)
            }
        )
        // 목록중 하나의 todo를 길게 클릭했을 때 동작 정의
        { index ->
            val builder = AlertDialog.Builder(this)
            builder.setMessage("삭제하시겠습니까?")
                .setPositiveButton(
                    "삭제"
                ) { _, _ ->
                    // 데이터 삭제
                    todoList.removeAt(index)

                    // todoList 안의 객체의 개수가 없을경우 메세지 표시
                    // 등록된게 있을경우 등록내용 표시
                    if (todoList.isEmpty()) {
                        binding.announce.visibility = View.VISIBLE
                        binding.list.visibility = View.GONE
                    } else {
                        binding.announce.visibility = View.GONE
                        binding.list.visibility = View.VISIBLE
                    }

                    // 정렬
                    todoList.sortBy {
                        it.date
                    }

                    // 가장 큰 날짜가 위로오도록 정렬
                    todoList.reverse()

                    // 변경됬음을 전달
                    binding.list.adapter?.notifyDataSetChanged()
                }
                .setNegativeButton(
                    "취소"
                ) { _, _ ->
                }
            builder.create()
            builder.show()
        }

        // 목록의 어뎁터 전달
        binding.list.adapter = adapter

        // 등록하기 버튼 클릭시 등록 액티비티 실행
        binding.writeButton.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivityForResult(intent, 1000)
        }

    }

    /**
     * 액티비티에서 넘어온 값을 받아서 처리하는 함수
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 1000은 등록화면을 호출할 때 넘겼던 데이터
        if (requestCode === 1000 && resultCode === Activity.RESULT_OK) {
            val newTodo = data?.getParcelableExtra<Todo>("data")
            if (newTodo !== null) {
                todoList.add(newTodo)
            }

            // todoList 안의 객체의 개수가 없을경우 메세지 표시
            // 등록된게 있을경우 등록내용 표시
            if (todoList.isEmpty()) {
                binding.announce.visibility = View.VISIBLE
                binding.list.visibility = View.GONE
            } else {
                binding.announce.visibility = View.GONE
                binding.list.visibility = View.VISIBLE
            }

            // 정렬
            todoList.sortBy {
                it.date
            }

            // 가장 큰 날짜가 위로오도록 정렬
            todoList.reverse()

            // 변경됬음을 전달
            binding.list.adapter?.notifyDataSetChanged()

            binding.list.adapter?.notifyDataSetChanged()
        }

        // 2000은 등록화면을 호출할 때 넘겼던 데이터
        else if (requestCode === 2000 && resultCode === Activity.RESULT_OK) {
            val newTodo = data?.getParcelableExtra<Todo>("data")
            val index = data?.getIntExtra("index", -1)
            if (newTodo !== null && index != null && index > -1) {
                todoList.set(index, newTodo)

                // todoList 안의 객체의 개수가 없을경우 메세지 표시
                // 등록된게 있을경우 등록내용 표시
                if (todoList.isEmpty()) {
                    binding.announce.visibility = View.VISIBLE
                    binding.list.visibility = View.GONE
                } else {
                    binding.announce.visibility = View.GONE
                    binding.list.visibility = View.VISIBLE
                }

                // 정렬
                todoList.sortBy {
                    it.date
                }

                // 가장 큰 날짜가 위로오도록 정렬
                todoList.reverse()

                // 변경됬음을 전달
                binding.list.adapter?.notifyDataSetChanged()
            }

        }
    }
}