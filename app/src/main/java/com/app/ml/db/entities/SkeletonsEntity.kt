package com.app.ml.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SkeletonsEntity(
    @PrimaryKey val context: String,
    val renders: List<String>
)
