package com.personal.loanofficer.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: CustomerEntity)

    @Query(
        "SELECT * FROM customers " +
        "WHERE customerName LIKE '%' || :search || '%' " +
        "OR mobileNumber LIKE '%' || :search || '%' " +
        "ORDER BY createdAt DESC"
    )
    fun searchCustomers(search: String): Flow<List<CustomerEntity>>

    @Query("SELECT * FROM customers ORDER BY createdAt DESC")
    fun getAllCustomers(): Flow<List<CustomerEntity>>

    @Delete
    suspend fun deleteCustomer(customer: CustomerEntity)
}
