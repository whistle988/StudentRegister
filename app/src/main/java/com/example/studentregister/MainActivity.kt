package com.example.studentregister

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.studentregister.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var studentAppDatabase: StudentAppDataBase? = null
    private var students: ArrayList<Student>? = null
    private var studentDataAdapter: StudentDataAdapter? = null
    val NEW_STUDENT_ACTIVITY_REQUEST_CODE = 1

    private var activityMainBinding : ActivityMainBinding? = null
    private var handlers: MainActivityClickHandlers? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //var activityMainBinding = this.activityMainBinding

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        handlers = MainActivityClickHandlers(this)
        activityMainBinding!!.clickHandler = handlers

        rvStudents.layoutManager = rvStudents.layoutManager
        rvStudents.setHasFixedSize(true)

        studentDataAdapter = StudentDataAdapter()
        rvStudents.adapter = studentDataAdapter

        studentAppDatabase = Room.databaseBuilder(applicationContext, StudentAppDataBase::class.java, "StudentDB").build()

        loadData()

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                       override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(@NonNull viewHolder: RecyclerView.ViewHolder, i: Int) {
                val studentToDelete = students!![viewHolder.getAdapterPosition()]
                deleteStudent(studentToDelete)
            }
        }).attachToRecyclerView(rvStudents)


        /*val fab: FloatingActionButton = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, AddNewStudentActivity::class.java)
            startActivityForResult(intent, NEW_STUDENT_ACTIVITY_REQUEST_CODE)
        })*/
    }

    inner class MainActivityClickHandlers (val context: Context) {

        fun onFABClicked(view: View) {
            val intent = Intent(this@MainActivity, AddNewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_STUDENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val name = data!!.getStringExtra("NAME")
            val email = data.getStringExtra("EMAIL")
            val country = data.getStringExtra("COUNTRY")
            val simpleDateFormat = SimpleDateFormat("EEE, d MMM yyyy")
            val currentDate = simpleDateFormat.format(Date())
            val student = Student()
            student.name = name
            student.email = email
            student.country = country
            student.registeredTime = currentDate
            addNewStudent(student)
        }
    }

    fun loadData() {
        GetAllStudentsAsyncTask().execute()
    }

    private inner class GetAllStudentsAsyncTask : AsyncTask<Void?, Void?, Void?>() {

        override fun doInBackground(vararg params: Void?): Void? {
            students = studentAppDatabase!!.getStudentDao()!!.getAllStudents //as ArrayList<Student?>
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            studentDataAdapter!!.setStudents(students)
        }
    }


    private fun deleteStudent(student: Student) {
        DeleteStudentAsyncTask().execute(student)
    }

    private inner class DeleteStudentAsyncTask : AsyncTask<Student?, Void?, Void?>() {
        override fun doInBackground(vararg params: Student?): Void? {
            studentAppDatabase!!.getStudentDao()!!.delete(params[0])
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            loadData()
        }
    }


    private fun addNewStudent(student: Student) {
        AddNewStudentAsyncTask().execute(student)
    }

    private inner class AddNewStudentAsyncTask : AsyncTask<Student?, Void?, Void?>() {

        override fun doInBackground(vararg params: Student?): Void? {
            studentAppDatabase!!.getStudentDao()!!.insert(params[0])
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            loadData()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
