package wong.jonathan.app

fun main() {
    val day01 = Day01()
    day01.challengeOne()
    day01.challengeTwo()

    val day02 = Day02()
    day02.challengeOne()
    day02.challengeTwo()

    val day03 = Day03()
    day03.challengeOne()
    day03.challengeTwo()

    val day04 = Day04()
    day04.challengeOne()
    day04.challengeTwo()

    val day05 = Day05()
    day05.challengeOne()
    day05.challengeTwo()

    val day06 = Day06()
    day06.challengeOne()
    day06.challengeTwo()

    val day07 = Day07()
    day07.challengeOne()
    day07.challengeTwo()
}

fun readFileAsLinesUsingGetResourceAsStream(fileName: String): List<String> {
    return {}.javaClass.getResourceAsStream(fileName).bufferedReader(Charsets.UTF_8).readLines()
}

fun processLinesInto2dArray(lines: List<String>): Array<CharArray> {
    val charArray2D = Array(lines.size) { CharArray(lines[0].length) }
    for (i in lines.indices) {
        for (j in lines[i].indices) {
            charArray2D[i][j] = lines[i][j]
        }
    }
    return charArray2D
}
