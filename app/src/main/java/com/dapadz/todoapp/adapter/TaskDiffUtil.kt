package com.dapadz.todoapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.dapadz.todoapp.data.models.Task

class TaskDiffUtil(
    private val oldList : List<Task>,
    private val newList : List<Task>,
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition : Int, newItemPosition : Int) : Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition : Int, newItemPosition : Int) : Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return when {
            oldItem.id != newItem.id -> false
            oldItem.title != newItem.title -> false
            oldItem.createDate != newItem.createDate -> false
            else -> true
        }

    }

    override fun getOldListSize() : Int = oldList.size
    override fun getNewListSize() : Int = newList.size
}