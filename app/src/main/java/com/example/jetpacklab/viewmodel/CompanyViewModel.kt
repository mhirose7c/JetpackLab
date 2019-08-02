package com.example.jetpacklab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpacklab.dao.CompanyDao
import com.example.jetpacklab.entity.Company
import kotlinx.coroutines.*

class CompanyViewModel : ViewModel() {

    private var mCompanies: MutableLiveData<List<Company>> = MutableLiveData()
    private var mDao: CompanyDao? = null

    fun getCompanies(dao: CompanyDao): LiveData<List<Company>> {
        mDao = dao
        loadCompanies()
        return mCompanies
    }

    private fun loadCompanies() = GlobalScope.launch(Dispatchers.Main) {
        // DBとの通信はBackgroundで実行
        async(Dispatchers.Default) {
            mCompanies.postValue(requireNotNull(mDao).getAllCompany())
        }
    }

    fun addCompany(companyName: String): Boolean {
        insertCompany(companyName)
        return true
    }

    private fun insertCompany(companyName: String) = GlobalScope.launch(Dispatchers.Main) {
        runBlocking(Dispatchers.Default) {
            val max = requireNotNull(mDao).getLastId()
            val newCompany = Company(max.plus(1), companyName)
            requireNotNull(mDao).insertCompany(newCompany)
        }
    }

}