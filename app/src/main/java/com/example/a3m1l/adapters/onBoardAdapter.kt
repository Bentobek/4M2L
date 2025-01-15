package com.example.a3m1l.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.a3m1l.views.onBoard.OnBoardFragment

class onBoardAdapter(fragment: Fragment): FragmentStateAdapter(fragment){
    override fun createFragment(position: Int) = OnBoardFragment().apply {
        arguments = Bundle().apply {
            putInt(OnBoardFragment.ARG_ONBOARD_POSITION , position)
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

}