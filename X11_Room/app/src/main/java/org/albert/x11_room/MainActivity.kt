package org.albert.x11_room

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.albert.x11_room.adapters.NoteListWithNoteItemsAdapter
import org.albert.x11_room.databinding.ActivityMainBinding
import org.albert.x11_room.model.NoteItem
import org.albert.x11_room.model.NoteList
import org.albert.x11_room.viewholders.NoteListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteListWithNoteItemsAdapter
    private lateinit var viewModel: NoteListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            factory = NoteListViewModelFactory(applicationContext)
        ).get(NoteListViewModel::class)

        adapter = NoteListWithNoteItemsAdapter()
//        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.layoutManager = GridLayoutManager(this, 2)
        binding.rv.adapter = adapter

        // Observe changes from ViewModel
        lifecycleScope.launch {
            viewModel.noteListWithNoteItemsFlow.collectLatest { notes ->
                Log.e(this::class.simpleName, "collectLatest: ${notes}")
                adapter.submitList(notes)
            }
        }

        // Inserting noteLists and noteItems
//        val lastIdPlusOne = viewModel.dao.getLastId() + 1
//        if (lastIdPlusOne < 5) {
//            for (i in lastIdPlusOne..(lastIdPlusOne + 4)) {
//                viewModel.insertNoteList(NoteList(title = "sacrorum_$i", id = i))
//                val itemLastIdPlusOne = viewModel.db.noteItemDao().getLastId() + 1
//                for(j in itemLastIdPlusOne..(itemLastIdPlusOne+2))
//                    viewModel.insertNoteItem(NoteItem(id=j, value="$j lorem ipsum dolor amet scir sciato", noteListId = i))
//            }
//        }

        binding.add.setOnClickListener{
            insert()
        }

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                viewModel.search(query)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun insert() {
        viewModel.insertNoteListWithLastIdPlusOne()
    }

    class NoteListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NoteListViewModel(context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}