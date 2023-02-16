package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.tictactoe.controller.SetPlayersController
import com.example.tictactoe.model.SetPlayersModel
import com.google.android.material.textfield.TextInputLayout

class SetPlayers : AppCompatActivity() {
    private lateinit var setPlayersController: SetPlayersController
    private lateinit var setPlayersModel: SetPlayersModel
    private lateinit var textInputLayoutPlayerX: TextInputLayout
    private lateinit var textInputLayoutPlayerO: TextInputLayout
    private lateinit var buttonPlay: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_players)
        init()
    }
    private fun init(){
        setPlayersModel = ViewModelProvider(this)[SetPlayersModel::class.java]
        setPlayersController = SetPlayersController(this, setPlayersModel)
        textInputLayoutPlayerX = findViewById(R.id.textInputLayoutPlayerX)
        textInputLayoutPlayerO = findViewById(R.id.textInputLayoutPlayerO)
        buttonPlay = findViewById(R.id.buttonPlay)
        setPlayersController.setNames()
        playButton()
        saveInput()
    }
    private fun saveInput() {
        textInputLayoutPlayerX.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setPlayersController.updatePlayerXName(textInputLayoutPlayerX.editText!!.text.toString())
            }
            override fun afterTextChanged(s: Editable) {}
        })
        textInputLayoutPlayerO.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setPlayersController.updatePlayerOName(textInputLayoutPlayerO.editText!!.text.toString())
            }
            override fun afterTextChanged(s: Editable) {}
        })
    }
    private fun playButton(){
        buttonPlay.setOnClickListener {
            setPlayersController.playButton(textInputLayoutPlayerX.editText?.text.toString(), textInputLayoutPlayerO.editText?.text.toString())
        }
    }
    fun setNames(playerX: String, PlayerO: String) {
        textInputLayoutPlayerX.editText?.setText(playerX)
        textInputLayoutPlayerO.editText?.setText(PlayerO)
    }
    fun setPlayerXHelper(text: String) {
        textInputLayoutPlayerX.helperText = text
    }
    fun setPlayerOHelper(text: String) {
        textInputLayoutPlayerO.helperText = text
    }
}