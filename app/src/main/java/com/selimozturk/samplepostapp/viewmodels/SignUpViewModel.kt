package com.selimozturk.samplepostapp.viewmodels

import androidx.lifecycle.ViewModel
import com.selimozturk.samplepostapp.data.repository.SamplePostAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val samplePostAppRepository: SamplePostAppRepository) :
    ViewModel() {

    fun signUp(email: String, password: String) {
        samplePostAppRepository.signUp(email, password)
    }

}