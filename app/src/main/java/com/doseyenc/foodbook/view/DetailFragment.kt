package com.doseyenc.foodbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.doseyenc.foodbook.R
import com.doseyenc.foodbook.viewmodel.FoodDetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment(R.layout.fragment_detail) {
    private lateinit var viewModel: FoodDetailViewModel
    private val args by navArgs<DetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FoodDetailViewModel::class.java)

        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.food.observe(viewLifecycleOwner) { food ->
            food?.let {
                textViewFoodName.text = food.foodName
                textViewCalories.text = food.foodCalory
                textVievCarbohydrate.text = food.foodCarbonHydrat
                textVievFat.text = food.foodFat
                textVievProtein.text = food.foodProtein
            }
        }
    }


}