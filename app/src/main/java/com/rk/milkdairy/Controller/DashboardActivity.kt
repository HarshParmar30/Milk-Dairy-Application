package com.rk.milkdairy.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView

import com.rk.milkdairy.R
import com.rk.milkdairy.databinding.ActivityDashboardBinding
import java.util.*

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private  lateinit var  infoIcon : ImageView
    private lateinit var lang_btn : ImageView

    private lateinit var customer_manage : CardView
    private lateinit var account_settings : CardView


    private lateinit var text_milk_collection : TextView
    private lateinit var text_customer_manage : TextView
    private lateinit var sell_milk_products : TextView
    private lateinit var text_print : TextView
    private lateinit var text_backup : TextView
    private lateinit var text_reports : TextView
    private lateinit var text_rate_settings : TextView
    private lateinit var text_help : TextView
    private lateinit var text_account : TextView







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

      // binding couses error
     //   binding = ActivityDashboardBinding.inflate(layoutInflater)
     //   setContentView(binding.root)

        infoIcon = findViewById(R.id.info_icon)
        infoIcon.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setView(layoutInflater.inflate(R.layout.info_alert , null))
            alert.create().show()
        }

        lang_btn = findViewById(R.id.lang_elipses)
        registerForContextMenu(lang_btn)

        customer_manage = findViewById(R.id.cust_manage)
        account_settings = findViewById(R.id.act_settings)

        customer_manage.setOnClickListener()
        {
            val CustomerManageActivity = Intent(this ,CustomerManagementActivity ::class.java)
            startActivity(CustomerManageActivity)
        }
        account_settings.setOnClickListener()
        {
            val AccountSetting = Intent(this ,AccountSettingsActivity ::class.java)
            startActivity(AccountSetting)
        }
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val mfi = menuInflater
        mfi.inflate(R.menu.menu , menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var local_type = "en"

//           changing locale
        if(item.itemId == R.id.action_hindi){
            local_type = "hi"
        }else if(item.itemId == R.id.action_gujarati){
            local_type = "gu"
        }else if(item.itemId == R.id.action_english) {
            local_type = "en"
        }
        val local : Locale = Locale(local_type)
        changeLang(local)
        return super.onContextItemSelected(item)
    }

    private fun changeLang(locale: Locale) {
        val conf = resources.configuration
        conf.setLocale(locale)
        val res = resources
        res.updateConfiguration(conf , resources.displayMetrics)

        text_milk_collection = findViewById(R.id.tv_milk_collection)
        text_customer_manage = findViewById(R.id.tv_customer_manage)
        sell_milk_products = findViewById(R.id.tv_sell_milk)
        text_print = findViewById(R.id.tv_print)
        text_backup = findViewById(R.id.tv_backup)
        text_rate_settings = findViewById(R.id.rate_settings)
        text_help = findViewById(R.id.help_and_support)
        text_account = findViewById(R.id.tv_account_settings)
        text_reports = findViewById(R.id.tv_reports)


        text_milk_collection .setText(R.string.milk_collection)
        text_customer_manage .setText(R.string.customer_management)
        sell_milk_products .setText(R.string.sell_milk_amp_products)
        text_print .setText(R.string.print_amp_message_settigs)
        text_backup .setText(R.string.backup)
        text_reports.setText(R.string.all_reports)
        text_rate_settings.setText(R.string.rate_setting)
        text_help.setText(R.string.help_support)
        text_reports.setText(R.string.account_settings)

    }
}