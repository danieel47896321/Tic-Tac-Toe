package com.example.tictactoe.controller

import android.content.Intent
import com.example.tictactoe.Play
import com.example.tictactoe.R
import com.example.tictactoe.SetPlayers
import com.example.tictactoe.model.SetPlayersModel

class SetPlayersController(private var view: SetPlayers, private var setPlayersModel: SetPlayersModel) {
    fun setNames() {
       view.setNames(setPlayersModel.playerX, setPlayersModel.playerO)
    }
    fun playButton(playerX: String, playerO: String) {
        setPlayersModel.playerX = playerX
        setPlayersModel.playerO = playerO
        if (checkInput(playerX, playerO)) {
            val intent = Intent(view, Play::class.java)
            intent.putExtra("playerXName", playerX)
            intent.putExtra("playerOName", playerO)
            view.startActivity(intent)
        }
    }
    private fun checkInput(playerX: String, playerO: String): Boolean {
        var flag = true
        if (playerX.isEmpty()) {
            view.setPlayerXHelper(view.resources.getString(R.string.Required))
            flag = false
        } else {
            view.setPlayerXHelper("")
        }

        if (playerO.isEmpty()) {
            view.setPlayerOHelper(view.resources.getString(R.string.Required))
            flag = false
        } else {
            view.setPlayerOHelper("")
        }
        return flag
    }
}