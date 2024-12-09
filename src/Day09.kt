package wong.jonathan.app

class Day09 : Challenge("day09", false) {
    private val diskMap = readInputFileAsListOfStrings()

    private var freeSpaceBlockIndicator: Long = -1

    override fun part1(): String {
        val diskMapAsBlocks = mutableListOf<Long>()
        var processFreeSpace = false
        var blockId: Long = 0

        for (char in diskMap[0]) {
            for (i in 1..char.toString().toLong()) {
                diskMapAsBlocks.add(if (processFreeSpace) freeSpaceBlockIndicator else blockId)
            }
            if (!processFreeSpace) blockId++
            processFreeSpace = !processFreeSpace
        }

        for (i in diskMapAsBlocks.indices.reversed()) {
            val freeBlockIndex = diskMapAsBlocks.indexOf(freeSpaceBlockIndicator)
            if (freeBlockIndex < i) {
                diskMapAsBlocks[freeBlockIndex] = diskMapAsBlocks[i]
                diskMapAsBlocks[i] = freeSpaceBlockIndicator
            }
        }

        return calculateCheckSum(diskMapAsBlocks).toString()
    }

    private fun calculateCheckSum(diskMapAsBlocks: List<Long>): Long
        = diskMapAsBlocks.filter { it >= 0 }.foldIndexed(0) { index, acc, value -> acc + value * index }


    override fun part2(): String {
        return ""
    }
}
