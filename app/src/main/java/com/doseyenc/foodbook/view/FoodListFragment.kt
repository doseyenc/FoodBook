package com.doseyenc.foodbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.doseyenc.foodbook.R
import com.doseyenc.foodbook.adapter.FoodListAdapter
import com.doseyenc.foodbook.viewmodel.FoodListViewModel
import kotlinx.android.synthetic.main.fragment_food_list.*


class FoodListFragment : Fragment(R.layout.fragment_food_list) {
    private lateinit var viewModel: FoodListViewModel
    private val rv_adapter = FoodListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_food_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FoodListViewModel::class.java)
        viewModel.refresh_data()
        setUpRecyclerView()
        observeLiveData()

    }

    private fun setUpRecyclerView() {
        rv_foodList.layoutManager = LinearLayoutManager(context)
        rv_foodList.adapter = rv_adapter
    }

    fun observeLiveData() {
        viewModel.foods.observe(viewLifecycleOwner) {
            it?.let {
                rv_foodList.visibility = View.VISIBLE
                rv_adapter.updateList(it)
            }
        }
        viewModel.foodErrorMessage.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    textViewError.visibility = View.VISIBLE
                } else {
                    textViewError.visibility = View.GONE
                }
            }
        }
        viewModel.foodLoading.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    rv_foodList.visibility = View.GONE
                    textViewError.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        }

    }
}

