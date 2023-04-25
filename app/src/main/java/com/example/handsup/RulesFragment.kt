package com.example.handsup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.handsup.databinding.FragmentRulesBinding
import com.example.handsup.databinding.FragmentStartPageBinding


class RulesFragment : Fragment() {
    lateinit var binding: FragmentRulesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRulesBinding.inflate(inflater, container, false)
        binding.closeButton.setOnClickListener(::goToSelectCategoryFragment)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = RulesFragment()
    }

    private fun goToSelectCategoryFragment(view: View){

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment, SelectCategoryFragment.newInstance())
            .commit()

    }
}
