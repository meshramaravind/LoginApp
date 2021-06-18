package com.arvind.loginroomapp.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arvind.loginroomapp.R
import com.arvind.loginroomapp.databinding.FragmentStaffDetailsBinding
import com.arvind.loginroomapp.model.LoginStaffUser
import com.arvind.loginroomapp.utils.DetailState
import com.arvind.loginroomapp.utils.cleanTextContent
import com.arvind.loginroomapp.utils.indianRupee
import com.arvind.loginroomapp.view.base.BaseFragment
import com.arvind.loginroomapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.content_layout_details.view.*
import kotlinx.android.synthetic.main.fragment_staff_details.view.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentStaffDetailsBinding, LoginViewModel>() {
    private val args: DetailsFragmentArgs by navArgs()
    override val viewModel: LoginViewModel by activityViewModels()
    private lateinit var loginStaffUser: LoginStaffUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginStaffUser = args.loginstaffuser
        getdetailsstaff()
        getLoginStaffdetails(loginStaffUser.id)

        binding.buttonStaffedit.setOnClickListener {
            val direction =
                DetailsFragmentDirections.actionStaffDetailsFragmentToEditStaffFragment(
                    loginStaffUser
                )
            it.findNavController().navigate(direction)
        }

    }

    private fun getLoginStaffdetails(id: Int) {
        viewModel.getByID(id)
    }

    private fun getdetailsstaff() = lifecycleScope.launchWhenCreated {

        viewModel.detailState.collect { detailState ->
            when (detailState) {
                DetailState.Loading -> {
                }
                is DetailState.Success -> {
                    onDetailsLoaded(detailState.loginStaffUser)
                }
                is DetailState.Error -> {
                    toast("Error something")
                }
                DetailState.Empty -> {
                    findNavController().navigateUp()
                }
            }
        }
    }

    private fun onDetailsLoaded(loginStaffUser: LoginStaffUser) = with(binding.layoutStaffDetails) {

        tv_name_details.text = loginStaffUser.name
        tv_designation_details.text = loginStaffUser.designationType
        tv_salary_details.text =
            indianRupee(loginStaffUser.salary).cleanTextContent
        tv_createdAt_details.text = loginStaffUser.createdAtDateFormat


    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentStaffDetailsBinding.inflate(inflater, container, false)
}