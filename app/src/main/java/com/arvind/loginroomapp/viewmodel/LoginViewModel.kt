package com.arvind.loginroomapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.arvind.loginroomapp.model.LoginStaffUser
import com.arvind.loginroomapp.repository.LoginStaffRepository
import com.arvind.loginroomapp.storage.datastore.UIModeDataStore
import com.arvind.loginroomapp.utils.DetailState
import com.arvind.loginroomapp.utils.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    application: Application,
    private val loginStaffRepository: LoginStaffRepository
) :
    AndroidViewModel(application) {


    private val _uiState = MutableStateFlow<ViewState>(ViewState.Loading)
    private val _detailState = MutableStateFlow<DetailState>(DetailState.Loading)

    // UI collect from this stateFlow to get the state updates
    val uiState: StateFlow<ViewState> = _uiState
    val detailState: StateFlow<DetailState> = _detailState

    private val uiModeDataStore = UIModeDataStore(application)

    // get ui mode
    val getUIMode = uiModeDataStore.uiMode

    // save ui mode
    fun saveToDataStore(isNightMode: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            uiModeDataStore.saveToDataStore(isNightMode)
        }
    }

    //insert add staff
    fun insertstaff(loginStaffUser: LoginStaffUser) = viewModelScope.launch {
        loginStaffRepository.insert(loginStaffUser)
    }

    //insert login staff
    fun insertlogin(loginStaffUser: LoginStaffUser) = viewModelScope.launch {
        loginStaffRepository.insertlogin(loginStaffUser)
    }

    //update staff
    fun updateloginstaff(loginStaffUser: LoginStaffUser) = viewModelScope.launch {
        loginStaffRepository.update(loginStaffUser)
    }

    //delete staff
    fun deleteloginstaff(loginStaffUser: LoginStaffUser) = viewModelScope.launch {
        loginStaffRepository.delete(loginStaffUser)
    }

    // get login staff by id
    fun getByID(id: Int) = viewModelScope.launch {
        _detailState.value = DetailState.Loading
        loginStaffRepository.getByID(id).collect { result: LoginStaffUser? ->
            if (result != null) {
                _detailState.value = DetailState.Success(result)
            }
        }

    }

    //get all data
    fun getAllloginstaff() = loginStaffRepository.getAllLlginstaff()

    //get login
    fun getAllLogin(email: String, password: String) =
        loginStaffRepository.getLogin(email, password)

    //get login email
    fun getAllLoginEmail(email: String) =
        loginStaffRepository.getLoginEmail(email)


}