package com.example.week6_project

class Food() : java.io.Serializable {
    private var name: String
    private var calories: String

    init {
        name = ""
        calories = ""
    }

    constructor(name: String, calories: String): this() {
        this.name = name
        this.calories = calories
    }

    fun getName(): String? = name
    fun getCalories(): String? = calories

}