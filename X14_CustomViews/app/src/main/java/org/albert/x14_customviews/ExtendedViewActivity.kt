package org.albert.x14_customviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.albert.x14_customviews.data.DataColorManager
import org.albert.x14_customviews.databinding.ActivityExtendedViewBinding

class ExtendedViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExtendedViewBinding
    private var color = DataColorManager.color

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityExtendedViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.colorSlider.selectedColor = color
        binding.colorSlider.addListener { c ->
            color = c
        }
    }

    override fun onPause() {
        super.onPause()
        DataColorManager.color = color
    }
}