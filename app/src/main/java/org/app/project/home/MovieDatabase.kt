package org.app.project.home

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun MovieDao(): MovieDao

    companion object {
        private var instance: MovieDatabase? = null

        @Synchronized
        fun getInstance(context: MoreActivity): MovieDatabase? {
            if (instance == null) {
                synchronized(MovieDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "user-database"
                    ).allowMainThreadQueries().build()
                }
            }
            return instance
        }
    }
}