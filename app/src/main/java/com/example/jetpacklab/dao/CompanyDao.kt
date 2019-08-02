package com.example.jetpacklab.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.jetpacklab.entity.Company

@Dao
interface CompanyDao {
    @Query("select * from company")
    fun getAllCompany(): List<Company>

    @Query("select * from company where id = :id")
    fun getCompany(id: Int): Company

    @Query("select max(id) num from company ")
    fun getLastId(): Int

    @Insert
    fun insertCompany(company: Company)

    @Update
    fun udateCompany(company: Company)

    @Query("delete from company where id > 2")
    fun deletePartOfCompany()
}