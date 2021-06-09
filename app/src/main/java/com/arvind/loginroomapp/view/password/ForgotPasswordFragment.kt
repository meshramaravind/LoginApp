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
import com.arvind.loginroomapp.databinding.FragmentForgotPasswordBinding
import com.arvind.loginroomapp.model.LoginStaffUser
import com.arvind.loginroomapp.view.base.BaseFragment
import com.arvind.loginroomapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_forgot_password.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import java.util.regex.Matcher
import java.util.regex.Pattern

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doinits()
    }

    private fun doinits() = with(binding) {
        gettextwathcerForgPassword()
        buttonContinueForgtpassword.setOnClickListener {

            if (!validateUserEmail()) {
                return@setOnClickListener
            } else {
                val direction =
                    ForgotPasswordFragmentDirections.actionForgotpasswordStaffFragmentToChangepasswordStaffFragment()
                it.findNavController().navigate(direction)
            }

        }
    }

    private fun validateUserEmail(): Boolean {

        if (ed_email_forgotpassword.text.toString()
                .isEmpty() or !isValidEmailaddress(ed_email_forgotpassword.text.toString())
        ) {
            tverror_email_forgotpassword.error = tverror_email_forgotpassword.error
            tverror_email_forgotpassword.visibility = View.VISIBLE

            return false
        } else {
            tverror_email_forgotpassword.isEnabled = false
            tverror_email_forgotpassword.visibility = View.GONE
            tverror_email_forgotpassword.error = null
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

    private fun gettextwathcerForgPassword() {
        ed_email_forgotpassword.addTextChangedListener(emailTextWatcher)
    }

    private val emailTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable) {
            validateUserEmail()
        }
    }


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentForgotPasswordBinding.inflate(inflater, container, false)
}