package wong.jonathan.app

class Day02 {

    private val challengeFileName = "input/day02.txt"
    private val reports = readFileAsLinesUsingGetResourceAsStream(challengeFileName)

    fun challengeOne() {
        var safeReports = 0

        for (report in reports) {
            if (report.isNotBlank()) {
                val levels = report.split(" ")

                if (findBadLevels(levels) == 0)
                    safeReports++
            }
        }

        println("Day 02 - Challenge 1 - $safeReports")
    }

    fun challengeTwo() {
        var safeReports = 0

        for (report in reports) {
            if (report.isNotBlank()) {
                val levels = report.split(" ")

                if (findBadLevels(levels) == 0)
                    safeReports++
                else {
                    for (i in levels.indices) {
                        val updatedLevels = levels.toMutableList()
                        updatedLevels.removeAt(i)
                        if (findBadLevels(updatedLevels) == 0) {
                            safeReports++
                            break
                        }
                    }
                }
            }
        }

        println("Day 02 - Challenge 2 - $safeReports")
    }

    private fun findBadLevels(levels: List<String>): Int {
        var increasing = true
        if ((levels[0].toInt() - levels[1].toInt()) > 0)
            increasing = false

        var badLevels = 0
        for (i in levels.indices) {
            if (i + 1 >= levels.size) break

            val diff = levels[i].toInt() - levels[i + 1].toInt()

            if (!isLevelSafe(diff, increasing)) {
                badLevels++
            }
        }
        return badLevels
    }

    private fun isLevelSafe(diff: Int, increasing: Boolean): Boolean {
        return (!increasing && diff in 1..3) || (increasing && diff in -3..-1)
    }
}
