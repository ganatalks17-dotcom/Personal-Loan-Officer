package com.personal.loanofficer.data

import com.personal.loanofficer.data.database.CustomerDao
import com.personal.loanofficer.data.database.CustomerEntity
import kotlinx.coroutines.flow.Flow

class CustomerRepository(
    private val customerDao: CustomerDao
) {

    suspend fun saveCustomer(
        customerName: String,
        mobileNumber: String
    ) {
        val customer = CustomerEntity(
            customerName = customerName,
            mobileNumber = mobileNumber
        )

        customerDao.insertCustomer(customer)
    }

    fun searchCustomers(
        search: String
    ): Flow<List<CustomerEntity>> {
        return customerDao.searchCustomers(search)
    }

    fun getAllCustomers(): Flow<List<CustomerEntity>> {
        return customerDao.getAllCustomers()
    }

    suspend fun deleteCustomer(
        customer: CustomerEntity
    ) {
        customerDao.deleteCustomer(customer)
    }
}
