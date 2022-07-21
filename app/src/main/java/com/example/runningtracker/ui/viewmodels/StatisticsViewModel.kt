package com.example.runningtracker.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.runningtracker.repositories.MainRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    mainRepositories: MainRepositories
) : ViewModel() {

    val totalTimeRun = mainRepositories.getTotalTimeInMillis()
    val totalDistance= mainRepositories.getTotalDistanceInMeters()
    val totalCaloriesBurned = mainRepositories.getTotalCaloriesBurned()
    val totalAvgSpeed = mainRepositories.getTotalAvgSpeed()

    val runsSortedByDate = mainRepositories.getAllRunsSortedByDate()

}