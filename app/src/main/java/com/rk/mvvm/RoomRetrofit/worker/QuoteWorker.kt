package com.rk.mvvm.RoomRetrofit.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.rk.mvvm.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context,private val parameters: WorkerParameters):Worker(context, parameters) {
    override fun doWork(): Result {
        Log.d("BackGround", "Background Task is running")
        val repositoy=(context as QuoteApplication).quoteRepositoy
        CoroutineScope(Dispatchers.IO).launch {
            repositoy.getQuoteBackGround()
        }
        return Result.success()
    }
}