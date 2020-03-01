package com.example.studentregister

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
class Student : BaseObservable {
    /*After further investigation it turns out that Room Entities should not extend the BaseObservable class.
    It contains fields that can't be marked with @Ignore and break the code generation.
    Room works well with inheritance. The annotations are processed as expected and the
    DB operations behave normally. You can extend from both an Entity and a POJO.*/

    @get: Bindable
    var studentId: Int = 0
    set(value) {
        field = value
        notifyPropertyChanged(BR.studentId)
    }

    @get: Bindable
    var name: String = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.name)
    }

    @get: Bindable
    var email: String = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.email)
    }

    @get: Bindable
    var country: String = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.country)
    }

    @get: Bindable
    var registeredTime: String = ""
    set(value) {
        field = value
        notifyPropertyChanged(BR.registeredTime)
    }


    @Ignore
    constructor() {
    }

    constructor(
        //@PrimaryKey(autoGenerate = true)

        studentId: Int,
        name: String,
        email: String,
        country: String,
        registeredTime: String
    ) {
        this.studentId = studentId
        this.name = name
        this.email = email
        this.country = country
        this.registeredTime = registeredTime
    }

}
