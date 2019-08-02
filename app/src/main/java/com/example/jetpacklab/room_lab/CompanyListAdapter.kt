package com.example.jetpacklab.room_lab

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpacklab.R
import com.example.jetpacklab.databinding.CompanyListBinding
import com.example.jetpacklab.entity.Company

class CompanyListAdapter : RecyclerView.Adapter<CompanyListAdapter.RoomListViewHolder>() {

    private var mCompanyList: List<Company>? = null

    open class RoomListViewHolder(val binding: CompanyListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomListViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.company_list,
            parent,
            false
        ) as CompanyListBinding
        return RoomListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomListViewHolder, position: Int) {
        holder.binding.company = requireNotNull(mCompanyList)[position]
    }

    override fun getItemCount(): Int {
        return if (mCompanyList == null) 0 else requireNotNull(mCompanyList).size
    }

    fun setCompanyList(companyList: List<Company>) {

        if (this.mCompanyList == null) {
            this.mCompanyList = companyList
            notifyItemRangeInserted(0, companyList.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return requireNotNull(this@CompanyListAdapter.mCompanyList).size
                }

                override fun getNewListSize(): Int {
                    return companyList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return requireNotNull(this@CompanyListAdapter.mCompanyList)[oldItemPosition].id == companyList[newItemPosition].id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val company = companyList[newItemPosition]
                    val old = companyList[oldItemPosition]
                    return company.id == old.id
                }
            })
            this.mCompanyList = companyList

            result.dispatchUpdatesTo(this)
        }
    }
}