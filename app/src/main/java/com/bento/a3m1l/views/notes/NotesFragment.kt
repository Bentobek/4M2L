package com.bento.a3m1l.views.notes

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bento.a3m1l.App
import com.bento.a3m1l.PreferncesHelper
import com.bento.a3m1l.R.*
import com.bento.a3m1l.adapters.NotesAdapter
import com.bento.a3m1l.data.model.NoteEntity
import com.bento.a3m1l.databinding.FragmentNotesBinding
import com.bento.a3m1l.extension.onItemClick
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class NotesFragment : Fragment(), onItemClick {
    private lateinit var binding: FragmentNotesBinding
    private lateinit var adapter: NotesAdapter
    private var isLinear = true
    private var noteslist: ArrayList<NoteEntity> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
        initialize()
        getData()
    }

    private fun initialize() {
        adapter = NotesAdapter(this)
        binding.rvNotes.adapter = adapter
        binding.btnAdd.setOnClickListener {

        findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToWriteNoteFragment())

        }

        binding.shape.setOnClickListener{
            throw RuntimeException("Test Crash 1")
            adapter.apply {
                if (isLinear){
                    binding.shape.setImageResource(drawable.shape2)
                    binding.rvNotes.layoutManager = androidx.recyclerview.widget.GridLayoutManager(requireContext(), 2)
                    isLinear = false
                }else{
                    binding.shape.setImageResource(drawable.shape)
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


    private fun getData() {
        val pref = PreferncesHelper()
        pref.unit(requireContext())
        if (pref.isAnonim) {
            App.appDataBase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { model ->
                noteslist.clear()
                noteslist.addAll(model)
                adapter.submitList(noteslist)
            }
        } else {
            val db = Firebase.firestore
            db.collection("notes")
                .get()
                .addOnSuccessListener { result ->
                    noteslist.clear()
                    for (document in result) {
                        Log.d(TAG, "${document.id} => ${document.data}")
                        val title = document.data["title"].toString()
                        val description = document.data["description"].toString()
                        val time = document.data["time"].toString()
                        val note = NoteEntity(title, description, time)
                        noteslist.addAll(arrayListOf((note)))
                        adapter.submitList(noteslist)
                        adapter.notifyDataSetChanged()
                    }
                }
                            .addOnFailureListener { e ->
                    Log.e(TAG, "Ошибка получения заметок: ${e.message}", e)
                }
        }
    }
    private fun filterNotes(query: String) {
        val filtrelist = noteslist.filter { note ->
            note.title.contains(query,true) || note.description.contains(query,true)
            }
        adapter.submitList(filtrelist)
    }

    override fun onClick(note: NoteEntity) {
        findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToWriteNoteFragment(note.id))
    }

    override fun onLongClick(note: NoteEntity) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder
            .setMessage(string.text_massage)
            .setTitle(string.I_am_the_title)
            .setPositiveButton(string.yes) { dialog, which ->
                dialog.cancel()
            }
            .setNegativeButton(string.no) { dialog, which ->
                App.appDataBase?.noteDao()?.delete(note)
                val db = Firebase.firestore
                db.collection("notes").document(note.id.toString())
                    .delete()
                    .addOnSuccessListener {
                        Log.e("ololo", "Document successfully deleted!")
                    }
            }


        val dialog: AlertDialog = builder.create()
        dialog.show()


    }
    companion object {
        private const val TAG = "ololo"
    }
}
