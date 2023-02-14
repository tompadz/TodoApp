package com.dapadz.todoapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dapadz.todoapp.data.models.Task
import com.dapadz.todoapp.databinding.ItemTaskBinding

class TaskAdapter(
    private val listener : TaskAdapterClickListener,
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var tasks = emptyList<Task>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data : List<Task>) {
        val diffUtil = TaskDiffUtil(tasks, data)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        tasks = data
        diffResult.dispatchUpdatesTo(this)
    }

    inner class TaskViewHolder(private val binding : ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task : Task) {
            with(binding) {
                textDate.text = task.getCreateDate()
                textTitle.text = task.title
                root.setOnClickListener {
                    listener.onRemoveClick(task)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder : TaskViewHolder, position : Int) = holder.bind(tasks[position])
    override fun getItemCount() : Int = tasks.size
}

