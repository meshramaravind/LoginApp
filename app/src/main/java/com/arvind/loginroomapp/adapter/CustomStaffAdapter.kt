package com.arvind.loginroomapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arvind.loginroomapp.databinding.ItemsStaffBinding
import com.arvind.loginroomapp.model.LoginStaffUser
import com.arvind.loginroomapp.utils.indianRupee
import com.arvind.loginroomapp.view.dashboard.DashboardFragmentDirections

class CustomStaffAdapter : RecyclerView.Adapter<CustomStaffAdapter.StaffViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffViewHolder {
        return StaffViewHolder(
            ItemsStaffBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomStaffAdapter.StaffViewHolder, position: Int) {
        val loginStaffUser = differ.currentList[position]
        holder.bind(loginStaffUser)
    }


    inner class StaffViewHolder(private val itemsStaffBinding: ItemsStaffBinding) :
        RecyclerView.ViewHolder(itemsStaffBinding.root) {
        fun bind(loginStaffUser: LoginStaffUser) {
            itemsStaffBinding.apply {
                itemsStaffBinding.loginstaff = loginStaffUser
                itemsStaffBinding.executePendingBindings()

                tvAmount.text = "+ ".plus(indianRupee(loginStaffUser.salary))

                root.setOnClickListener { v ->
                    val direction = DashboardFragmentDirections
                        .actionDashboardFragmentToDetailsStaffFragment(loginStaffUser)
                    v.findNavController().navigate(direction)
                }
            }
        }

    }

    private val differCallback =
        object : DiffUtil.ItemCallback<LoginStaffUser>() {
            override fun areItemsTheSame(
                oldItem: LoginStaffUser,
                newItem: LoginStaffUser
            ): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.name == newItem.name &&
                        oldItem.designationType == newItem.designationType &&
                        oldItem.salary == newItem.salary
            }

            override fun areContentsTheSame(
                oldItem: LoginStaffUser,
                newItem: LoginStaffUser
            ): Boolean {
                return oldItem == newItem
            }

        }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}