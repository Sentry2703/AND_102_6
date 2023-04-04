package com.example.week6_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private val context: Context, private val foods: List<DisplayFood>) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
                private val foodNameView = itemView.findViewById<TextView>(R.id.food_text)
                private val caloriesNameView = itemView.findViewById<TextView>(R.id.calories_text)

            init {
                itemView.setOnClickListener(this)
            }

            fun bind(food : DisplayFood) {
                foodNameView.text = food.name
                caloriesNameView.text = food.calories
            }

        override fun onClick(v: View?) {
            Toast.makeText(context, "Nothing to see here", Toast.LENGTH_SHORT).show()
        }
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodAdapter.ViewHolder, position: Int) {
        val food = foods[position]
        holder.bind(food)
    }

    override fun getItemCount(): Int = foods.size

}
