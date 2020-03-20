package com.tchair.tchktest.mainpage

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.tchair.tchktest.R
import com.tchair.tchktest.databinding.FragmentMainBinding
import com.tchair.tchktest.net.UserData
import com.tchair.tchktest.util.UserDataAdapter

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private lateinit var binding: FragmentMainBinding

    private var query = ""

    private lateinit var snapshot: List<UserData>


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
        binding.usersList.addOnItemTouchListener(RecyclerItemClickListener(binding.usersList,
            object : RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    viewModel.getUsers().observe(viewLifecycleOwner, Observer {
                        view.findNavController().navigate(MainFragmentDirections.actionMainFragment2ToDetailsFragment(it[position]?.login))
                    })
                }
            }
            ))


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