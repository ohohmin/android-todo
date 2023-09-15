package com.example.todo.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.obj.Todo

class CustomAdapter(
    private val dataSet: ArrayList<Todo>,
    private val click: (index: Int) -> Unit,
    private val longClick: (index: Int) -> Unit
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val summaryTextView: TextView
        val placeTextView: TextView
        val dateTextView: TextView

        init {
            summaryTextView = view.findViewById(R.id.summary)
            placeTextView = view.findViewById(R.id.place)
            dateTextView = view.findViewById(R.id.date)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.summaryTextView.text = dataSet[position].summary
        viewHolder.dateTextView.text = dataSet[position].date
        viewHolder.placeTextView.text = dataSet[position].place
        viewHolder.itemView.setOnClickListener{
            this.click(position)
        }
        viewHolder.itemView.setOnLongClickListener {
            this.longClick(position)
            true
        }
    }

    override fun getItemCount() = dataSet.size

}
