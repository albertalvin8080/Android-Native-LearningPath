package org.albert.x06_hydrate_me

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import org.albert.x06_hydrate_me.databinding.ActivityCreateEntryBinding

private const val TAG = "CreateEntryActivity"

class CreateEntryActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCreateEntryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

//        setTitle("Some Title")

        binding.addBtn.setOnClickListener { view ->
            Snackbar
                .make(view, "Entry Saved.", Snackbar.LENGTH_LONG)
                .show()

            val ml = binding.input.text.toString().toInt()
            Log.d(TAG, ml.toString())

            val i = Intent()
            i.putExtra("intake", ml)
            setResult(Activity.RESULT_OK, i)

            finish()
        }
    }
}