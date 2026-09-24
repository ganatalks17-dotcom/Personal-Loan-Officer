package com.personal.loanofficer.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CallbackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCallback(callback: CallbackEntity)

    @Query("SELECT * FROM callbacks ORDER BY createdAt DESC")
    fun getAllCallbacks(): Flow<List<CallbackEntity>>

    @Query(
        "SELECT * FROM callbacks " +
        "WHERE customerName LIKE '%' || :search || '%' " +
        "OR mobileNumber LIKE '%' || :search || '%' " +
        "ORDER BY createdAt DESC"
    )
    fun searchCallbacks(search: String): Flow<List<CallbackEntity>>

    @Query(
        "UPDATE callbacks " +
        "SET status = :status " +
        "WHERE id = :callbackId"
    )
    suspend fun updateStatus(
        callbackId: Int,
        status: String
    )

    @Delete
    suspend fun deleteCallback(callback: CallbackEntity)
}
