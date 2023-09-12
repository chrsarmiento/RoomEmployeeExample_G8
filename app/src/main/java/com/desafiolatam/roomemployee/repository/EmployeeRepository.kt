package com.desafiolatam.roomemployee.repository

import com.desafiolatam.roomemployee.data.EmployeeEntity
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {

    suspend fun getEmployees(): Flow<List<EmployeeEntity>>

    suspend fun addEmployee(employeeEntity: EmployeeEntity)

    suspend fun deleteEmployee(employeeEntity: EmployeeEntity)

    suspend fun updateEmployee(employeeEntity: EmployeeEntity, updateEmployeeEntity: EmployeeEntity)
}