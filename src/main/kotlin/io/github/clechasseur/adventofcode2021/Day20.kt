package io.github.clechasseur.adventofcode2021

import io.github.clechasseur.adventofcode2021.data.Day20Data
import io.github.clechasseur.adventofcode2021.util.Pt

object Day20 {
    private val algoData = Day20Data.algoData
    private val imageData = Day20Data.imageData

    fun part1(): Int = 0

    private object Algo {
        fun `enhance!`(pixels: List<Boolean>): Boolean = false
    }

    private class Image(val data: Map<Pt, Boolean>, val unknown: Boolean) {
        operator fun get(pt: Pt): Boolean = data.getOrDefault(pt, unknown)
    }

    private fun String.toImage() = Image(lines().flatMapIndexed { y, line ->
        line.mapIndexed { x, c -> Pt(x, y) to (c == '#') }
    }.toMap(), false)
}
