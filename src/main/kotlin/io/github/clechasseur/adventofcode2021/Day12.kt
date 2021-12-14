package io.github.clechasseur.adventofcode2021

object Day12 {
    private val data = """
        start-kc
        pd-NV
        start-zw
        UI-pd
        HK-end
        UI-kc
        pd-ih
        ih-end
        start-UI
        kc-zw
        end-ks
        MF-mq
        HK-zw
        LF-ks
        HK-kc
        ih-HK
        kc-pd
        ks-pd
        MF-pd
        UI-zw
        ih-NV
        ks-HK
        MF-kc
        zw-NV
        NV-ks
    """.trimIndent()

    private val possiblePaths = data.lines().flatMap { line ->
        val (a, b) = line.split("-")
        listOf(a to b, b to a)
    }.groupBy { it.first }.mapValues { (_, l) -> l.map { it.second } }

    fun part1(): Int = paths(listOf("start"), false).count()

    fun part2(): Int = paths(listOf("start"), true).count()

    private fun paths(soFar: List<String>, canVisitOneTwice: Boolean): List<List<String>> {
        if (soFar.last() == "end") {
            return listOf(soFar)
        }
        val smallCavesVisited = soFar.filter { it.lowercase() == it }.groupBy { it }.mapValues { (_, l) -> l.size }
        val visitedOneTwice = smallCavesVisited.any { it.value == 2 }
        val possibleNextDestinations = possiblePaths[soFar.last()]!!.filter {
            it !in smallCavesVisited || (canVisitOneTwice && !visitedOneTwice && it != "start" && it != "end")
        }
        return possibleNextDestinations.flatMap { paths(soFar + it, canVisitOneTwice) }
    }
}
