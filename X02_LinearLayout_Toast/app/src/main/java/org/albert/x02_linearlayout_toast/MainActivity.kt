package org.albert.x02_linearlayout_toast

import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.albert.x02_linearlayout_toast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReal.setOnClickListener {
            fromYen(binding.yenInput.text, "BRL", 0.038808)
        }

        binding.btnDolar.setOnClickListener {
            fromYen(binding.yenInput.text, "USD", 0.0065)
        }

        binding.btnEuro.setOnClickListener {
            fromYen(binding.yenInput.text, "EUR", 0.0061)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun fromYen(text: Editable, currency: String, rate: Double) {
        val text = text.toString().trim()
        if (text.isEmpty()) {
            binding.output.text = "Invalid number"
            Toast.makeText(applicationContext, "Invalid number", Toast.LENGTH_SHORT).show()
            return
        }

        val yen = convert(text.toDouble(), rate)
        show(currency, yen)
    }

    private fun convert(value: Double, rate: Double): Double {
        return value * rate
    }

    private fun show(currency: String, value: Double) {
        val msg = String.format("$currency: %.2f", value)
        binding.output.text = msg
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}