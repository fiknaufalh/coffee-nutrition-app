package com.fiknaufalh.coffeenutrition.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fiknaufalh.coffeenutrition.R
import com.fiknaufalh.coffeenutrition.adapters.ListCoffeeAdapter
import com.fiknaufalh.coffeenutrition.data.Coffee

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var rvCoffees: RecyclerView
    private val list = ArrayList<Coffee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAboutPage: ImageButton = findViewById(R.id.about_page)
        btnAboutPage.setOnClickListener(this)

        rvCoffees = findViewById(R.id.rv_coffees)
        rvCoffees.setHasFixedSize(true)

        list.addAll(getListCoffees())
        showRecyclerList()
    }

    private fun getListCoffees(): ArrayList<Coffee> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataImage = resources.getStringArray(R.array.data_image)
        val dataCalories = resources.getStringArray(R.array.data_calories)
        val dataProtein = resources.getStringArray(R.array.data_protein)
        val dataFats = resources.getStringArray(R.array.data_fats)
        val dataCarbs = resources.getStringArray(R.array.data_carbs)
        val dataSugar = resources.getStringArray(R.array.data_sugar)

        val listCoffee = ArrayList<Coffee>()
        for (i in dataName.indices) {
            val coffee = Coffee(
                dataName[i],
                dataDescription[i],
                dataImage[i],
                dataCalories[i].toDouble(),
                dataProtein[i].toDouble(),
                dataFats[i].toDouble(),
                dataCarbs[i].toDouble(),
                dataSugar[i].toDouble()
            )
            listCoffee.add(coffee)
        }

        return listCoffee
    }

    private fun showRecyclerList() {
        rvCoffees.layoutManager = LinearLayoutManager(this)
        val listCoffeeAdapter = ListCoffeeAdapter(list)
        rvCoffees.adapter = listCoffeeAdapter
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.about_page -> {
                val aboutPageIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutPageIntent)
            }
        }
    }
}