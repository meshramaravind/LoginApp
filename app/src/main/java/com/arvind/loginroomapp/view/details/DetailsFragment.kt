package com.arvind.loginroomapp.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.arvind.loginroomapp.databinding.FragmentStaffDetailsBinding
import com.arvind.loginroomapp.model.LoginStaffUser
import com.arvind.loginroomapp.utils.cleanTextContent
import com.arvind.loginroomapp.utils.indianRupee
import com.arvind.loginroomapp.view.base.BaseFragment
import com.arvind.loginroomapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.content_layout_details.view.*

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentStaffDetailsBinding, LoginViewModel>() {
    private val args: DetailsFragmentArgs by navArgs()
    override val viewModel: LoginViewModel by activityViewModels()
    private lateinit var loginStaffUser: LoginStaffUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginStaffUser = args.loginstaffuser
        getdetailsstaff()

    }

    private fun getdetailsstaff() = with(binding) {
        layoutStaffDetails.tv_name_details.text = loginStaffUser.name
        layoutStaffDetails.tv_designation_details.text = loginStaffUser.designationType
        layoutStaffDetails.tv_salary_details.text =
            indianRupee(loginStaffUser.salary).cleanTextContent
        layoutStaffDetails.tv_createdAt_details.text = loginStaffUser.createdAtDateFormat

        buttonStaffedit.setOnClickListener {
            val direction =
                DetailsFragmentDirections.actionStaffDetailsFragmentToEditStaffFragment(loginStaffUser)
            it.findNavController().navigate(direction)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentStaffDetailsBinding.inflate(inflater, container, false)
}