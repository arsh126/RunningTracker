package com.example.runningtracker.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.runningtracker.R
import com.example.runningtracker.other.CustomMarkerView
import com.example.runningtracker.other.TrackingUtility
import com.example.runningtracker.ui.viewmodels.StatisticsViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_statistics.*
import java.util.*
import kotlin.math.round

@AndroidEntryPoint
class StatisticsFragment: Fragment(R.layout.fragment_statistics){
    private val viewModel: StatisticsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObserver()
        setupBarChart()
    }

    private fun setupBarChart(){
        barChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            setDrawLabels(false)
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
        }

        barChart.axisLeft.apply {
            setDrawGridLines(false)
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
        }
        barChart.axisRight.apply {
            setDrawGridLines(false)
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
        }
        barChart.apply {
            description.text = "Avg Speed Over Time"
            legend.isEnabled = false
        }

    }

    private fun subscribeToObserver(){

        viewModel.totalTimeRun.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                val totalTimeRun = TrackingUtility.getFormattedStopWatchTime(it)
                tvTotalTime.text = totalTimeRun
            }
        })

        viewModel.totalDistance.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                val km = it / 1000f
               val totalDistance = round(km / 10f) / 10f
                val totalDistanceString = "${totalDistance}km"
                tvTotalDistance.text = totalDistanceString
            }
        })

        viewModel.totalAvgSpeed.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
               val avgSpeed = round(it / 10f) / 10f
                val avgSpeedString = "${avgSpeed}Km/H"
                tvAverageSpeed.text = avgSpeedString
            }
        })

        viewModel.totalCaloriesBurned.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                val totalCalories = "${it}Kcal"
                tvTotalCalories.text = totalCalories
            }
        })

        viewModel.runsSortedByDate.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                val allAvgSpeed = it.indices.map { i -> BarEntry(i.toFloat(), it[i].avgSpeedInKMH) }
                val barDataSet = BarDataSet(allAvgSpeed, "avg Speed Over Time").apply {
                    valueTextColor= Color.WHITE
                    color = ContextCompat.getColor(requireContext(),R.color.colorAccent)
                }
                barChart.data = BarData(barDataSet)
                barChart.marker = CustomMarkerView(it.reversed(), requireContext(), R.layout.marker_view,)
                barChart.invalidate()
            }
        })
    }


}