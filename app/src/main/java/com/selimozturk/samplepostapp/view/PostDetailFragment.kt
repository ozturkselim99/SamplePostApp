package com.selimozturk.samplepostapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.selimozturk.samplepostapp.adapters.CommentsAdapter
import com.selimozturk.samplepostapp.databinding.FragmentPostDetailBinding
import com.selimozturk.samplepostapp.viewmodels.PostDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : Fragment() {

    private var _binding: FragmentPostDetailBinding? = null
    private val binding get() = _binding!!
    private val args: PostDetailFragmentArgs by navArgs()
    private val viewModel: PostDetailViewModel by viewModels()
    private val adapter = CommentsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        setupRecyclerView()
        viewModel.getPostComments(args.postDomain.id.toString())
        viewModel.getUserInfo(args.postDomain.userId)
        binding.postArgs = args.postDomain
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.comment.observe(viewLifecycleOwner) {comments->
            adapter.items = comments
        }
        viewModel.user.observe(viewLifecycleOwner) {user->
            binding.name.text = user.name
            binding.username.text = user.username
        }
        viewModel.userLoading.observe(viewLifecycleOwner) {
            binding.userLoadingProgressBar.isVisible = it
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.commentsRW.layoutManager = layoutManager
        binding.commentsRW.adapter = adapter
    }

}