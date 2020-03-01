package com.example.studentregister

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {
    @Insert
    fun insert(student: Student?)

    @get:Query("SELECT * FROM student_table")
    val getAllStudents: ArrayList<Student>

    @Delete
    fun delete(student: Student?)
}