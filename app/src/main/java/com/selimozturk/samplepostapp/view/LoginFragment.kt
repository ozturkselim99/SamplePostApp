package com.selimozturk.samplepostapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.selimozturk.samplepostapp.R
import com.selimozturk.samplepostapp.databinding.FragmentLoginBinding
import com.selimozturk.samplepostapp.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.loginButton.setOnClickListener {
            if (binding.editTextPersonEmail.text.isNotEmpty() && binding.editTextPersonPassword.text.isNotEmpty()) {
                if (viewModel.isUserSignedUp(
                        binding.editTextPersonEmail.text.toString(),
                        binding.editTextPersonPassword.text.toString()
                    )
                ) {
                    findNavController().navigate(R.id.action_loginFragment_to_postListFragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Password or email incorrect",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(requireContext(), "All enter fields", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

}