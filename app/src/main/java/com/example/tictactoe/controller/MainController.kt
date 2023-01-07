package com.example.tictactoe.controller

import android.widget.Button
import com.example.tictactoe.MainActivity
import com.example.tictactoe.R
import com.example.tictactoe.enum.Mark
import com.example.tictactoe.enum.PlayerTurn
import com.example.tictactoe.model.MainModel

class MainController(var mainModel: MainModel, var view: MainActivity) {
    fun setGame() {
        view.setBoard()
        updateView()
    }
    fun setCell(a0: Button, row: Int, col: Int) {
        if(mainModel.board[row][col] != Mark.NONE){
            a0.text = if(mainModel.board[row][col] == Mark.X) "X" else "O"
        }
    }
    private fun updateView() {
        var currentTurn = "${view.resources.getString(R.string.Turn)} ${view.resources.getString(R.string.PlayerX)}"
        if(mainModel.playerTurn == PlayerTurn.O_TURN)
            currentTurn = "${view.resources.getString(R.string.Turn)} ${view.resources.getString(R.string.PlayerO)}"
        val xPlayerScore = "${view.resources.getString(R.string.PlayerX)}\n" + mainModel.p1Score.toString()
        val oPlayerScore = "${view.resources.getString(R.string.PlayerO)}\n" + mainModel.p2Score.toString()
        view.setInfo(xPlayerScore, oPlayerScore, currentTurn)
    }
    fun boardSelected(selected: Button, i: Int, j: Int) {
        if(mainModel.board[i][j] == Mark.NONE){
            if(mainModel.playerTurn == PlayerTurn.X_TURN) {
                mainModel.board[i][j] = Mark.X
                selected.text = "X"
                mainModel.playerTurn = PlayerTurn.O_TURN
                winnerCheck(Mark.X, "X")
            } else {
                mainModel.board[i][j] = Mark.O
                selected.text = "O"
                mainModel.playerTurn = PlayerTurn.X_TURN
                winnerCheck(Mark.O, "O")
            }
            updateView()
        }
    }
    private fun winnerCheck(cell: Mark, player: String){
        if(checkRow(cell) || checkCol(cell) || checkSlant(cell))
            winner(player)
        else if(tieCheck())
            view.winner(view.resources.getString(R.string.TieGame))
    }
    private fun tieCheck(): Boolean {
        for(row in 0..2)
            for(col in 0..2)
                if(mainModel.board[row][col] == Mark.NONE)
                    return false
        return true
    }
    private fun checkRow(cell: Mark): Boolean {
        return  ( mainModel.board[0][0] == cell && mainModel.board[0][1] == cell && mainModel.board[0][2] == cell ) ||
                ( mainModel.board[1][0] == cell && mainModel.board[1][1] == cell && mainModel.board[1][2] == cell ) ||
                ( mainModel.board[2][0] == cell && mainModel.board[2][1] == cell && mainModel.board[2][2] == cell )
    }
    private fun checkCol(cell: Mark): Boolean {
        return  ( mainModel.board[0][0] == cell && mainModel.board[1][0] == cell && mainModel.board[2][0] == cell ) ||
                ( mainModel.board[0][1] == cell && mainModel.board[1][1] == cell && mainModel.board[2][1] == cell ) ||
                ( mainModel.board[0][2] == cell && mainModel.board[1][2] == cell && mainModel.board[2][2] == cell )
    }
    private fun checkSlant(cell: Mark): Boolean {
        return  ( mainModel.board[0][0] == cell && mainModel.board[1][1] == cell && mainModel.board[2][2] == cell ) ||
                ( mainModel.board[2][0] == cell && mainModel.board[1][1] == cell && mainModel.board[0][2] == cell )
    }
    private fun winner(s: String){
        var winner = "${view.resources.getString(R.string.PlayerO)} ${view.resources.getString(R.string.Won)}"
        if(s == "X")
            winner = "${view.resources.getString(R.string.PlayerX)} ${view.resources.getString(R.string.Won)}"
        addScore(s)
        view.winner(winner)
    }
    private fun addScore(s: String){
        if(s == "X")
            mainModel.p1Score++
        else if(s == "O")
            mainModel.p2Score++
    }
    fun clearBoard(){
        for(row in 0..2)
            for(col in 0..2)
                mainModel.board[row][col] = Mark.NONE
        view.clearBoard()
        updateView()
    }
}