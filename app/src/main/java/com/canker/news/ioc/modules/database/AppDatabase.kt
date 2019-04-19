package com.canker.news.ioc.modules.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.canker.data.database.model.Color
import com.canker.data.database.dao.ColorsDao

@Database(entities = [Color::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
	abstract fun colorsDao(): ColorsDao
}
