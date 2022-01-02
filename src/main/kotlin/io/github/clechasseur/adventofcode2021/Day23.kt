package io.github.clechasseur.adventofcode2021

import io.github.clechasseur.adventofcode2021.dij.Dijkstra
import io.github.clechasseur.adventofcode2021.dij.Graph
import io.github.clechasseur.adventofcode2021.util.Direction
import io.github.clechasseur.adventofcode2021.util.Pt
import kotlin.math.min

object Day23 {
    private val data = """
        #############
        #...........#
        ###D#A#D#C###
          #B#C#B#A#
          #########
    """.trimIndent()

    fun part1(): Int = lowestCost(data.toState())

    private val energyCost = mapOf(
        'A' to 1,
        'B' to 10,
        'C' to 100,
        'D' to 1000,
    )

    private data class Room(val pts: List<Pt>, val owners: Char) {
        fun legalDestinationFor(amphipod: Char, tiles: Map<Pt, Char>): Boolean =
            amphipod == owners && pts.all { tiles[it]!! in listOf('.', amphipod) }
    }

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

    private val hallway = (1..11).map { Pt(it, 1) }

    private data class State(val tiles: Map<Pt, Char>) {
        val amphipods: Map<Pt, Char>
            get() = tiles.filterValues { it.isLetter() }

        val solved: Boolean
            get() = rooms.all { room ->
                room.pts.all { tiles[it]!! == room.owners }
            }

        fun legalMove(from: Pt, to: Pt): Boolean = !lava.contains(to) && rooms.all { room ->
            (!room.pts.contains(to) || room.legalDestinationFor(tiles[from]!!, tiles)) &&
                    (!room.pts.contains(from) || room.owners != tiles[from]!!)
        } && (!hallway.contains(from) || !hallway.contains(to))

        fun move(from: Pt, to: Pt): State {
            val newTiles = tiles.toMutableMap()
            newTiles[from] = '.'
            newTiles[to] = tiles[from]!!
            return State(newTiles)
        }

        override fun toString(): String {
            return (0..4).joinToString("\n") { y ->
                (0..12).joinToString("") { x -> (tiles[Pt(x, y)] ?: ' ').toString() }
            }
        }
    }

    private class StateGraph(val state: State, val cost: Int) : Graph<Pt> {
        override fun allPassable(): List<Pt> = state.tiles.filterValues { it == '.' }.keys.toList()
        override fun neighbours(node: Pt): List<Pt> = Direction.displacements.mapNotNull { disp ->
            if (state.tiles.getOrDefault(node + disp, ' ') == '.') node + disp else null
        }
        override fun dist(a: Pt, b: Pt): Long = cost.toLong()
    }

    private fun String.toState() = State(lines().flatMapIndexed { y, line ->
        line.mapIndexed { x, c -> Pt(x, y) to c }
    }.toMap())

    private fun lowestCost(initialState: State): Int {
        var states = mapOf(initialState to 0)
        while (states.isNotEmpty()) {
            val newStates = mutableMapOf<State, Int>()
            states.toList().sortedBy { (_, cost) -> cost }.forEach { (state, costSoFar) ->
                if (state.solved) {
                    return costSoFar
                }

                state.amphipods.forEach { (pos, amphipod) ->
                    val (dist, _) = Dijkstra.build(StateGraph(state, energyCost[amphipod]!!), pos)
                    dist.forEach { (to, cost) ->
                        if (cost > 0 && cost != Long.MAX_VALUE && state.legalMove(pos, to)) {
                            val newState = state.move(pos, to)
                            val newCost = costSoFar + cost.toInt()
                            newStates[newState] = min(newStates[newState] ?: Int.MAX_VALUE, newCost)
                        }
                    }
                }
            }
            states = newStates
        }
        error("Should have solved by now")
    }
}
