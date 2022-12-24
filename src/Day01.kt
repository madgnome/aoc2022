fun main() {
    fun part1(input: List<String>): Int {

        var currentMax = -1;
        var elfCalories = 0;
        val maxCalories = listOf<Int>()
        for (line in input) {
            if (line.isBlank()) {
                if (elfCalories > currentMax) {
                    currentMax = elfCalories
                }

                elfCalories = 0;
                continue
            }

            elfCalories += line.toInt()
        }

        if (elfCalories > currentMax) {
            currentMax = elfCalories
        }

        return currentMax
    }

    fun part2(input: List<String>): Int {
        var elfCalories = 0;
        val maxCalories = mutableListOf<Int>()
        for (line in input) {
            if (line.isBlank()) {
                maxCalories.add(elfCalories)
                elfCalories = 0;
                continue
            }

            elfCalories += line.toInt()
        }

        maxCalories.add(elfCalories)

        maxCalories.sortDescending()

        return maxCalories[0] + maxCalories[1] + maxCalories[2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
