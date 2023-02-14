package com.dapadz.todoapp.ui.fragments.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.dapadz.todoapp.adapter.TaskAdapter
import com.dapadz.todoapp.adapter.TaskAdapterClickListener
import com.dapadz.todoapp.data.models.Task
import com.dapadz.todoapp.databinding.FragmentTasksBinding
import com.dapadz.todoapp.ui.decorators.TaskBackgroundItemDecorator
import com.dapadz.todoapp.ui.fragments.task_create.TaskCreateFragment
import com.dapadz.todoapp.utils.NavigationUtils.Companion.getNavigationResultLiveData
import com.google.android.material.transition.MaterialSharedAxis
import com.google.android.material.transition.MaterialSharedAxis.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TasksFragment : Fragment(), TaskAdapterClickListener {

    private var _binding : FragmentTasksBinding? = null
    private val binding get() = _binding !!

    private lateinit var taskAdapter : TaskAdapter
    private val viewModel : TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialSharedAxis(Y, true)
        reenterTransition = MaterialSharedAxis(Y, false)
    }

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?,
    ) : View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        initRecyclerView()
        observeViewModel()
        observeFragmentResult()
        return binding.root
    }

    private fun initRecyclerView() {
        taskAdapter = TaskAdapter(this)
        with(binding) {
            recyclerView.adapter = taskAdapter
            recyclerView.addItemDecoration(TaskBackgroundItemDecorator())
        }
    }

    private fun observeViewModel() {
        lifecycle.coroutineScope.launch {
            viewModel.getAll().collect() {
                taskAdapter.setData(it)
            }
        }
    }

    private fun observeFragmentResult() {
        getNavigationResultLiveData<Task>(TaskCreateFragment.REQUEST_KEY)?.observe(viewLifecycleOwner) {
            createTask(it)
        }
    }

    private fun createTask(task : Task) = viewModel.createTask(task)
    override fun onRemoveClick(task : Task) = viewModel.removeTask(task)

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}