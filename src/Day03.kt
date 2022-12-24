fun main() {

    fun itemToPriority(item: Char): Int {
        if (item.isLowerCase())
            return item.code - 'a'.code + 1

        return item.code - 'A'.code + 27;
    }

    fun findErrorInRucksack(rucksack: String): Char {
        val compartmentSize = rucksack.length / 2

        val presentInFirst = mutableSetOf<Char>()
        for (i in 0..rucksack.length) {
            val item = rucksack[i]
            if (i < compartmentSize) {
                presentInFirst.add(item)
            } else {
                if (presentInFirst.contains(item)) {
                    return item
                }
            }
        }

        return 'a'
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { itemToPriority(findErrorInRucksack(it)) }
    }

    fun findBadge(group: List<String>): Char {
        val intersection = group[0].toSet().intersect(group[1].toSet()).intersect(group[2].toSet())

        intersection.println()
        return intersection.first()
    }

    fun part2(input: List<String>): Int {
        return input.windowed(3, 3).sumOf(fun(it: List<String>): Int {
            val badge = findBadge(it)
            val itemToPriority = itemToPriority(badge)
            itemToPriority.println()
            return itemToPriority
        })
    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    var input = readInput("Day03")
    part1(input).println()

    check(part2(testInput) == 70)
    part2(input).println()
}