package com.example.esameandroid_letiziagiammorcaro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.esameandroid_letiziagimmorcaro.R

const val EXTRA_STUDENT = "com.example.esameandroid_letiziagiammorcaro"
const val EXTRA_SAVE_ALL = "com.example.esameandroid_letiziagiammorcaro"
class StudentActivity : AppCompatActivity(){

    private lateinit var student:Student
    private lateinit var name: TextView
    private lateinit var macBook: TextView
    private lateinit var inComodato: CheckBox
    private lateinit var buttonSave: Button

    companion object{
        fun newIntent(context: Context, student: Student): Intent{
            return Intent(context, StudentActivity::class.java).apply {
                putExtra(EXTRA_STUDENT,student)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_details)

        name = findViewById(R.id.name)
        macBook = findViewById(R.id.macBook)
        inComodato = findViewById(R.id.flag)
        buttonSave = findViewById(R.id.buttonSave)

        student = intent.getSerializableExtra(EXTRA_STUDENT) as Student

        name.text = student.name
        macBook.text = student.macBook

        buttonSave.setOnClickListener {
            val data = Intent().apply {
                putExtra(EXTRA_STUDENT,student)
            }
            setResult(RESULT_OK,data)
            finish()
        }

//----- qui metto il flag nella schermata studente ------//
        inComodato.isChecked = student.inaffitto
        inComodato.setOnCheckedChangeListener { _, isChecked ->
            student.inaffitto = inComodato.isChecked
            val data = Intent().apply{
                putExtra(EXTRA_STUDENT, student)
            }
            setResult(RESULT_OK,data)

        }
    }
}