package com.bekirfarukarabaci.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import com.bekirfarukarabaci.calculator.databinding.ActivityMainBinding
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var firstNumber =""
    private var currentNumber =""
    private var currentOperators =""
    private var result =""




    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        binding.apply {
            binding.gridLayout.children.filterIsInstance<Button>().forEach { button ->
                button.setOnClickListener{
                    val buttonText = button.text.toString()
                    when{
                        buttonText.matches(Regex("[0-9]"))->{
                            if (currentOperators.isEmpty()){
                                firstNumber += buttonText
                                textView.text = firstNumber
                            }
                            else{
                                currentNumber += buttonText
                                textView.text = currentNumber
                            }
                        }
                        buttonText.matches(Regex("[+\\-*/]"))->{
                            currentNumber = ""
                            if (textView.text.toString().isNotEmpty()){
                                currentOperators = buttonText
                                textView.text = "0"
                            }
                        }
                        buttonText == "="->{
                            if (currentNumber.isNotEmpty() && currentOperators.isNotEmpty()){
                                textView3.text = "$firstNumber$currentOperators$currentNumber"
                                result = evalutaExpression(firstNumber,currentNumber,currentOperators)
                                firstNumber = result
                                textView.text = result
                            }
                        }
                        buttonText == "."->{
                            if (currentOperators.isEmpty()){
                                if (!firstNumber.contains(".")){
                                        if (firstNumber.isEmpty())firstNumber+="0$buttonText"
                                        else firstNumber += buttonText
                                        binding.textView.text = firstNumber
                                }
                            }
                            else{
                                if (!currentNumber.contains(".")){
                                    if (currentNumber.isEmpty()) currentNumber+="0$buttonText"
                                    else currentNumber += "0$buttonText"
                                    textView.text = currentNumber
                                }
                            }
                        }
                        buttonText == "C"->{
                            currentNumber = ""
                            firstNumber = ""
                            currentNumber = ""
                            textView.text = "0"
                            textView3.text = ""
                        }
                    }
                }
            }
        }


    }


    private fun evalutaExpression(firstNumber: String,secondNumber : String, operator:String):String{
        val num1 = firstNumber.toDouble()
        val num2 = secondNumber.toDouble()

        return when(operator){
            "+"->(num1+num2).toString()
            "-"->(num1-num2).toString()
            "*"->(num1*num2).toString()
            "/"->(num1/num2).toString()
            else ->""
        }
    }
}