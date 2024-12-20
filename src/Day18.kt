package wong.jonathan.app

import java.util.*

class Day18 : Challenge("day18", false) {
    data class Position(var row: Int, var column: Int, var step: Int)

    private val lines = readInputFileAsListOfStrings()

    private val size = 70 //6 for example, 70 for real
    private val maxBytes = 1024 //12 for example, 1024 for real

    private var map = Array(size + 1) { CharArray(size + 1) }

    // right, down, left, up
    private var directions = arrayOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))

    override fun part1(): String {
        buildMap(maxBytes)

        var steps = -1

        val visited = mutableSetOf<Pair<Int, Int>>()
        val queue: Queue<Position> = LinkedList()

        queue.add(Position(0, 0, 0))
        visited.add(Pair(0, 0))

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            for (direction in directions) {
                val nextRow = current.row + direction.first
                val nextColumn = current.column + direction.second

                if (nextRow < 0 || nextColumn < 0 || nextRow > size || nextColumn > size) continue
                if (map[nextRow][nextColumn] == '#') continue
                if (visited.contains(nextRow to nextColumn)) continue

                if (nextRow == size && nextColumn == size) {
                    steps = current.step + 1
                    break
                }

                visited.add(Pair(nextRow, nextColumn))
                queue.add(Position(nextRow, nextColumn, current.step + 1))
            }

            if (steps > 0) break
        }

        return steps.toString()
    }

    private fun buildMap(maxBytes: Int) {
        for (i in 0 until maxBytes) {
            val coords = lines[i].split(",").map { it.toInt() }
            map[coords[1]][coords[0]] = '#'
        }
    }

    override fun part2(): String {
        return ""
    }
}
