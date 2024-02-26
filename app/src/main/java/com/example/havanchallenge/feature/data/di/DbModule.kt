package com.example.havanchallenge.feature.data.di

import android.content.Context
import androidx.room.Room
import com.example.havanchallenge.feature.data.local.db.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun provideFavoriteDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context = context,
            klass = FavoriteDatabase::class.java,
            name = "favorite_db_app"
        ).build()

    @Provides
    fun provideFavoriteDao(
        database: FavoriteDatabase
    ) = database.favoriteDao()

}