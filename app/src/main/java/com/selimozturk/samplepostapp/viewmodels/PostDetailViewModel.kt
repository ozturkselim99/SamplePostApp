package com.selimozturk.samplepostapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selimozturk.samplepostapp.data.repository.SamplePostAppRepository
import com.selimozturk.samplepostapp.domain.model.CommentDomain
import com.selimozturk.samplepostapp.domain.model.UserDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(private val samplePostAppRepository: SamplePostAppRepository) : ViewModel() {

    private val _user = MutableLiveData<UserDomain>()
    val user: LiveData<UserDomain> get() = _user

    private val _comments = MutableLiveData<List<CommentDomain>>()
    val comment: LiveData<List<CommentDomain>> get() = _comments

    fun getUserInfo(id:Int){
        viewModelScope.launch {
            _user.postValue(samplePostAppRepository.getUserInfo(id))
        }
    }

    fun getPostComments(postId: String){
        viewModelScope.launch {
            _comments.postValue(samplePostAppRepository.getPostComments(postId))
        }
    }
}