package com.selimozturk.samplepostapp.view

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.selimozturk.samplepostapp.R


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Handler(Looper.getMainLooper()).postDelayed({
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }, 1500)
            }
            else {
                Log.i("Permission", "No Camera Permission")
            }
        }

        permissionLauncher.launch(Manifest.permission.CAMERA)

        return inflater.inflate(R.layout.fragment_splash, container, false)

    }
}