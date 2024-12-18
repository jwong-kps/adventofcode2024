package wong.jonathan.app

class Day17 : Challenge("day17", false) {
    private val lines = readInputFileAsListOfStrings()
    private val regex = "\\d+".toRegex()

    private var a: Int = 0
    private var b: Int = 0
    private var c: Int = 0

    override fun part1(): String {
        val output = mutableListOf<Int>()

        a = regex.find(lines[0])?.value?.toInt()!!
        b = regex.find(lines[1])?.value?.toInt()!!
        c = regex.find(lines[2])?.value?.toInt()!!

        val program = regex.findAll(lines[4]).map { it.value.toInt() }.toList()

        var pointer = 0

        while (pointer < program.size) {
            val opcode = program[pointer]
            val operand = program[pointer + 1]

            when (opcode) {
                0 -> a = a shr getComboValue(operand)
                1 -> b = b xor operand
                2 -> b = getComboValue(operand) % 8
                3 -> {
                    if (a != 0) {
                        pointer = operand
                        continue
                    }
                }

                4 -> b = b xor c
                5 -> output.add(getComboValue(operand) % 8)
                6 -> b = a shr getComboValue(operand)
                7 -> c = a shr getComboValue(operand)
            }

            pointer += 2
        }

        return output.joinToString(",")
    }

    private fun getComboValue(operand: Int): Int = when (operand) {
        0, 1, 2, 3 -> operand
        4 -> a
        5 -> b
        6 -> c
        else -> throw RuntimeException("Invalid combination")
    }

    override fun part2(): String {
        return ""
    }
}
