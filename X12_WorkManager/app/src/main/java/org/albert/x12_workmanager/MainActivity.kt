package org.albert.x12_workmanager

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkContinuation
import androidx.work.WorkManager
import androidx.work.workDataOf
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.albert.x12_workmanager.databinding.ActivityMainBinding
import org.albert.x12_workmanager.workers.SimpleWorker
import org.albert.x12_workmanager.singletons.SingletonWorkerData
import org.albert.x12_workmanager.workers.DatabaseWriterWorker
import org.albert.x12_workmanager.workers.NetworkRecommendationWorker
import org.albert.x12_workmanager.workers.ObjectDetectionWorker
import org.albert.x12_workmanager.workers.RetryWorker
import java.util.concurrent.TimeUnit
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LoginViewModel

    @SuppressLint("EnqueueWork")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[LoginViewModel::class]

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            viewModel.login(username, password)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginFlow.collectLatest { logged ->
                    if (logged) {
                        binding.status.setTextColor(Color.GREEN)
                        binding.status.text = "LOGGED IN"
                    } else {
                        binding.status.setTextColor(Color.RED)
                        binding.status.text = "Not Logged In"
                    }
                }
            }
        }
    }
}