package com.example.jetpacklab.room_lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.jetpacklab.R
import com.example.jetpacklab.dao.CompanyDao
import com.example.jetpacklab.database.BaseDatabase
import com.example.jetpacklab.databinding.FragmentRoomLabMainBinding
import com.example.jetpacklab.viewmodel.CompanyViewModel


class RoomLabMainFragment : Fragment() {

    private var mDao: CompanyDao? = null
    private var mCompanyAdapter: CompanyListAdapter? = null
    private var mBinding: FragmentRoomLabMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_lab_main, container, false)
        mCompanyAdapter = CompanyListAdapter()
        requireNotNull(mBinding).companyList.adapter = mCompanyAdapter
        requireNotNull(mBinding).isLoading = true
        return requireNotNull(mBinding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDao = BaseDatabase.getInstance(requireNotNull(this.context)).companyDao()
        val viewModel = ViewModelProviders.of(this).get(CompanyViewModel::class.java)
        observeViewModel(viewModel)

        requireNotNull(mBinding).addButton.setOnClickListener {
            val companyName = requireNotNull(mBinding).addCompanyText.text.toString()
            if (companyName.isEmpty()) {
                requireNotNull(mBinding).addCompanyText.error = "入力してください"
                return@setOnClickListener
            } else {
                viewModel.addCompany(companyName)
                observeViewModel(viewModel)
            }
        }
    }

    private fun observeViewModel(viewModel: CompanyViewModel) {
        viewModel.getCompanies(requireNotNull(mDao)).observe(this, Observer { companies ->
            if (companies != null) {
                requireNotNull(mBinding).isLoading = false
                mCompanyAdapter!!.setCompanyList(companies)
            }
        })
    }
}
