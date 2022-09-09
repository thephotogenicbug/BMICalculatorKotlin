/*
 * *
 *  * Created by naveen on 09/09/22, 11:24 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 09/09/22, 10:50 PM
 *
 */

package com.naveen.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString() // get weight and height in string format
            val height = heightText.text.toString()
            // validate
            if(validateInput(weight,height)) {
                                               // it will give height in cm divided by 100 to
                                               // convert cm to mts and to get square values multiple by
                                               // same
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                // get result with two decimal places
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }
    }

    private fun validateInput(weight:String?,height:String?):Boolean{

        return when{
             weight.isNullOrEmpty() ->{
                 Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                 return false
             }
            height.isNullOrEmpty() ->{
                Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }


    }

    private fun displayResult(bmi:Float){ // add bmi:Float as parameter
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "(Normal range is 18.5 - 24.9)"

        var resultText = ""
        var color = 0

        when{
            bmi<18.50 ->{
                resultText = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText = "Overweight"
                color = R.color.over_weight
            }
            bmi > 29.99 ->{
                resultText = "Obese"
                color = R.color.obese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this, color))
        resultDescription.text = resultText

    }
}