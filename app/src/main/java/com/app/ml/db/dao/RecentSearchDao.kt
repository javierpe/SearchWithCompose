package com.app.ml.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.ml.db.entities.RecentSearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RecentSearchDao {

    @Query("SELECT * FROM RecentSearchEntity WHERE search LIKE :value || '%' GROUP BY search")
    abstract fun loadRecentSearchByValue(value: String): List<RecentSearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addRecentSearchValue(value: RecentSearchEntity)
}