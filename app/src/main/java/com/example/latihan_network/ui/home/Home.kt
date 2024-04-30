package com.example.latihan_network.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihan_network.R
import com.example.latihan_network.databinding.FragmentHomeBinding
import com.example.latihan_network.databinding.FragmentProfilBinding
import com.example.latihan_network.ui.profile.ProfilViewModel

class Home : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home , container , false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        val viewAdapter = TweetAdapter()
        val viewManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL ,false)
        binding.rv.adapter = viewAdapter
        binding.rv.layoutManager = viewManager

        binding.rv.addItemDecoration(DividerItemDecoration(context , viewManager.orientation))

        viewModel.teksTweet.observe(viewLifecycleOwner , Observer{
            list ->
            if (list.isNotEmpty()){
                viewAdapter.submitList(list)
                hideLoding()
                binding.swipeRefresh.isRefreshing = false
            }
        })

        binding.swipeRefresh.setOnRefreshListener {
            showLoding()
            viewModel.initData()
        }

        viewModel.response.observe(viewLifecycleOwner, Observer{
            binding.txtData.text = it
        })

        binding.icon.setOnClickListener {
            it.findNavController().navigate(R.id.action_home_to_profil)
        }

        return binding.root
    }

    private fun showLoding(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoding(){
        binding.progressBar.visibility = View.GONE
    }

}