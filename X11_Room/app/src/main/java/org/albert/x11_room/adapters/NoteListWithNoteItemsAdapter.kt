package org.albert.x11_room.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.albert.x11_room.databinding.CardNoteItemBinding
import org.albert.x11_room.databinding.CardNoteListBinding
import org.albert.x11_room.model.NoteListWithNoteItems

class NoteListWithNoteItemsAdapter
    : ListAdapter<NoteListWithNoteItems, NoteListWithNoteItemsAdapter.NLWNIViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NLWNIViewHolder {
        val binding =
            CardNoteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NLWNIViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NLWNIViewHolder, position: Int) {
        Log.e(this::class.simpleName, "On bind view holder")
        val note = getItem(position)
        holder.bind(note)
    }

    inner class NLWNIViewHolder(private val binding: CardNoteListBinding)
        : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(noteListWithNoteItems: NoteListWithNoteItems) {
            binding.title.text = noteListWithNoteItems.noteList.title
            val noteItemsLayout = binding.noteItemsLayout
            noteItemsLayout.removeAllViews()

            // Add views for each note item dynamically
            for (noteItem in noteListWithNoteItems.noteItems) {
                val cardNoteItemBinding = CardNoteItemBinding.inflate(
                    LayoutInflater.from(binding.root.context), noteItemsLayout, false
                )
//                cardNoteItemBinding.id.text = noteItem.id.toString()
                cardNoteItemBinding.value.text = noteItem.value
                noteItemsLayout.addView(cardNoteItemBinding.root)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NoteListWithNoteItems>() {
            override fun areItemsTheSame(
                oldItem: NoteListWithNoteItems,
                newItem: NoteListWithNoteItems
            ): Boolean {
                Log.d(this::class.simpleName, "areItemsTheSame")
                return oldItem.noteList.id == newItem.noteList.id
            }

            override fun areContentsTheSame(
                oldItem: NoteListWithNoteItems,
                newItem: NoteListWithNoteItems
            ): Boolean {
                Log.d(this::class.simpleName, "areContentsTheSame")
                return oldItem.noteList.title == newItem.noteList.title
            }
        }
    }
}
