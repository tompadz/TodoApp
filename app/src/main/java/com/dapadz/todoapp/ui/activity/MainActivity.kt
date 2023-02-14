package com.dapadz.todoapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.dapadz.todoapp.R
import com.dapadz.todoapp.databinding.ActivityMainBinding
import com.dapadz.todoapp.ui.fragments.tasks.TasksFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavContainer()
        initFAB()
        addNavigationDirectionListener()
    }

    private fun initNavContainer() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun initFAB() {
        binding.fabAddTask.setOnClickListener {
            val action = TasksFragmentDirections.actionTasksFragmentToTaskCreateFragment()
            navController.navigate(action)
        }
    }

    private fun addNavigationDirectionListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            with(binding.fabAddTask) {
                if (destination.id == R.id.tasksFragment)  show() else hide()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}