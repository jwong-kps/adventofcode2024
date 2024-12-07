package wong.jonathan.app

class Day01 {

    private val challengeFileName = "/input/day01.txt"

    fun challengeOne() {
        val lines = readFileAsLinesUsingGetResourceAsStream(challengeFileName)

        val locations1 = mutableListOf<String>()
        val locations2 = mutableListOf<String>()

        for (item in lines!!) {
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

        println("Day 01 - Challenge 1 - $totalDistance")
    }

    fun challengeTwo() {
        val lines = readFileAsLinesUsingGetResourceAsStream("/input/day01.txt")

        val locations1 = mutableListOf<String>()
        val locations2Set = mutableMapOf<String, Int>()

        for (item in lines!!) {
            if (item.isNotBlank()) {
                val splitValues = item.split("  ")
                locations1.add(splitValues[0].replace("\\s".toRegex(), ""))
                val location2 = splitValues[1].replace("\\s".toRegex(), "")
                locations2Set[location2] = locations2Set.getOrDefault(location2, 0) + 1
            }
        }

        var similarityScore = 0

        locations1.forEach { location1 -> similarityScore += (location1.toInt() * locations2Set.getOrDefault(location1, 0)) }

        println("Day 01 - Challenge 2 - $similarityScore")
    }
}
