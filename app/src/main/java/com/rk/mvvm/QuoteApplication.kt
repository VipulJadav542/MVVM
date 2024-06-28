package com.rk.mvvm

import android.app.Application
import androidx.constraintlayout.widget.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.rk.mvvm.RoomRetrofit.Repositoy.QuoteRepositoy
import com.rk.mvvm.RoomRetrofit.api.QuoteAPI
import com.rk.mvvm.RoomRetrofit.api.RetrofitHelper
import com.rk.mvvm.RoomRetrofit.db.QuoteDatabase1
import com.rk.mvvm.RoomRetrofit.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication:Application() {

    lateinit var quoteRepositoy: QuoteRepositoy
    override fun onCreate() {
        super.onCreate()
        intialize()
//        setupWorker()
    }

    private fun setupWorker() {
        val constraint=androidx.work.Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest=PeriodicWorkRequest.Builder(QuoteWorker::class.java,30,TimeUnit.MINUTES)
            .setConstraints(constraint)
            .build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun intialize() {
        val quoteRepositoy1 = RetrofitHelper.getInstance().create(QuoteAPI::class.java)
        val database = QuoteDatabase1.getDatabase(applicationContext)
        quoteRepositoy=QuoteRepositoy(quoteRepositoy1,database,applicationContext)
    }
}