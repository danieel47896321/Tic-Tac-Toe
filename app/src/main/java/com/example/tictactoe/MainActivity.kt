package com.example.tictactoe

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textViewTurn :TextView
    private lateinit var textViewPlayerX :TextView
    private lateinit var textViewPlayerO :TextView
    private lateinit var a0 :Button
    private lateinit var a1 :Button
    private lateinit var a2 :Button
    private lateinit var b0 :Button
    private lateinit var b1 :Button
    private lateinit var b2 :Button
    private lateinit var c0 :Button
    private lateinit var c1 :Button
    private lateinit var c2 :Button
    private var board = ArrayList<Button>()
    private var score = ArrayList<Int>()
    private lateinit var playerX :String
    private lateinit var playerO :String
    private lateinit var currentTurn :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
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
        board.add(a0)
        board.add(a1)
        board.add(a2)
        board.add(b0)
        board.add(b1)
        board.add(b2)
        board.add(c0)
        board.add(c1)
        board.add(c2)
        score.add(0)
        score.add(0)
        score[0] = 0
        score[1] = 0
        setInfo()
        setButtonClick()
    }
    private fun setInfo(){
        textViewPlayerX.text = "${resources.getString(R.string.PlayerX)}\n${score[0]}"
        textViewPlayerO.text = "${resources.getString(R.string.PlayerO)}\n${score[1]}"
        playerX = "${resources.getString(R.string.Turn)} ${resources.getString(R.string.PlayerX)}"
        playerO = "${resources.getString(R.string.Turn)} ${resources.getString(R.string.PlayerO)}"
        currentTurn = playerX
        textViewTurn.text = currentTurn
        for (button: Button in board){
            button.text = ""
        }
    }
    private fun setButtonClick(){
        for (button: Button in board){
            button.setOnClickListener{
                if(button.text == "") {
                    if (currentTurn == playerX) {
                        button.text = "X"
                        currentTurn = playerO
                        textViewTurn.text = currentTurn
                        winnerCheck("X")
                    } else {
                        button.text = "O"
                        currentTurn = playerX
                        textViewTurn.text = currentTurn
                        winnerCheck("O")
                    }
                }
            }
        }
    }
    private fun winnerCheck(s: String) {
        checkRow(s)
        checkCol(s)
        checkSlant(s)
    }
    private fun checkRow(s: String) {
        if( ( board[0].text == s && board[1].text == s && board[2].text == s ) ||
            ( board[3].text == s && board[4].text == s && board[5].text == s ) ||
            ( board[6].text == s && board[7].text == s && board[8].text == s ) ){
            winner(s)
        }
    }
    private fun checkCol(s: String) {
        if( ( board[0].text == s && board[3].text == s && board[6].text == s ) ||
            ( board[1].text == s && board[4].text == s && board[7].text == s ) ||
            ( board[2].text == s && board[5].text == s && board[8].text == s ) ){
            winner(s)
        }
    }
    private fun checkSlant(s: String) {
        if( ( board[0].text == s && board[4].text == s && board[8].text == s ) ||
            ( board[2].text == s && board[4].text == s && board[6].text == s )){
            winner(s)
        }
    }
    private fun winner(s: String){
        val builder = AlertDialog.Builder(this, R.style.AlertDialog)
        builder.setTitle(resources.getString(R.string.Finish))
        if(s == "X")
            builder.setMessage("${resources.getString(R.string.PlayerX)} ${resources.getString(R.string.Won)}")
        else
            builder.setMessage("${resources.getString(R.string.PlayerO)} ${resources.getString(R.string.Won)}")
        builder.setPositiveButton(R.string.OK) { _, _ ->
            addScore(s)
            setInfo()
        }
        builder.show()
    }
    private fun addScore(s: String){
        if(s == "X"){
            score[0]++
        }else{
            score[1]++
        }
    }
}