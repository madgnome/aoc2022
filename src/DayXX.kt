fun main() {
    fun part1(input: List<String>): Int {

        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("Day01_test")
    check(part1(testInput) == 1)
    var input = readInput("Day01")
    part1(input).println()

    check(part2(testInput) == 1)
    part2(input).println()
}
