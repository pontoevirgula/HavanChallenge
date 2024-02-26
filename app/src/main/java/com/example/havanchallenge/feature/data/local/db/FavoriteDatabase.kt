package com.example.havanchallenge.feature.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.havanchallenge.feature.data.local.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1
)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao
}