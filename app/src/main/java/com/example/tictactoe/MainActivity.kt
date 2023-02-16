package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private lateinit var textViewClickHere: TextView
    private lateinit var constraintLayout: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        constraintLayout = findViewById(R.id.constraintLayout)
        textViewClickHere = findViewById(R.id.textViewClickHere)
        setClick()
        setClickHereAnimation()
    }
    private fun setClickHereAnimation() {
        Thread {
            while (true) {
                val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.animation)
                textViewClickHere.startAnimation(animation)
                Thread.sleep(2000)
            }
        }.start()
    }
    private fun setClick() {
        constraintLayout.setOnClickListener {
            startActivity(Intent(this, SetPlayers::class.java))
            finish()
        }
    }
}