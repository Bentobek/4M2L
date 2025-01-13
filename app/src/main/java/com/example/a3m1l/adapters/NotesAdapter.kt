package com.example.a3m1l.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a3m1l.databinding.NoteItemBinding
import com.example.a3m1l.models.Notes
import androidx.recyclerview.widget.ListAdapter


class NotesAdapter (private val notes: ArrayList<Notes>): ListAdapter<Notes,NotesAdapter.NotesViewholder>(DiffCallBack()) {

    private val background = listOf(
        com.example.a3m1l.R.drawable.bg_cyan,
        com.example.a3m1l.R.drawable.bg_green,
        com.example.a3m1l.R.drawable.bg_red,
        com.example.a3m1l.R.drawable.bg_yellow,
        com.example.a3m1l.R.drawable.bg_orange
    )


    inner class NotesViewholder(private val binding: NoteItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(note: Notes){
            binding.tvTitle.text = note.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewholder {
        val binding= NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewholder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewholder, position: Int) {
        val background = background[position % background.size]
        holder.itemView.setBackgroundResource(background)
        holder.bind(notes[position])
    }
    class DiffCallBack: DiffUtil.ItemCallback<Notes>(){
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.title == newItem.title
        }

    }

    override fun getItemCount(): Int {
        return notes.size
    }
}
