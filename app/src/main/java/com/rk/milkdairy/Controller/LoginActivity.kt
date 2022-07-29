package com.rk.milkdairy.Controller

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.rk.milkdairy.R
import org.json.JSONObject
import java.util.*

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {

    val langs = arrayOf("English" ,  "ગુજરાતી" , "हिंदी")

    private lateinit var spin_lang : Spinner
    private lateinit var et_pass : EditText
    private lateinit var et_mobile : EditText
    private lateinit var tv_forgot : TextView
    private lateinit var tv_orx : TextView
    private lateinit var btn_login : Button
    private lateinit var btn_register : Button
    private lateinit var lv_divider : LinearLayout
    private lateinit var loc : String
    private lateinit var auth_token : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

                val view = findViewById<LinearLayout>(R.id.lv_divider)

                spin_lang = findViewById(R.id.spin_lang)
                et_pass = findViewById(R.id.et_pass)
                et_mobile = findViewById(R.id.et_mobile)
                tv_orx = view.getChildAt(1) as TextView
                tv_forgot = findViewById(R.id.tv_forgot)
                btn_login = findViewById(R.id.btn_login)
                btn_register = findViewById(R.id.btn_step_save)

                val ad : ArrayAdapter<String> = ArrayAdapter(this , android.R.layout.simple_expandable_list_item_1 , langs)
                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spin_lang.adapter = ad

                spin_lang.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        loc = when (p2) {
                            2 ->  "hi"
                            1 ->  "gu"
                            else ->  "en"
                        }

                        val locale = Locale(loc)
                        changeLang(locale)

                        val sp = getSharedPreferences("lang.txt" , MODE_PRIVATE)
                        val editor = sp.edit()
                        editor.clear()
                        editor.putString("language" , loc)
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }

                btn_register.setOnClickListener {
                    val registerActivity = Intent(this ,RegisterActivity ::class.java)
                    registerActivity.putExtra("lang" , loc)
                    startActivity(registerActivity)
                }

                btn_login.setOnClickListener {

                    val MobileNo : String =   et_mobile.text.toString()
                    val Password : String = et_pass.text.toString()
                    val DeviceId: String = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
                    val DeviceCompanyName : String = Build.BRAND
                    val DeviceModel : String = Build.MODEL
                    val AndroidVersion : String = Build.VERSION.RELEASE

                    Log.e("MyActivity", "Mobile No : " + MobileNo
                            + " \n Password " + Password
                            + " \n Device Id " + DeviceId
                            + " \n Company " + DeviceCompanyName
                            + " \n Model " + DeviceModel
                            + " \n Version " + AndroidVersion)



                    val Dairy = JSONObject();
                    Dairy.put("MobileNo", MobileNo)
                    Dairy.put("Password", Password)
                    Dairy.put("DeviceId", DeviceId)
                    Dairy.put("DeviceCompanyName", DeviceCompanyName)
                    Dairy.put("DeviceModel", DeviceModel)
                    Dairy.put("AndroidVersion", AndroidVersion)

                    val data = JSONObject()
                    data.put("Dairy" ,Dairy )

                  /*  val jsonLoginRequest = object : JsonObjectRequest(Request.Method.POST, URL, data,
                        Response.Listener {

                            auth_token = it.getString("authtoken").toString()
                            Toast.makeText(this , auth_token, Toast.LENGTH_SHORT).show()
                        },

                        Response.ErrorListener {
                            Toast.makeText(this , "There is some error" , Toast.LENGTH_SHORT).show()
                        }
                    )
                    { //  Headers

                        override fun getHeaders(): Map<String, String> {
                            // Create HashMap of your Headers as the example provided below

                            val headers = HashMap<String, String>()
                            headers["Content-Type"] = "application/json"
                            headers["app_id"] = APP_ID
                            headers["app_key"] = API_KEY

                            return headers
                        }
                    }
*/

                    val dashBoardActivity = Intent(this , DashboardActivity::class.java)
                    dashBoardActivity.putExtra("lang" , loc)
                    startActivity(dashBoardActivity)
                }

            }

            private fun changeLang(locale: Locale) {
                val conf = resources.configuration
                conf.setLocale(locale)
                val res = resources
                res.updateConfiguration(conf , resources.displayMetrics)
                btn_register.setText(R.string.tv_register)
                btn_login.setText(R.string.tv_login)
                tv_forgot.setText(R.string.tv_forgot_password)
                tv_orx.setText(R.string.tv_or)
                et_pass.setHint(R.string.et_password)
                et_mobile.setHint(R.string.et_mobile_number)
            }
        }




