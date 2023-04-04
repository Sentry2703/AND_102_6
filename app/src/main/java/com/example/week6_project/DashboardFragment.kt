package com.example.week6_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 * Use the [Dashboard.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment() : Fragment() {

    constructor(food: List<DisplayFood>): this() {
        foods = food as MutableList<DisplayFood>
    }

    private var foods = mutableListOf<DisplayFood>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        var min: Int = Int.MAX_VALUE
        var max: Int = Int.MIN_VALUE
        var sum = 0

        for (food: DisplayFood in foods) {
            sum += food.calories?.toInt() ?: 0
            if ((food.calories?.toInt() ?: 0) < min) {
                min = food.calories?.toInt() ?: 0
            } else if ((food.calories?.toInt() ?: 0) > max) {
                max = food.calories?.toInt() ?: 0
            }
        }

        val minCalories: TextView = view.findViewById(R.id.Min_Cal)
        val maxCalories: TextView = view.findViewById(R.id.Max_Cal)
        val avgCalories: TextView = view.findViewById(R.id.Avg_Cal)


        if (foods.size > 1) {
            avgCalories.text = (sum/foods.size).toString()
        } else if (foods.size == 1){
            max = when(max == Int.MAX_VALUE || max == Int.MIN_VALUE) {
                true -> min
                false -> max
            }
            min = when(min == Int.MAX_VALUE || min == Int.MIN_VALUE) {
                true -> max
                false -> min
            }
            avgCalories.text = max.toString()
        } else {
            avgCalories.text = "0"
        }
        minCalories.text = min.toString()
        maxCalories.text = max.toString()

        return view
    }

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }

    }
}