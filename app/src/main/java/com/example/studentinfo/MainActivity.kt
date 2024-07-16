package com.example.studentinfo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studentinfo.ui.theme.StudentinfoTheme


class MainActivity : ComponentActivity() {

    private lateinit var btnAddStudent: Button
    private lateinit var listViewStudents: ListView
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: ArrayList<Student>

    private val addStudentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            data?.let {
                val name = it.getStringExtra("name")
                val age = it.getStringExtra("age")
                val major = it.getStringExtra("major")
                val department = it.getStringExtra("department")
                val position = it.getIntExtra("position", -1)
                if (name != null && age != null && major != null && department != null) {
                    if (position == -1) {
                        // Add new student
                        studentList.add(Student(name, age, major, department))
                    } else {
                        // Update existing student
                        studentList[position] = Student(name, age, major, department)
                    }
                    studentAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddStudent = findViewById(R.id.btnAddStudent)
        listViewStudents = findViewById(R.id.listViewStudents)

        studentList = ArrayList()
        studentAdapter = StudentAdapter(this, studentList)
        listViewStudents.adapter = studentAdapter

        btnAddStudent.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            addStudentLauncher.launch(intent)
        }

        listViewStudents.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val student = studentList[position]
            val intent = Intent(this, AddStudentActivity::class.java).apply {
                putExtra("name", student.name)
                putExtra("age", student.age)
                putExtra("major", student.major)
                putExtra("department", student.department)
                putExtra("position", position)
            }
            addStudentLauncher.launch(intent)
        }
    }
}