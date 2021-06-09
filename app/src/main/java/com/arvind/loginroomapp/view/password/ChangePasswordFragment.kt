package com.arvind.loginroomapp.view.password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.arvind.loginroomapp.databinding.FragmentChangePasswordBinding
import com.arvind.loginroomapp.view.base.BaseFragment
import com.arvind.loginroomapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doinits()
    }

    private fun doinits() = with(binding) {
        tvBacktoLoginChangepassword.setOnClickListener {
            val direction =
                ChangePasswordFragmentDirections.actionChangepasswordStaffFragmentToLoginStaffFragment()
            it.findNavController().navigate(direction)
        }

        buttonSubmitChangepassword.setOnClickListener {
            val direction =
                ChangePasswordFragmentDirections.actionChangepasswordStaffFragmentToDashboardFragment()
            it.findNavController().navigate(direction)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentChangePasswordBinding.inflate(inflater, container, false)
}