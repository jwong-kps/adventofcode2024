package wong.jonathan.app

import java.math.BigInteger

class Day07 {
    private val challengeFileName = "input/day07.txt"

    private val lines = readFileAsLinesUsingGetResourceAsStream(challengeFileName)
    private val badLines: MutableList<String> = mutableListOf()
    private var sumOfValidEquations: BigInteger = BigInteger.ZERO

    fun challengeOne() {

        for (line in lines) {
            val result = line.split(": ")[0].toBigInteger()
            val parts = line.split(": ")[1].split(" ")
            val bigIntArray = convertStringArrayToBigIntArray(parts)

            if (calculate(bigIntArray[0], 1, bigIntArray, result))
                sumOfValidEquations += result
            else
                badLines.add(line)
        }

        println("Day 07 - Challenge 1 - $sumOfValidEquations")
    }

    private fun convertStringArrayToBigIntArray(input: List<String>): Array<BigInteger> {
        return input.map { BigInteger(it) }.toTypedArray()
    }

    private fun calculate(current: BigInteger, index: Int, bigIntegers: Array<BigInteger>, actualResult: BigInteger): Boolean {
        if (index < bigIntegers.size) {
            if (calculate(current * bigIntegers[index], index + 1, bigIntegers, actualResult)) return true

            return calculate(current + bigIntegers[index], index + 1, bigIntegers, actualResult)
        }

        return current == actualResult
    }

    fun challengeTwo() {
        var sumOfValidEquations: BigInteger = sumOfValidEquations

        for (line in badLines) {
            val result = line.split(": ")[0].toBigInteger()
            val parts = line.split(": ")[1].split(" ")
            val bigIntArray = convertStringArrayToBigIntArray(parts)

            if (calculateWithNewOp(bigIntArray[0], 1, bigIntArray, result))
                sumOfValidEquations += result
        }

        println("Day 07 - Challenge 2 - $sumOfValidEquations")
    }


    private fun calculateWithNewOp(current: BigInteger, index: Int, bigIntegers: Array<BigInteger>, actualResult: BigInteger): Boolean {
        if (index < bigIntegers.size) {
            val newNumber = current.toString().plus(bigIntegers[index].toString())

            if (calculateWithNewOp(current * bigIntegers[index], index + 1, bigIntegers, actualResult)) return true

            if (calculateWithNewOp(current + bigIntegers[index], index + 1, bigIntegers, actualResult)) return true

            return calculateWithNewOp(newNumber.toBigInteger(), index + 1, bigIntegers, actualResult)
        }

        return current == actualResult
    }

}
