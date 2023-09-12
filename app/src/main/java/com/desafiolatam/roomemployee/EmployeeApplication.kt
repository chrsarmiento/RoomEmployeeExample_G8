package com.desafiolatam.roomemployee

import android.app.Application
import androidx.room.Room
import com.desafiolatam.roomemployee.data.EmployeeDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EmployeeApplication : Application()