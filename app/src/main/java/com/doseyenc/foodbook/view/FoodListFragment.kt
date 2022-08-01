package com.doseyenc.foodbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.doseyenc.foodbook.R
import com.doseyenc.foodbook.adapter.FoodListAdapter
import com.doseyenc.foodbook.databinding.FragmentFoodListBinding
import com.doseyenc.foodbook.model.FoodModel
import com.doseyenc.foodbook.viewmodel.FoodListViewModel


class FoodListFragment : Fragment(R.layout.fragment_food_list), FoodListAdapter.FoodClick {
    private lateinit var viewModel: FoodListViewModel
    private lateinit var binding: FragmentFoodListBinding
    private val rv_adapter = FoodListAdapter(arrayListOf(), this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_food_list,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FoodListViewModel::class.java)
        setUpRecyclerView()
        viewModel.refreshData()
        binding.apply {
            swipeRefreshLayout.setOnRefreshListener {
                progressBar.visibility = View.VISIBLE
                textViewError.visibility = View.GONE
                rvFoodList.visibility = View.GONE
                viewModel.refreshFromServer()
                swipeRefreshLayout.isRefreshing = false
            }
        }


        observeLiveData()

    }

    private fun setUpRecyclerView() {
        binding.apply {
            rvFoodList.layoutManager=LinearLayoutManager(context)
            rvFoodList.adapter = rv_adapter
        }

    }

    private fun observeLiveData() {
        viewModel.foods.observe(viewLifecycleOwner) {
            it?.let {
                binding.rvFoodList.visibility = View.VISIBLE
                rv_adapter.updateList(it)
            }
        }
        viewModel.foodErrorMessage.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    binding.textViewError.visibility = View.VISIBLE
                } else {
                    binding.textViewError.visibility = View.GONE
                }
            }
        }
        viewModel.foodLoading.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    binding.rvFoodList.visibility = View.GONE
                    binding.textViewError.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

    }

    override fun onFoodClick(foodModel: FoodModel) {
        findNavController().navigate(
            FoodListFragmentDirections.actionFoodListFragmentToDetailFragment(
                foodModel.id
            )
        )
    }
}

