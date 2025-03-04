package org.albert.pluralsight01

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import org.albert.pluralsight01.databinding.ActivityNotesListBinding
import org.albert.pluralsight01.databinding.ContentNotesListBinding

class NotesListActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNotesListBinding
    private lateinit var content: ContentNotesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotesListBinding.inflate(layoutInflater)
        content = ContentNotesListBinding.bind(binding.root)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            val intent = Intent(this, NoteEditActivity::class.java)
            startActivity(intent)
        }

        content.listNotes.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            DataManager.notes
        )

        content.listNotes.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, NoteEditActivity::class.java)
            intent.putExtra(NOTE_POSITION, position)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        (content.listNotes.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }
}