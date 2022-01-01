package io.github.clechasseur.adventofcode2021

import io.github.clechasseur.adventofcode2021.data.Day22Data
import io.github.clechasseur.adventofcode2021.util.Pt3D

object Day22 {
    private val data = Day22Data.data

    fun part1(): Int = data.lineSequence().take(20).map { it.toCuboid() }.fold(Core(emptySet())) { core, cuboid ->
        core apply cuboid
    }.cubes.size

    fun part2(): Long {
        val cuboids = data.lines().map { it.toCuboid() }.reversed()
        val minx = cuboids.minOf { it.xr.first }
        val maxx = cuboids.maxOf { it.xr.last }
        val miny = cuboids.minOf { it.yr.first }
        val maxy = cuboids.maxOf { it.yr.last }
        val minz = cuboids.minOf { it.zr.first }
        val maxz = cuboids.maxOf { it.zr.last }
        return (minx..maxx).asSequence().flatMap { x ->
            (miny..maxy).asSequence().flatMap { y ->
                (minz..maxz).asSequence().map { z -> if (cuboids.on(Pt3D(x, y, z))) 1L else 0L }
            }
        }.sum()
    }

    private class Core(val cubes: Set<Pt3D>)

    private class Cuboid(val on: Boolean, val xr: IntRange, val yr: IntRange, val zr: IntRange) {
        val cubes: Set<Pt3D>
            get() = xr.flatMap { x ->
                yr.flatMap { y ->
                    zr.map { z -> Pt3D(x, y, z) }
                }
            }.toSet()

        fun contains(pt: Pt3D): Boolean = pt.x in xr && pt.y in yr && pt.z in zr
    }

    private infix fun Core.apply(cuboid: Cuboid): Core = if (cuboid.on) {
        Core(cubes + cuboid.cubes)
    } else {
        Core(cubes - cuboid.cubes)
    }

    private fun List<Cuboid>.on(pt: Pt3D): Boolean = find { it.contains(pt) }?.on ?: false

    private val cuboidRegex = """^(on|off) x=(-?\d+)\.\.(-?\d+),y=(-?\d+)\.\.(-?\d+),z=(-?\d+)\.\.(-?\d+)$""".toRegex()

    private fun String.toCuboid(): Cuboid {
        val match = cuboidRegex.matchEntire(this) ?: error("Wrong cuboid: $this")
        val (onOff, xmin, xmax, ymin, ymax, zmin, zmax) = match.destructured
        return Cuboid(
            on=onOff == "on",
            xr=xmin.toInt()..xmax.toInt(),
            yr=ymin.toInt()..ymax.toInt(),
            zr=zmin.toInt()..zmax.toInt(),
        )
    }
}
