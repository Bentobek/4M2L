package com.bento.a3m1l.views.onBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bento.a3m1l.PreferncesHelper
import com.bento.a3m1l.R
import com.bento.a3m1l.adapters.onBoardAdapter
import com.bento.a3m1l.databinding.FragmentPagerItemBinding

class PagerItemFragment : Fragment() {

    private lateinit var binding: FragmentPagerItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagerItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }

    private fun setupListener() {
        binding.viewPager.adapter = onBoardAdapter(this@PagerItemFragment)
    }
    private fun initialize() = with(binding.viewPager) {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.dotsIndicator.attachTo(binding.viewPager)
                if (position == 2) {
                    binding.viewPagerBtn.visibility = View.VISIBLE
                    binding.viewPagerBtn.text = getString(R.string.next)
                    binding.viewPagerBtn.setOnClickListener{
                        val pref = PreferncesHelper()
                        pref.unit(requireContext())
                        pref.isOnBoardShown = true
                        findNavController().navigate(PagerItemFragmentDirections.Companion.actionPagerItemFragmentToSignInFragment())
                    }
                } else  {
                    binding.viewPagerBtn.visibility = View.GONE
                    }
            }
        })
        binding.viewPagerBtn.setOnClickListener{
            if (currentItem < 3) {
                setCurrentItem(currentItem + 1, true)
            }
        }
    }
}