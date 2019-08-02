package com.maiconhellmann.sixtcodechallenge.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.maiconhellmann.sixtcodechallenge.local.model.CarCache
import io.reactivex.Single

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 02/08/2019
 * 
 * (c) 2019 
 */
@Dao
interface CarDao {

    @Query("SELECT * FROM CarCache")
    fun getAll(): Single<List<CarCache>>

    @Query("DELETE FROM CarCache")
    fun deleteAll()

    @Insert
    fun insertAll(list: List<CarCache>)

    @Transaction
    fun updateDate(list: List<CarCache>) {
        deleteAll()
        insertAll(list)
    }
}