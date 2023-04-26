package com.example.handsup

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.handsup.databinding.FragmentSelectCategoryBinding

@Suppress("DEPRECATION")
class SelectCategoryFragment : Fragment() {
    private lateinit var binding: FragmentSelectCategoryBinding
    lateinit var category:Category

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSelectCategoryBinding.inflate(inflater, container, false)
        buttonsListenerInit()
        activity?.window?.navigationBarColor = resources.getColor(R.color.white)
        binding.settingsButton.setOnClickListener(::goToSettings)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SelectCategoryFragment()
    }

    private fun goToSettings(view: View){

        parentFragmentManager.beginTransaction()
            .replace(R.id.gamePlaceholder, SettingsFragment.newInstance())
            .commit()

    }
    private fun buttonsListenerInit(){
        binding.settingsButton.setOnClickListener(::goToSettings)
        binding.artCategory.setOnClickListener(::choseCategory)
        binding.aroundTheWorldCategory.setOnClickListener(::choseCategory)
        binding.famousPersonCategory.setOnClickListener(::choseCategory)
        binding.natureCategory.setOnClickListener(::choseCategory)
        binding.cinemaAndAnimated.setOnClickListener(::choseCategory)
        binding.different.setOnClickListener(::choseCategory)
    }

    private fun choseCategory(view: View){
        when(view.id){
            binding.natureCategory.id ->  category = nature
            binding.artCategory.id -> category = atr
            binding.aroundTheWorldCategory.id -> category = aroundTheWorld
            binding.famousPersonCategory.id -> category = famousPerson
            binding.cinemaAndAnimated.id -> category = cinemaAndAnimations
            binding.different.id -> category = different
        }

        val intent = Intent(activity, Game::class.java)
        intent.putExtra(Game.CATEGORY, category)
        startActivity(intent)

    }

}