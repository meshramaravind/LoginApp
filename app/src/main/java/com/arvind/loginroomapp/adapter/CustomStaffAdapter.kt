package com.arvind.loginroomapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arvind.loginroomapp.databinding.ItemsStaffBinding
import com.arvind.loginroomapp.model.LoginUser
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
        fun bind(loginUser: LoginUser) {
            itemsStaffBinding.apply {
                itemsStaffBinding.loginstaff = loginUser
                itemsStaffBinding.executePendingBindings()

                tvAmount.text = "+ ".plus(indianRupee(loginUser.salary))

                root.setOnClickListener { v ->
                    val direction = DashboardFragmentDirections
                        .actionDashboardFragmentToDetailsStaffFragment(loginUser)
                    v.findNavController().navigate(direction)
                }
            }
        }

    }

    private val differCallback =
        object : DiffUtil.ItemCallback<LoginUser>() {
            override fun areItemsTheSame(
                oldItem: LoginUser,
                newItem: LoginUser
            ): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.name == newItem.name &&
                        oldItem.designationType == newItem.designationType &&
                        oldItem.salary == newItem.salary
            }

            override fun areContentsTheSame(
                oldItem: LoginUser,
                newItem: LoginUser
            ): Boolean {
                return oldItem == newItem
            }

        }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}