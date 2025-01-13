package com.example.a3m1l.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a3m1l.PreferncesHelper
import com.example.a3m1l.adapters.NotesAdapter
import com.example.a3m1l.databinding.FragmentNotesBinding
import com.example.a3m1l.extension.getBackStackData
import com.example.a3m1l.models.Notes

class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding
    private lateinit var adapter: NotesAdapter
    private val notes: ArrayList<Notes> = arrayListOf(
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
        initialize()
        getData()
    }


    private fun initialize() {
        adapter = NotesAdapter(notes)
        binding.rvNotes.adapter = adapter
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToWriteNoteFragment())
        }
        val pref = PreferncesHelper()
        pref.unit(requireContext())
        pref.text?.let { notes.add(Notes(it)) }
        }
    private fun getData(){
        getBackStackData<String>("key"){
//            val note = Notes(it)
//            notes.add(note)
            adapter.submitList(notes)
        }
    }
    }
