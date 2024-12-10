package wong.jonathan.app

class Day09 : Challenge("day09", false) {
    private val diskMap = readInputFileAsListOfStrings()

    private var freeSpaceBlockIndicator: Long = -1
    private var blockIdToBlockSize = mutableMapOf<Long, Long>()

    override fun part1(): String {
        val diskMapAsBlocks = generateBlockDiskMap()

        for (i in diskMapAsBlocks.indices.reversed()) {
            val freeBlockIndex = diskMapAsBlocks.indexOf(freeSpaceBlockIndicator)
            if (freeBlockIndex < i) {
                diskMapAsBlocks[freeBlockIndex] = diskMapAsBlocks[i]
                diskMapAsBlocks[i] = freeSpaceBlockIndicator
            }
        }

        return calculateCheckSum(diskMapAsBlocks).toString()
    }

    private fun generateBlockDiskMap(): MutableList<Long> {
        val diskMapAsBlocks = mutableListOf<Long>()
        var processFreeSpace = false
        var blockId: Long = 0

        for (char in diskMap[0]) {
            val blockSize = char.toString().toLong()
            for (i in 1..blockSize) {
                diskMapAsBlocks.add(if (processFreeSpace) freeSpaceBlockIndicator else blockId)
            }
            if (!processFreeSpace) {
                blockIdToBlockSize[blockId] = blockSize
                blockId++
            }
            processFreeSpace = !processFreeSpace
        }

        return diskMapAsBlocks
    }

    private fun calculateCheckSum(diskMapAsBlocks: List<Long>): Long = diskMapAsBlocks.filter { it >= 0 }.foldIndexed(0) { index, acc, value -> acc + value * index }

    override fun part2(): String {
        blockIdToBlockSize = mutableMapOf()
        val diskMapAsBlocks = generateBlockDiskMap()

        for (key in blockIdToBlockSize.keys.reversed()) {
            val index = findIndexOfFreeSpaceForBlockSize(blockIdToBlockSize.getOrDefault(key, 0), diskMapAsBlocks)
            val indexOfBlock = diskMapAsBlocks.indexOf(key)
            if (index == -1 || index > indexOfBlock) continue
            diskMapAsBlocks.replaceAll { if (it == key) freeSpaceBlockIndicator else it }
            for (i in index..(index - 1 + blockIdToBlockSize[key]!!)) {
                diskMapAsBlocks[i.toInt()] = key
            }
        }

        diskMapAsBlocks.replaceAll { if (it < 0) 0 else it }

        return calculateCheckSum(diskMapAsBlocks).toString()
    }

    private fun findIndexOfFreeSpaceForBlockSize(blockSize: Long, diskMapAsBlocks: MutableList<Long>): Int {
        var spaceFound = false
        var index = -1
        for (i in diskMapAsBlocks.indices) {
            if (diskMapAsBlocks[i] == freeSpaceBlockIndicator) {
                spaceFound = true
                for (j in (i + 1)..<i + blockSize) {
                    if (j.toInt() == diskMapAsBlocks.size || diskMapAsBlocks[j.toInt()] != freeSpaceBlockIndicator) {
                        spaceFound = false
                        break
                    }
                }
                if (spaceFound)
                {
                    index = i
                    break
                }
            }
        }

        return index
    }
}
