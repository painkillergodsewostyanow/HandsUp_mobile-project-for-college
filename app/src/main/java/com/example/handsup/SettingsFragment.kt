package com.example.handsup

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
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
        backToSelectCategoryFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {

                    backToSelectCategoryFragment()

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    private fun backToSelectCategoryFragment(){

        parentFragmentManager.beginTransaction()
            .replace(R.id.gamePlaceholder, SelectCategoryFragment.newInstance())
            .commit()

    }
}