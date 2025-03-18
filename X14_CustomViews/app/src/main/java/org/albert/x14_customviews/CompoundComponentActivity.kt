package org.albert.x14_customviews

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.albert.x14_customviews.data.DataColorManager
import org.albert.x14_customviews.databinding.ActivityCompoundComponentBinding

class CompoundComponentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCompoundComponentBinding
    private var color = DataColorManager.color

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCompoundComponentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.colorSelector.selectedColor = color
        binding.colorSelector.addColorListener { c ->
            color = c
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(this::class.simpleName, "onPause")
        DataColorManager.color = color
    }

    // WARNING: This onDestroy may not necessarily be called before onResume of the previous activity / fragment.
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(this::class.simpleName, "onDestroy")
//        DataColorManager.color = color
//    }
}