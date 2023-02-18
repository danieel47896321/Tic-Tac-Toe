package com.example.tictactoe.controller

import android.widget.ImageView
import com.example.tictactoe.Play
import com.example.tictactoe.R
import com.example.tictactoe.enum.Mark
import com.example.tictactoe.enum.PlayerTurn
import com.example.tictactoe.model.PlayModel

class PlayController(private var view: Play, private var playModel: PlayModel) {
    fun setGame(playerXName: String, playerOName: String) {
        view.setBoard()
        playModel.p1Name = playerXName
        playModel.p2Name = playerOName
        updateView()
    }
    private fun updateView() {
        view.setScore(playModel.p1Score.toString(), playModel.p2Score.toString())
        view.updateTurn(playModel.playerTurn)
    }
    fun setCell(a0: ImageView, row: Int, col: Int) {
        if(playModel.board[row][col] != Mark.NONE){
            if(playModel.board[row][col] == Mark.X) {
                a0.setImageResource(R.drawable.x)
            } else {
                a0.setImageResource(R.drawable.o)
            }
        }
    }
    fun boardSelected(selected: ImageView, i: Int, j: Int) {
        if(playModel.board[i][j] == Mark.NONE){
            if(playModel.playerTurn == PlayerTurn.X_TURN) {
                playModel.board[i][j] = Mark.X
                selected.setImageResource(R.drawable.x)
                if (!winnerCheck(Mark.X, "X")) {
                    playModel.playerTurn = PlayerTurn.O_TURN
                }
            } else {
                playModel.board[i][j] = Mark.O
                selected.setImageResource(R.drawable.o)
                if (!winnerCheck(Mark.O, "O")) {
                    playModel.playerTurn = PlayerTurn.X_TURN
                }
            }
            updateView()
        }
    }
    private fun winnerCheck(cell: Mark, player: String): Boolean {
        if(checkRow(cell) || checkCol(cell) || checkSlant(cell)) {
            winner(player)
            return true
        }
        else if(tieCheck()) {
            playModel.winner = view.resources.getString(R.string.TieGame)
            view.winner(playModel.winner)
            return true
        }
        return false
    }
    private fun tieCheck(): Boolean {
        for(row in 0..2)
            for(col in 0..2)
                if(playModel.board[row][col] == Mark.NONE)
                    return false
        return true
    }
    private fun checkRow(cell: Mark): Boolean {
        return  ( playModel.board[0][0] == cell && playModel.board[0][1] == cell && playModel.board[0][2] == cell ) ||
                ( playModel.board[1][0] == cell && playModel.board[1][1] == cell && playModel.board[1][2] == cell ) ||
                ( playModel.board[2][0] == cell && playModel.board[2][1] == cell && playModel.board[2][2] == cell )
    }
    private fun checkCol(cell: Mark): Boolean {
        return  ( playModel.board[0][0] == cell && playModel.board[1][0] == cell && playModel.board[2][0] == cell ) ||
                ( playModel.board[0][1] == cell && playModel.board[1][1] == cell && playModel.board[2][1] == cell ) ||
                ( playModel.board[0][2] == cell && playModel.board[1][2] == cell && playModel.board[2][2] == cell )
    }
    private fun checkSlant(cell: Mark): Boolean {
        return  ( playModel.board[0][0] == cell && playModel.board[1][1] == cell && playModel.board[2][2] == cell ) ||
                ( playModel.board[2][0] == cell && playModel.board[1][1] == cell && playModel.board[0][2] == cell )
    }
    private fun winner(s: String){
        playModel.winner = "${playModel.p2Name} (O) ${view.resources.getString(R.string.Won)}"
        if(s == "X")
            playModel.winner = "${playModel.p1Name} (X) ${view.resources.getString(R.string.Won)}"
        addScore(s)
        view.winner(playModel.winner)
    }
    private fun addScore(s: String){
        if(s == "X")
            playModel.p1Score++
        else if(s == "O")
            playModel.p2Score++
    }
    fun clearBoard(){
        for(row in 0..2)
            for(col in 0..2)
                playModel.board[row][col] = Mark.NONE
        view.clearBoard()
        updateView()
    }
}