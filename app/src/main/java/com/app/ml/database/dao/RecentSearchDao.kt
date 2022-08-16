package com.app.ml.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.ml.database.entities.RecentSearchEntity

@Dao
abstract class RecentSearchDao {

    @Query("SELECT * FROM RecentSearchEntity WHERE search LIKE :value || '%' GROUP BY search")
    abstract fun loadRecentSearchByValue(value: String): List<RecentSearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addRecentSearchValue(value: RecentSearchEntity)
}