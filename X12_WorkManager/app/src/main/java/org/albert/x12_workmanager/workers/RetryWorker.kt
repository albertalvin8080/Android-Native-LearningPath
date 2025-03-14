package org.albert.x12_workmanager.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.albert.x12_workmanager.singletons.RetryCounter

class RetryWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        return if (RetryCounter.count < 2) {
            RetryCounter.count += 1
            Result.retry()
        } else {
            Result.failure()
        }
    }
}