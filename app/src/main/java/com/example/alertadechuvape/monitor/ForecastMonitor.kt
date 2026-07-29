package com.example.alertadechuvape.monitor

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class ForecastMonitor(
    context: Context
) {

    private val workManager =
        WorkManager.getInstance(context)

    fun iniciar() {

        val request =

            PeriodicWorkRequestBuilder<ForecastWorker>(
                15,
                TimeUnit.MINUTES
            )
                .build()

        workManager.enqueueUniquePeriodicWork(

            "forecast",

            ExistingPeriodicWorkPolicy.UPDATE,

            request

        )

    }

    fun parar() {

        workManager.cancelUniqueWork("forecast")

    }

}