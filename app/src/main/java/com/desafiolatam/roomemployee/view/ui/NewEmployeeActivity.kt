package com.desafiolatam.roomemployee.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.desafiolatam.roomemployee.data.Address
import com.desafiolatam.roomemployee.data.EmployeeEntity
import com.desafiolatam.roomemployee.databinding.ActivityNewEmployeeBinding
import com.desafiolatam.roomemployee.view.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

@AndroidEntryPoint
class NewEmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewEmployeeBinding
    private val viewModel: EmployeeViewModel by viewModels()
    private var day: Int = 0
    private var month: Int = 0
    private var year: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etFechaContrato.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnAddEmployee.setOnClickListener {
            addEmployee()
            finish()
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month+1, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.etFechaContrato.setText("$day/$month/$year")
        this.day = day
        this.month = month
        this.year = year
    }

    private fun addEmployee(){
        lifecycleScope.launch {
            val firstName = binding.etNombre.text.toString()
            val lastName = binding.etApellido.text.toString()
            val email = binding.etEmail.text.toString()
            val salary = binding.etSalary.text.toString()
            val address = binding.etDireccionNombre.text.toString()
            val city = binding.etCiudad.text.toString()
            viewModel.addEmployee(
                EmployeeEntity(
                    id=0,
                    firstName=firstName,
                    lastName=lastName,
                    email=email,
                    salary=salary.toInt(),
                    address = Address(address,city),
                    fechaContrato = LocalDateTime.of(year,month,day,0,0)
                )
            )
        }
        Toast.makeText(this,"Empleado registrado", Toast.LENGTH_SHORT).show()
    }
}