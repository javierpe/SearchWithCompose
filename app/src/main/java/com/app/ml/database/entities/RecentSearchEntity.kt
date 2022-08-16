package com.app.ml.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecentSearchEntity(
    @PrimaryKey
    val search: String
)
