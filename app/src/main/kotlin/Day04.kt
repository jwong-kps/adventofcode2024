package wong.jonathan.app

class Day04 {

    private val DAY_FILE_NAME = "input/day04.txt"

    private val M_POS = 1
    private val A_POS = 2
    private val S_POS = 3

    private val lines = readFileAsLinesUsingGetResourceAsStream(DAY_FILE_NAME)
    private val charArray2D = Array(lines.size) { CharArray(lines[0].length) }

    // below are the possible ways to make XMAS in each logical direction

    //         S     S     S
    //           A   A   A
    //             M M M
    //         S A M X M A S
    //             M M M
    //           A   A   A
    //         S     S     S

    // x = i,j

    // directions
    // NW: i-x , j-x
    // N:  i   , j-x
    // NE: i+x , j-x
    // E:  i+x , j
    // SE: i+x , j+x
    // S:  i   , j+x
    // SE: i-x , j+x
    // W:  i-x , j

    // m: x=1
    // a: x=2
    // s: x=3
    fun challengeOne() {

        //process lines into 2D array
        for (i in lines.indices) {
            for (j in lines[i].indices) {
                charArray2D[i][j] = lines[i][j]
            }
        }

        var xmasWordCount = 0

        for (i in charArray2D.indices) {
            for (j in charArray2D[i].indices) {
                //check current position is equal to X
                if (checkLetter(i, j, 'X')) {
                    // begin checking each direction from current position
                    if (checkXmasNorthWest(i, j))
                        xmasWordCount++
                    if (checkXmasNorth(i, j))
                        xmasWordCount++
                    if (checkXmasNorthEast(i, j))
                        xmasWordCount++
                    if (checkXmasEast(i, j))
                        xmasWordCount++
                    if (checkXmasSouthEast(i, j))
                        xmasWordCount++
                    if (checkXmasSouth(i, j))
                        xmasWordCount++
                    if (checkXmasSouthWest(i, j))
                        xmasWordCount++
                    if (checkXmasWest(i, j))
                        xmasWordCount++
                }
            }
        }

        println("Day 04 - Challenge 1 - $xmasWordCount")
    }

    private fun checkLetter(i: Int, j: Int, letter: Char): Boolean {
        if (i < 0 || i >= charArray2D.size || j < 0 || j >= charArray2D[0].size)
            return false

        return charArray2D[i][j] == letter
    }

    private fun checkXmasNorthWest(i: Int, j: Int): Boolean {
        return checkLetter(i - M_POS, j - M_POS, 'M') && checkLetter(i - A_POS, j - A_POS, 'A') && checkLetter(i - S_POS, j - S_POS, 'S')
    }

    private fun checkXmasNorth(i: Int, j: Int): Boolean {
        return checkLetter(i, j - M_POS, 'M') && checkLetter(i, j - A_POS, 'A') && checkLetter(i, j - S_POS, 'S')
    }

    private fun checkXmasNorthEast(i: Int, j: Int): Boolean {
        return checkLetter(i + M_POS, j - M_POS, 'M') && checkLetter(i + A_POS, j - A_POS, 'A') && checkLetter(i + S_POS, j - S_POS, 'S')
    }

    private fun checkXmasEast(i: Int, j: Int): Boolean {
        return checkLetter(i + M_POS, j, 'M') && checkLetter(i + A_POS, j, 'A') && checkLetter(i + S_POS, j, 'S')
    }

    private fun checkXmasSouthEast(i: Int, j: Int): Boolean {
        return checkLetter(i + M_POS, j + M_POS, 'M') && checkLetter(i + A_POS, j + A_POS, 'A') && checkLetter(i + S_POS, j + S_POS, 'S')
    }

    private fun checkXmasSouth(i: Int, j: Int): Boolean {
        return checkLetter(i, j + M_POS, 'M') && checkLetter(i, j + A_POS, 'A') && checkLetter(i, j + S_POS, 'S')
    }

    private fun checkXmasSouthWest(i: Int, j: Int): Boolean {
        return checkLetter(i - M_POS, j + M_POS, 'M') && checkLetter(i - A_POS, j + A_POS, 'A') && checkLetter(i - S_POS, j + S_POS, 'S')
    }

    private fun checkXmasWest(i: Int, j: Int): Boolean {
        return checkLetter(i - M_POS, j, 'M') && checkLetter(i - A_POS, j, 'A') && checkLetter(i - S_POS, j, 'S')
    }

    fun challengeTwo() {

        //process lines into 2D array
        for (i in lines.indices) {
            for (j in lines[i].indices) {
                charArray2D[i][j] = lines[i][j]
            }
        }
        var crossMasWordCount = 0

        for (i in charArray2D.indices) {
            for (j in charArray2D[i].indices) {
                //check current position is equal to A
                if (checkLetter(i, j, 'A')) {
                    // begin checking each direction from current position
                    if (checkCrossMasTopLeft(i, j, 'M') && checkCrossMasTopRight(i, j, 'M') && checkCrossMasBottomLeft(i, j, 'S') && checkCrossMasBottomRight(i, j, 'S'))
                        crossMasWordCount++
                    if (checkCrossMasTopLeft(i, j, 'S') && checkCrossMasTopRight(i, j, 'M') && checkCrossMasBottomLeft(i, j, 'S') && checkCrossMasBottomRight(i, j, 'M'))
                        crossMasWordCount++
                    if (checkCrossMasTopLeft(i, j, 'S') && checkCrossMasTopRight(i, j, 'S') && checkCrossMasBottomLeft(i, j, 'M') && checkCrossMasBottomRight(i, j, 'M'))
                        crossMasWordCount++
                    if (checkCrossMasTopLeft(i, j, 'M') && checkCrossMasTopRight(i, j, 'S') && checkCrossMasBottomLeft(i, j, 'M') && checkCrossMasBottomRight(i, j, 'S'))
                        crossMasWordCount++
                }
            }
        }

        println("Day 04 - Challenge 2 - $crossMasWordCount")
    }

    // directions
    // NW: i-1 , j-1
    // NE: i+1 , j-1
    // SE: i+1 , j+1
    // SE: i-1 , j+1
    private fun checkCrossMasTopLeft(i: Int, j: Int, letter: Char): Boolean {
        return checkLetter(i - 1, j - 1, letter)
    }

    private fun checkCrossMasTopRight(i: Int, j: Int, letter: Char): Boolean {
        return checkLetter(i + 1, j - 1, letter)
    }

    private fun checkCrossMasBottomRight(i: Int, j: Int, letter: Char): Boolean {
        return checkLetter(i + 1, j + 1, letter)
    }

    private fun checkCrossMasBottomLeft(i: Int, j: Int, letter: Char): Boolean {
        return checkLetter(i - 1, j + 1, letter)
    }
}
