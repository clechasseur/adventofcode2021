package io.github.clechasseur.adventofcode2021

object Day14 {
    private val initial = "FSHBKOOPCFSFKONFNFBB"

    private val pairings = mapOf(
        "FO" to 'K',
        "FF" to 'H',
        "SN" to 'C',
        "CC" to 'S',
        "BB" to 'V',
        "FK" to 'H',
        "PC" to 'P',
        "PH" to 'N',
        "OB" to 'O',
        "PV" to 'C',
        "BH" to 'B',
        "HO" to 'C',
        "VF" to 'H',
        "HB" to 'O',
        "VO" to 'N',
        "HK" to 'N',
        "OF" to 'V',
        "PF" to 'C',
        "KS" to 'H',
        "KV" to 'F',
        "PO" to 'B',
        "BF" to 'P',
        "OO" to 'B',
        "PS" to 'S',
        "KC" to 'P',
        "BV" to 'K',
        "OC" to 'B',
        "SH" to 'C',
        "SF" to 'P',
        "NH" to 'C',
        "BS" to 'C',
        "VH" to 'F',
        "CH" to 'S',
        "BC" to 'B',
        "ON" to 'K',
        "FH" to 'O',
        "HN" to 'O',
        "HS" to 'C',
        "KK" to 'V',
        "OK" to 'K',
        "VC" to 'H',
        "HV" to 'F',
        "FS" to 'H',
        "OV" to 'P',
        "HF" to 'F',
        "FB" to 'O',
        "CK" to 'O',
        "HP" to 'C',
        "NN" to 'V',
        "PP" to 'F',
        "FC" to 'O',
        "SK" to 'N',
        "FN" to 'K',
        "HH" to 'F',
        "BP" to 'O',
        "CP" to 'K',
        "VV" to 'S',
        "BO" to 'N',
        "KN" to 'S',
        "SB" to 'B',
        "SC" to 'H',
        "OS" to 'S',
        "CF" to 'K',
        "OP" to 'P',
        "CO" to 'C',
        "VK" to 'C',
        "NB" to 'K',
        "PB" to 'S',
        "FV" to 'B',
        "CS" to 'C',
        "HC" to 'P',
        "PK" to 'V',
        "BK" to 'P',
        "KF" to 'V',
        "NS" to 'P',
        "SO" to 'C',
        "CV" to 'P',
        "NP" to 'V',
        "VB" to 'F',
        "KO" to 'C',
        "KP" to 'F',
        "KH" to 'N',
        "VN" to 'S',
        "NO" to 'P',
        "NF" to 'K',
        "CB" to 'H',
        "VS" to 'V',
        "NK" to 'N',
        "KB" to 'C',
        "SV" to 'F',
        "NC" to 'H',
        "VP" to 'K',
        "PN" to 'H',
        "OH" to 'K',
        "CN" to 'N',
        "BN" to 'F',
        "NV" to 'K',
        "SP" to 'S',
        "SS" to 'K',
        "FP" to 'S',
    )

    fun part1(): Long = puzzle(10)

    fun part2(): Long = puzzle(40)

    private fun initialPairCounts(): Map<String, Long> = initial.zipWithNext().map { (a, b) ->
        "$a$b"
    }.groupBy { it }.mapValues { (_, l) ->
        l.size.toLong()
    }

    private fun pairCounts(): Sequence<Map<String, Long>> = generateSequence(initialPairCounts()) { prev ->
        prev.flatMap { (p, s) ->
            listOf("${p.first()}${pairings[p]!!}" to s, "${pairings[p]!!}${p.last()}" to s)
        }.groupBy { it.first }.mapValues { (_, l) -> l.sumOf { it.second } }
    }

    private fun puzzle(steps: Int): Long {
        val pc = pairCounts().drop(steps).first()
        val counts = pc.toList().groupBy { it.first.first() }.mapValues { (c, l) ->
            l.sumOf { it.second } + if (c == initial.last()) 1L else 0L
        }
        return counts.maxOf { it.value } - counts.minOf { it.value }
    }
}
