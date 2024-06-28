package com.rk.mvvm.RoomRetrofit.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rk.mvvm.RoomRetrofit.model.Result


@Database(entities = [Result::class], version = 1)
abstract class QuoteDatabase1 : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object {
        @Volatile
        private var INSTANCE: QuoteDatabase1? = null

        fun getDatabase(context: Context): QuoteDatabase1 {
            if (INSTANCE == null) {
                synchronized(this)
                {
                    INSTANCE =
                        Room.databaseBuilder(context, QuoteDatabase1::class.java, "Quote_Database1")
//                            .createFromAsset("quotes.db")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}