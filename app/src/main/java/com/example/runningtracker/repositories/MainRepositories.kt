package com.example.runningtracker.repositories

import com.example.runningtracker.db.Run
import com.example.runningtracker.db.RunDao
import javax.inject.Inject

class MainRepositories @Inject constructor(
   val  runDao: RunDao
) {
   suspend fun insertRun(run: Run) =  runDao.insertRun(run)
   suspend fun deleteRun(run: Run) = runDao.deleteRun(run)
   fun getAllRunsSortedByDate()= runDao.getAllRunsSortedByDate()
   fun getAllRunsSortedByAvgSpeed()= runDao.getAllRunsSortedByAvgSpeed()
   fun getAllRunsSortedByCaloriesBurned()= runDao.getAllRunsSortedByCaloriesBurned()
   fun getAllRunsSortedByDistanceInMeters()= runDao.getAllRunsSortedByDistanceInMeters()
   fun getAllRunsSortedByTimeInMillis()= runDao.getAllRunsSortedByTimeInMillis()

   fun getTotalAvgSpeed() = runDao.getTotalAvgSpeedInKMH()
   fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()
   fun getTotalDistanceInMeters() = runDao.getTotalDistanceInMeters()
   fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()
}