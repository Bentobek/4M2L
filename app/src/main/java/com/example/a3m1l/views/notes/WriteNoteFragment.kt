package com.example.a3m1l.views.notes

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a3m1l.App
import com.example.a3m1l.PreferncesHelper
import com.example.a3m1l.data.model.NoteEntity
import com.example.a3m1l.databinding.FragmentWriteNoteBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class WriteNoteFragment : Fragment() {
    private lateinit var binding: FragmentWriteNoteBinding
    private var isEdit = false
    private val hundler = Handler(Looper.getMainLooper())
    private var currentData = object : Runnable {
        override fun run() {
            val currentTime = SimpleDateFormat("dd MM HH:mm", Locale.getDefault()).format(Date())
            binding.date.text = currentTime
            hundler.postDelayed(this, 30000)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setlistener()
        runTimer()
    }

    private fun runTimer() {
        hundler.post(currentData)
    }

    override fun onDestroy() {
        super.onDestroy()
        hundler.removeCallbacks(currentData)
    }

    private fun setlistener() {
        val pref = PreferncesHelper()
        pref.unit(requireContext())
    if (pref.isAnonim){
        binding.apply {
            val args =  WriteNoteFragmentArgs.fromBundle(requireArguments())
            App.appDataBase?.noteDao()?.getById(args.note)?.let {
                etTitle.setText(it.title)
                description.setText(it.description)
                isEdit = true
            }
        }
        binding.apply {
            btnSave.setOnClickListener {
                val title = etTitle.text.toString()
                val description = description.text.toString()
                val time = date.text.toString()
                val note = NoteEntity(title, description, time)
                if (isEdit ) {
                    val args = WriteNoteFragmentArgs.fromBundle(requireArguments())
                    note.id = args.note
                    App.appDataBase?.noteDao()?.update(note)
                    findNavController().navigateUp()
                } else {
                    App.appDataBase?.noteDao()?.insert(note)
                    findNavController().navigateUp()
                }
            }
        }
            }else{
        val db = Firebase.firestore
        val note = hashMapOf(
            "tittle" to binding.etTitle.text.toString(),
            "description" to binding.description.toString(),
            "time" to binding.date.text.toString(),
        )
        db.collection("notes")
            .add(note)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    TAG,
                    "DocumentSnapshot added with ID: ${documentReference.id}"
                )
                findNavController().navigateUp()
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
         }
    }
    companion object {
        private const val TAG = "ololo"
    }
}