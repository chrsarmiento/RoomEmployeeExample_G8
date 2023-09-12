package com.desafiolatam.roomemployee.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desafiolatam.roomemployee.data.EmployeeEntity
import com.desafiolatam.roomemployee.repository.EmployeeRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val repository: EmployeeRepositoryImp
    ) : ViewModel() {

    private val _data: MutableStateFlow<List<EmployeeEntity>> = MutableStateFlow(emptyList())
    val employeeListStateFlow: StateFlow<List<EmployeeEntity>> = _data.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getEmployees().collectLatest {
                _data.value = it
            }
        }
    }


    suspend fun addEmployee(employeeEntity: EmployeeEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEmployee(employeeEntity)
        }
    }

    suspend fun deleteEmployee(employeeEntity: EmployeeEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEmployee(employeeEntity)
        }
    }


}