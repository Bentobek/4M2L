package com.example.a3m1l.views.onBoard

import android.R
import android.R.drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a3m1l.databinding.FragmentOnBoardBinding

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
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
                binding.lottiAnimationView.setAnimation(com.example.a3m1l.R.raw.first,)
                binding.onTxt.text = "Удобство"
                binding.onTxt2.text = "Создавайте заметки в два клика! " +
                                      "Записывайте мысли, идеи и " +
                                        "важные задачи мгновенно."
            }
            1 -> {
                binding.skip.setOnClickListener{
                    findNavController().navigate(PagerItemFragmentDirections.actionPagerItemFragmentToNotesFragment())
                }
                binding.lottiAnimationView.setAnimation(com.example.a3m1l.R.raw.second,)
                binding.onTxt.text = "Организация"
                binding.onTxt2.text = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
            }
            2 -> {
                binding.skip.visibility = View.GONE
                binding.lottiAnimationView.setAnimation(com.example.a3m1l.R.raw.third,)
                binding.onTxt.text = "Синхронизация"
                binding.onTxt2.text = "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
            }
        }
    }

    companion object{
        const val ARG_ONBOARD_POSITION = "object"
    }
}