package com.maiconhellmann.sixtcodechallenge.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maiconhellmann.sixtcodechallenge.local.model.CarCache

/*
 * This file is part of SixtCodeChallenge.
 * 
 * Created by maiconhellmann on 02/08/2019
 * 
 * (c) 2019
 */
@Database(version = 1, entities = [CarCache::class], exportSchema = false)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao

    companion object {
        fun createDatabase(context: Context): CarDao {
            return Room.databaseBuilder(context, CarDatabase::class.java, "car.db")
                .fallbackToDestructiveMigration().build().carDao()
        }

        fun createDatabaseInMemory(context: Context): CarDao {
            return Room.inMemoryDatabaseBuilder(context, CarDatabase::class.java)
                .fallbackToDestructiveMigration().build().carDao()
        }
    }
}