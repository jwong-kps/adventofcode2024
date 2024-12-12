package wong.jonathan.app

private const val maxBlinks = 25

class Day11 : Challenge("day11", false) {
    private val lines = readInputFileAsListOfStrings()

    override fun part1(): String {
        val stones = lines[0].split(" ").toMutableList()

        for (blink in 1..maxBlinks) {
            val newStones = mutableListOf<String>()
            stones.forEach { stone ->
                var temp = ruleOne(stone)
                if (temp.isEmpty()) {
                    temp = ruleTwo(stone)
                    if (temp.isEmpty()) {
                        temp = ruleThree(stone)
                    }
                }
                newStones.addAll(temp)
            }
            stones.clear()
            stones.addAll(newStones)
        }

        return stones.size.toString()
    }

    private fun ruleOne(stone: String): List<String> = if (stone == "0") listOf("1") else emptyList()
    private fun ruleTwo(stone: String): List<String> = if (stone.length % 2 == 0) listOf(stone.substring(0, stone.length/2), stone.substring(stone.length/2)).map { it.toInt().toString() } else emptyList()
    private fun ruleThree(stone: String): List<String> = listOf((stone.toLong() * 2024).toString())

    override fun part2(): String {
        return ""
    }
}
