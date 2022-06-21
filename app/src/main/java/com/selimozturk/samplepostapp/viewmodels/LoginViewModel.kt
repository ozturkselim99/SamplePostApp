package com.selimozturk.samplepostapp.viewmodels

import androidx.lifecycle.ViewModel
import com.selimozturk.samplepostapp.data.repository.SamplePostAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val samplePostAppRepository: SamplePostAppRepository) :
    ViewModel() {

    fun isUserSignedUp(email: String, password: String):Boolean {
        return samplePostAppRepository.isUserSignedUp(email, password)
    }
}