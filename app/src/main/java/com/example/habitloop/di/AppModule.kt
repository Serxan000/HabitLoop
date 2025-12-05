package com.example.habitloop.di

import android.app.Application
import androidx.room.Room
import com.example.habitloop.data.local.HabitDatabase
import com.example.habitloop.data.repository.HabitRepositoryImpl
import com.example.habitloop.domain.repository.HabitRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHabitDatabase(app: Application): HabitDatabase =
        Room.databaseBuilder(app, HabitDatabase::class.java, "habit_db")
            .fallbackToDestructiveMigration() // <- köhnə DB silinir və yenidən yaradılır
            .build()


    @Provides
    @Singleton
    fun provideHabitDao(db: HabitDatabase) = db.getDao()

}




@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindHabitRepository(
        habitRepositoryImpl: HabitRepositoryImpl
    ): HabitRepository
}

