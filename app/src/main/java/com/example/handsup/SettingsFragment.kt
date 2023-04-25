package com.example.handsup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.handsup.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        activity?.window?.navigationBarColor = resources.getColor(R.color.white)
        binding.menuButton.setOnClickListener(::saveSettings)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }

    private fun saveSettings(view: View){

        when(binding.playTimeRadioGroup.checkedRadioButtonId){
            binding.sixtySecond.id -> playTime = playTimeSixtySecond
            binding.ninetySecond.id -> playTime = playTimeNinetySecond
            binding.oneHundredTwelveSecond.id -> playTime = playTimeOneHundredTwelveSecond
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment, SelectCategoryFragment.newInstance())
            .commit()

    }
}