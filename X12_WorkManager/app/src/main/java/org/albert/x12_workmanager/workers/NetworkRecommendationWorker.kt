package org.albert.x12_workmanager.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.albert.x12_workmanager.database.ProductDatabase
import kotlin.random.Random

class NetworkRecommendationWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {
    override fun doWork(): Result {
        Thread.sleep(1000)
        println(this::class.java.name)

        val success = inputData.getBoolean("SUCCESS", false)
        val name = inputData.getString("NAME")

        return if (success) {
            name?.let { println("$name SUCCESS") }
            val (color, product) = ProductDatabase.products[Random.nextInt(0, ProductDatabase.products.size)]
            println("Recommended: $color $product")
            Result.success()
        } else {
            name?.let { println("$name FAILURE") }
            Result.failure()
        }
    }
}