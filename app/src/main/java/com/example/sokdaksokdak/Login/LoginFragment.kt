package com.example.sokdaksokdak.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.sokdaksokdak.database.AppDatabase
import com.example.sokdaksokdak.databinding.FragmentLoginBinding



class LoginFragment : Fragment() {
    private lateinit var loginViewModel : LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.kakaoLoginButton.setOnClickListener{
            loginViewModel.Login("kakao")
        }

        return binding.root
    }


}