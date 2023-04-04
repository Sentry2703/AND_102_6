package com.example.week6_project

import android.app.Application

class FoodApplication: Application() {
    val db by lazy {FoodDatabase.getInstance(this)}
}