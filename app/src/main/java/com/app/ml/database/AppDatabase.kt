package com.app.ml.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.ml.database.converters.SkeletonConverter
import com.app.ml.database.dao.RecentSearchDao
import com.app.ml.database.dao.SkeletonsDao
import com.app.ml.database.entities.RecentSearchEntity
import com.app.ml.database.entities.SkeletonsEntity

@Database(
    entities = [
        SkeletonsEntity::class,
        RecentSearchEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(SkeletonConverter::class)
abstract class AppDatabase: RoomDatabase() {

    /**
     * Only skeletons
     */
    abstract fun skeletonsDao(): SkeletonsDao

    /**
     * Recent search
     */
    abstract fun recentSearchDao(): RecentSearchDao
}