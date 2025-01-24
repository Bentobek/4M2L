package com.example.a3m1l.views.notes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.util.query
import com.example.a3m1l.App
import com.example.a3m1l.adapters.NotesAdapter
import com.example.a3m1l.data.model.NoteEntity
import com.example.a3m1l.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding
    private lateinit var adapter: NotesAdapter
    private var isLinear = true
    private var noteslist:List<NoteEntity> = listOf()



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
        adapter = NotesAdapter()
        binding.rvNotes.adapter = adapter
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(NotesFragmentDirections.Companion.actionNotesFragmentToWriteNoteFragment())
        }
        binding.shape.setOnClickListener{
            adapter.apply {
                if (isLinear){
                    binding.shape.setImageResource(com.example.a3m1l.R.drawable.shape2)
                    binding.rvNotes.layoutManager = androidx.recyclerview.widget.GridLayoutManager(requireContext(), 2)
                    isLinear = false
                }else{
                    binding.shape.setImageResource(com.example.a3m1l.R.drawable.shape)
                    binding.rvNotes.layoutManager =  androidx.recyclerview.widget.LinearLayoutManager(requireContext())
                    isLinear = true
                }
            }
        }
        binding.search.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterNotes(s.toString())
             }

            override fun afterTextChanged(s: Editable?) {

                }
            })
        }

    private fun getData(){
      App.appDataBase?.noteDao()?.getAll()?.observe(viewLifecycleOwner){ model->
            Log.e("ololo", "getData: $model")
            noteslist = model
            adapter.submitList(noteslist)
            adapter.notifyDataSetChanged()
        }
    }
    private fun filterNotes(query: String) {
        val filtrelist = noteslist.filter { note ->
            note.title.contains(query, ignoreCase = true) || note.title.contains(query, ignoreCase = true)
            }
        adapter.submitList(filtrelist)
    }
    }
