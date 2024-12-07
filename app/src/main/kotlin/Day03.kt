package wong.jonathan.app

class Day03 {

    private val challengeFileName = "/input/day03.txt"
    private val lines = readFileAsLinesUsingGetResourceAsStream(challengeFileName)

    fun challengeOne() {
        val regex = Regex("mul\\(\\d+,\\d+\\)")

        var sum = 0

        for (line in lines) {
            val matches = regex.findAll(line)
            for (match in matches) {
                val first = match.value.replace("mul(", "").replace(")", "").split(",")[0]
                val second = match.value.replace("mul(", "").replace(")", "").split(",")[1]
                sum += (first.toInt() * second.toInt())
            }
        }

        println("Day 03 - Challenge 1 - $sum")
    }

    fun challengeTwo() {
        val regex = Regex("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)")

        var sum = 0
        var enabled = true

        for (line in lines) {
            val matches = regex.findAll(line)
            for (match in matches) {
                if (match.value == "do()") {
                    enabled = true
                    continue
                }
                if (match.value == "don't()") enabled = false
                if (enabled) {
                    val first = match.value.replace("mul(", "").replace(")", "").split(",")[0]
                    val second = match.value.replace("mul(", "").replace(")", "").split(",")[1]
                    sum += (first.toInt() * second.toInt())
                }
            }
        }

        println("Day 03 - Challenge 2 - $sum")
    }
}
