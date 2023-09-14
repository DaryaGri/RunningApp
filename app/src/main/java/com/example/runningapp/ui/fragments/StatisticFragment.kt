package com.example.runningapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.runningapp.R
import com.example.runningapp.other.TrackingUtility
import com.example.runningapp.ui.viewModels.MainViewModel
import com.example.runningapp.ui.viewModels.StatisticViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlin.math.round

@AndroidEntryPoint
class StatisticFragment : Fragment(R.layout.fragment_statistics) {

    private val viewModel: StatisticViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.totalTimeRun.observe(viewLifecycleOwner, Observer {
            it?.let {
                val totalTimeRun = TrackingUtility.getFormattedStopWatchTime(it)
                tvTotalTime.text = totalTimeRun
            }
        })
        viewModel.totalDistance.observe(viewLifecycleOwner, Observer {
            it?.let {
                val km = it / 1000f
                val totalDistance = round(km * 10f) / 10f
                val totalDistanceString = "${totalDistance}km"
                tvTotalDistance.text = totalDistanceString
            }
        })

        viewModel.totalAvgSpeed.observe(viewLifecycleOwner, Observer {
            it?.let {
                val avgSpeed = round(it / 10f) / 10f
                val avgSpeedString = "${avgSpeed}km/h"
                tvAverageSpeed.text = avgSpeedString
            }
        })

        viewModel.totalCaloriesBurned.observe(viewLifecycleOwner, Observer {
            it?.let {
                val totalCalories = "${it}Kcal"
                tvTotalCalories.text = totalCalories
            }
        })
    }
}