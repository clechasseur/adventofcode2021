package io.github.clechasseur.adventofcode2021

object Day6 {
    private val data = listOf(1,3,4,1,5,2,1,1,1,1,5,1,5,1,1,1,1,3,1,1,1,1,1,1,1,2,1,5,1,1,1,1,1,4,4,1,1,4,1,1,2,3,1,5,1,4,1,2,4,1,1,1,1,1,1,1,1,2,5,3,3,5,1,1,1,1,4,1,1,3,1,1,1,2,3,4,1,1,5,1,1,1,1,1,2,1,3,1,3,1,2,5,1,1,1,1,5,1,5,5,1,1,1,1,3,4,4,4,1,5,1,1,4,4,1,1,1,1,3,1,1,1,1,1,1,3,2,1,4,1,1,4,1,5,5,1,2,2,1,5,4,2,1,1,5,1,5,1,3,1,1,1,1,1,4,1,2,1,1,5,1,1,4,1,4,5,3,5,5,1,2,1,1,1,1,1,3,5,1,2,1,2,1,3,1,1,1,1,1,4,5,4,1,3,3,1,1,1,1,1,1,1,1,1,5,1,1,1,5,1,1,4,1,5,2,4,1,1,1,2,1,1,4,4,1,2,1,1,1,1,5,3,1,1,1,1,4,1,4,1,1,1,1,1,1,3,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,5,1,2,1,1,1,1,1,1,1,1,1)

    fun part1(): Int = fishCount(data, 80)

    fun part2(): Long {
        val fishes = data.groupBy { it }.mapValues { (_, l) ->  l.count().toLong() }
        return fishCount2(fishes, 256)
    }

    private tailrec fun fishCount(fishes: List<Int>, days: Int): Int = if (days == 0) {
        fishes.count()
    } else {
        fishCount(fishes.flatMap { fish -> if (fish > 0) {
            listOf(fish - 1)
        } else {
            listOf(6, 8)
        } }, days - 1)
    }

    private tailrec fun fishCount2(fishes: Map<Int, Long>, days: Int): Long = if (days == 0) {
        fishes.values.sum()
    } else {
        var babies = 0L
        val newFishes = fishes.map { (fish, c) -> if (fish > 0) {
            (fish - 1) to c
        } else {
            babies += c
            6 to c
        } }.groupBy { it.first }.mapValues { (_, l) -> l.sumOf { it.second } }.toMutableMap()
        if (babies > 0) {
            newFishes[8] = babies
        }
        fishCount2(newFishes, days - 1)
    }
}
