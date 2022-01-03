package io.github.clechasseur.adventofcode2021

import io.github.clechasseur.adventofcode2021.data.Day24Data
import kotlin.math.floor

object Day24 {
    private val data = Day24Data.data

    fun part1(): Long = generateSequence(99999999999999L) { it - 1L }.find { modelNumber ->
        val alu = ALU(data.lines(), modelNumber.toString())
        alu.execute()
        alu.ram['z'] == 0L
    } ?: error("No valid model number")

    private val inpRegex = """^inp (w|x|y|z)$""".toRegex()
    private val cmdRegex = """^(add|mul|div|mod|eql) (w|x|y|z) (w|x|y|z|-?\d+)$""".toRegex()

    private class ALU(program: List<String>, input: String) {
        private val ip = program.iterator()
        private val cin = input.iterator()

        val ram = mutableMapOf(
            'w' to 0L,
            'x' to 0L,
            'y' to 0L,
            'z' to 0L,
        )

        fun execute() {
            while (ip.hasNext()) {
                executeNext()
            }
        }

        private fun executeNext() {
            val line = ip.next()
            val inp = inpRegex.matchEntire(line)
            if (inp != null) {
                require(cin.hasNext()) { "Empty input stream" }
                ram[inp.groupValues[1].first()] = cin.nextChar().toString().toLong()
            } else {
                val cmd = cmdRegex.matchEntire(line) ?: error("Wrong program line: $line")
                val (instruction, dest, src) = cmd.destructured
                val destValue = getValue(dest)
                val srcValue = getValue(src)
                when (instruction) {
                    "add" -> ram[dest.first()] = destValue + srcValue
                    "mul" -> ram[dest.first()] = destValue * srcValue
                    "div" -> ram[dest.first()] = floor(destValue.toDouble() / srcValue.toDouble()).toLong()
                    "mod" -> ram[dest.first()] = destValue % srcValue
                    "eql" -> ram[dest.first()] = if (destValue == srcValue) 1L else 0L
                    else -> error("Invalid instruction: $instruction")
                }
            }
        }

        private fun getValue(varOrVal: String): Long = if (varOrVal.length == 1 && varOrVal.first().isLetter()) {
            ram[varOrVal.first()] ?: error("Wrong variable: $varOrVal")
        } else varOrVal.toLong()
    }
}
