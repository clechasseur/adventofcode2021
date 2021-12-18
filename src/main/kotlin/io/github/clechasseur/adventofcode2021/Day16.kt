package io.github.clechasseur.adventofcode2021

object Day16 {
    private const val data = "2052ED9802D3B9F465E9AE6003E52B8DEE3AF97CA38100957401A88803D05A25C1E00043E1545883B397259385B47E40257CCEDC7401700043E3F42A8AE0008741E8831EC8020099459D40994E996C8F4801CDC3395039CB60E24B583193DD75D299E95ADB3D3004E5FB941A004AE4E69128D240130D80252E6B27991EC8AD90020F22DF2A8F32EA200AC748CAA0064F6EEEA000B948DFBED7FA4660084BCCEAC01000042E37C3E8BA0008446D8751E0C014A0036E69E226C9FFDE2020016A3B454200CBAC01399BEE299337DC52A7E2C2600BF802B274C8848FA02F331D563B3D300566107C0109B4198B5E888200E90021115E31C5120043A31C3E85E400874428D30AA0E3804D32D32EED236459DC6AC86600E4F3B4AAA4C2A10050336373ED536553855301A600B6802B2B994516469EE45467968C016D004E6E9EE7CE656B6D34491D8018E6805E3B01620C053080136CA0060801C6004A801880360300C226007B8018E0073801A801938004E2400E01801E800434FA790097F39E5FB004A5B3CF47F7ED5965B3CF47F7ED59D401694DEB57F7382D3F6A908005ED253B3449CE9E0399649EB19A005E5398E9142396BD1CA56DFB25C8C65A0930056613FC0141006626C5586E200DC26837080C0169D5DC00D5C40188730D616000215192094311007A5E87B26B12FCD5E5087A896402978002111960DC1E0004363942F8880008741A8E10EE4E778FA2F723A2F60089E4F1FE2E4C5B29B0318005982E600AD802F26672368CB1EC044C2E380552229399D93C9D6A813B98D04272D94440093E2CCCFF158B2CCFE8E24017CE002AD2940294A00CD5638726004066362F1B0C0109311F00424CFE4CF4C016C004AE70CA632A33D2513004F003339A86739F5BAD5350CE73EB75A24DD22280055F34A30EA59FE15CC62F9500"

    fun part1(): Int = bits().iterator().nextPacket().versionSum

    fun part2(): Long = bits().iterator().nextPacket().value

    private fun bits(): Sequence<Char> = data.asSequence().flatMap {
        it.toString().toInt(16).toString(2).padStart(4, '0').toList()
    }

    private abstract class Packet(val version: Int, val typeId: Int) {
        open val versionSum: Int
            get() = version

        abstract val value: Long
    }

    private class LiteralPacket(version: Int, val literalValue: Long) : Packet(version, 4) {
        override val value: Long
            get() = literalValue
    }

    private abstract class OperatorPacket(version: Int, typeId: Int, val subPackets: List<Packet>) : Packet(version, typeId) {
        override val versionSum: Int
            get() = super.versionSum + subPackets.sumOf { it.versionSum }
    }

    private class SumPacket(version: Int, subPackets: List<Packet>) : OperatorPacket(version, 0, subPackets) {
        override val value: Long
            get() = subPackets.sumOf { it.value }
    }

    private class ProductPacket(version: Int, subPackets: List<Packet>) : OperatorPacket(version, 1, subPackets) {
        override val value: Long
            get() = subPackets.fold(1L) { acc, p -> acc * p.value }
    }

    private class MinPacket(version: Int, subPackets: List<Packet>) : OperatorPacket(version, 2, subPackets) {
        override val value: Long
            get() = subPackets.minOf { it.value }
    }

    private class MaxPacket(version: Int, subPackets: List<Packet>) : OperatorPacket(version, 3, subPackets) {
        override val value: Long
            get() = subPackets.maxOf { it.value }
    }

    private class GreaterThanPacket(version: Int, subPackets: List<Packet>) : OperatorPacket(version, 5, subPackets) {
        override val value: Long
            get() = if (subPackets[0].value > subPackets[1].value) 1L else 0L
    }

    private class LessThanPacket(version: Int, subPackets: List<Packet>) : OperatorPacket(version, 6, subPackets) {
        override val value: Long
            get() = if (subPackets[0].value < subPackets[1].value) 1L else 0L
    }

    private class EqualToPacket(version: Int, subPackets: List<Packet>) : OperatorPacket(version, 7, subPackets) {
        override val value: Long
            get() = if (subPackets[0].value == subPackets[1].value) 1L else 0L
    }

    private fun Iterator<Char>.nextPacket(): Packet {
        val version = nextValue(3)
        val typeId = nextValue(3)
        if (typeId == 4) {
            return LiteralPacket(version, nextLiteral())
        }

        val lengthTypeId = next()
        val subPackets = mutableListOf<Packet>()
        if (lengthTypeId == '0') {
            val subLength = nextValue(15)
            val subData = nextData(subLength)
            val subIt = subData.iterator()
            while (subIt.hasNext()) {
                subPackets.add(subIt.nextPacket())
            }
        } else {
            val numSubPackets = nextValue(11)
            for (i in 0 until numSubPackets) {
                subPackets.add(nextPacket())
            }
        }
        return when (typeId) {
            0 -> SumPacket(version, subPackets)
            1 -> ProductPacket(version, subPackets)
            2 -> MinPacket(version, subPackets)
            3 -> MaxPacket(version, subPackets)
            5 -> GreaterThanPacket(version, subPackets)
            6 -> LessThanPacket(version, subPackets)
            7 -> EqualToPacket(version, subPackets)
            else -> error("Wrong operator type ID: $typeId")
        }
    }

    private fun Iterator<Char>.nextData(size: Int): String {
        var s = ""
        for (i in 0 until size) {
            s += next()
        }
        return s
    }

    private fun Iterator<Char>.nextValue(size: Int): Int = nextData(size).toInt(2)

    private fun Iterator<Char>.nextLiteral(): Long {
        var s = ""
        while (true) {
            val prefix = next()
            s += nextData(4)
            if (prefix == '0') {
                break
            }
        }
        return s.toLong(2)
    }
}
