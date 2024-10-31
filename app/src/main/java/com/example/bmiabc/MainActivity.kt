package com.example.bmiabc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val weightEditText: EditText = findViewById(R.id.weightEditText)
        val heightEditText: EditText = findViewById(R.id.heightEditText)
        val calculateBmiButton: Button = findViewById(R.id.bmiButton)
        val resultTextView: TextView = findViewById(R.id.bmiResultTextView)

        calculateBmiButton.setOnClickListener{
            val weightText = weightEditText.text.toString()
            val heightText = heightEditText.text.toString()
            if(weightText.isNotEmpty() && heightText.isNotEmpty()) {
            val weight = weightText.toFloat()
            val height = heightText.toFloat()
            var bmi = calculateBMI(weight, height)

            resultTextView.text = String.format("Your bmi is: %.2f", bmi)
            resultTextView.append("\n" + getBMICategory(bmi))

            } else {
                Toast.makeText(this, "Weight or Height is missing", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getBMICategory(bmi: Float): String {

        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Normal weight"
            bmi < 29.9 -> "Overweight"
            else -> "Obese"
        }

    }

    private fun calculateBMI(weight:Float, height:Float):Float {
        return weight/(height * height)
    }
}