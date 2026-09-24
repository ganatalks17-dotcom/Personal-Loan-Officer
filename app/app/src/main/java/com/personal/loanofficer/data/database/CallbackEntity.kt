package com.personal.loanofficer.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "callbacks")
data class CallbackEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val customerName: String,

    val mobileNumber: String,

    val callbackDate: String,

    val callbackTime: String,

    val status: String = "Pending",

    val createdAt: Long = System.currentTimeMillis()
)
