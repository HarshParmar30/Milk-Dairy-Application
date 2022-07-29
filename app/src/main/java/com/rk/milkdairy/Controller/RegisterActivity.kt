package com.rk.milkdairy.Controller

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.RequiresApi
import com.rk.milkdairy.MainActivity
import com.rk.milkdairy.R
import com.rk.milkdairy.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private var step  : Int = 1
    private val total_step : Int = 3

    private lateinit var img_ind : ImageView
    private lateinit var img_pure : ImageView

    private lateinit var rg_referal : RadioGroup
    private lateinit var et_reg_referal : EditText
    private lateinit var cb_referal : CheckBox

    private lateinit var anim : Animation
    private lateinit var lv_state : LinearLayout
    private lateinit var lv_district : LinearLayout
    private lateinit var lv_taluka : LinearLayout

    private lateinit var spin_state : Spinner
    private lateinit var spin_district : Spinner
    private lateinit var tv_taluka : TextView

    private lateinit var et_taluka : EditText

    private lateinit var btn_step_save : Button
    private lateinit var tv_step_save : TextView


    private lateinit var btn_1 : Button
    private lateinit var btn_2 : Button
    private lateinit var btn_3 : Button

    private lateinit var lv_rate_settings : LinearLayout

    val stateList = arrayOf("Gujarat" , "Maharashtra" , "Rajasthan")
    val districtList : HashMap<String , ArrayList<String>> = HashMap()
    val districtNameList = arrayOf("Gujarat" , "Maharashtra" , "Rajasthan")
    private lateinit var district_adapter : ArrayAdapter<String>

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        anim = AnimationUtils.loadAnimation(this , R.anim.bottom_to_top)

        lv_rate_settings = findViewById(R.id.lv_rate_settings)
        btn_1 = findViewById(R.id.btn_1)
        btn_2 = findViewById(R.id.btn_2)
        btn_3 = findViewById(R.id.btn_3)

        img_ind = findViewById(R.id.img_reg_ind)
        img_pure = findViewById(R.id.img_reg_pure)

        tv_step_save = findViewById(R.id.tv_step_save)

        rg_referal = findViewById(R.id.rg_referal)
        et_reg_referal = findViewById(R.id.et_reg_referalcode)
        cb_referal = findViewById(R.id.cb_referal)
        et_reg_referal.visibility = View.GONE

        cb_referal.setOnClickListener {
            if(et_reg_referal.visibility == View.VISIBLE){
                et_reg_referal.visibility = View.GONE
            }else {
                et_reg_referal.visibility = View.VISIBLE
            }
        }

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                // when fadeout animation ends, fade in your second image
            }
        })
        img_ind.startAnimation(anim)
        img_pure.startAnimation(anim)

        et_taluka = findViewById(R.id.et_taluka)

        btn_step_save = findViewById(R.id.btn_step_save)

        findViewById<View>(R.id.step_2).visibility = View.GONE
        findViewById<View>(R.id.step_3).visibility = View.GONE
//        overridePendingTransition(R.anim.bottom_to_top , R.anim.at_top)

        val gujDistList : ArrayList<String> = ArrayList()
        gujDistList.add("Anand")
        gujDistList.add("Ahmedabad")
        gujDistList.add("Porbandar")
        districtList.put("Gujarat" , gujDistList)

        val mahDistList : ArrayList<String> = ArrayList()
        mahDistList.add("Mumbai")
        mahDistList.add("Nagpur")
        mahDistList.add("Pune")
        districtList.put("Maharashtra" , mahDistList)

        val rajDistList : ArrayList<String> = ArrayList()
        rajDistList.add("Jaipur")
        rajDistList.add("Udaypur")
        rajDistList.add("Bhangadh")
        districtList.put("Rajasthan" , rajDistList)

        lv_state = findViewById(R.id.lv_state)
        spin_state = lv_state.findViewById(R.id.spin_state)
        val state_adapter = ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , stateList)
        state_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin_state.adapter = state_adapter

        lv_district = findViewById(R.id.lv_district)
        spin_district = lv_district.findViewById(R.id.spin_district)

        var selectedState : String = "Gujarat"
        spin_state.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 1){
                    selectedState = "Maharashtra"
                    changeCity(selectedState)
                }else if(p2 == 2){
                    selectedState = "Rajasthan"
                    changeCity(selectedState)
                }else{
                    selectedState = "Gujarat"
                    changeCity(selectedState)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        lv_taluka = findViewById(R.id.lv_taluka)

        btn_1.setOnClickListener {
            val v = findViewById<View>(R.id.kg_fat)
            v.visibility = View.VISIBLE
            findViewById<View>(R.id.snf).visibility = View.GONE
            findViewById<View>(R.id.clr).visibility = View.GONE
        }
        btn_2.setOnClickListener {
            val v = findViewById<View>(R.id.snf)
            v.visibility = View.VISIBLE
            findViewById<View>(R.id.kg_fat).visibility = View.GONE
            findViewById<View>(R.id.clr).visibility = View.GONE

        }
        btn_3.setOnClickListener {
            val v = findViewById<View>(R.id.clr)
            v.visibility = View.VISIBLE
            findViewById<View>(R.id.snf).visibility = View.GONE
            findViewById<View>(R.id.kg_fat).visibility = View.GONE
        }


        btn_step_save.setOnClickListener {
            step = (++step % 5)

            when(step){
                1 -> {
                    val v = findViewById<View>(R.id.step_1)
                    v.visibility = View.VISIBLE
                    findViewById<View>(R.id.step_2).visibility = View.GONE
                    findViewById<View>(R.id.step_3).visibility = View.GONE
                    updateBtnText(tv_step_save , step , total_step)
                }
                2 -> {
                    val v = findViewById<View>(R.id.step_2)
                    v.visibility = View.VISIBLE
                    findViewById<View>(R.id.step_1).visibility = View.GONE
                    findViewById<View>(R.id.step_3).visibility = View.GONE
                    updateBtnText(tv_step_save , step , total_step)
                }
                3 -> {
                    val v = findViewById<View>(R.id.step_3)
                    v.visibility = View.VISIBLE
                    findViewById<View>(R.id.step_1).visibility = View.GONE
                    findViewById<View>(R.id.step_2).visibility = View.GONE
                    updateBtnText(tv_step_save , step , total_step)
                }
                4 -> {
                    val i = Intent(this , DashboardActivity::class.java)
                    startActivity(i)
                }

            }

        }
    }

    private fun updateBtnText(tvStepSave: TextView?, step: Int, totalStep: Int) {

        if (tvStepSave != null) {
            if(step == totalStep){
                btn_step_save.setText("Save")
            }
            tvStepSave.setText("Step $step/$totalStep")
        }
    }

    private fun changeCity(selectedState: String) {
        district_adapter = ArrayAdapter<String>(this , android.R.layout.simple_spinner_dropdown_item , districtList.get(
            selectedState)!!.toMutableList())

        district_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin_district.adapter = district_adapter
    }



}