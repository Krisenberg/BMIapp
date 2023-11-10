package com.example.bmiapp

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //change the action bar title here
        val actionBar = supportActionBar
        actionBar?.title = "BMI calculator"

        //setup the Unit Adapter so it can use the SharedPreferences
        UnitAdapter.setup(applicationContext)
        HistoryHandler.setup(applicationContext)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        val button = findViewById<Button>(R.id.calculateButton)
        button.setOnClickListener{
            if (!mainViewModel.calculateBMI())
                Toast.makeText(this,"Please provide valid height and weight", Toast.LENGTH_SHORT).show()
            else {
                val bmi_value = mainViewModel.bmi_value().value!!
                val bmi_color = DescriptionProvider.getCategoryColorBasedOnValue(bmi_value, this)
                val date_formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")
                val current_date = ZonedDateTime.now(ZoneId.of("Europe/Warsaw")).format(date_formatter)
                val newEntry = HistoryEntry(current_date.toString(), mainViewModel.height_value()!!, mainViewModel.height_unit(),
                                            mainViewModel.weight_value()!!, mainViewModel.weight_unit(),
                                            bmi_value, ContextCompat.getColor(this, bmi_color))
                HistoryHandler.addEntry(newEntry)
//                Toast.makeText(this,"Entry added, BMI: ${newEntry.bmi}", Toast.LENGTH_SHORT).show()
            }
        }

        mainViewModel.bmi_value().observe(this, Observer {
            updateBMIonUI(it)
        })
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.checkUnitAndUpdateHeight()
        mainViewModel.checkUnitAndUpdateWeight()

        findViewById<TextView>(R.id.heightUnitTV).text = mainViewModel.height_unit()
        findViewById<TextView>(R.id.weightUnitTV).text = mainViewModel.weight_unit()

        val heightET = findViewById<EditText>(R.id.heightInput)
        //Toast.makeText(this,"BMI value: ${mainViewModel.bmi_value().value}", Toast.LENGTH_SHORT).show()
        if (mainViewModel.height_value() == null)
            heightET.setText("")
        else
            heightET.setText(mainViewModel.height_value().toString())
        val weightET = findViewById<EditText>(R.id.weightInput)
        if (mainViewModel.weight_value() == null)
            weightET.setText("")
        else
            weightET.setText(mainViewModel.weight_value().toString())

        getHeightInputAndUpdate()
        getWeightInputAndUpdate()


        //updateBMIonUI(mainViewModel.bmi_value().value)
    }

    fun updateBMIonUI(bmi_value: Double?) {
        val resultTV = findViewById<TextView>(R.id.bmiTV)
        if(bmi_value == null) {
            resultTV.text = ""
        }
        else {
            val result_str = String.format("%.2f", bmi_value)
            val resultColor = DescriptionProvider.getCategoryColorBasedOnValue(bmi_value, this)
//            val resultColor = mainViewModel.getBMIcolor()
//            resultTV.text = result_str
            val span_result = SpannableString(result_str)

            val clickable_result: ClickableSpan = object: ClickableSpan() {
                override fun onClick(p0: View) {
//                    Toast.makeText(this@MainActivity, "Ready to jump into description", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity, BMIdetails::class.java).also {
                        it.putExtra("BMI_VALUE", bmi_value!!)
                        startActivity(it)
                    }
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText=true
                    ds.color=ContextCompat.getColor(this@MainActivity, resultColor)
//                    ds.bgColor = Color.TRANSPARENT
                }
            }
            span_result.setSpan(clickable_result, 0, result_str.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            //resultTV.setTextColor(ContextCompat.getColor(this, resultColor))
            resultTV.setText(span_result, TextView.BufferType.SPANNABLE)
            resultTV.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    fun getHeightInputAndUpdate() {
        val heightInput = findViewById<EditText>(R.id.heightInput)

        heightInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editableVal: Editable?) {
                val newValue = editableVal.toString().toDoubleOrNull()
                mainViewModel.updateHeightValue(newValue)
            }
        })
    }

    fun getWeightInputAndUpdate() {
        val weightInput = findViewById<EditText>(R.id.weightInput)

        weightInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editableVal: Editable?) {
                val newValue = editableVal.toString().toDoubleOrNull()
                mainViewModel.updateWeightValue(newValue)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_history -> {
                //Toast.makeText(this, "Nothing is here", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, History::class.java))
            }
            R.id.nav_settings -> {
//                Toast.makeText(this@MainActivity, "HeightUnit ${mainViewModel.current_height_value.value}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Settings::class.java))
            }
            R.id.nav_author -> {
                startActivity(Intent(this, Author::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}