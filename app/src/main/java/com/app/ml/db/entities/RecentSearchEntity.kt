package com.app.ml.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecentSearchEntity(
    @PrimaryKey
    val search: String
)
