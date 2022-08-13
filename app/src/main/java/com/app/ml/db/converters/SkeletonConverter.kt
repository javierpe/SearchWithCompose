package com.app.ml.db.converters

import androidx.room.TypeConverter
import org.json.JSONArray
import org.json.JSONObject

class SkeletonConverter {

    @TypeConverter
    fun fromRenders(renderType: List<String>): String {
        val data = JSONObject()
        val list = JSONArray()
        renderType.forEach { list.put(it) }

        return data.put("renders", list).toString()
    }

    @TypeConverter
    fun toRenders(renders: String): List<String> {
        val data = JSONObject(renders).getJSONArray("renders")

        val renderList = mutableListOf<String>()
        (0 until data.length()).forEach { item ->
            renderList.add(data.getString(item).uppercase())
        }

        return renderList
    }
}