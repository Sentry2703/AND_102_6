package com.example.week6_project

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EnterFood : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_food)

        val foodNameView: EditText = findViewById(R.id.editFoodText)
        val caloriesNameView: EditText = findViewById(R.id.editCaloriesText)
        val returnButton: Button = findViewById(R.id.recordButton)

        var foodName: String
        var foodCalories: String

        returnButton.setOnClickListener {
            foodName = foodNameView.text.toString()
            foodCalories = caloriesNameView.text.toString()

            if (foodName.isNullOrEmpty() || foodCalories.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter values for both of the fields", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    //(application as FoodApplication).db.foodDao().deleteAll()
                    (application as FoodApplication).db.foodDao().insert(
                        FoodEntity(
                            name = foodName,
                            calories = foodCalories
                        )
                    )
                }
                intent.putExtra("name", foodName)
                intent.putExtra("calories", foodCalories)
                finish()
            }



        }

    }
}


