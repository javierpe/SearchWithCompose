package com.app.ml.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SkeletonsEntity(
    @PrimaryKey val context: String,
    val renders: List<String>
)
