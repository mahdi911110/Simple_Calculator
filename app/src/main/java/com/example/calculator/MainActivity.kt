package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastNumeric = false
    var isLastNum = true
    var lastDot = false
    var isDot = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view: View){
        val TvInput = findViewById<TextView>(R.id.tvInput)
        TvInput.append((view as Button).text)
        lastNumeric = true
        isDot = false
    }
    fun onClear(view: View){
        val TvInput = findViewById<TextView>(R.id.tvInput)
        TvInput.text = ""
        lastNumeric = false
        lastDot = false
        isLastNum = true
    }
    fun onDecimalPoint(view: View){
        val TvInput = findViewById<TextView>(R.id.tvInput)
        if(lastNumeric && !lastDot){
            TvInput.append(".")
            lastDot = true
            isDot = true
        }
    }
    fun onOperator(view: View){
        val TvInput = findViewById<TextView>(R.id.tvInput)
        if (TvInput.text.isEmpty() && (view as Button).text == "-")
            TvInput.append("-")
        if (lastNumeric && isLastNum && !isDot){
            TvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
            isLastNum = false
        }
    }
    private fun removeZero(result: String): String{
        var Value = result
        if (Value.contains(".0"))
            Value = result.substring(0,result.length - 2)
        return Value
    }
    fun onEqual(view: View){
        if (lastNumeric){
            val TvInput = findViewById<TextView>(R.id.tvInput)
            var TvInputText = TvInput.text.toString()
            var prefix = ""
            try {
                if (TvInputText.startsWith("-")){
                    prefix = "-"
                    TvInputText = TvInputText.substring(1)
                }
                if(TvInputText.contains("-")){
                    val TvInputSplit = TvInputText.split("-")
                    var One = TvInputSplit[0]
                    val Two = TvInputSplit[1]
                    if (prefix.isNotEmpty())
                        One = prefix + One
                    TvInput.text = removeZero((One.toDouble() - Two.toDouble()).toString())
                }
                if(TvInputText.contains("+")){
                    val TvInputSplit = TvInputText.split("+")
                    var One = TvInputSplit[0]
                    val Two = TvInputSplit[1]
                    if (prefix.isNotEmpty())
                        One = prefix + One
                    TvInput.text = removeZero((One.toDouble() + Two.toDouble()).toString())
                }
                if(TvInputText.contains("*")){
                    val TvInputSplit = TvInputText.split("*")
                    var One = TvInputSplit[0]
                    val Two = TvInputSplit[1]
                    if (prefix.isNotEmpty())
                        One = prefix + One
                    TvInput.text = removeZero((One.toDouble() * Two.toDouble()).toString())
                }
                if(TvInputText.contains("/")){
                    val TvInputSplit = TvInputText.split("/")
                    var One = TvInputSplit[0]
                    val Two = TvInputSplit[1]
                    if (prefix.isNotEmpty())
                        One = prefix + One
                    TvInput.text = removeZero((One.toDouble() / Two.toDouble()).toString())
                }
            } catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
}