package com.android.produceme

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val iv_user = findViewById<ImageView>(R.id.imageView2)
        val tv_id = findViewById<TextView>(R.id.tv_inputId)
        val btn_close = findViewById<Button>(R.id.btn_close)

        tv_id.text = intent.getStringExtra("id")
        val random = Random
        when(random.nextInt(5)){
            0 -> iv_user.setImageResource(R.drawable.swordsman)
            1 -> iv_user.setImageResource(R.drawable.assasin)
            2 -> iv_user.setImageResource(R.drawable.crossbow)
            3 -> iv_user.setImageResource(R.drawable.player)
            4 -> iv_user.setImageResource(R.drawable.alchemy)
        }

        btn_close.setOnClickListener {
            finish()
        }
    }
}
