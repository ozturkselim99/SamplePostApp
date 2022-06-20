package com.selimozturk.samplepostapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.selimozturk.samplepostapp.data.remote.entities.postListToPostDomainDataList
import com.selimozturk.samplepostapp.data.repository.SamplePostAppRepository
import com.selimozturk.samplepostapp.domain.model.PostDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(private val samplePostAppRepository: SamplePostAppRepository) : ViewModel() {
    fun getPosts(): LiveData<PagingData<PostDomain>> {
        return samplePostAppRepository.getPosts().postListToPostDomainDataList().cachedIn(viewModelScope)
    }
}