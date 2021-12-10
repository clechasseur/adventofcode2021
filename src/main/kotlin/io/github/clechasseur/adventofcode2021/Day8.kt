package io.github.clechasseur.adventofcode2021

import io.github.clechasseur.adventofcode2021.data.Day8Data

object Day8 {
    private val data = Day8Data.data

    private val lineInfos = data.lines().map { it.toLineInfo() }

    fun part1(): Int = lineInfos.flatMap { it.outputValues }.count { it.length in listOf(2, 3, 4, 7) }

    fun part2(): Int = 0

    private data class LineInfo(val signalPatterns: List<String>, val outputValues: List<String>)

    private fun String.toLineInfo(): LineInfo {
        val (patternsPart, outputPart) = split('|')
        return LineInfo(
            patternsPart.trim().split("\\s+".toRegex()),
            outputPart.trim().split("\\s+".toRegex())
        )
    }
}
