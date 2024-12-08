package wong.jonathan.app

class Day06 : Challenge("day06", false) {
    private val lines = readInputFileAsListOfStrings()

    enum class Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    override fun part1(): String {
        val processedFileData = processLinesInto2dArray(lines)

        val distinctPositions: MutableSet<String> = mutableSetOf()
        var currentPosition: Pair<Int, Int> = findCurrentPosition(processedFileData)
        var currentDirection = Direction.NORTH

        var iterations = 0
        while (iterations < 100000) {
            distinctPositions.add(currentPosition.first.toString() + "," + currentPosition.second.toString())

            val nextPositionVector = findNextPosition(currentDirection)

            if (hasEscaped(currentPosition, nextPositionVector, processedFileData)) break

            if (processedFileData[currentPosition.first + nextPositionVector.first][currentPosition.second + nextPositionVector.second] == '#') {
                val changePositionVector = changePosition(currentDirection)
                currentPosition = Pair(currentPosition.first + changePositionVector.first, currentPosition.second + changePositionVector.second)
                currentDirection = findNewDirection(currentDirection)
            } else {
                currentPosition = Pair(currentPosition.first + nextPositionVector.first, currentPosition.second + nextPositionVector.second)
            }

            iterations++
        }


        return distinctPositions.size.toString()
    }

    override fun part2(): String {
        return "N/A"
    }

    private fun hasEscaped(currentPosition: Pair<Int, Int>, nextPositionVector: Pair<Int, Int>, processedFileData: Array<CharArray>): Boolean {
        if ((currentPosition.first + nextPositionVector.first) < 0 || (currentPosition.first + nextPositionVector.first) >= processedFileData.size) return true
        if ((currentPosition.second + nextPositionVector.second) < 0 || (currentPosition.second + nextPositionVector.second) >= processedFileData[0].size) return true
        return false
    }

    private fun findCurrentPosition(processedFileData: Array<CharArray>): Pair<Int, Int> {
        for (i in processedFileData.indices) {
            for (j in processedFileData[i].indices) {
                if (processedFileData[i][j] == '^') {
                    return Pair(i, j)
                }
            }
        }
        return Pair(0, 0)
    }

    private fun findNewDirection(direction: Direction): Direction {
        return when (direction) {
            Direction.NORTH -> Direction.EAST
            Direction.EAST -> Direction.SOUTH
            Direction.SOUTH -> Direction.WEST
            Direction.WEST -> Direction.NORTH
        }
    }

    // no obstacle in the way
    // facing north: i=0, j=-1
    // facing east: i=1, j=0
    // facing south: i=0, j=1
    // facing west: i=-1, j=0
    //
    private fun findNextPosition(direction: Direction): Pair<Int, Int> {
        return when (direction) {
            Direction.NORTH -> Pair(-1, 0)
            Direction.EAST -> Pair(0, 1)
            Direction.SOUTH -> Pair(1, 0)
            Direction.WEST -> Pair(0, -1)
        }
    }

    // at an obstacle
    // facing north: i=1, j=0
    // facing east: i=0, j=1
    // facing south: i=-1, j=0
    // facing west: i=0, j=-1
    //
    private fun changePosition(direction: Direction): Pair<Int, Int> {
        return when (direction) {
            Direction.NORTH -> Pair(0, 1)
            Direction.EAST -> Pair(1, 0)
            Direction.SOUTH -> Pair(0, -1)
            Direction.WEST -> Pair(-1, 0)
        }
    }
}
