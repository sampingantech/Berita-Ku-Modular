package com.anangkur.beritaku.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anangkur.beritaku.local.model.ArticleCached

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: ArticleCached)

    @Query("DELETE FROM ArticleCached WHERE category = :category")
    suspend fun deleteByCategory(category: String)

    @Query("SELECT * FROM ArticleCached WHERE category = :category")
    fun getAllDataByCategory(category: String): LiveData<List<ArticleCached>>
}