package com.selimozturk.samplepostapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.selimozturk.samplepostapp.adapters.PostsAdapter
import com.selimozturk.samplepostapp.databinding.FragmentPostListBinding
import com.selimozturk.samplepostapp.viewmodels.PostListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostListFragment : Fragment() {

    private var _binding: FragmentPostListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PostListViewModel by viewModels()
    private val adapter = PostsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPostListBinding.inflate(inflater, container, false)

        setupPostsRecyclerView()

        adapter.addLoadStateListener { loadState ->
            binding.postLoadingProgressBar.isVisible = loadState.refresh is LoadState.Loading
            binding.retryButton.isVisible = loadState.refresh !is LoadState.NotLoading
            binding.noInternetText.isVisible = loadState.refresh is LoadState.Error
        }

        adapter.onItemClicked = {
            val action = PostListFragmentDirections.actionPostListFragmentToPostDetailFragment(it)
            findNavController().navigate(action)
        }

        binding.retryButton.setOnClickListener {
            adapter.retry()
        }

        submitDataToPostsAdapter()

        return binding.root
    }

    private fun submitDataToPostsAdapter() {
        lifecycleScope.launch {
            viewModel.getPosts()
                .observe(viewLifecycleOwner) {posts->
                    posts?.let {
                        adapter.submitData(lifecycle, posts)
                    }
                }
        }
    }

    private fun setupPostsRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.postsRW.layoutManager = linearLayoutManager
        binding.postsRW.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}