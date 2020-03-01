package com.example.studentregister

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.*

class StudentDataAdapter : RecyclerView.Adapter<StudentDataAdapter.StudentViewHolder?>() {

    private var students: ArrayList<Student>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.student_list_item, viewGroup, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(studentViewHolder: StudentViewHolder, i: Int) {
        val currentStudent = students!![i]
        studentViewHolder.name.text = currentStudent.name
        studentViewHolder.email.text = currentStudent.email
        studentViewHolder.country.text = currentStudent.country
        studentViewHolder.date.text = currentStudent.registeredTime
    }

    override fun getItemCount(): Int {
        if (students != null) {
            return students!!.size
        }
        return 0
    }

    fun setStudents(students: ArrayList<Student>?) {
        this.students = students
        notifyDataSetChanged()
    }

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.tvName
        val email: TextView = itemView.tvEmail
        val country: TextView = itemView.tvCountry
        val date: TextView = itemView.tvTime


    }
}
