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
import com.google.firebase.firestore.FieldPath.documentId
import com.google.firebase.firestore.SetOptions
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

        if (pref.isAnonim) {
            // Сохранение в локальную БД (Room)
            binding.btnSave.setOnClickListener {
                val title = binding.etTitle.text.toString()
                val description = binding.description.text.toString()
                val time = binding.date.text.toString()
                val note = NoteEntity(title = title, description = description, time = time)

                if (isEdit) {
                    val args = WriteNoteFragmentArgs.fromBundle(requireArguments())
                    note.id = args.note
                    App.appDataBase?.noteDao()?.update(note)
                } else {
                    App.appDataBase?.noteDao()?.insert(note)
                }
                findNavController().navigateUp()
            }
        } else {
            binding.btnSave.setOnClickListener {
                val title = binding.etTitle.text.toString()
                val description = binding.description.text.toString()
                val time = binding.date.text.toString()

                val noteData = hashMapOf(
                    "title" to title,
                    "description" to description,
                    "time" to time
                )

                val db = Firebase.firestore

                if (isEdit) {
                    val args = WriteNoteFragmentArgs.fromBundle(requireArguments())
                    val firestoreId = args.noteId

                    if (firestoreId != null) {
                        db.collection("notes")
                            .document(firestoreId)
                            .update(noteData as Map<String, Any>)
                            .addOnSuccessListener {
                                Log.d(TAG, "Заметка обновлена в Firestore")
                                Toast.makeText(requireContext(), "Заметка обновлена!", Toast.LENGTH_SHORT).show()
                                findNavController().navigateUp()
                            }
                            .addOnFailureListener { e ->
                                Log.e(TAG, "Ошибка обновления заметки: ${e.message}", e)
                                Toast.makeText(requireContext(), "Ошибка обновления", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Log.e(TAG, "Ошибка: Firestore ID отсутствует")
                    }
                } else {
                    db.collection("notes")
                        .add(noteData)
                        .addOnSuccessListener { documentReference ->
                            Log.d(TAG, "Документ создан с ID: ${documentReference.id}")

                            val note = NoteEntity(
                                firestoreId = documentReference.id,
                                title = title,
                                description = description,
                                time = time
                            )
                            App.appDataBase?.noteDao()?.insert(note)

                            Toast.makeText(requireContext(), "Заметка сохранена!", Toast.LENGTH_SHORT).show()
                            findNavController().navigateUp()
                        }
                        .addOnFailureListener { e ->
                            Log.e(TAG, "Ошибка сохранения заметки: ${e.message}", e)
                            Toast.makeText(requireContext(), "Ошибка сохранения", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }
    }

    companion object {
        private const val TAG = "ololo"
    }
}