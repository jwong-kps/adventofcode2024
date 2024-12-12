package wong.jonathan.app

private const val maxBlinks: Long = 25

class Day11 : Challenge("day11", false) {
    private val stones = readInputFileAsListOfStrings()

    override fun part1(): String {
        var stoneCount: Long = 0

        stones[0].split(" ").map { it.toLong() }.forEach { stone ->
            stoneCount += changeStone(stone, 1)
        }

        return stoneCount.toString()
    }

    private fun changeStone(stone1: Long, blink: Long): Long {
        if (blink > maxBlinks) {
            return 1
        }


        var count: Long = 0

        if (stone1 == "0".toLong()) {
            count += changeStone(1, blink + 1)
        } else if (stone1.toString().length % 2 == 0) {
            val stone1Str = stone1.toString()

            val newStone1 = stone1Str.substring(0, stone1Str.length / 2)
            count += changeStone(newStone1.toLong(), blink + 1)

            val newStone2 = stone1Str.substring(stone1Str.length / 2)
            count += changeStone(newStone2.toLong(), blink + 1)

        } else {
            count += changeStone(stone1 * 2024, blink + 1)
        }

        return count
    }

    override fun part2(): String {
        return ""
    }
}
