package io.github.clechasseur.adventofcode2021

import io.github.clechasseur.adventofcode2021.util.Pt

object Day23 {
    private val data = """
        #############
        #...........#
        ###D#A#D#C###
          #B#C#B#A#
          #########
    """.trimIndent()

    fun part1(): Int = 0

    private val energyCost = mapOf(
        'A' to 1,
        'B' to 10,
        'C' to 100,
        'D' to 1000,
    )

    private data class Room(val pts: List<Pt>, val owners: Char)

    private val rooms = listOf(
        Room(listOf(Pt(3, 2), Pt(3, 3)), 'A'),
        Room(listOf(Pt(5, 2), Pt(5, 3)), 'B'),
        Room(listOf(Pt(7, 2), Pt(7, 3)), 'C'),
        Room(listOf(Pt(9, 2), Pt(9, 3)), 'D'),
    )

    private val lava = listOf(
        Pt(3, 1),
        Pt(5, 1),
        Pt(7, 1),
        Pt(9, 1),
    )
}
