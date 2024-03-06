package com.fiknaufalh.coffeenutrition.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fiknaufalh.coffeenutrition.R
import com.fiknaufalh.coffeenutrition.data.Coffee
import android.os.Build
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val KEY_COFFEE = "key_coffee"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataCoffee = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_COFFEE, Coffee::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_COFFEE)
        }

        val tvHeading: TextView = findViewById(R.id.tv_heading)
        val imgDetail: ImageView = findViewById(R.id.img_detail)
        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val btnShare: ImageButton = findViewById(R.id.btn_share)
        val btnBack: ImageButton = findViewById(R.id.btn_back)

        tvDetailName.text = dataCoffee?.name
        tvDetailDescription.text = dataCoffee?.description
        if (dataCoffee?.image != null) {
            Glide.with(imgDetail).load(dataCoffee.image).into(imgDetail)
        }

        btnShare.setOnClickListener(this)
        btnBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_back -> {
                finish()
            }
            R.id.btn_share -> {
                val desc : TextView = findViewById(R.id.tv_detail_description)
                val shareText = desc.text.toString()

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, shareText)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }
}