package com.example.a3m1l.views.notes

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a3m1l.App
import com.example.a3m1l.data.model.NoteEntity
import com.example.a3m1l.databinding.FragmentWriteNoteBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class WriteNoteFragment : Fragment() {
    private lateinit var binding: FragmentWriteNoteBinding
    private val hundler = Handler(Looper.getMainLooper())
    private var currentData = object : Runnable {
        override fun run() {
            val currentTime = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
            binding.date.text = currentTime
            hundler.postDelayed(this, 30000)
        }
    }
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
        runTimer()
    }

    private fun runTimer() {
        hundler.post(currentData)
    }

    override fun onDestroy() {
        super.onDestroy()
        hundler.removeCallbacks(currentData)
    }
    @SuppressLint("SuspiciousIndentation")
    private fun setlistener() {
        binding.apply {
            btnSave.setOnClickListener{
                val title = binding.etTitle.text.toString()
                val description = binding.description.text.toString()
                val time = binding.date.text.toString()
                    App.appDataBase?.noteDao()?.insert(NoteEntity(title, description, time))
                        findNavController().navigateUp()
//                val pref = PreferncesHelper()
//                pref.unit(requireContext())
//                pref.text = title

            }
        }
    }
}
