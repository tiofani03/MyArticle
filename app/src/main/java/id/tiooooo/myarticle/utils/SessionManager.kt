package id.tiooooo.myarticle.utils

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object SessionManager {
    fun scheduleLogoutTimer(context: Context) {
        val logoutWorkRequest = OneTimeWorkRequestBuilder<LogoutWorker>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(context).enqueue(logoutWorkRequest)
    }
}