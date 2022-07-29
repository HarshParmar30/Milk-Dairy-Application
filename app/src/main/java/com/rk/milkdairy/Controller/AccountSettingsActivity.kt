package com.rk.milkdairy.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toolbar
import com.rk.milkdairy.R
import com.rk.milkdairy.databinding.ActivityAccountSettingsBinding
import com.rk.milkdairy.databinding.ActivityDashboardBinding

class AccountSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountSettingsBinding
    private  lateinit var  nav_head : androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)
        

        nav_head = findViewById(R.id.toolbarName)
        nav_head.setTitle("Account Settings")
    }
}