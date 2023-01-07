package com.example.tictactoe.model

import androidx.lifecycle.ViewModel
import com.example.tictactoe.enum.Mark
import com.example.tictactoe.enum.PlayerTurn

class MainModel: ViewModel() {
    var board = Array(3) { Array(3){ Mark.NONE } }
    var playerTurn : PlayerTurn = PlayerTurn.X_TURN
    var p1Score = 0
    var p2Score = 0
}