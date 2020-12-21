package com.example.esameandroid_letiziagiammorcaro


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esameandroid_letiziagimmorcaro.R


private const val TAG ="StudentListFragment"
const val REQUEST_CODE_STUDENT = 1
class StudentListFragment: Fragment() {

    private lateinit var studentRecyclerView: RecyclerView
    private var adapter: StudentAdapter?= null
    private val studentListViewModel: StudentListViewModel by lazy {
        ViewModelProvider(this).get(StudentListViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
        studentRecyclerView = view.findViewById(R.id.student_recycler_view)
        studentRecyclerView.layoutManager = LinearLayoutManager(context)
        studentRecyclerView.adapter = StudentAdapter(studentListViewModel.studentsLists)

        return view
    }

    private fun updateUI() {
        val students = studentListViewModel.studentsLists
        adapter = StudentAdapter(students)
        studentRecyclerView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_STUDENT ){
            val student = data?.getSerializableExtra(EXTRA_STUDENT) as Student
            for(item in studentListViewModel.studentsLists){
                if (item.name == student.name){
                    item.inaffitto = student.inaffitto
                    break
                }
            }
        }
        updateUI()
    }

    private inner class StudentHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var student: Student
        private val nameView: TextView = itemView.findViewById(R.id.name)
        private val macBookView: TextView = itemView.findViewById(R.id.macBook)
        private val laptopImageView: ImageView = itemView.findViewById(R.id.inComodato)

        init {
            Log.i(StudentHolder::class.java.simpleName, "Call Init")
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            context?.let { context ->
                val intent=StudentActivity.newIntent(context, student)
                startActivityForResult(intent, REQUEST_CODE_STUDENT)
            }
        }

        fun bind(student: Student) {
            this.student = student
            nameView.text = this.student.name
            macBookView.text = this.student.macBook
            laptopImageView.visibility = if(student.inaffitto){
                View.VISIBLE
            }else{
                View.GONE
            }
        }
    }
    private inner class StudentAdapter(var students: List<Student>) : RecyclerView.Adapter<StudentHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
            val view = layoutInflater.inflate(R.layout.list_item_student, parent, false)
            return StudentHolder(view)
        }
        override fun getItemCount() = students.size

        override fun onBindViewHolder(holder: StudentHolder, position: Int) {
            val student = students[position]
            holder.bind(student)
        }
    }
}