package com.example.havanchallenge.feature.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.havanchallenge.feature.data.local.entity.FavoriteEntity

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Query("select * from favoriteentity")
    fun listFavorites() : List<FavoriteEntity>

    @Delete
    suspend fun deleteFavoriteById(entity: FavoriteEntity)
}