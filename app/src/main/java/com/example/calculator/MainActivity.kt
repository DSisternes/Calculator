package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var firstValue: Double = 0.0
    var operator: Char = '0'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.ButtonClear.setOnClickListener{
            binding.editTextCalculations.text.clear()
        }

        binding.ButtonDelete.setOnClickListener{
            binding.editTextCalculations.setText(binding.editTextCalculations.text.dropLast(1))
        }

        binding.ButtonEquals.setOnClickListener{
            when(operator) {
                '+' -> {
                    binding.editTextCalculations.setText("${firstValue + binding.editTextCalculations.text.toString().toDouble()}")
                }
                '÷' -> {
                    binding.editTextCalculations.setText("${firstValue / binding.editTextCalculations.text.toString().toDouble()}")
                }
                'x' -> {
                    binding.editTextCalculations.setText("${firstValue * binding.editTextCalculations.text.toString().toDouble()}")
                }
                '-' -> {
                    binding.editTextCalculations.setText("${firstValue - binding.editTextCalculations.text.toString().toDouble()}")
                }
                else -> binding.editTextCalculations.setText("ERROR")
            }
            operator = '0'
            firstValue = 0.0
        }

        binding.ButtonPercentage.setOnClickListener{
            binding.editTextCalculations.setText(percentage(operator))
        }
    }
    fun percentage(operatorToUse: Char) : String {
        when(operatorToUse) {
            '+' -> { return "${firstValue + (firstValue*binding.editTextCalculations.text.toString().toDouble()/100)}"}
            '-' -> { return "${firstValue - (firstValue*binding.editTextCalculations.text.toString().toDouble()/100)}"}
            '0' -> { return "${binding.editTextCalculations.text.toString().toDouble()/100}"}
            else -> {return "ERROR"}
        }
    }

    fun getOperator(view: View){
        val button = view as Button

        operator = button.text.get(0)

/*        operator = when(button.id){
            binding.ButtonDivision.id -> { '÷' }
            binding.ButtonMultiplication.id -> { 'x' }
            binding.ButtonAddition.id -> { '+' }
            binding.ButtonSubtraction.id -> { '-' }
            else -> { '0' }
        }
*/
        firstValue = binding.editTextCalculations.text.toString().toDouble()
        binding.editTextCalculations.text.clear()
    }

    fun numberButtonClicked(view: View){
        val button = view as Button

        if (button.id.equals(binding.ButtonDecimal.id)) {
            Toast.makeText(this, "decimal pressed", Toast.LENGTH_SHORT).show()
            if (!binding.editTextCalculations.text.contains(".")) {
                binding.editTextCalculations.text.append(button.text)
                Toast.makeText(this, "no previous decimal", Toast.LENGTH_SHORT).show()
            }
        }else{
            binding.editTextCalculations.text.append(button.text)
        }

/*
        when(button.id){
            binding.ButtonDecimal.id -> {
                if (!binding.editTextCalculations.text.contains(".")){
                    binding.editTextCalculations.text.append(".")
                }
            }
            binding.Button1.id -> {binding.editTextCalculations.text.append("1")}
            binding.Button2.id -> {binding.editTextCalculations.text.append("2")}
            binding.Button3.id -> {binding.editTextCalculations.text.append("3")}
            binding.Button4.id -> {binding.editTextCalculations.text.append("4")}
            binding.Button5.id -> {binding.editTextCalculations.text.append("5")}
            binding.Button6.id -> {binding.editTextCalculations.text.append("6")}
            binding.Button7.id -> {binding.editTextCalculations.text.append("7")}
            binding.Button8.id -> {binding.editTextCalculations.text.append("8")}
            binding.Button9.id -> {binding.editTextCalculations.text.append("9")}
            binding.Button0.id -> {binding.editTextCalculations.text.append("0")}
            else -> {
                Toast.makeText(this, "else", Toast.LENGTH_SHORT).show()
            }
        }
*/
    }
}