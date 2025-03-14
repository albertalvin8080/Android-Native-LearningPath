package org.albert.x12_workmanager

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkContinuation
import androidx.work.WorkManager
import androidx.work.workDataOf
import org.albert.x12_workmanager.databinding.ActivityMain02Binding
import org.albert.x12_workmanager.databinding.ActivityMainBinding
import org.albert.x12_workmanager.workers.SimpleWorker
import org.albert.x12_workmanager.singletons.SingletonWorkerData
import org.albert.x12_workmanager.workers.DatabaseWriterWorker
import org.albert.x12_workmanager.workers.NetworkRecommendationWorker
import org.albert.x12_workmanager.workers.ObjectDetectionWorker
import org.albert.x12_workmanager.workers.RetryWorker
import java.util.concurrent.TimeUnit

class MainActivity02 : AppCompatActivity() {
    private lateinit var binding: ActivityMain02Binding
    private lateinit var workManager: WorkManager

    @SuppressLint("EnqueueWork")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        workManager = WorkManager.getInstance(this)

        binding = ActivityMain02Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // tag:WM-WorkerWrapper

        binding.singleChainSuc.setOnClickListener {
            val objDWorker = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val netRecWorker = OneTimeWorkRequestBuilder<NetworkRecommendationWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val dbWriterWorker = OneTimeWorkRequestBuilder<DatabaseWriterWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(objDWorker)
                .then(netRecWorker)
                .then(dbWriterWorker)
                .enqueue()
        }

        binding.singleChainSuc.setOnClickListener {
            val objDWorker = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val netRecWorker = OneTimeWorkRequestBuilder<NetworkRecommendationWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val dbWriterWorker = OneTimeWorkRequestBuilder<DatabaseWriterWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(objDWorker)
                .then(netRecWorker)
                .then(dbWriterWorker)
                .enqueue()
        }

        binding.singleChainFail.setOnClickListener {
            val objDWorker = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to false))
                .build()
            val netRecWorker = OneTimeWorkRequestBuilder<NetworkRecommendationWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val dbWriterWorker = OneTimeWorkRequestBuilder<DatabaseWriterWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(objDWorker)
                .then(netRecWorker)
                .then(dbWriterWorker)
                .enqueue()
        }

        binding.groupChainSuc.setOnClickListener {
            val objDWorker = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val objDWorker2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val netRecWorker = OneTimeWorkRequestBuilder<NetworkRecommendationWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val dbWriterWorker = OneTimeWorkRequestBuilder<DatabaseWriterWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(listOf(objDWorker, objDWorker2))
                .then(netRecWorker)
                .then(dbWriterWorker)
                .enqueue()
        }

        binding.groupChainFail.setOnClickListener {
            val objDWorker = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val objDWorker2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to false))
                .build()
            val netRecWorker = OneTimeWorkRequestBuilder<NetworkRecommendationWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val dbWriterWorker = OneTimeWorkRequestBuilder<DatabaseWriterWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(listOf(objDWorker, objDWorker2))
                .then(netRecWorker)
                .then(dbWriterWorker)
                .enqueue()
        }

        binding.multipleChainSuc.setOnClickListener {
            val objDWorker = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val objDWorker2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val netRecWorker = OneTimeWorkRequestBuilder<NetworkRecommendationWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val netRecWorker2 = OneTimeWorkRequestBuilder<NetworkRecommendationWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val dbWriterWorker = OneTimeWorkRequestBuilder<DatabaseWriterWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val dbWriterWorker2 = OneTimeWorkRequestBuilder<DatabaseWriterWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            val chain1 = workManager.beginWith(objDWorker)
                .then(netRecWorker)
                .then(dbWriterWorker)

            val chain2 = workManager.beginWith(objDWorker2)
                .then(netRecWorker2)
                .then(dbWriterWorker2)

            val root = WorkContinuation.combine(listOf(chain1, chain2))
            root.enqueue()
        }

        binding.multipleChainFail.setOnClickListener {
            val objDWorker = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val objDWorker2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to false))
                .build()
            val netRecWorker = OneTimeWorkRequestBuilder<NetworkRecommendationWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val netRecWorker2 = OneTimeWorkRequestBuilder<NetworkRecommendationWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val dbWriterWorker = OneTimeWorkRequestBuilder<DatabaseWriterWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val dbWriterWorker2 = OneTimeWorkRequestBuilder<DatabaseWriterWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            val chain1 = workManager.beginWith(objDWorker)
                .then(netRecWorker)
                .then(dbWriterWorker)

            val chain2 = workManager.beginWith(objDWorker2)
                .then(netRecWorker2)
                .then(dbWriterWorker2)

            val root = WorkContinuation.combine(listOf(chain1, chain2))
            root.enqueue()
        }
    }
}