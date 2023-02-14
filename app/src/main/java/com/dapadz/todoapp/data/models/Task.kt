package com.dapadz.todoapp.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@kotlinx.parcelize.Parcelize
@Entity(tableName = "table_task")
data class Task(
    @PrimaryKey(autoGenerate = true) val id : Long = 0,
    val title : String,
    val createDate : Long = System.currentTimeMillis()
) : Parcelable {
    fun getCreateDate(pattern:String = "dd MMM") : String {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(Date(createDate))
    }
}
