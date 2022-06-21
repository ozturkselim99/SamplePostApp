package com.selimozturk.samplepostapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.selimozturk.samplepostapp.R
import com.selimozturk.samplepostapp.databinding.FragmentSignUpBinding
import com.selimozturk.samplepostapp.util.Constants
import com.selimozturk.samplepostapp.viewmodels.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private var emailIsValid: Boolean = false
    private var nameIsValid: Boolean = false
    private var surnameIsValid: Boolean = false
    private var ageIsValid: Boolean = false
    private var telephoneIsValid: Boolean = false
    private var passwordIsValid: Boolean = false
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        nameControl()
        surnameControl()
        ageControl()
        emailControl()
        telephoneControl()
        passwordControl()

        signUp()

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        return binding.root

    }

    private fun signUp() {
        binding.signUpButton.setOnClickListener {
            if (emailIsValid && nameIsValid && surnameIsValid && ageIsValid && telephoneIsValid && passwordIsValid) {
                viewModel.signUp(
                    binding.editTextPersonEmail.text.toString(),
                    binding.editTextPassword.text.toString()
                )
                Toast.makeText(requireContext(), "Sign up success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "All components is not valid", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun emailControl() {
        binding.editTextPersonEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.isNotEmpty() && binding.editTextPersonEmail.text.toString().trim()
                        .matches(Constants.EMAIL_PATTERN.toRegex())
                ) {
                    binding.editTextPersonEmail.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_baseline_check_24,
                        0
                    )
                    emailIsValid = true
                } else {
                    binding.editTextPersonEmail.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        null,
                        null
                    )
                    emailIsValid = false
                }
            }
        })
    }

    private fun nameControl() {
        binding.editTextPersonName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {
                    binding.editTextPersonName.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_baseline_check_24,
                        0
                    )
                    nameIsValid = true
                } else {
                    binding.editTextPersonName.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        null,
                        null
                    )
                    nameIsValid = false
                }
            }

        })
    }

    private fun surnameControl() {
        binding.editTextPersonSurname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {
                    binding.editTextPersonSurname.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_baseline_check_24,
                        0
                    )
                    surnameIsValid = true
                } else {
                    binding.editTextPersonSurname.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        null,
                        null
                    )
                    surnameIsValid = false
                }
            }

        })
    }

    private fun ageControl() {
        binding.editTextPersonAge.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty()) {
                    binding.editTextPersonAge.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_baseline_check_24,
                        0
                    )
                    ageIsValid = true
                } else {
                    binding.editTextPersonAge.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        null,
                        null
                    )
                    ageIsValid = false
                }
            }

        })
    }

    private fun telephoneControl() {
        binding.editTextTelephone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty() && binding.editTextTelephone.text.toString().trim()
                        .matches(Constants.TELEPHONE_PATTERN.toRegex())
                ) {
                    binding.editTextTelephone.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_baseline_check_24,
                        0
                    )
                    telephoneIsValid = true
                } else {
                    binding.editTextTelephone.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        null,
                        null
                    )
                    telephoneIsValid = false
                }
            }
        })
    }

    private fun passwordControl() {
        binding.editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty() && s.length >= 6) {
                    binding.editTextPassword.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_baseline_check_24,
                        0
                    )
                    passwordIsValid = true
                } else {
                    binding.editTextPassword.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        null,
                        null
                    )
                    passwordIsValid = false
                }
            }
        })
    }

}