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

    class Day3Puzzles {
        @Test
        fun `day 3, part 1`() {
            assertEquals(4174964, Day3.part1())
        }

        @Test
        fun `day 3, part 2`() {
            assertEquals(4474944, Day3.part2())
        }
    }

    class Day4Puzzles {
        @Test
        fun `day 4, part 1`() {
            assertEquals(23177, Day4.part1())
        }

        @Test
        fun `day 4, part 2`() {
            assertEquals(6804, Day4.part2())
        }
    }

    class Day5Puzzles {
        @Test
        fun `day 5, part 1`() {
            assertEquals(5169, Day5.part1())
        }

        @Test
        fun `day 5, part 2`() {
            assertEquals(22083, Day5.part2())
        }
    }

    class Day6Puzzles {
        @Test
        fun `day 6, part 1`() {
            assertEquals(386755, Day6.part1())
        }

        @Test
        fun `day 6, part 2`() {
            assertEquals(1732731810807L, Day6.part2())
        }
    }

    class Day7Puzzles {
        @Test
        fun `day 7, part 1`() {
            assertEquals(333755, Day7.part1())
        }

        @Test
        fun `day 7, part 2`() {
            assertEquals(94017638, Day7.part2())
        }
    }
}
