package com.fiknaufalh.coffeenutrition.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fiknaufalh.coffeenutrition.R
import com.fiknaufalh.coffeenutrition.activities.DetailActivity
import com.fiknaufalh.coffeenutrition.adapters.ListCoffeeAdapter.ListViewHolder
import com.fiknaufalh.coffeenutrition.data.Coffee

class ListCoffeeAdapter(private val listCoffee: ArrayList<Coffee>):
    RecyclerView.Adapter<ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgCoffee: ImageView = itemView.findViewById(R.id.img_item_coffee)
        val tvName : TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription : TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(
            R.layout.coffee_card_view,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, image) = listCoffee[position]

        Glide.with(holder.itemView.context)
            .load(image)
            .into(holder.imgCoffee)

        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_coffee", listCoffee[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listCoffee.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Coffee)
    }
}