package com.example.handsup

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.handsup.databinding.FragmentCategoryDescriptionBinding

private lateinit var category: Category
class CategoryDescriptionFragment : Fragment() {
    private lateinit var binding: FragmentCategoryDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentCategoryDescriptionBinding.inflate(inflater, container, false)
        binding.description.text = category.description
        binding.backButton.setOnClickListener(::onBackPressed)
        binding.nextButton.setOnClickListener(::startGame)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    companion object {

        @JvmStatic
        fun newInstance(arg1: Category): CategoryDescriptionFragment {
            val fragment = CategoryDescriptionFragment()
            category = arg1
            return fragment
        }
    }

    private fun onBackPressed(view:View){

        activity?.onBackPressed()

    }

    private fun startGame(view:View){
        Log.d("TAGTAG", "LOGLOG")

        parentFragmentManager
            .beginTransaction()
            .replace(R.id.gameFragments, MainGameFragment.newInstance(category)).commit()

    }
}
