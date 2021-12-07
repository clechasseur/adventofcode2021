package io.github.clechasseur.adventofcode2021

import kotlin.test.Test
import kotlin.test.assertEquals

class AdventOfCode2021 {
    class Day1Puzzles {
        @Test
        fun `day 1, part 1`() {
            assertEquals(1529, Day1.part1())
        }

        @Test
        fun `day 1, part 2`() {
            assertEquals(1567, Day1.part2())
        }
    }

    class Day2Puzzles {
        @Test
        fun `day 2, part 1`() {
            assertEquals(1893605, Day2.part1())
        }

        @Test
        fun `day 2, part 2`() {
            assertEquals(2120734350, Day2.part2())
        }
    }
}
