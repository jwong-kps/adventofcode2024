package wong.jonathan.app

class Day08 : Challenge("day08", false) {
    private val lines = readInputFileAsListOfStrings()

    private val grid = processLinesInto2dArray(lines)

    override fun part1(): String {
        val antiNodesLocs: MutableSet<Pair<Int, Int>> = mutableSetOf()

        for (antennas in searchForAntennaLocations()) {
            for (thisAntenna in antennas.value) {
                for (otherAntenna in (antennas.value.takeLastWhile { it != thisAntenna })) {
                    val diffRow = thisAntenna.first - otherAntenna.first
                    val diffColumn = thisAntenna.second - otherAntenna.second

                    val antiNodeLoc1 = Pair(thisAntenna.first - diffRow * -1, thisAntenna.second - diffColumn * -1)
                    val antiNodeLoc2 = Pair(otherAntenna.first - diffRow, otherAntenna.second - diffColumn)

                    if (checkLocationIsInMap(antiNodeLoc1))
                        antiNodesLocs.add(antiNodeLoc1)

                    if (checkLocationIsInMap(antiNodeLoc2))
                        antiNodesLocs.add(antiNodeLoc2)
                }
            }
        }

        return antiNodesLocs.size.toString()
    }

    private fun searchForAntennaLocations(): Map<Char, List<Pair<Int, Int>>> {
        val antennaLocs: MutableMap<Char, MutableList<Pair<Int, Int>>> = mutableMapOf()

        for (row in grid.indices) {
            for (column in grid[row].indices) {
                if (grid[row][column] != '.') {
                    val antennas = antennaLocs.getOrPut(grid[row][column]) { mutableListOf() }
                    antennas.add(Pair(row, column))
                }
            }
        }

        return antennaLocs
    }

    private fun checkLocationIsInMap(loc: Pair<Int, Int>): Boolean = loc.first >= 0 && loc.second >= 0 && loc.first < grid.size && loc.second < grid[0].size

    override fun part2(): String {
        val antiNodesLocs: MutableSet<Pair<Int, Int>> = mutableSetOf()

        for (antennas in searchForAntennaLocations()) {
            for (thisAntenna in antennas.value) {
                antiNodesLocs.add(thisAntenna)
                for (otherAntenna in (antennas.value.takeLastWhile { it != thisAntenna })) {
                    val diffRow = thisAntenna.first - otherAntenna.first
                    val diffColumn = thisAntenna.second - otherAntenna.second

                    var iteration = 1
                    do {
                        val antiNodeLoc = Pair(thisAntenna.first - (iteration * diffRow * -1), thisAntenna.second - (iteration * diffColumn * -1))
                        val stillInMap = checkLocationIsInMap(antiNodeLoc)

                        if (stillInMap) {
                            antiNodesLocs.add(antiNodeLoc)
                            iteration++
                        }
                    } while (stillInMap)

                    iteration = 1
                    do {
                        val antiNodeLoc = Pair(thisAntenna.first - (iteration * diffRow), thisAntenna.second - (iteration * diffColumn))
                        val stillInMap = checkLocationIsInMap(antiNodeLoc)

                        if (stillInMap) {
                            antiNodesLocs.add(antiNodeLoc)
                            iteration++
                        }
                    } while (stillInMap)
                }
            }
        }

        return antiNodesLocs.size.toString()
    }
}
