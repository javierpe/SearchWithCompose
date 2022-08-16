package com.app.ml.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.ml.database.entities.SkeletonsEntity

@Dao
abstract class SkeletonsDao {

    @Query("SELECT * FROM SkeletonsEntity WHERE context = :context")
    abstract fun provideSkeletonsByContext(context: String): SkeletonsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveSkeletonsByContext(skeletonsEntity: SkeletonsEntity)
}