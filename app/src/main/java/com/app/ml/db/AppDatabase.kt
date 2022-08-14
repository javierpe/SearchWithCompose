package com.app.ml.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.ml.db.converters.SkeletonConverter
import com.app.ml.db.dao.RecentSearchDao
import com.app.ml.db.dao.SkeletonsDao
import com.app.ml.db.entities.RecentSearchEntity
import com.app.ml.db.entities.SkeletonsEntity

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