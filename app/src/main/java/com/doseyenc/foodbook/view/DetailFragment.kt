package com.doseyenc.foodbook.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.doseyenc.foodbook.R
import com.doseyenc.foodbook.util.createPlaceHolder
import com.doseyenc.foodbook.util.setImage
import com.doseyenc.foodbook.viewmodel.FoodDetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlin.properties.Delegates


class DetailFragment : Fragment(R.layout.fragment_detail) {
    private lateinit var viewModel: FoodDetailViewModel
    private val args by navArgs<DetailFragmentArgs>()
    private  var clickedFoodId:Int =-1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.let {
            clickedFoodId = args.id
            Log.e("id",clickedFoodId.toString())
        }
        viewModel = ViewModelProvider(this).get(FoodDetailViewModel::class.java)
        viewModel.getRoomData(clickedFoodId)
        observeLiveData()
    }
    private fun observeLiveData() {
        viewModel.food.observe(viewLifecycleOwner) { food ->
            food?.let {
                textViewFoodName.text = food.foodName
                textViewCalories.text = food.foodCalory
                textVievCarbohydrate.text = food.foodCarbonHydrat
                textVievFat.text = food.foodFat
                textVievProtein.text = food.foodProtein
                imageViewFood.setImage(food.image,createPlaceHolder(requireContext()))
            }
        }
    }


}