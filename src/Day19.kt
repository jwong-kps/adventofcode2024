package wong.jonathan.app

class Day19 : Challenge("day19", false) {
    private val lines = readInputFileAsListOfStrings()
    private var patterns = lines[0].split(", ")
    private var maxPatternLength: Int = patterns.maxByOrNull { it.length }?.length ?: 0
    private var cache = mutableMapOf<String, Boolean>()
    private var cacheCount = mutableMapOf<String, Long>()

    override fun part1(): String {
        var count = 0
        for (lineIndex in 2..lines.lastIndex) {
            if (canMakeDesign(lines[lineIndex]) == true) count++
        }
        return count.toString()
    }

    private fun canMakeDesign(design: String): Boolean? {
        if (design.isBlank()) return true

        if (cache.containsKey(design)) return cache[design]

        val maxLength = if (design.length > maxPatternLength) maxPatternLength else design.length

        for (i in 1..maxLength) {
            if (patterns.contains(design.take(i)) && canMakeDesign(design.drop(i)) == true) {
                cache[design] = true
                return true
            }
        }

        cache[design] = false

        return false
    }

    override fun part2(): String {
        var count = 0L
        for (lineIndex in 2..lines.lastIndex) {
            count += countDesigns(lines[lineIndex])
        }
        return count.toString()
    }

    private fun countDesigns(design: String): Long {
        if (design.isBlank()) return 1

        if (cacheCount.containsKey(design)) return cacheCount.getOrDefault(design, 0)

        val maxLength = if (design.length > maxPatternLength) maxPatternLength else design.length

        var count = 0L
        for (i in 1..maxLength) {
            if (patterns.contains(design.take(i))) {
                count += countDesigns(design.drop(i))
            }
        }

        cacheCount[design] = count

        return count
    }
}
