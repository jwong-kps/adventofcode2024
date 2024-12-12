package wong.jonathan.app

class Day10 : Challenge("day10", false) {
    private val lines = readInputFileAsListOfStrings()
    private val map = processLinesInto2dArray(lines)
    private val directions = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
    private val trailsFound = mutableSetOf<Pair<Int, Int>>()
    private var totalTrails = 0

    override fun part1(): String {
        for (row in map.indices) {
            for (column in map[row].indices) {
                if (map[row][column] == '0') {
                    trailsFound.clear()
                    searchForValidTrails(row, column)
                    totalTrails += trailsFound.size
                }
            }
        }
        return totalTrails.toString()
    }

    override fun part2(): String {
        return ""
    }

    private fun searchForValidTrails(row: Int, column: Int) {
       for (direction in directions) {
            val nextRow = row + direction.first
            val nextColumn = column + direction.second
            if (checkLocationIsInMap(nextRow, nextColumn)) {
                val nextValue = map[nextRow][nextColumn]
                if (nextValue - map[row][column] == 1) {
                    if (nextValue == '9')
                        trailsFound.add(Pair(nextRow, nextColumn))
                    else
                        searchForValidTrails(nextRow, nextColumn)
                }
            }
        }
    }

    private fun checkLocationIsInMap(i: Int, j: Int): Boolean = i >= 0 && j >= 0 && i < map.size && j < map[0].size
}
