package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.controller.PlayController
import com.example.tictactoe.enum.PlayerTurn
import com.example.tictactoe.model.PlayModel

class Play : AppCompatActivity() {
    private lateinit var mainController: PlayController
    private lateinit var playModel: PlayModel
    private lateinit var textViewPlayerXScore: TextView
    private lateinit var textViewPlayerOScore: TextView
    private lateinit var textViewPlayerXName: TextView
    private lateinit var textViewPlayerOName: TextView
    private lateinit var linearLayoutX: LinearLayout
    private lateinit var linearLayoutO: LinearLayout
    private lateinit var a0: ImageView
    private lateinit var a1: ImageView
    private lateinit var a2: ImageView
    private lateinit var b0: ImageView
    private lateinit var b1: ImageView
    private lateinit var b2: ImageView
    private lateinit var c0: ImageView
    private lateinit var c1: ImageView
    private lateinit var c2: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        init()
    }
    private fun init(){
        val playerXName = (intent.getSerializableExtra("playerXName") as? String)!!
        val playerOName = (intent.getSerializableExtra("playerOName") as? String)!!
        playModel = ViewModelProvider(this)[PlayModel::class.java]
        mainController = PlayController(this, playModel)
        textViewPlayerXScore = findViewById(R.id.textViewPlayerXScore)
        textViewPlayerOScore = findViewById(R.id.textViewPlayerOScore)
        textViewPlayerXName = findViewById(R.id.textViewPlayerXName)
        textViewPlayerOName = findViewById(R.id.textViewPlayerOName)
        linearLayoutX = findViewById(R.id.linearLayoutX)
        linearLayoutO = findViewById(R.id.linearLayoutO)
        textViewPlayerXName.text = playerXName
        textViewPlayerOName.text = playerOName
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
    fun setBoard() {
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
    fun setScore(playerXScore: String, playerOScore: String){
        textViewPlayerXScore.text = playerXScore
        textViewPlayerOScore.text = playerOScore
    }
    fun winner(message: String) {
        mainController.clearBoard()
        val builder = AlertDialog.Builder(this, R.style.AlertDialog)
        builder.setTitle(resources.getString(R.string.Finish))
        builder.setMessage(message)
        builder.setPositiveButton(R.string.OK) { _, _ -> }
        builder.setCancelable(false)
        builder.show()
    }
    fun clearBoard() {
        a0.setImageResource(0)
        a1.setImageResource(0)
        a2.setImageResource(0)
        b0.setImageResource(0)
        b1.setImageResource(0)
        b2.setImageResource(0)
        c0.setImageResource(0)
        c1.setImageResource(0)
        c2.setImageResource(0)
    }

    fun updateTurn(playerTurn: PlayerTurn) {
        if (playerTurn == PlayerTurn.X_TURN) {
            linearLayoutX.background = getDrawable(R.drawable.current_player_turn_frame)
            linearLayoutO.background = getDrawable(R.drawable.waiting_player_turn_frame)
        } else {
            linearLayoutX.background = getDrawable(R.drawable.waiting_player_turn_frame)
            linearLayoutO.background = getDrawable(R.drawable.current_player_turn_frame)
        }
    }
}