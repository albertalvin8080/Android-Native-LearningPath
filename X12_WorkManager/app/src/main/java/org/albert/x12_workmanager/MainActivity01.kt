package org.albert.x12_workmanager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import org.albert.x12_workmanager.databinding.ActivityMain01Binding
import org.albert.x12_workmanager.databinding.ActivityMainBinding
import org.albert.x12_workmanager.workers.SimpleWorker
import org.albert.x12_workmanager.singletons.SingletonWorkerData
import org.albert.x12_workmanager.workers.RetryWorker
import java.util.concurrent.TimeUnit

class MainActivity01 : AppCompatActivity() {
    private lateinit var binding: ActivityMain01Binding
    private lateinit var workManager: WorkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        workManager = WorkManager.getInstance(this)

        binding = ActivityMain01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startWork.setOnClickListener {
            val data = workDataOf("KEY_MESSAGE" to "Detestatio Sacrorum")

            val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

//            PeriodicWorkRequestBuilder<SimpleWorker>(5, TimeUnit.MINUTES)
            val work = OneTimeWorkRequestBuilder<SimpleWorker>()
                .setInputData(data)
                .setConstraints(constraints)
                .build()

            Toast.makeText(this, "starting work", Toast.LENGTH_SHORT)
                .show()
            workManager.enqueue(work)
        }

        binding.displayWorkStatus.setOnClickListener {
            Toast.makeText(this, """
                completed: ${SingletonWorkerData.completed}
                message: ${SingletonWorkerData.message}
            """.trimIndent(), Toast.LENGTH_SHORT)
                .show()
        }

        binding.reset.setOnClickListener {
            SingletonWorkerData.completed = false
            SingletonWorkerData.message = null
        }

        binding.retry.setOnClickListener {
            val work = OneTimeWorkRequestBuilder<RetryWorker>()
                // 10 Seconds is the minimum
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    10,
                    TimeUnit.SECONDS
                )
                .build()

            workManager.enqueue(work)
        }
    }
}