package io.github.clechasseur.adventofcode2021

import kotlin.test.Ignore
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

    class Day8Puzzles {
        @Test
        fun `day 8, part 1`() {
            assertEquals(521, Day8.part1())
        }

        @Test
        fun `day 8, part 2`() {
            assertEquals(1016804, Day8.part2())
        }
    }

    class Day9Puzzles {
        @Test
        fun `day 9, part 1`() {
            assertEquals(500, Day9.part1())
        }

        @Test
        fun `day 9, part 2`() {
            assertEquals(970200, Day9.part2())
        }
    }

    class Day10Puzzles {
        @Test
        fun `day 10, part 1`() {
            assertEquals(215229, Day10.part1())
        }

        @Test
        fun `day 10, part 2`() {
            assertEquals(1105996483L, Day10.part2())
        }
    }

    class Day11Puzzles {
        @Test
        fun `day 11, part 1`() {
            assertEquals(1588, Day11.part1())
        }

        @Test
        fun `day 11, part 2`() {
            assertEquals(517, Day11.part2())
        }
    }

    class Day12Puzzles {
        @Test
        fun `day 12, part 1`() {
            assertEquals(4720, Day12.part1())
        }

        @Test
        fun `day 12, part 2`() {
            assertEquals(147848, Day12.part2())
        }
    }

    class Day13Puzzles {
        @Test
        fun `day 13, part 1`() {
            assertEquals(621, Day13.part1())
        }

        @Test
        fun `day 13, part 2`() {
            val expected = """
                #..#.#..#.#..#...##..##...##....##.####
                #..#.#.#..#..#....#.#..#.#..#....#....#
                ####.##...#..#....#.#....#..#....#...#.
                #..#.#.#..#..#....#.#.##.####....#..#..
                #..#.#.#..#..#.#..#.#..#.#..#.#..#.#...
                #..#.#..#..##...##...###.#..#..##..####
            """.trimIndent()
            assertEquals(expected, Day13.part2())
        }
    }

    class Day14Puzzles {
        @Test
        fun `day 14, part 1`() {
            assertEquals(3143L, Day14.part1())
        }

        @Test
        fun `day 14, part 2`() {
            assertEquals(4110215602456L, Day14.part2())
        }
    }

    class Day15Puzzles {
        @Test
        fun `day 15, part 1`() {
            assertEquals(656, Day15.part1())
        }

        @Test
        @Ignore("Took me an hour to run this one on my machine")
        fun `day 15, part 2`() {
            assertEquals(2979, Day15.part2())
        }
    }

    class Day16Puzzles {
        @Test
        fun `day 16, part 1`() {
            assertEquals(957, Day16.part1())
        }

        @Test
        fun `day 16, part 2`() {
            assertEquals(744953223228L, Day16.part2())
        }
    }

    class Day17Puzzles {
        @Test
        fun `day 17, part 1`() {
            assertEquals(8911, Day17.part1())
        }

        @Test
        fun `day 17, part 2`() {
            assertEquals(4748, Day17.part2())
        }
    }

    class Day18Puzzles {
        @Test
        fun `day 18, part 1`() {
            assertEquals(4641, Day18.part1())
        }

        @Test
        fun `day 18, part 2`() {
            assertEquals(4624, Day18.part2())
        }
    }

    class Day19Puzzles {
        @Test
        fun `day 19, part 1`() {
            assertEquals(357, Day19.part1())
        }

        @Test
        fun `day 19, part 2`() {
            assertEquals(12317, Day19.part2())
        }
    }

    class Day20Puzzles {
        @Test
        fun `day 20, part 1`() {
            assertEquals(5306, Day20.part1())
        }

        @Test
        fun `day 20, part 2`() {
            assertEquals(17497, Day20.part2())
        }
    }

    class Day21Puzzles {
        @Test
        fun `day 21, part 1`() {
            assertEquals(412344, Day21.part1())
        }

        @Test
        fun `day 21, part 2`() {
            assertEquals(214924284932572L, Day21.part2())
        }
    }

    class Day22Puzzles {
        @Test
        fun `day 22, part 1`() {
            assertEquals(542711, Day22.part1())
        }

        @Test
        fun `day 22, part 2`() {
            assertEquals(1160303042684776L, Day22.part2())
        }
    }

    class Day23Puzzles {
        @Test
        @Ignore("Took 2.5 min on my machine")
        fun `day 23, part 1`() {
            assertEquals(14348, Day23.part1())
        }

        @Test
        @Ignore("Took 2.5 min on my machine")
        fun `day 23, part 2`() {
            assertEquals(40954, Day23.part2())
        }
    }

    class Day24Puzzles {
        @Test
        fun `day 24, part 1`() {
            assertEquals(52926995971999L, Day24.part1())
        }

        @Test
        fun `day 24, part 2`() {
            assertEquals(11811951311485L, Day24.part2())
        }
    }

    class Day25Puzzles {
        @Test
        fun `day 25, part 1`() {
            assertEquals(424, Day25.part1())
        }
    }
}
