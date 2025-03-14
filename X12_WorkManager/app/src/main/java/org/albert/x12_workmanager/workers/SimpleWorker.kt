package org.albert.x12_workmanager.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.albert.x12_workmanager.singletons.SingletonWorkerData

class SimpleWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
//        Thread.sleep(5000)

        SingletonWorkerData.message = inputData.getString("KEY_MESSAGE")
        SingletonWorkerData.completed = true

        return Result.success()
    }
}