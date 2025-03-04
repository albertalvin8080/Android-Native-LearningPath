package org.albert.x06_hydrate_me

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.albert.x06_hydrate_me.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode != Activity.RESULT_OK)
                return@registerForActivityResult

            val intake = result.data?.getIntExtra("intake", 0) ?: 0
            IntakeManager.addIntake(intake)

            updateTodaysTotal()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
//        WARNING: This DOES NOT mean you can use actionBarStyle instead of toolbarStyle.
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        binding.fab.setOnClickListener { view ->
            val i = Intent(this, CreateEntryActivity::class.java)
//            startActivity(i)
            startForResult.launch(i)
        }

        updateTodaysTotal()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("SetTextI18n")
    private fun updateTodaysTotal() {
        val totalIntake = IntakeManager.getTotalIntake()

        binding.totalIntake.text = totalIntake.toString()
        if (IntakeManager.getAboveMinimum()) {
            binding.totalIntake.setTextColor(Color.GREEN)
            binding.msg.text = R.string.done.toString()
        } else {
            binding.totalIntake.setTextColor(Color.RED)
            binding.msg.text = R.string.not_done.toString()
        }
    }
}