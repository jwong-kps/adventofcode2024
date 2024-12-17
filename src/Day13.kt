package wong.jonathan.app


class Day13 : Challenge("day13", false) {
    private val lines = readInputFileAsListOfStrings()
    private val regex = "\\d+".toRegex()

    override fun part1(): String {

        var ax = 0
        var ay = 0
        var bx = 0
        var by = 0
        var px = 0
        var py = 0

        var count = 0

        for (line in lines) {
            val numbers = regex.findAll(line).map { it.value }.toList()
            if (line.startsWith("Button A"))
            {
                ax = numbers[0].toInt()
                ay = numbers[1].toInt()
            } else if (line.startsWith("Button B"))
            {
                bx = numbers[0].toInt()
                by = numbers[1].toInt()
            } else if (line.startsWith("Prize"))
            {
                px = numbers[0].toInt()
                py = numbers[1].toInt()

                if ((by * ax - bx * ay) == 0 || (ay * bx - ax * by) == 0) continue

                val buttonA = solveForButtonA(ax, ay, bx, by, px, py)
                val buttonB = solveForButtonB(ax, ay, bx, by, px, py)

                if (!hasNonZeroDecimalPart(buttonA.toString()) && !hasNonZeroDecimalPart(buttonB.toString())) count += buttonA.toInt() * 3 + buttonB.toInt()
            }
        }

        return count.toString()
    }

    fun hasNonZeroDecimalPart(numberString: String): Boolean {
        val parts = numberString.split(".")
        return parts.size == 2 && parts[1].toIntOrNull() != 0
    }

    /*
    px = ax * aCount + bx * bCount
    py = ay * aCount + by * bCount

    Solve to eliminate bCount
      by * px = by * ax * aCount + by * bx * bCount
      bx * py = bx * ay * aCount + bx * by * bCount
      by * px - bx * py = by * ax * aCount - bx * ay * aCount
      by * px - bx * py = aCount(by * ax - bx * ay)
      aCount = (by * px - bx * py) / (by * ax - bx * ay)

    Solve to eliminate aCount
      ay * px = ay * ax * aCount + ay * bx * bCount
      ax * py = ax * ay * aCount + ax * by * bCount
      ay * px - ax * py = ay * bx * bCount - ax * by * bCount
      ay * px - ax * py = bCount(ay * bx - ax * by)
      bCount = (ay * px - ax * py) / (ay * bx - ax * by)
     */
    private fun solveForButtonA(ax: Int, ay: Int, bx: Int, by: Int, px: Int, py: Int): Double = (by.toDouble() * px.toDouble() - bx.toDouble() * py.toDouble()) / (by.toDouble() * ax.toDouble() - bx.toDouble() * ay.toDouble())

    private fun solveForButtonB(ax: Int, ay: Int, bx: Int, by: Int, px: Int, py: Int): Double = (ay.toDouble() * px.toDouble() - ax.toDouble() * py.toDouble()) / (ay.toDouble() * bx.toDouble() - ax.toDouble() * by.toDouble())

    override fun part2(): String {
        return ""
    }
}
