package com.desafiolatam.roomemployee.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafiolatam.roomemployee.data.EmployeeEntity
import com.desafiolatam.roomemployee.databinding.ActivityMainBinding
import com.desafiolatam.roomemployee.view.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: EmployeeViewModel by viewModels()

    @Inject
    lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getEmployeeList()

        binding.fabAddTask.setOnClickListener {
            startActivity(Intent(this,NewEmployeeActivity::class.java))
        }
    }

    private fun getEmployeeList() {
        lifecycleScope.launchWhenResumed {
            viewModel.employeeListStateFlow.collectLatest {
                initRecyclerView(it)
            }
        }
    }

    private fun initRecyclerView(employeeList: List<EmployeeEntity>) {
        adapter = EmployeeAdapter()
        adapter.employeeList = employeeList
        binding.rvEmployee.layoutManager = LinearLayoutManager(this)
        binding.rvEmployee.adapter = adapter
        adapter.onLongClickListener = {

            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setMessage("Estas seguro que deseas eliminar al empleado seleccionado?")
                .setCancelable(false)
                .setPositiveButton("Si") { dialog, id ->
                    // Delete selected note from database
                    deleteEmployee(it)
                }
                .setNegativeButton("Cancelar") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }
    }

    private fun deleteEmployee(employee: EmployeeEntity){
        lifecycleScope.launchWhenCreated {
            viewModel.deleteEmployee(employee)
        }
    }




}