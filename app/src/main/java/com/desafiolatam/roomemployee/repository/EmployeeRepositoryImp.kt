package com.desafiolatam.roomemployee.repository

import com.desafiolatam.roomemployee.data.EmployeeDao
import com.desafiolatam.roomemployee.data.EmployeeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmployeeRepositoryImp @Inject constructor(
    private val employeeDao: EmployeeDao
) : EmployeeRepository {

    override suspend fun getEmployees(): Flow<List<EmployeeEntity>> = employeeDao.getEmployees()

    override suspend fun addEmployee(employeeEntity: EmployeeEntity) = employeeDao.addEmployee(employeeEntity)

    override suspend fun deleteEmployee(employeeEntity: EmployeeEntity) = employeeDao.deleteEmployee(employeeEntity.id)

    override suspend fun updateEmployee(employeeEntity: EmployeeEntity, updateEmployeeEntity: EmployeeEntity)
        = employeeDao.updateEmployee(updateEmployeeEntity.firstName, employeeEntity.id)


}