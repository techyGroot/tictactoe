package com.kukki.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kukki.tictactoe.databinding.ActivityMainBinding
import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()
    private var activeplayer = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }

    fun restartGame(view: View) {
        player1.clear()
        player2.clear()
        binding.winnerText.text = ""
    }


    fun checkCell(view: View) {
        val cellSelected = view as Button
        var cellID = 0
        when (cellSelected.id) {
            R.id.cell1 -> cellID = 1
            R.id.cell2 -> cellID = 2
            R.id.cell3 -> cellID = 3
            R.id.cell4 -> cellID = 4
            R.id.cell5 -> cellID = 5
            R.id.cell6 -> cellID = 6
            R.id.cell7 -> cellID = 7
            R.id.cell8 -> cellID = 8
            R.id.cell9 -> cellID = 9
        }

        playGame(cellID, cellSelected)
    }


    private fun playGame(cellID: Int, cellSelected: Button) {
        if (activeplayer == 1) {
            cellSelected.text = "X"
            cellSelected.textSize = 25F
            player1.add(cellID)
            activeplayer = 2
            autoPlay()
        } else {
            cellSelected.text = "0"
            cellSelected.textSize = 25F
            player2.add(cellID)
            activeplayer = 1
        }

        cellSelected.isEnabled = false

        checkWinner()
    }

    private fun autoPlay() {
        val emptyCells = ArrayList<Int>()
        for (cellID in 1..9) {
            if (!(player1.contains(cellID) || player2.contains(cellID))) {
                emptyCells.add(cellID)
            }
        }
        val r = Random1
        val randIndex = r.nextInt(emptyCells.size - 0) + 0
        val cellID = emptyCells[randIndex]

        val cellSelected: Button = when (cellID) {
            1 -> binding.cell1
            2 -> binding.cell2
            3 -> binding.cell3
            4 -> binding.cell4
            5 -> binding.cell5
            6 -> binding.cell6
            7 -> binding.cell7
            8 -> binding.cell8
            9 -> binding.cell9
            else -> binding.cell1

        }

        playGame(cellID, cellSelected)
    }


    @SuppressLint("SetTextI18n")
    private fun checkWinner() {
        var winner = -1
        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 2
        }
        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        // col 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }


        // col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }


        // col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //diagonal
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }


        if (winner != -1) {
            binding.winnerText.text = "Player $winner wins the game"
//            Toast.makeText(this, "Player $winner wins the game", Toast.LENGTH_LONG).show()

        } else {
            binding.winnerText.text = "Game Draw"
//            Toast.makeText(this, "Game Draws", Toast.LENGTH_LONG).show()
        }
    }

}