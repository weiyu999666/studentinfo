package com.example.studentinfo;

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.ComponentActivity

class AddStudentActivity : ComponentActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var editTextMajor: EditText
    private lateinit var spinnerDepartment: Spinner
    private lateinit var btnSave: Button
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        editTextName = findViewById(R.id.editTextName)
        editTextAge = findViewById(R.id.editTextAge)
        editTextMajor = findViewById(R.id.editTextMajor)
        spinnerDepartment = findViewById(R.id.spinnerDepartment)
        btnSave = findViewById(R.id.btnSave)

        ArrayAdapter.createFromResource(
            this,
            R.array.departments_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerDepartment.adapter = adapter
        }

        // Get the data if we are editing an existing student
        intent?.let {
            editTextName.setText(it.getStringExtra("name"))
            editTextAge.setText(it.getStringExtra("age"))
            editTextMajor.setText(it.getStringExtra("major"))
            val department = it.getStringExtra("department")
            position = it.getIntExtra("position", -1)
            val departmentIndex = resources.getStringArray(R.array.departments_array).indexOf(department)
            spinnerDepartment.setSelection(departmentIndex)
        }

        btnSave.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString()
            val major = editTextMajor.text.toString()
            val department = spinnerDepartment.selectedItem.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("name", name)
            resultIntent.putExtra("age", age)
            resultIntent.putExtra("major", major)
            resultIntent.putExtra("department", department)
            resultIntent.putExtra("position", position)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
