package com.example.tictactoe

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var game: TicTacToe
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        game = TicTacToe()

//        Strings resources
        val turnX = resources.getString(R.string.turn_x)
        val turnO = resources.getString(R.string.turn_o)
        val scoreXString = resources.getString(R.string.score_x, game.getWinsX())
        val scoreOString = resources.getString(R.string.score_o, game.getWinsO())
        val draw = resources.getString(R.string.draw, game.getDraws())

        val buttons = Array(3) { row ->
            Array(3) { col ->
                when (row) {
                    0 -> when (col) {
                        0 -> binding.btn00
                        1 -> binding.btn01
                        else -> binding.btn02
                    }
                    1 -> when (col) {
                        0 -> binding.btn10
                        1 -> binding.btn11
                        else -> binding.btn12
                    }
                    else -> when (col) {
                        0 -> binding.btn20
                        1 -> binding.btn21
                        else -> binding.btn22
                    }
                }
            }
        }

        for (row in buttons.indices) {
            for (col in buttons[row].indices) {
                buttons[row][col].setOnClickListener {
                    if (game.makeMove(row, col)) {
                        buttons[row][col].text = game.getCurrentPlayerSymbol()
                        if (game.getCurrentPlayerSymbol() == "X"){
                            binding.turn.text = turnO
                        }
                        if (game.getCurrentPlayerSymbol() == "O"){
                            binding.turn.text = turnX
                        }
                        if (game.isGameWon()) {
                            Toast.makeText(this, "${game.getCurrentPlayerSymbol()} wins!", Toast.LENGTH_SHORT).show()
                            if (game.getCurrentPlayerSymbol() == "X"){
                                binding.X.text = scoreXString
                            }
                            if (game.getCurrentPlayerSymbol() == "O"){
                                binding.O.text = scoreOString
                            }
                            game.resetGame()
                            resetBoard(buttons)
                            binding.turn.text = turnX

                        } else if (game.isGameDraw()) {
                            binding.draw.text = draw
                            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()
                            game.resetGame()
                            resetBoard(buttons)
                            binding.turn.text = turnX
                        } else {
                            game.switchPlayer()
                        }
                    }
                }
            }
        }
    }

    private fun resetBoard(buttons: Array<Array<AppCompatButton>>) {
        for (row in buttons) {
            for (button in row) {
                button.text = ""
            }
        }
    }
}

