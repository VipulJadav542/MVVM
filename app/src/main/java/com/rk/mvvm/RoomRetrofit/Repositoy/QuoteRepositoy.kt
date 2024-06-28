package com.rk.mvvm.RoomRetrofit.Repositoy

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rk.mvvm.RoomRetrofit.Utils.NetworkUtils
import com.rk.mvvm.RoomRetrofit.api.QuoteAPI
import com.rk.mvvm.RoomRetrofit.db.QuoteDatabase1
import com.rk.mvvm.RoomRetrofit.model.QuoteList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepositoy(
    private val quoteAPI: QuoteAPI,
    private val database: QuoteDatabase1,
    private val applicationContext: Context
) {
    private val quoteliveData = MutableLiveData<Response<QuoteList>>()

    val quote: LiveData<Response<QuoteList>>
        get() = quoteliveData

    suspend fun getQuote(page: Int) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            try {
                val quote = quoteAPI.getQuotes(page)
                if (quote != null) {

                    database.quoteDao().addQuote(quote.body()!!.results)
                    quoteliveData.postValue(Response.Success(quote.body()))
                } else {
                    quoteliveData.postValue(Response.Error("API Error"))
                }
            } catch (e: Exception) {
                quoteliveData.postValue(Response.Error(e.message.toString()))
            }

        } else {
            val quote = database.quoteDao().getQuote()
            val list = QuoteList(1, 1, 1, quote, 1, 1)
            quoteliveData.postValue(Response.Success(list))

        }
    }

    suspend fun getQuoteBackGround() {
        val randomnumber = (Math.random()).toInt()
        withContext(Dispatchers.IO) {
            try {
                val quoteResponse = quoteAPI.getQuotes(randomnumber)
                if (quoteResponse.isSuccessful) {
                    quoteResponse.body()?.let { quoteList ->
                        database.quoteDao().addQuote(quoteList.results)
                        quoteliveData.postValue(Response.Success(quoteList))
                    } ?: run {
                        quoteliveData.postValue(Response.Error("API response body is null"))
                    }
                } else {
                    quoteliveData.postValue(Response.Error("API Error: ${quoteResponse.message()}"))
                }
            } catch (e: Exception) {
                quoteliveData.postValue(Response.Error("Exception occurred: ${e.message}"))
            }
        }
    }
}