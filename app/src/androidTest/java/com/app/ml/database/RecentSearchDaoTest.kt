package com.app.ml.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.ml.database.dao.RecentSearchDao
import com.app.ml.database.entities.RecentSearchEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RecentSearchDaoTest {

    private lateinit var recentSearchDao: RecentSearchDao
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        recentSearchDao = db.recentSearchDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeRecentSearchAndReadIt() {
        val recentSearchEntity = RecentSearchEntity(
            "TEST"
        )

        recentSearchDao.addRecentSearchValue(recentSearchEntity)
        val recentSearchRecovered = recentSearchDao.loadRecentSearchByValue("TEST")
        assert(recentSearchRecovered.contains(recentSearchEntity))
    }
}