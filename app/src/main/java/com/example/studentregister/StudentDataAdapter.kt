package com.example.studentregister

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.studentregister.databinding.StudentListItemBinding
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.*

class StudentDataAdapter : RecyclerView.Adapter<StudentDataAdapter.StudentViewHolder?>() {

    private var students: ArrayList<Student>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): StudentViewHolder {

        val studentListItemBinding: StudentListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.student_list_item, viewGroup, false)

        return StudentViewHolder(studentListItemBinding)
    }

    override fun onBindViewHolder(studentViewHolder: StudentViewHolder, i: Int) {
        val currentStudent = students!![i]

        studentViewHolder.studentListItemBinding!!.student = currentStudent

        /*studentViewHolder.name.text = currentStudent.name
        studentViewHolder.email.text = currentStudent.email
        studentViewHolder.country.text = currentStudent.country
        studentViewHolder.date.text = currentStudent.registeredTime*/
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

    inner class StudentViewHolder(@NonNull studentListItemBinding : StudentListItemBinding)
        : RecyclerView.ViewHolder(studentListItemBinding.root) {

        var studentListItemBinding : StudentListItemBinding? = null

        init {
            this.studentListItemBinding = studentListItemBinding
        }

        /*val name: TextView = itemView.tvName
        val email: TextView = itemView.tvEmail
        val country: TextView = itemView.tvCountry
        val date: TextView = itemView.tvTime*/


    }
}
