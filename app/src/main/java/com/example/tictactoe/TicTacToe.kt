package com.example.tictactoe

class TicTacToe {
    private var currentPlayer = "X"
    private var board = Array(3) { Array(3) { "" } }
    private var winsX = 0
    private var winsO = 0
    private var draws = 0

    fun makeMove(row: Int, col: Int): Boolean {
        if (board[row][col].isEmpty()) {
            board[row][col] = currentPlayer
            return true
        }
        return false
    }

    fun getCurrentPlayerSymbol(): String {
        return currentPlayer
    }

    fun switchPlayer() {
        currentPlayer = if (currentPlayer == "X") "O" else "X"
    }

    fun isGameWon(): Boolean {
        // Check rows
        for (row in board) {
            if (row[0] == currentPlayer && row[1] == currentPlayer && row[2] == currentPlayer) {
                updateWins(currentPlayer)
                return true
            }
        }
        // Check columns
        for (col in 0..2) {
            if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
                updateWins(currentPlayer)
                return true
            }
        }
        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            updateWins(currentPlayer)
            return true
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            updateWins(currentPlayer)
            return true
        }
        return false
    }

    fun isGameDraw(): Boolean {
        for (row in board) {
            for (cell in row) {
                if (cell.isEmpty()) {
                    return false
                }
            }
        }
        updateDraws()
        return true
    }

    fun resetGame() {
        currentPlayer = "X"
        board = Array(3) { Array(3) { "" } }
    }

    fun getWinsX(): Int {
        return winsX
    }

    fun getWinsO(): Int {
        return winsO
    }

    fun getDraws(): Int {
        return draws
    }

    private fun updateWins(player: String) {
        if (player == "X") {
            winsX++
        } else {
            winsO++
        }
    }

    private fun updateDraws() {
        draws++
    }
}
