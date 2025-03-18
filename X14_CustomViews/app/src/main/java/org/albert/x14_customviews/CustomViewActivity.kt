package org.albert.x14_customviews

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.albert.x14_customviews.data.DataColorManager
import org.albert.x14_customviews.databinding.ActivityCustomViewBinding

class CustomViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomViewBinding
    private var color = DataColorManager.color

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.colorDial.selectedColor = color
        binding.colorDial.addListener { c ->
            color = c
        }
    }

    override fun onPause() {
        super.onPause()
        DataColorManager.color = color
    }
}