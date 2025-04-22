package id.tiooooo.myarticle.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import androidx.work.Worker
import androidx.work.WorkerParameters
import id.tiooooo.myarticle.data.implementation.local.datastore.AppDatastore
import id.tiooooo.myarticle.utils.AppConstants.BROADCAST_LOGOUT_TAG
import kotlinx.coroutines.runBlocking
import org.koin.java.KoinJavaComponent.getKoin

class LogoutWorker(
    context: Context,
    workerParams: WorkerParameters,
//    private val appDatastore: AppDatastore
) : Worker(context, workerParams) {
//    private val appDatastore = AppDatastore(context)
private val appDatastore: AppDatastore = getKoin().get() // Ambil dependensi dari Koin secara manual


    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun doWork(): Result = runBlocking {
        try {
            logoutUser()
            showSimpleNotification(applicationContext)
            appDatastore.setIsLoggedIn(false)
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }


    @SuppressLint("UnsafeImplicitIntentLaunch")
    private fun logoutUser() {
        val intent = Intent(BROADCAST_LOGOUT_TAG)
        applicationContext.sendBroadcast(intent)

    }
}

class LogoutReceiver(private val onLogout: () -> Unit) : BroadcastReceiver() {
    constructor() : this({})

    override fun onReceive(context: Context?, intent: Intent?) {
        onLogout()
    }
}