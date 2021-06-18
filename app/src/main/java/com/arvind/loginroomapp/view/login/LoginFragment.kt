package com.arvind.loginroomapp.view.login

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
import com.arvind.loginroomapp.databinding.FragmentLoginBinding
import com.arvind.loginroomapp.model.LoginStaffUser
import com.arvind.loginroomapp.view.base.BaseFragment
import com.arvind.loginroomapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.content_add_layout.*
import kotlinx.android.synthetic.main.content_add_layout.view.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import java.util.regex.Matcher
import java.util.regex.Pattern


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by activityViewModels()
    lateinit var strEmail: String
    lateinit var strPassword: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doinits()
    }

    private fun doinits() = with(binding) {
        gettextwathcerlogin()
        buttonLogin.setOnClickListener {
            strEmail = edEmailLogin.text.toString().trim()
            strPassword = edPasswordLogin.text.toString().trim()
            if (!validateUserEmail() or !validateUserPassword()) {
                return@setOnClickListener
            } else {
                viewModel.getAllLogin(strEmail, strPassword).run {
                    toast(getString(R.string.success_login))
                    findNavController().navigate(
                        R.id.action_loginSatffFragment_to_dashboardFragment
                    )
                }


            }

        }

        buttonRegister.setOnClickListener {
            val direction =
                LoginFragmentDirections.actionLoginStaffFragmentToRegisterStaffFragment()
            it.findNavController().navigate(direction)
        }

        tvForgotpasswordViewlogin.setOnClickListener {
            val direction =
                LoginFragmentDirections.actionLoginStaffFragmentToForgotpasswordStaffFragment()
            it
                .findNavController().navigate(direction)
        }
    }

    private fun validateUserPassword(): Boolean {

        if (ed_password_login.text.toString()
                .isEmpty() or !isValidPassword(ed_password_login.text.toString())
        ) {
            tverror_password_viewlogin.error = tverror_password_viewlogin.error
            tverror_password_viewlogin.visibility = View.VISIBLE

            return false
        } else {
            tverror_password_viewlogin.isEnabled = false
            tverror_password_viewlogin.visibility = View.GONE
            tverror_password_viewlogin.error = null
        }

        return true

    }

    private fun isValidPassword(password: String): Boolean {
        val regex = ("^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$")

        val p = Pattern.compile(regex)
        val m = p.matcher(password)
        return m.matches()

    }

    private fun validateUserEmail(): Boolean {

        if (ed_email_login.text.toString()
                .isEmpty() or !isValidEmailaddress(ed_email_login.text.toString())
        ) {
            tverror_email_viewlogin.error = tverror_email_viewlogin.error
            tverror_email_viewlogin.visibility = View.VISIBLE

            return false
        } else {
            tverror_email_viewlogin.isEnabled = false
            tverror_email_viewlogin.visibility = View.GONE
            tverror_email_viewlogin.error = null
        }

        return true

    }

    private fun isValidEmailaddress(email: String): Boolean {

        val emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$"

        val pat = Pattern.compile(emailRegex)
        return pat.matcher(email).matches()

    }

    private fun gettextwathcerlogin() {
        ed_email_login.addTextChangedListener(emailTextWatcher)

        ed_password_login.addTextChangedListener(passwordTextWatcher)
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

    private fun getLoginContent(): LoginStaffUser = binding.mainlayoutLogin.let {
        val email = it.ed_email_login.text.toString()
        val password = it.ed_password_login.text.toString()

        return LoginStaffUser("0", "0", 0.0, email, password, "0", "0")

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)
}