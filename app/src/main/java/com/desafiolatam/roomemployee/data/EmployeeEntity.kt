package com.desafiolatam.roomemployee.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

@Entity(tableName = "employee_table")
@Parcelize
data class EmployeeEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "firstname")val firstName: String,
    @ColumnInfo(name = "lastname")val lastName: String,
    @ColumnInfo(name = "email")val email: String,
    @ColumnInfo(name = "salary")val salary: Int?,
    @Embedded
    val address: @RawValue Address,
    @ColumnInfo(name = "fecha_contrato")val fechaContrato: LocalDateTime
) : Parcelable {
    val createdDateFormatted : String
        get() = fechaContrato.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}

data class Address (
    val location: String,
    val city: String,
)