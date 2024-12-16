package wong.jonathan.app

class Day15 : Challenge("day15", false) {
    private val lines = readInputFileAsListOfStrings()

    private var map = Array(lines.size) { CharArray(lines[0].length) }

    private var moves = ""

    override fun part1(): String {
        processLinesIntoMapAndMoves()

        for (move in moves) {
            val direction = findDirection(move.toString())
            //println("Moving $move in direction $direction")
            val robotPosition = findRobot()
            val boxes = mutableSetOf<Pair<Int, Int>>()
            var freeSpaceFound = false

            var nextPosition = addPairs(direction, robotPosition)
            while (true) {

                if (!checkLocationIsInMap(nextPosition) || map[nextPosition.first][nextPosition.second] == '#') {
                    break
                } else if (map[nextPosition.first][nextPosition.second] == '.') {
                    freeSpaceFound = true
                    break
                } else if (map[nextPosition.first][nextPosition.second] == 'O') {
                    boxes.add(nextPosition)
                }

                nextPosition = addPairs(direction, nextPosition)
            }

            if (freeSpaceFound) {
                val nextPosition = addPairs(direction, robotPosition)
                map[nextPosition.first][nextPosition.second] = '@'
                map[robotPosition.first][robotPosition.second] = '.'
                if (boxes.size > 0) { //need to move the boxes by one
                    for (box in boxes) {
                        val newBoxPosition = addPairs(direction, box)
                        map[newBoxPosition.first][newBoxPosition.second] = 'O'
                    }
                }
            }

            //printMap()
        }

        return calculateGpsCoordinatesOfBoxes().toString()
    }

    private fun clearConsole() {
        print("\u001b[H\u001b[2J")
        System.out.flush()
    }

    private fun printMap() {
        clearConsole()
        for (row in map.indices) {
            for (column in map[row].indices) {
                print(map[row][column])
            }
            println()
        }
        Thread.sleep(3000)
    }

    private fun calculateGpsCoordinatesOfBoxes(): Int {
        var sum = 0
        for (row in map.indices) {
            for (column in map[row].indices) {
                if (map[row][column] == 'O') {
                    sum += (100 * row) + column
                }
            }
        }
        return sum
    }

    private fun addPairs(pair1: Pair<Int, Int>, pair2: Pair<Int, Int>): Pair<Int, Int> {
        val firstSum = pair1.first + pair2.first
        val secondSum = pair1.second + pair2.second
        return Pair(firstSum, secondSum)
    }

    private fun findRobot(): Pair<Int, Int> {
        for (row in map.indices) {
            for (column in map[row].indices) {
                if (map[row][column] == '@') {
                    return Pair(row, column)
                }
            }
        }
        return Pair(-1, -1)
    }

    private fun findDirection(move: String): Pair<Int, Int> = when (move) {
        "^" -> Pair(-1, 0)
        ">" -> Pair(0, 1)
        "v" -> Pair(1, 0)
        else -> Pair(0, -1)
    }

    private fun checkLocationIsInMap(loc: Pair<Int, Int>): Boolean = loc.first >= 0 && loc.second >= 0 && loc.first < map.size && loc.second < map[0].size

    private fun processLinesIntoMapAndMoves() {
        val blankLine = lines.indexOfFirst { it == "" }
        map = processLinesInto2dArray(lines.take(blankLine))
        moves = lines.drop(blankLine).filter { it != "" }.joinToString("")
    }

    override fun part2(): String {
        return ""
    }
}
