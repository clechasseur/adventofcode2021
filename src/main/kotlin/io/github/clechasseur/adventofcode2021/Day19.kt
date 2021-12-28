package io.github.clechasseur.adventofcode2021

import io.github.clechasseur.adventofcode2021.data.Day19Data
import io.github.clechasseur.adventofcode2021.util.Pt3D
import kotlin.math.abs

object Day19 {
    private val data = Day19Data.data

    fun part1(): Int = 0

    private val facings: List<(Pt3D) -> Pt3D> = listOf(
        { it },
        { Pt3D(-it.y, it.x, it.z) },
        { Pt3D(-it.x, -it.y, it.z) },
        { Pt3D(it.y, -it.x, it.z) },
    )

    private val rotations: List<(Pt3D) -> Pt3D> = listOf(
        { it },
        { Pt3D(it.x, -it.z, it.y) },
        { Pt3D(it.x, -it.y, -it.z) },
        { Pt3D(it.x, it.z, -it.y) },
        { Pt3D(it.z, it.y, -it.x) },
        { Pt3D(-it.z, it.y, it.x) },
    )

    private val orientations: List<(Pt3D) -> Pt3D> = facings.flatMap { facing ->
        rotations.map { rotation -> { rotation(facing(it)) } }
    }

    private class Scanner(val beacons: List<Pt3D>) {
        fun allOrientations(): List<Scanner> = orientations.map { orientation ->
            Scanner(beacons.map { orientation(it) })
        }

        fun distanceMap(): Set<Pt3D> = beacons.flatMap { b1 ->
            beacons.filter { it != b1 }.map { b2 ->
                Pt3D(abs(b1.x - b2.x), abs(b1.y - b2.y), abs(b1.z - b2.z))
            }
        }.toSet()

        fun connect(other: Scanner): Scanner? {
            val leftDm = distanceMap()
            val right = other.allOrientations().find { leftDm.intersect(it.distanceMap()).size >= 12 } ?: return null
            
        }
    }

    private val scannerRegex = """^--- scanner (\d+) ---$""".toRegex()
    private val beaconRegex = """^(-?\d+),(-?\d+),(-?\d+)$""".toRegex()

    private fun String.toScanners(): List<Scanner> {
        val it = lines().iterator()
        val scanners = mutableListOf<Scanner>()
        while (it.hasNext()) {
            it.next()
            val beacons = mutableListOf<Pt3D>()
            while (it.hasNext()) {
                val beaconLine = it.next()
                if (beaconLine.isNotBlank()) {
                    val beaconMatch = beaconRegex.matchEntire(beaconLine) ?: error("Wrong beacon line: $beaconLine")
                    val (x, y, z) = beaconMatch.destructured
                    beacons.add(Pt3D(x.toInt(), y.toInt(), z.toInt()))
                } else {
                    break
                }
            }
            scanners.add(Scanner(beacons))
        }
        return scanners
    }
}
