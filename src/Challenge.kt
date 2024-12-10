package wong.jonathan.app

import java.io.File
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

abstract class Challenge(private val day: String, private val example: Boolean) {

    fun readInputFileAsListOfStrings(): List<String> = if (example) File("input/examples/".plus(day).plus(".txt")).bufferedReader().readLines()
    else File("input/".plus(day).plus(".txt")).bufferedReader().readLines()

    fun processLinesInto2dArray(lines: List<String>): Array<CharArray> {
        val charArray2D = Array(lines.size) { CharArray(lines[0].length) }
        for (i in lines.indices) {
            for (j in lines[i].indices) {
                charArray2D[i][j] = lines[i][j]
            }
        }
        return charArray2D
    }

    fun execute() {
        println("Running the solutions for $day [example=$example]")

        var timeTaken = measureTimeMillis {
            // Your method or code block here
            print("Answer for part 1 [${part1()}]")
        }
        println(", time taken: $timeTaken ms")

        timeTaken = measureTimeMillis {
            // Your method or code block here
            print("Answer for part 1 [${part2()}]")
        }
        println(", time taken: $timeTaken ms")

        println()
    }

    abstract fun part1(): String
    abstract fun part2(): String
}
