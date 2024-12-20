package wong.jonathan.app

class Day14 : Challenge("day14", false) {
    data class Robot(var posX: Int, var posY: Int, var velX: Int, var velY: Int)

    private val lines = readInputFileAsListOfStrings()
    private val positionRegEx = Regex("p=\\d+,\\d+")
    private val velocityRegEx = Regex("v=-?\\d+,-?\\d+")

//    private val width = 11
//    private val height = 7

    private val width = 101
    private val height = 103

    private val seconds = 100

    private var robots = mutableListOf<Robot>()

    private fun parseLinesIntoRobots() {
        robots.clear()
        for (line in lines) {
            val positions = positionRegEx.find(line)!!.value.replace("p=", "").split(",").map { it.toInt() }
            val velocities = velocityRegEx.find(line)!!.value.replace("v=", "").split(",").map { it.toInt() }
            robots.add(Robot(positions[0], positions[1], velocities[0], velocities[1]))
        }
    }

    override fun part1(): String {
        parseLinesIntoRobots()

        for (robot in robots) {
            robot.posX = (robot.posX + (robot.velX * seconds)) % width
            robot.posY = (robot.posY + (robot.velY * seconds)) % height

            if (robot.posX < 0) robot.posX += width
            if (robot.posY < 0) robot.posY += height
        }

        var sum = 1
        sum *= robots.filter { it.posX < width / 2 && it.posY < height / 2 }.size
        sum *= robots.filter { it.posX > width / 2 && it.posY < height / 2 }.size
        sum *= robots.filter { it.posX < width / 2 && it.posY > height / 2 }.size
        sum *= robots.filter { it.posX > width / 2 && it.posY > height / 2 }.size

        return sum.toString()
    }

    override fun part2(): String {
        return ""
    }
}
