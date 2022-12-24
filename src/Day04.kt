data class Range(val start: Int, val end: Int)

fun main() {

    fun findAssignments(input: String): Pair<Range, Range> {
        val (startOne, endOne, startTwo, endTwo) = "(\\d+)-(\\d+),(\\d+)-(\\d+)".toRegex()
            .matchEntire(input)
            ?.destructured
            ?: throw IllegalArgumentException("Incorrect input line $input")

        return Pair(Range(startOne.toInt(), endOne.toInt()), Range(startTwo.toInt(), endTwo.toInt()))
    }

    fun areFullyOverlapping(assignments: Pair<Range, Range>): Boolean {
        if (assignments.first.start <= assignments.second.start &&
            assignments.first.end >= assignments.second.end)
            return true

        if (assignments.second.start <= assignments.first.start &&
            assignments.second.end >= assignments.first.end)
            return true


        return false
    }

    fun areNotOverlapping(assignments: Pair<Range, Range>): Boolean {
        if (assignments.first.end < assignments.second.start || assignments.first.start > assignments.second.end)
            return true

        return false
    }

    fun part1(input: List<String>): Int {
        return input.count(fun(it: String): Boolean {
            val assignments = findAssignments(it)
            return areFullyOverlapping(assignments)
        })
    }

    fun part2(input: List<String>): Int {
        val notOverllaping = input.count(fun(it: String): Boolean {
            val assignments = findAssignments(it)
            return areNotOverlapping(assignments)
        })
        return input.size - notOverllaping
    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    var input = readInput("Day04")
    part1(input).println()

    check(part2(testInput) == 4)
    part2(input).println()
}
