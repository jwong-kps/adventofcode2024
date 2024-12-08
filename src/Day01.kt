package wong.jonathan.app

class Day01 : Challenge("day01", false) {
    private val lines = readInputFileAsListOfStrings()

    override fun part1(): String {
        val locations1 = mutableListOf<String>()
        val locations2 = mutableListOf<String>()

        for (item in lines) {
            if (item.isNotBlank()) {
                val splitValues = item.split("  ")
                locations1.add(splitValues[0].replace("\\s".toRegex(), ""))
                locations2.add(splitValues[1].replace("\\s".toRegex(), ""))
            }
        }

        val sortedLocations1 = locations1.sorted()
        val sortedLocations2 = locations2.sorted()

        var totalDistance = 0

        sortedLocations1.forEachIndexed { index, item ->
            val distance = sortedLocations2[index].toInt() - item.toInt()
            totalDistance += if (distance > 0) distance else (-1 * distance)
        }

        return totalDistance.toString()
    }

    override fun part2(): String {
        val locations1 = mutableListOf<String>()
        val locations2Set = mutableMapOf<String, Int>()

        for (item in lines) {
            if (item.isNotBlank()) {
                val splitValues = item.split("  ")
                locations1.add(splitValues[0].replace("\\s".toRegex(), ""))
                val location2 = splitValues[1].replace("\\s".toRegex(), "")
                locations2Set[location2] = locations2Set.getOrDefault(location2, 0) + 1
            }
        }

        var similarityScore = 0

        locations1.forEach { location1 -> similarityScore += (location1.toInt() * locations2Set.getOrDefault(location1, 0)) }

        return similarityScore.toString()
    }
}
