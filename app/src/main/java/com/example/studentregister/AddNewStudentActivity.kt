package com.example.studentregister

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR.*
import com.example.studentregister.databinding.ActivityAddNewStudentBinding
import kotlinx.android.synthetic.main.activity_add_new_student.*

class AddNewStudentActivity : AppCompatActivity() {

    var activityAddNewStudentBinding : ActivityAddNewStudentBinding? = null
    var student : Student? = null
    var addNewStudentActivityClickHandlers : AddNewStudentActivityClickHandlers? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_student)

        student = Student()
        activityAddNewStudentBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_student)
        activityAddNewStudentBinding!!.student = student

        addNewStudentActivityClickHandlers = AddNewStudentActivityClickHandlers(this)
        activityAddNewStudentBinding!!.clickHandler = addNewStudentActivityClickHandlers

        /*btnSubmit.setOnClickListener {
            if (TextUtils.isEmpty(et_name!!.text)) {
                Toast.makeText(getApplicationContext(),"Name field cannot be empty", Toast.LENGTH_LONG).show()
            } else {
                val name = et_name!!.text.toString()
                val email = et_email!!.text.toString()
                val country = et_country!!.text.toString()

                val intent = Intent()
                intent.putExtra("NAME", name)
                intent.putExtra("EMAIL", email)
                intent.putExtra("COUNTRY", country)
                setResult(RESULT_OK, intent)
                finish()
            }
        }*/
    }

    inner class AddNewStudentActivityClickHandlers (val context: Context) {

        fun onSubmitClicked(view: View) {

           if (Student().name == null) {
               Toast.makeText(getApplicationContext(),"Name field cannot be empty", Toast.LENGTH_LONG).show()
           }else{
               val intent = Intent()
               intent.putExtra("NAME", name)
               intent.putExtra("EMAIL", email)
               intent.putExtra("COUNTRY", country)
               setResult(RESULT_OK, intent)
               finish()
           }

            /*val intent = Intent(this, AddNewStudentActivity::class.java)
            startActivity(intent)*/
        }
    }
}