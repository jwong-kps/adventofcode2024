package wong.jonathan.app


class Day13 : Challenge("day13", false) {
    private val lines = readInputFileAsListOfStrings()
    private val regex = "\\d+".toRegex()

    override fun part1(): String {
        var ax: Double = 0.0
        var ay: Double = 0.0
        var bx: Double = 0.0
        var by: Double = 0.0
        var px: Double = 0.0
        var py: Double = 0.0

        var count: Long = 0L

        for (line in lines) {
            val numbers = regex.findAll(line).map { it.value }.toList()
            if (line.startsWith("Button A")) {
                ax = numbers[0].toDouble()
                ay = numbers[1].toDouble()
            } else if (line.startsWith("Button B")) {
                bx = numbers[0].toDouble()
                by = numbers[1].toDouble()
            } else if (line.startsWith("Prize")) {
                px = numbers[0].toDouble()
                py = numbers[1].toDouble()

                if ((by * ax - bx * ay) == 0.0 || (ay * bx - ax * by) == 0.0) continue

                val buttonA = solveForButtonA(ax, ay, bx, by, px, py)
                val buttonB = solveForButtonB(ax, ay, bx, by, px, py)

                if (buttonA % 1.0 == 0.0 && buttonB % 1.0 == 0.0) count += buttonA.toLong() * 3 + buttonB.toLong()
            }
        }

        return count.toString()
    }

    private fun hasNonZeroDecimalPart(numberString: String): Boolean {
        val parts = numberString.split(".")
        return parts.size == 2 && parts[1].toDoubleOrNull() != 0.0
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
    private fun solveForButtonA(ax: Double, ay: Double, bx: Double, by: Double, px: Double, py: Double): Double =
        (by * px - bx * py) / (by * ax - bx * ay)

    private fun solveForButtonB(ax: Double, ay: Double, bx: Double, by: Double, px: Double, py: Double): Double =
        (ay * px - ax * py) / (ay * bx - ax * by)

    override fun part2(): String {
        var ax: Double = 0.0
        var ay: Double = 0.0
        var bx: Double = 0.0
        var by: Double = 0.0
        var px: Double = 0.0
        var py: Double = 0.0

        var count: Long = 0L

        for (line in lines) {
            val numbers = regex.findAll(line).map { it.value }.toList()
            if (line.startsWith("Button A")) {
                ax = numbers[0].toDouble()
                ay = numbers[1].toDouble()
            } else if (line.startsWith("Button B")) {
                bx = numbers[0].toDouble()
                by = numbers[1].toDouble()
            } else if (line.startsWith("Prize")) {
                px = numbers[0].toDouble() + 10000000000000.0
                py = numbers[1].toDouble() + 10000000000000.0

                if ((by * ax - bx * ay) == 0.0 || (ay * bx - ax * by) == 0.0) continue

                val buttonA = solveForButtonA(ax, ay, bx, by, px, py)
                val buttonB = solveForButtonB(ax, ay, bx, by, px, py)

                if (buttonA % 1.0 == 0.0 && buttonB % 1.0 == 0.0) count += buttonA.toLong() * 3 + buttonB.toLong()
            }
        }

        return count.toString()
    }
}
