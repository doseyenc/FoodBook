package com.doseyenc.foodbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doseyenc.foodbook.R
import com.doseyenc.foodbook.model.FoodModel
import kotlinx.android.synthetic.main.rv_item_design.view.*

class FoodListAdapter(val list:ArrayList<FoodModel>) :RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rv_item_design,parent,false)
        return FoodViewHolder(view)

    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentFood=list[position]
        holder.itemView.tv_foodName.text = currentFood.foodName
        holder.itemView.tv_foodCalory.text = currentFood.foodCalory
       /* glide.with(holder.itemView.context)
            .load(currentFood.image)
            .into(holder.itemView.iv_foodImage)*/
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<FoodModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

}


