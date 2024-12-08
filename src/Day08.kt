package wong.jonathan.app

class Day08 {
    private val challengeFileNameExample = "input/examples/day08.txt"
    private val challengeFileName = "input/day08.txt"

    private val lines = readFileAsLinesUsingGetResourceAsStream(challengeFileNameExample)
    private val map = processLinesInto2dArray(lines)

    fun challengeOne() {
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

        println("Day 08 - Challenge 1 - ${antiNodesLocs.size}")
    }

    private fun searchForAntennaLocations(): Map<Char, List<Pair<Int, Int>>> {
        val antennaLocs: MutableMap<Char, MutableList<Pair<Int, Int>>> = mutableMapOf()

        for (row in map.indices) {
            for (column in map[row].indices) {
                if (map[row][column] != '.') {
                    val antennas = antennaLocs.getOrPut(map[row][column]) { mutableListOf() }
                    antennas.add(Pair(row, column))
                }
            }
        }

        return antennaLocs
    }

    private fun checkLocationIsInMap(loc: Pair<Int, Int>): Boolean {
        return loc.first >= 0 && loc.second >= 0 && loc.first < map.size && loc.second < map[0].size
    }

    fun challengeTwo() {
        val antiNodesLocs: MutableSet<Pair<Int, Int>> = mutableSetOf()

        for (antennas in searchForAntennaLocations()) {
            for (thisAntenna in antennas.value) {
                antiNodesLocs.add(thisAntenna)
                for (otherAntenna in (antennas.value.takeLastWhile { it != thisAntenna })) {
                    val diffRow = thisAntenna.first - otherAntenna.first
                    val diffColumn = thisAntenna.second - otherAntenna.second

                    var iteration = 1
                    do {
                        var stillInMap = false
                        val antiNodeLoc = Pair(thisAntenna.first - (iteration * diffRow * -1), thisAntenna.second - (iteration * diffColumn * -1))

                        if (checkLocationIsInMap(antiNodeLoc)) {
                            antiNodesLocs.add(antiNodeLoc)
                            stillInMap = true
                            iteration++
                        }
                    } while (stillInMap)

                    iteration = 1
                    do {
                        var stillInMap = false
                        val antiNodeLoc = Pair(thisAntenna.first - (iteration * diffRow), thisAntenna.second - (iteration * diffColumn))

                        if (checkLocationIsInMap(antiNodeLoc)) {
                            antiNodesLocs.add(antiNodeLoc)
                            stillInMap = true
                            iteration++
                        }
                    } while (stillInMap)
                }
            }
        }

        println("Day 08 - Challenge 2 - ${antiNodesLocs.size}")
    }
}
