package io.github.clechasseur.adventofcode2021

object Day21 {
    private const val player1StartingPos = 8
    private const val player2StartingPos = 3

    fun part1(): Int = playGame(DeterministicDice())

    private interface Die {
        fun roll(): Int
    }

    private class Player(var pos: Int) {
        var score: Int = 0

        fun move(die: Die): Int {
            val moveBy = die.roll() + die.roll() + die.roll()
            pos = (pos + moveBy - 1) % 10 + 1
            score += pos
            return 3
        }
    }

    private fun playGame(die: Die): Int {
        val player1 = Player(player1StartingPos)
        val player2 = Player(player2StartingPos)
        var rolls = 0
        while (true) {
            rolls += player1.move(die)
            if (player1.score >= 1000) {
                return rolls * player2.score
            }
            rolls += player2.move(die)
            if (player2.score >= 1000) {
                return rolls * player1.score
            }
        }
    }

    private class DeterministicDice : Die {
        var next: Int = 1

        override fun roll(): Int {
            val rolled = next++
            if (next > 100) {
                next = 1
            }
            return rolled
        }
    }
}
