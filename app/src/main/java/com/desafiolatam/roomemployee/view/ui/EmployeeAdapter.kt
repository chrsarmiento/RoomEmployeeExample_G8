package com.desafiolatam.roomemployee.view.ui

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.desafiolatam.roomemployee.data.EmployeeEntity
import com.desafiolatam.roomemployee.databinding.ItemEmployeeBinding
import java.text.DecimalFormat
import kotlin.coroutines.coroutineContext

class EmployeeAdapter :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private lateinit var binding: ItemEmployeeBinding
    lateinit var employeeList: List<EmployeeEntity>

    lateinit var onLongClickListener: ((EmployeeEntity) -> Unit)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmployeeAdapter.EmployeeViewHolder {
        binding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.onBind(employeeList[position])
    }

    override fun getItemCount(): Int = employeeList.size

    inner class EmployeeViewHolder(binding: ItemEmployeeBinding) : ViewHolder(binding.root) {
        fun onBind(employeeEntity: EmployeeEntity){

            val formato: DecimalFormat = DecimalFormat("$#,###")
            binding.run {
                tvNameEmployee.text = employeeEntity.firstName + " " + employeeEntity.lastName
                tvEmail.text = "Email: " + employeeEntity.email
                tvSalary.text = "Sueldo: " + formato.format(employeeEntity.salary)
                tvAddress.text = "Dirección:" + employeeEntity.address.location + ", " + employeeEntity.address.city
                tvFechaContrato.text = "Fecha contrato: " + employeeEntity.createdDateFormatted

                clItem.setOnLongClickListener {
                    onLongClickListener.invoke(employeeEntity)
                    false
                }
            }
        }
    }
}