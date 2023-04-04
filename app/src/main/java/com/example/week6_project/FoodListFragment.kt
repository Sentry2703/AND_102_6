package com.example.week6_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FoodListFragment() : Fragment() {
    constructor(food: List<DisplayFood>): this() {
        foods = food as MutableList<DisplayFood>
    }

    private var foods = mutableListOf<DisplayFood>()
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_food_list, container, false)

        val layoutManager = LinearLayoutManager(context)
        foodRecyclerView = view.findViewById(R.id.food_recycler_view)
        foodRecyclerView.layoutManager = layoutManager
        foodRecyclerView .setHasFixedSize(true)
        foodAdapter = FoodAdapter(view.context, foods)
        foodRecyclerView.adapter = foodAdapter

        foodRecyclerView.layoutManager = LinearLayoutManager(context).also {
            val dividerItemDecoration = DividerItemDecoration(context, it.orientation)
            foodRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        return view
    }

    companion object {
        fun newInstance(): FoodListFragment {
            return FoodListFragment()
        }
    }
}