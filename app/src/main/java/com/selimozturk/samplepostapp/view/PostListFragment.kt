package com.selimozturk.samplepostapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        setupCharactersRecyclerView()
        adapter.addLoadStateListener { loadStates ->
            binding.postLoadingProgressBar.visibility =
                if (loadStates.refresh is LoadState.Loading) View.VISIBLE else View.GONE
        }
        adapter.onItemClicked={
            val action=PostListFragmentDirections.actionPostListFragmentToPostDetailFragment(it.id)
            findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitDataToCharactersAdapter()
    }

    private fun submitDataToCharactersAdapter() {
        lifecycleScope.launch {
            viewModel.getPosts()
                .observe(viewLifecycleOwner) {
                    it?.let {
                        adapter.submitData(lifecycle, it)
                    }
                }
        }
    }

    private fun setupCharactersRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.postsRW.layoutManager = linearLayoutManager
        binding.postsRW.adapter = adapter
    }

}