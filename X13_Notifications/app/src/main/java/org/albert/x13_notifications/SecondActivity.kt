package org.albert.x13_notifications

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.albert.x13_notifications.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authorName = intent.getStringExtra("KEY_AUTHOR_NAME")
        if (authorName != null)
            binding.msg.text = authorName
    }
}