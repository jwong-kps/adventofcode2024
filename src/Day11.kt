package wong.jonathan.app

class Day11 : Challenge("day11", false) {
    private val stones = readInputFileAsListOfStrings()

    private var maxBlinks: Long = 0

    private val cache = mutableMapOf<Pair<Long, Long>, Long>()

    override fun part1(): String {
        maxBlinks = 25
        var stoneCount: Long = 0

        stones[0].split(" ").map { it.toLong() }.forEach { stone ->
            stoneCount += changeStone(stone, 1)
        }

        return stoneCount.toString()
    }

    private fun changeStone(stone: Long, blink: Long): Long {
        if (blink > maxBlinks) return 1

        val count = cache.getOrPut(Pair(stone, blink + 1)) {
            if (stone == 0L) {
                changeStone(1, blink + 1)
            } else if (stone.toString().length % 2 == 0) {
                val stone1Str = stone.toString()
                val newStone1 = stone1Str.substring(0, stone1Str.length / 2)
                val newStone2 = stone1Str.substring(stone1Str.length / 2)
                changeStone(newStone1.toLong(), blink + 1) + changeStone(newStone2.toLong(), blink + 1)
            } else {
                changeStone(stone * 2024, blink + 1)
            }
        }

        return count
    }

    override fun part2(): String {
        maxBlinks = 75
        cache.clear()
        var stoneCount: Long = 0

        stones[0].split(" ").map { it.toLong() }.forEach { stone ->
            stoneCount += changeStone(stone, 1)
        }

        return stoneCount.toString()
    }
}
