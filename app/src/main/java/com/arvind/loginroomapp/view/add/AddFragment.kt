package com.arvind.loginroomapp.view.add

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.arvind.loginroomapp.R
import com.arvind.loginroomapp.databinding.FragmentAddStaffBinding
import com.arvind.loginroomapp.model.LoginStaffUser
import com.arvind.loginroomapp.utils.Constants
import com.arvind.loginroomapp.utils.parseDouble
import com.arvind.loginroomapp.view.base.BaseFragment
import com.arvind.loginroomapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.content_add_layout.*
import kotlinx.android.synthetic.main.content_add_layout.view.*
import kotlinx.android.synthetic.main.fragment_dashboard.*


@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddStaffBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doinits()
    }

    private fun doinits() {
        val designationTypeAdapter =
            ArrayAdapter(
                requireContext(),
                R.layout.item_autocomplete_layout,
                Constants.designationType
            )
        with(binding) {
            addStaffLayout.et_deignationType.setAdapter(designationTypeAdapter)

            gettextwathceradd()

            buttonSubmitAdd.setOnClickListener {
                binding.addStaffLayout.apply {
                    val (name, designationType, salary) = getStaffContent()

                    if (!validateUserName() or !validateUserdesignationType() or !validateUserSalary()) {
                        return@apply
                    } else {
                        viewModel.insertstaff(getStaffContent()).run {
                            toast(getString(R.string.success_addstaff_saved))
                            findNavController().navigate(
                                R.id.action_addStaffFragment_to_dashboardFragment
                            )
                        }
                    }

                }
            }

        }
    }


    private fun gettextwathceradd() {
        ed_name_add.addTextChangedListener(nameTextWatcher)

        et_deignationType.addTextChangedListener(designationTypeTextWatcher)

        ed_salary_add.addTextChangedListener(salaryTextWatcher)
    }

    private fun validateUserSalary(): Boolean {
        val salary = parseDouble(binding.addStaffLayout.ed_salary_add.text.toString())
        if (salary.isNaN()) {
            tverror_salary_add.error = tverror_salary_add.error
            tverror_salary_add.visibility = View.VISIBLE

            return false
        } else {
            tverror_salary_add.isEnabled = false
            tverror_salary_add.visibility = View.GONE
            tverror_salary_add.error = null
        }

        return true
    }

    private fun validateUserdesignationType(): Boolean {

        if (et_deignationType.text.toString().isEmpty()) {
            tverror_designation_add.error = tverror_designation_add.error
            tverror_designation_add.visibility = View.VISIBLE

            return false
        } else {
            tverror_designation_add.isEnabled = false
            tverror_designation_add.visibility = View.GONE
            tverror_designation_add.error = null
        }

        return true
    }

    private fun validateUserName(): Boolean {
        if (ed_name_add.text.toString().isEmpty()) {
            tverror_name_add.error = tverror_name_add.error
            tverror_name_add.visibility = View.VISIBLE

            return false
        } else {
            tverror_name_add.isEnabled = false
            tverror_name_add.visibility = View.GONE
            tverror_name_add.error = null
        }

        return true
    }

    private val nameTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            validateUserName()
        }
    }

    private val designationTypeTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            validateUserdesignationType()
        }
    }

    private val salaryTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            validateUserSalary()
        }
    }

    private fun getStaffContent(): LoginStaffUser = binding.addStaffLayout.let {
        val name = it.ed_name_add.text.toString()
        val desigantionType = it.et_deignationType.text.toString()
        val salary = parseDouble(it.ed_salary_add.text.toString())
        val date = it.tvDate_salaryadd.text.toString().trim()

        return LoginStaffUser(name, desigantionType, salary, "0", "0", "0", date)

    }


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAddStaffBinding.inflate(inflater, container, false)
}