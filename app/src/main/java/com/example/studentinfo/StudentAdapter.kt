package com.example.studentinfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class StudentAdapter(private val context: Context, private val dataSource: ArrayList<Student>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_item_student, parent, false)

        val textViewName = rowView.findViewById(R.id.textViewName) as TextView
        val textViewAge = rowView.findViewById(R.id.textViewAge) as TextView
        val textViewMajor = rowView.findViewById(R.id.textViewMajor) as TextView
        val textViewDepartment = rowView.findViewById(R.id.textViewDepartment) as TextView

        val student = getItem(position) as Student

        textViewName.text = student.name
        textViewAge.text = "Age: ${student.age}"
        textViewMajor.text = "Major: ${student.major}"
        textViewDepartment.text = "Department: ${student.department}"

        return rowView
    }
}
