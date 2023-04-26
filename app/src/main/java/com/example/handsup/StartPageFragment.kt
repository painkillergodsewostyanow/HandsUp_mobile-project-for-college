package com.example.handsup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.handsup.databinding.ActivityMainBinding
import com.example.handsup.databinding.FragmentStartPageBinding

class StartPageFragment : Fragment() {
    lateinit var binding: FragmentStartPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentStartPageBinding.inflate(inflater, container, false)
        binding.playButton.setOnClickListener(::goToGame)
        return binding.root



    }
    companion object {
        @JvmStatic
        fun newInstance() = StartPageFragment()
    }

    private fun goToGame(view:View) {

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment, RulesFragment.newInstance())
            .commit()


    }

}
