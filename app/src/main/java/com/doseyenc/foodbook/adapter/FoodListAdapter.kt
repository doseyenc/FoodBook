package com.doseyenc.foodbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.doseyenc.foodbook.R
import com.doseyenc.foodbook.model.FoodModel
import kotlinx.android.synthetic.main.rv_item_design.view.*

class FoodListAdapter(
    val list: ArrayList<FoodModel>,
    val foodClickInterface: FoodClick
) :
    RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(currentFood: FoodModel) {
            itemView.tv_foodName.text = currentFood.foodName
            itemView.tv_foodCalory.text = currentFood.foodCalory
            Glide.with(itemView)
                .load(currentFood.image)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(itemView.iv_foodImage)
            itemView.setOnClickListener {
                foodClickInterface.onFoodClick(currentFood)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rv_item_design, parent, false)
        return FoodViewHolder(view)

    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentFood = list[position]
        holder.bind(currentFood)

    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<FoodModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }


    interface FoodClick {
        fun onFoodClick(foodModel: FoodModel)
    }
}







