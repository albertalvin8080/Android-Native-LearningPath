package org.albert.pluralsight01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.content.res.AppCompatResources
import org.albert.pluralsight01.databinding.ActivityNoteEditBinding
import org.albert.pluralsight01.databinding.ContentNoteEditBinding

class NoteEditActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var activityNoteEditBinding: ActivityNoteEditBinding
    private lateinit var contentNoteEditBinding: ContentNoteEditBinding

    private var passedNotePos: Int = EXTRA_NOT_PASSED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityNoteEditBinding = ActivityNoteEditBinding.inflate(layoutInflater)
        contentNoteEditBinding = ContentNoteEditBinding.bind(activityNoteEditBinding.root)
        setContentView(activityNoteEditBinding.root)

        setSupportActionBar(activityNoteEditBinding.toolbar)

        val coursesAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList()
        )
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        contentNoteEditBinding.spinnerCourses.adapter = coursesAdapter

        passedNotePos = intent.getIntExtra(EXTRA_NOTE_POS, EXTRA_NOT_PASSED)
        if(passedNotePos != EXTRA_NOT_PASSED)
            displayNote()
    }

    private fun displayNote()
    {
        val note = DataManager.notes[passedNotePos]
        val courseIndex = DataManager.courses.values.indexOf(note.course)

        contentNoteEditBinding.textNoteTitle.setText(note.text)
        contentNoteEditBinding.textNoteContent.setText(note.text)
        contentNoteEditBinding.spinnerCourses.setSelection(courseIndex)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                next()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun next() {
        ++passedNotePos
        displayNote()
        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (passedNotePos == DataManager.notes.lastIndex)
        {
            val menuItem = menu?.findItem(R.id.action_next)
            menuItem?.icon = AppCompatResources.getDrawable(this, R.drawable.baseline_block_24)
            menuItem?.isEnabled = false
        }

        return super.onPrepareOptionsMenu(menu)
    }
}