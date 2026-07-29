package com.example.alertadechuvape.monitor

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.alertadechuvape.R
import android.app.PendingIntent
import android.content.Intent
import com.example.alertadechuvape.MainActivity
import android.os.Build


class ForecastWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    companion object {

        private const val CHANNEL_ID = "forecast_channel"

    }

    override fun doWork(): Result {



        val intent = Intent(
            applicationContext,
            MainActivity::class.java
        ).apply {

            addFlags(
                Intent.FLAG_ACTIVITY_SINGLE_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
            )

        }

        val pendingIntent = PendingIntent.getActivity(

            applicationContext,

            0,

            intent,

            PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_IMMUTABLE

        )

        if (
            ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            return Result.success()
        }

        createChannel()

        val notification =
            NotificationCompat.Builder(
                applicationContext,
                CHANNEL_ID
            )
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Alerta de Chuva PE")
                .setContentText(
                    "Confira a previsão do tempo atualizada."
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

        val manager =
            applicationContext.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

        manager.notify(1, notification)

        return Result.success()

    }

    private fun createChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                "Previsão do tempo",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager =
                applicationContext.getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager

            manager.createNotificationChannel(channel)
        }
    }

}