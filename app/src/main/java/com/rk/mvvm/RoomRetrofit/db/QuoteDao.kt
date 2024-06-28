package com.rk.mvvm.RoomRetrofit.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rk.mvvm.RoomRetrofit.model.Result


@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuote(result: List<Result>)

    @Query("select * from quotes")
    suspend fun getQuote():List<Result>
}