package com.example.handsup

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.handsup.databinding.FragmentRulesBinding


class RulesFragment : Fragment() {
    lateinit var binding: FragmentRulesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRulesBinding.inflate(inflater, container, false)
        binding.closeButton.setOnClickListener(::startGame)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = RulesFragment()
    }

    private fun startGame(view: View){

        val intent = Intent(activity, BeforeGame::class.java)
        startActivity(intent)

    }
    override fun onResume() {
        super.onResume()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}
