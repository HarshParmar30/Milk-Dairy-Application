package com.rk.milkdairy.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rk.milkdairy.Adapter.CustomerDetailsAdapter
import com.rk.milkdairy.Model.CustomerModel
import com.rk.milkdairy.R


class CustomerManagementActivity : AppCompatActivity() {

    private lateinit var lang_btn : ImageView
    private lateinit var rv_cust : RecyclerView
    private lateinit var custs : Array<CustomerModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_management)


        custs = getCustomers()


        rv_cust = findViewById(R.id.rv_customer)
        val customer_adapter = CustomerDetailsAdapter(custs)
        rv_cust.layoutManager = LinearLayoutManager(this)
        rv_cust.adapter = customer_adapter

        lang_btn = findViewById(R.id.lang_elipses)
        registerForContextMenu(lang_btn)


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



    private fun getCustomers(): Array<CustomerModel> {

        // call api and create array of customers(Modal) and return it

        return arrayOf(
            CustomerModel("1","harsh" , "50" , "9992229991"),
            CustomerModel("2","nick" , "52" , "9927629991"),
            CustomerModel("3","john" , "40" , "9993429991"),
            CustomerModel("4","bob" , "50" , "9992789991"),


            )
    }
}


