package com.example.studentregister

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Student::class], version = 1)
abstract class StudentAppDataBase : RoomDatabase() {

    abstract fun getStudentDao(): StudentDao?

}