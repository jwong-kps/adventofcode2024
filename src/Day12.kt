package wong.jonathan.app

import java.util.*

class Day12 : Challenge("day12", false) {
    private val lines = readInputFileAsListOfStrings()
    private val map = processLinesInto2dArray(lines)

    // right, down, left, up
    private var directions = arrayOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))

    override fun part1(): String {
        val regions = HashSet<Set<Pair<Int, Int>>>()
        val seen = mutableSetOf<Pair<Int, Int>>()

        for (row in map.indices) {
            for (col in map[row].indices) {
                if (seen.contains(Pair(row, col))) continue
                seen.add(Pair(row, col))

                val region = mutableSetOf(Pair(row, col))

                val queue: Queue<Pair<Int, Int>> = LinkedList()
                queue.add(Pair(row, col))

                val plant = map[row][col]

                while (queue.isNotEmpty()) {
                    val current = queue.poll()

                    for (direction in directions) {
                        val nextRow = current.first + direction.first
                        val nextColumn = current.second + direction.second

                        if (!checkLocationIsInMap(nextRow, nextColumn)) continue

                        if (map[nextRow][nextColumn] != plant) continue

                        if (region.contains(Pair(nextRow, nextColumn))) continue

                        region.add(Pair(nextRow, nextColumn))
                        seen.add(Pair(nextRow, nextColumn))
                        queue.add(Pair(nextRow, nextColumn))
                    }
                }

                if (region.isNotEmpty()) regions.add(region)
            }
        }

        //printRegions(regions)

        return calculatePrice(regions)
    }

    private fun calculatePrice(regions: HashSet<Set<Pair<Int, Int>>>): String
    {
        var price = 0

        for (region in regions)
        {
            for (pair in region)
            {
                var neighbours = 0
                for (direction in directions) {
                    val nextRow = pair.first + direction.first
                    val nextColumn = pair.second + direction.second
                    if (region.contains(Pair(nextRow, nextColumn))) neighbours++
                }

                price += (4 - neighbours) * region.size
            }
        }

        return price.toString()
    }

    private fun printRegions(regions: HashSet<Set<Pair<Int, Int>>>) {
        for (region in regions) {
            for (pair in region) {
                print(map[pair.first][pair.second])
            }
            print(", count=${region.size}")
            println()
        }
    }

    private fun checkLocationIsInMap(i: Int, j: Int): Boolean = i >= 0 && j >= 0 && i < map.size && j < map[0].size

    override fun part2(): String {
        return ""
    }
}
