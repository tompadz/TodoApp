package com.dapadz.todoapp.app

import android.app.Application
import com.dapadz.todoapp.utils.AndroidUtils
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class TodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidUtils.checkDisplaySize(applicationContext)
    }
}