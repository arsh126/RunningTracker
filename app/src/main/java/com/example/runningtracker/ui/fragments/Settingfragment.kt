package com.example.runningtracker.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.runningtracker.R
import com.example.runningtracker.other.Constants.KEY_FRIST_TIME_TOGGLE
import com.example.runningtracker.other.Constants.KEY_NAME
import com.example.runningtracker.other.Constants.KEY_WEIGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_setting.*

@AndroidEntryPoint
class Settingfragment: Fragment(R.layout.fragment_setting){
    lateinit var sharedPreferences: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
loadFieldsFromSharedPref()
        btnApplyChanges.setOnClickListener {
            val success = applyChangesToSharedPref()
            if(success){
                Snackbar.make(view, "Saved Changes", Snackbar.LENGTH_LONG).show()
            }else{
                Snackbar.make(view, "Please Fill Out All The Fields", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    fun loadFieldsFromSharedPref(){
        val name = sharedPreferences.getString(KEY_NAME, "") ?: ""
        val weight = sharedPreferences.getFloat(KEY_WEIGHT, 47f)
        etNameSetting.setText(name)
        etWeightSetting.setText(weight.toString())

    }

    fun applyChangesToSharedPref(): Boolean {
        val nameText = etNameSetting.text.toString()
        val weightText = etWeightSetting.text.toString()

        if(nameText.isEmpty() && weightText.isEmpty()){
            return false
        }
        sharedPreferences.edit()
            .putString( KEY_NAME,nameText)
            .putFloat(KEY_WEIGHT, weightText.toFloat())
            .apply()
        val toolbarTextSetting = "let's go $nameText !"
        requireActivity().tvToolbarTitle.text= toolbarTextSetting
        return true
    }
}