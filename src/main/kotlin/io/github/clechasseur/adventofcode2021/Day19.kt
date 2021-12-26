package io.github.clechasseur.adventofcode2021

import io.github.clechasseur.adventofcode2021.data.Day19Data
import io.github.clechasseur.adventofcode2021.util.Pt3D
import kotlin.math.abs

object Day19 {
    private val data = Day19Data.data

    fun part1(): Int = 0

    private class Scanner(val id: Int, val beacons: List<Pt3D>) {
        val distanceMap: Map<Pt3D, Pair<Pt3D, Pt3D>> by lazy {
           beacons.flatMap { b1 ->
               beacons.map { b2 ->
                   Pt3D(abs(b1.x - b2.x), abs(b1.y - b2.y), abs(b1.z - b2.z)) to (b1 to b2)
               }
           }.toMap()
        }
    }

    private val scannerRegex = """^--- scanner (\d+) ---$""".toRegex()
    private val beaconRegex = """^(-?\d+),(-?\d+),(-?\d+)$""".toRegex()

    private fun String.toScanners(): List<Scanner> {
        val it = lines().iterator()
        val scanners = mutableListOf<Scanner>()
        while (it.hasNext()) {
            val scannerLine = it.next()
            val scannerMatch = scannerRegex.matchEntire(scannerLine) ?: error("Wrong scanner line: $scannerLine")
            val id = scannerMatch.groupValues[1].toInt()
            val beacons = mutableListOf<Pt3D>()
            while (it.hasNext()) {
                val beaconLine = it.next()
                if (beaconLine.isNotBlank()) {
                    val beaconMatch = beaconRegex.matchEntire(beaconLine) ?: error("Wrong beacon line: $beaconLine")
                    val (x, y, z) = beaconMatch.destructured
                    beacons.add(Pt3D(x.toInt(), y.toInt(), z.toInt()))
                }
            }
            scanners.add(Scanner(id, beacons))
        }
        return scanners
    }
}
