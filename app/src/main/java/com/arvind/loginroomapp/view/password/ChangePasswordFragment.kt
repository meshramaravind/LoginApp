package com.arvind.loginroomapp.view.password

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
import com.arvind.loginroomapp.databinding.FragmentChangePasswordBinding
import com.arvind.loginroomapp.model.LoginStaffUser
import com.arvind.loginroomapp.view.base.BaseFragment
import com.arvind.loginroomapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_change_password.*
import kotlinx.android.synthetic.main.fragment_change_password.view.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import java.util.regex.Pattern

@AndroidEntryPoint
class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doinits()
    }

    private fun doinits() = with(binding) {
        gettextwathcerchangepassword()

        buttonSubmitChangepassword.setOnClickListener {

            if (!validateUserPassword() or !validateUserConfirmPassword()) {
                return@setOnClickListener
            } else {
                val direction =
                    ChangePasswordFragmentDirections.actionChangepasswordStaffFragmentToDashboardFragment()
                it.findNavController().navigate(direction)
            }
        }

        tvBacktoLoginChangepassword.setOnClickListener {
            val direction =
                ChangePasswordFragmentDirections.actionChangepasswordStaffFragmentToLoginStaffFragment()
            it.findNavController().navigate(direction)
        }
    }

    private fun validateUserConfirmPassword(): Boolean {

        if (ed_confirmpassword_changepassword.text.toString()
                .isEmpty() or isValidConfimrPassword(ed_confirmpassword_changepassword.text.toString())
        ) {
            tverror_confirmpassword_changepassword.error =
                tverror_confirmpassword_changepassword.error
            tverror_confirmpassword_changepassword.visibility = View.VISIBLE

            return false
        } else {
            tverror_confirmpassword_changepassword.isEnabled = false
            tverror_confirmpassword_changepassword.visibility = View.GONE
            tverror_confirmpassword_changepassword.error = null
        }

        return true

    }

    private fun isValidConfimrPassword(password: String): Boolean {
        val confimrpassword = ed_confirmpassword_changepassword.text.toString()

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

    private fun validateUserPassword(): Boolean {

        if (ed_password_changepassword.text.toString()
                .isEmpty() or isValidPassword(ed_password_changepassword.text.toString())
        ) {
            tverror_password_changepassword.error = tverror_password_changepassword.error
            tverror_password_changepassword.visibility = View.VISIBLE

            return false
        } else {
            tverror_password_changepassword.isEnabled = false
            tverror_password_changepassword.visibility = View.GONE
            tverror_password_changepassword.error = null
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

    private fun gettextwathcerchangepassword() {
        ed_password_changepassword.addTextChangedListener(passwordTextWatcher)

        ed_confirmpassword_changepassword.addTextChangedListener(confirmpasswordTextWatcher)
    }

    private val passwordTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            validateUserPassword()
        }
    }

    private val confirmpasswordTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            validateUserConfirmPassword()
        }
    }

    private fun geChangePasswordContent(): LoginStaffUser = binding.buttonSubmitChangepassword.let {
        val password = it.ed_password_changepassword.text.toString()
        val confirmpassword = it.ed_confirmpassword_changepassword.text.toString()

        return LoginStaffUser("0", "0", 0.0, "0", password, confirmpassword)

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentChangePasswordBinding.inflate(inflater, container, false)
}