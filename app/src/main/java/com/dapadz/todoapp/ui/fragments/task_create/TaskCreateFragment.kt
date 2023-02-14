package com.dapadz.todoapp.ui.fragments.task_create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import com.dapadz.todoapp.data.models.Task
import com.dapadz.todoapp.databinding.FragmentTaskCreateBinding
import com.dapadz.todoapp.utils.AndroidUtils
import com.dapadz.todoapp.utils.NavigationUtils.Companion.setNavigationResult
import com.google.android.material.transition.MaterialSharedAxis
import com.google.android.material.transition.MaterialSharedAxis.*


class TaskCreateFragment : Fragment() {

    private var _binding : FragmentTaskCreateBinding? = null
    private val binding get() = _binding !!

    companion object {
        const val REQUEST_KEY = "TASK_RESULT"
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(Y, true)
        returnTransition = MaterialSharedAxis(Y, false)
    }

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?,
    ) : View {
        _binding = FragmentTaskCreateBinding.inflate(inflater, container, false)
        initTextField()
        return binding.root
    }

    private fun initTextField() {
        with(binding.textField) {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    checkTitleField()
                    true
                } else false
            }
        }
    }

    private fun checkTitleField() {
        with(binding) {
            if (!textField.editableText.isNullOrBlank()) {
                setNavigationResult(createTask(), REQUEST_KEY)
                findNavController().popBackStack()
            }
        }
    }

    private fun createTask(): Task {
        return Task(
            title = binding.textField.editableText.toString()
        )
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidUtils().showKeyboard(binding.textField)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}