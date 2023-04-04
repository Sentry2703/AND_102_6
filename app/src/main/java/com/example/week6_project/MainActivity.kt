package com.example.week6_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.week6_project.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val foods = mutableListOf<DisplayFood>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lifecycleScope.launch {
            (application as FoodApplication).db.foodDao().getAll().collect { dbList ->
                dbList.map { entity ->
                    DisplayFood(
                        entity.name,
                        entity.calories
                    )
                }.also { mappedList ->
                    foods.clear()
                    foods.addAll(mappedList)
                    FoodListFragment(foods)
                }

            }
        }

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val addFoodButton = findViewById<Button>(R.id.add_button)



        bottomNavView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_food -> fragment = FoodListFragment(foods)
                R.id.nav_dashboard -> fragment = DashboardFragment(foods)
            }
            replaceFragment(fragment)
            true
        }

        addFoodButton.setOnClickListener {
            val intent = Intent(this, EnterFood::class.java)
            startActivity(intent)
           val food = DisplayFood(intent.getStringExtra("name"), intent.getStringExtra("name"))
            foods.add(food)
            replaceFragment(FoodListFragment(foods))
        }

        bottomNavView.selectedItemId = R.id.nav_food
    }

    private fun replaceFragment(listFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.food_frame_layout, listFragment)
        fragmentTransaction.commit()
    }

    @Deprecated("Deprecated in Java")
    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check that it is the SecondActivity with an OK result
        if (requestCode == 15) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                val name: String? = data?.extras!!.getString("foodn")
                val cal: String? = data.extras!!.getString("foodc")

                Toast.makeText(this, "Unsuccessful2 $name $cal", Toast.LENGTH_SHORT).show()
                // Set text view with string

            }
        }
        Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show()

    }


}