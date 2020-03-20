package com.tchair.tchktest.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.tchair.tchktest.databinding.FragmentUserDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding

    private lateinit var args: DetailsFragmentArgs

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProviders.of(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        args = DetailsFragmentArgs.fromBundle(arguments!!)
        binding = FragmentUserDetailsBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        viewModel.getUser(args.login)

        return binding.root
    }
}