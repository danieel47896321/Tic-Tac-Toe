package com.example.tictactoe

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.tictactoe.controller.MainController

class MainActivity : AppCompatActivity() {
    private lateinit var mainController: MainController
    private lateinit var textViewTurn: TextView
    private lateinit var textViewPlayerX: TextView
    private lateinit var textViewPlayerO: TextView
    private lateinit var a0: Button
    private lateinit var a1: Button
    private lateinit var a2: Button
    private lateinit var b0: Button
    private lateinit var b1: Button
    private lateinit var b2: Button
    private lateinit var c0: Button
    private lateinit var c1: Button
    private lateinit var c2: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        mainController = MainController(this)
        textViewTurn = findViewById(R.id.textViewTurn)
        textViewPlayerX = findViewById(R.id.textViewPlayerX)
        textViewPlayerO = findViewById(R.id.textViewPlayerO)
        a0 = findViewById(R.id.a0)
        a1 = findViewById(R.id.a1)
        a2 = findViewById(R.id.a2)
        b0 = findViewById(R.id.b0)
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        c0 = findViewById(R.id.c0)
        c1 = findViewById(R.id.c1)
        c2 = findViewById(R.id.c2)
        mainController.setGame()
        setButtonClick()
    }
    fun mainWhenCase(id: String){
        when(id){
            "setBoard" -> { setBoard() }
            "setInfo" -> { setInfo(mainController.getPlayerX(), mainController.getPlayerY(), mainController.getCurrentTurn()) }
            "clearBoard" -> { clearBoard("") }
            "winner" -> { winner(mainController.getWinner()) }

        }
    }
    private fun setButtonClick() {
        a0.setOnClickListener{ mainController.boardSelected(a0, 0, 0) }
        a1.setOnClickListener{ mainController.boardSelected(a1, 0, 1) }
        a2.setOnClickListener{ mainController.boardSelected(a2, 0, 2) }
        b0.setOnClickListener{ mainController.boardSelected(b0, 1, 0) }
        b1.setOnClickListener{ mainController.boardSelected(b1, 1, 1) }
        b2.setOnClickListener{ mainController.boardSelected(b2, 1, 2) }
        c0.setOnClickListener{ mainController.boardSelected(c0, 2, 0) }
        c1.setOnClickListener{ mainController.boardSelected(c1, 2, 1) }
        c2.setOnClickListener{ mainController.boardSelected(c2, 2, 2) }
    }
    private fun setBoard() {
        mainController.setCell(a0, 0, 0)
        mainController.setCell(a1, 0, 1)
        mainController.setCell(a2, 0, 2)
        mainController.setCell(b0, 1, 0)
        mainController.setCell(b1, 1, 1)
        mainController.setCell(b2, 1, 2)
        mainController.setCell(c0, 2, 0)
        mainController.setCell(c1, 2, 1)
        mainController.setCell(c2, 2, 2)
    }
    private fun setInfo(playerX: String, playerO: String, currentTurn: String){
        textViewPlayerX.text = playerX
        textViewPlayerO.text = playerO
        textViewTurn.text = currentTurn
    }
    private fun winner(message: String) {
        mainController.clearBoard()
        val builder = AlertDialog.Builder(this, R.style.AlertDialog)
        builder.setTitle(resources.getString(R.string.Finish))
        builder.setMessage(message)
        builder.setPositiveButton(R.string.OK) { _, _ -> }
        builder.setCancelable(false)
        builder.show()
    }
    private fun clearBoard(text: String) {
        a0.text = text
        a1.text = text
        a2.text = text
        b0.text = text
        b1.text = text
        b2.text = text
        c0.text = text
        c1.text = text
        c2.text = text
    }
}