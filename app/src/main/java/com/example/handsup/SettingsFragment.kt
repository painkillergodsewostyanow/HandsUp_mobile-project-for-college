package com.example.handsup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.window?.navigationBarColor = resources.getColor(R.color.white)
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}