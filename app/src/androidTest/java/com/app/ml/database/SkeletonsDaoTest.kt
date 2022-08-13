package com.app.ml.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.ml.db.AppDatabase
import com.app.ml.db.dao.SkeletonsDao
import com.app.ml.db.entities.SkeletonsEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SkeletonsDaoTest {

    private lateinit var skeletonsDao: SkeletonsDao
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        skeletonsDao = db.skeletonsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeSkeletonAndReadIt() {
        val skeletonsEntity = SkeletonsEntity(
            "TEST",
            emptyList()
        )

        skeletonsDao.saveSkeletonsByContext(skeletonsEntity)
        val skeletonsEntityRecovered = skeletonsDao.provideSkeletonsByContext("TEST")
        assert(skeletonsEntityRecovered == skeletonsEntity)
    }
}