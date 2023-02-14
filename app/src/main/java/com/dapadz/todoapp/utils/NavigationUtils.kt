package com.dapadz.todoapp.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController

class NavigationUtils {
    companion object {
        fun <T> Fragment.getNavigationResultLiveData(key: String = "result") : MutableLiveData<T>? {
            viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_DESTROY) {
                    findNavController().previousBackStackEntry?.savedStateHandle?.remove<T>(key)
                }
            })
            return findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData(key)
        }

        fun <T> Fragment.setNavigationResult(result: T, key: String = "result") {
            findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
        }
    }
}