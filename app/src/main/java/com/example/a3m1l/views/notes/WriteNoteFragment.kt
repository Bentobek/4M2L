package com.example.a3m1l.views.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a3m1l.PreferncesHelper
import com.example.a3m1l.extension.setBackStackData
import com.example.a3m1l.databinding.FragmentWriteNoteBinding


class WriteNoteFragment : Fragment() {
    private lateinit var binding: FragmentWriteNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWriteNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setlistener()
    }

    private fun setlistener() {
        binding.apply {
            btnSave.setOnClickListener{
                val title = binding.etTitle.text.toString()
                setBackStackData("key",title,true)
                val pref = PreferncesHelper()
                pref.unit(requireContext())
                pref.text = title

            }
        }
    }
}
