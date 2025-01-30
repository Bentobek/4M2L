package com.bento.a3m1l.views.onBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bento.a3m1l.R.*
import com.bento.a3m1l.databinding.FragmentOnBoardBinding

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentOnBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        val onBoardPosition = requireArguments().getInt(ARG_ONBOARD_POSITION)
        when(onBoardPosition){
            0 -> {
                binding.skip.setOnClickListener{
                    findNavController().navigate(PagerItemFragmentDirections.actionPagerItemFragmentToNotesFragment())
                }
                binding.lottiAnimationView.setAnimation(raw.first)
                binding.onTxt.text = getString(string.on_board_title)
                binding.onTxt2.text = getString(string.on_board_description)
            }
            1 -> {
                binding.skip.setOnClickListener{
                    findNavController().navigate(PagerItemFragmentDirections.actionPagerItemFragmentToNotesFragment())
                }
                binding.lottiAnimationView.setAnimation(raw.second)
                binding.onTxt.text = getString(string.on_board_title2)
                binding.onTxt2.text =getString(string.on_board_description2)
            }
            2 -> {
                binding.skip.visibility = View.GONE
                binding.lottiAnimationView.setAnimation(raw.third)
                binding.onTxt.text = getString(string.on_board_title3)
                binding.onTxt2.text = getString(string.on_board_description3)
            }
        }
    }

    companion object{
        const val ARG_ONBOARD_POSITION = "object"
    }
}