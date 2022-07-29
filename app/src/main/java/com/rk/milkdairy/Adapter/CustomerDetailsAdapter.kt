package com.rk.milkdairy.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rk.milkdairy.Model.CustomerModel
import com.rk.milkdairy.R

class CustomerDetailsAdapter (val custs : Array<CustomerModel>) : RecyclerView.Adapter<CustomerDetailsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView = view.findViewById(R.id.Idx)
        val name: TextView = view.findViewById(R.id.Namex)
        val rate: TextView = view.findViewById(R.id.Ratex)
        val mob_no: TextView = view.findViewById(R.id.MobileNox)
        val action: TextView = view.findViewById(R.id.emptyx)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.customer_details, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = custs.get(position)

        holder.id.setText(data.id)
        holder.name.setText(data.name)
        holder.rate.setText(data.rate)
        holder.mob_no.setText(data.mob_no)
        holder.action.setText("")

    }

    override fun getItemCount(): Int {
        return custs.size
    }
}