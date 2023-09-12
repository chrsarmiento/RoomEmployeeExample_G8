package com.desafiolatam.roomemployee.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employee_table")
    fun getEmployees(): Flow<List<EmployeeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEmployee(employeeEntity: EmployeeEntity)

    @Query("DELETE FROM employee_table WHERE id= :employeeId")
    suspend fun deleteEmployee(employeeId: Int)

    @Query("UPDATE employee_table SET firstname= :newFirstName WHERE id= :employeeId")
    suspend fun updateEmployee(newFirstName: String, employeeId: Int)


}