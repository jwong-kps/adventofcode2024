package wong.jonathan.app

class Day05 {

    private val challengeFileName = "input/day05.txt"
    private val lines = readFileAsLinesUsingGetResourceAsStream(challengeFileName)
    private val rules = mutableSetOf<String>()
    private val pages = mutableListOf<String>()
    private val badPages: MutableList<String> = mutableListOf();

    fun challengeOne() {

        processFileIntoRulesAndPages()

        var sumOfMiddlePages = 0;

        for (item in pages) {
            val pageArray = item.split(",")

            for ((index, value) in pageArray.withIndex()) {
                if (index == pageArray.lastIndex) {
                    sumOfMiddlePages += pageArray[pageArray.size / 2].toInt()
                    break
                } // last number so let's just end it there

                var noRuleFound = false
                for (i in index + 1..pageArray.lastIndex) {
                    if (!rules.contains(value + "|" + pageArray[i])) {
                        noRuleFound = true
                        break
                    }
                }
                if (noRuleFound) {
                    badPages.add(item)
                    break
                }
            }
        }

        println("Day 05 - Challenge 1 - $sumOfMiddlePages")
    }

    private fun processFileIntoRulesAndPages() {
        var usePages = false;
        for (item in lines) {
            if (item.isBlank()) usePages = true

            if (!usePages && item.isNotBlank())
                rules.add(item)
            else if (item.isNotBlank())
                pages.add(item)
        }
    }

    fun challengeTwo() {
        var sumOfMiddlePages = 0;

        for (item in badPages) {
            val pageArray = item.split(",")
            val ruleMap = mutableMapOf<String, MutableSet<Int>>()

            for (rule in rules) {
                val part1 = rule.split("|")[0]
                val part2 = rule.split("|")[1]
                if (rule.startsWith(part1) && pageArray.contains(part1) && pageArray.contains(part2)) {
                    val ruleSet = ruleMap.getOrDefault(part1, mutableSetOf())
                    ruleSet.add(rule.split("|")[1].toInt())
                    ruleMap[part1] = ruleSet
                }
            }

            val middlePageIndex = (pageArray.size / 2)

            for ((key, value) in ruleMap)
                if (value.size == middlePageIndex) {
                    sumOfMiddlePages += key.toInt()
                    break
                }

        }

        println("Day 05 - Challenge 2 - $sumOfMiddlePages")
    }
}
