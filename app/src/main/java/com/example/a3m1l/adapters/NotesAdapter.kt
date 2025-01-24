package com.example.a3m1l.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a3m1l.databinding.NoteItemBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.a3m1l.data.model.NoteEntity


class NotesAdapter : ListAdapter<NoteEntity,NotesAdapter.NotesViewholder>(DiffCallBack()) {

    private val background = listOf(
        com.example.a3m1l.R.drawable.bg_cyan,
        com.example.a3m1l.R.drawable.bg_green,
        com.example.a3m1l.R.drawable.bg_red,
        com.example.a3m1l.R.drawable.bg_yellow,
        com.example.a3m1l.R.drawable.bg_orange
    )


    inner class NotesViewholder(private val binding: NoteItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(note: NoteEntity){
            binding.tvTitle.text = note.title
            binding.tvDescritption.text = note.description
            binding.tvTime.text = note.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewholder {
        val binding= NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewholder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewholder, position: Int) {
        val background = background[position % background.size]
        holder.itemView.setBackgroundResource(background)
        holder.bind(getItem(position))
    }
    class DiffCallBack: DiffUtil.ItemCallback<NoteEntity>(){
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.description == newItem.description
                    && oldItem.time == newItem.time
        }
    }

}
