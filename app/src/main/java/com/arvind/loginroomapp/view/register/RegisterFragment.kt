package com.arvind.loginroomapp.view.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.arvind.loginroomapp.R
import com.arvind.loginroomapp.databinding.FragmentRegisterBinding
import com.arvind.loginroomapp.model.LoginStaffUser
import com.arvind.loginroomapp.view.base.BaseFragment
import com.arvind.loginroomapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.ed_email_login
import kotlinx.android.synthetic.main.fragment_login.ed_password_login
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import java.util.regex.Matcher
import java.util.regex.Pattern

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doinits()
    }

    private fun doinits() = with(binding) {
        gettextwathcerregister()
        buttonRegisterView.setOnClickListener {
            if (!validateUserName() or !validateUserEmail() or !validateUserPassword()) {
                return@setOnClickListener
            } else {
                viewModel.insertstaff(getRegisterContent()).run {
                    toast(getString(R.string.success_addstaff_saved))
                    findNavController().navigate(
                        R.id.action_loginSatffFragment_to_dashboardFragment
                    )
                }
            }
        }

        buttonLoginbacktoRegister.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun validateUserPassword(): Boolean {

        if (ed_password_register.text.toString()
                .isEmpty() or isValidPassword(ed_password_register.text.toString())
        ) {
            tverror_password_register.error = tverror_password_register.error
            tverror_password_register.visibility = View.VISIBLE

            return false
        } else {
            tverror_password_register.isEnabled = false
            tverror_password_register.visibility = View.GONE
            tverror_password_register.error = null
        }

        return true

    }

    private fun isValidPassword(password: String): Boolean {

        val regex = ("^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$")

        val p = Pattern.compile(regex)
        if (password == null) {
            return false
        }
        val m = p.matcher(password)
        return m.matches()

    }

    private fun validateUserEmail(): Boolean {

        if (ed_email_register.text.toString()
                .isEmpty() or !isValidEmailaddress(ed_email_register.text.toString())
        ) {
            tverror_email_register.error = tverror_email_register.error
            tverror_email_register.visibility = View.VISIBLE

            return false
        } else {
            tverror_email_register.isEnabled = false
            tverror_email_register.visibility = View.GONE
            tverror_email_register.error = null
        }

        return true

    }

    private fun isValidEmailaddress(email: String): Boolean {

        val pattern: Pattern
        val matcher: Matcher
        val EMAIL_PATTERN =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        pattern = Pattern.compile(EMAIL_PATTERN)
        matcher = pattern.matcher(email)
        return matcher.matches()

    }

    private fun validateUserName(): Boolean {

        if (ed_name_register.text.toString().isEmpty()) {
            tverror_name_register.error = tverror_name_register.error
            tverror_name_register.visibility = View.VISIBLE

            return false
        } else {
            tverror_name_register.isEnabled = false
            tverror_name_register.visibility = View.GONE
            tverror_name_register.error = null
        }

        return true

    }

    private fun gettextwathcerregister() {
        ed_name_register.addTextChangedListener(nameTextWatcher)
        ed_email_register.addTextChangedListener(emailTextWatcher)
        ed_password_register.addTextChangedListener(passwordTextWatcher)
    }

    private val nameTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            validateUserName()
        }
    }

    private val emailTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            validateUserEmail()
        }
    }

    private val passwordTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            validateUserPassword()
        }
    }

    private fun getRegisterContent(): LoginStaffUser = binding.buttonRegisterView.let {
        val name = it.ed_name_register.text.toString()
        val email = it.ed_email_register.text.toString()
        val password = it.ed_password_register.text.toString()

        return LoginStaffUser(name, "0", 0.0, email, password, "0", "0")


    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)
}