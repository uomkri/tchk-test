package com.tchair.tchktest.mainpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tchair.tchktest.R
import com.tchair.tchktest.databinding.FragmentMainBinding
import com.tchair.tchktest.util.UserDataAdapter

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private lateinit var binding: FragmentMainBinding

    private var query = ""



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMainBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        binding.searchButtom.setOnClickListener {
            query = binding.searchField.text.toString()
            if(query.isNotEmpty()){
                viewModel.fetchUsers(query)
            }
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UserDataAdapter()
        binding.usersList.adapter = adapter
        viewModel.getUsers().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
                //Log.i("data", adapter.data.toString())
                adapter.submitList(it)
            }
        })

    }
}