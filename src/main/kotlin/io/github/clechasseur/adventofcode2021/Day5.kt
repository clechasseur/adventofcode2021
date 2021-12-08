package io.github.clechasseur.adventofcode2021

import io.github.clechasseur.adventofcode2021.data.Day5Data
import io.github.clechasseur.adventofcode2021.util.Pt

object Day5 {
    private val data = Day5Data.data

    private val linesRegex = """^(\d+),(\d+) -> (\d+),(\d+)$""".toRegex()

    private val lines: List<Pair<Pt, Pt>> = data.lines().map {
        val match = linesRegex.matchEntire(it) ?: error("Wrong line format: $it")
        val (x1, y1, x2, y2) = match.destructured
        Pt(x1.toInt(), y1.toInt()) to Pt(x2.toInt(), y2.toInt())
    }

    fun part1(): Int = 0
}
