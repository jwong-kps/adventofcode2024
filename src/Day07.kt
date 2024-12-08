package wong.jonathan.app

import java.math.BigInteger

class Day07 : Challenge("day07", false) {
    private val lines = readInputFileAsListOfStrings()

    private val badLines: MutableList<String> = mutableListOf()
    private var sumOfValidEquations: BigInteger = BigInteger.ZERO

    override fun part1(): String {

        for (line in lines) {
            val result = line.split(": ")[0].toBigInteger()
            val parts = line.split(": ")[1].split(" ")
            val bigIntArray = convertStringArrayToBigIntArray(parts)

            if (calculate(bigIntArray[0], 1, bigIntArray, result))
                sumOfValidEquations += result
            else
                badLines.add(line)
        }

        return sumOfValidEquations.toString()
    }

    private fun convertStringArrayToBigIntArray(input: List<String>): Array<BigInteger> = input.map { BigInteger(it) }.toTypedArray()

    private fun calculate(current: BigInteger, index: Int, bigIntegers: Array<BigInteger>, actualResult: BigInteger): Boolean {
        if (index < bigIntegers.size) {
            if (calculate(current * bigIntegers[index], index + 1, bigIntegers, actualResult)) return true

            return calculate(current + bigIntegers[index], index + 1, bigIntegers, actualResult)
        }

        return current == actualResult
    }

    override fun part2(): String {
        var sumOfValidEquations: BigInteger = sumOfValidEquations

        for (line in badLines) {
            val result = line.split(": ")[0].toBigInteger()
            val parts = line.split(": ")[1].split(" ")
            val bigIntArray = convertStringArrayToBigIntArray(parts)

            if (calculateWithNewOp(bigIntArray[0], 1, bigIntArray, result))
                sumOfValidEquations += result
        }

        return sumOfValidEquations.toString()
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
