enum class Shape { ROCK, PAPER, SCISSOR, UNKNOWN }
enum class Outcome { WIN, LOSE, DRAW }

val shapeScoreMap = mapOf(Pair(Shape.ROCK, 1), Pair(Shape.PAPER, 2), Pair(Shape.SCISSOR, 3))
val outcomeScoreMap = mapOf(Pair(Outcome.LOSE, 0), Pair(Outcome.DRAW, 3), Pair(Outcome.WIN, 6))

val beats = mapOf(
    Pair(Shape.ROCK, Shape.SCISSOR),
    Pair(Shape.PAPER, Shape.ROCK),
    Pair(Shape.SCISSOR, Shape.PAPER),
)

val beatenBy: Map<Shape, Shape> = beats.entries.associate { it.value to it.key }

//val outcomes = mapOf(Pair(Pair(Shape.ROCK, Shape.ROCK), Outcome.DRAW))

fun main() {

    fun calculateOutcome(play: Pair<Shape, Shape>): Outcome {
        if (play.first == play.second)
            return Outcome.DRAW

        if ((play.first == Shape.ROCK && play.second == Shape.SCISSOR) ||
            (play.first == Shape.PAPER && play.second == Shape.ROCK) ||
            (play.first == Shape.SCISSOR && play.second == Shape.PAPER))
            return Outcome.LOSE

        return Outcome.WIN
    }

    fun calculateScore(play: Pair<Shape, Shape>): Int {
        val outcome: Outcome = calculateOutcome(play)
        return outcomeScoreMap[outcome]!! + shapeScoreMap[play.second]!!
    }

    fun calculateShape(play: Pair<Shape, Outcome>): Shape {
        if (play.second == Outcome.DRAW)
            return play.first

        if (play.second == Outcome.WIN)
            return beatenBy[play.first]!!

        return beats[play.first]!!
    }

    fun calculateScore(play: Pair<Shape, Outcome>): Int {
        val playedShape: Shape = calculateShape(play)
        return outcomeScoreMap[play.second]!! + shapeScoreMap[playedShape]!!
    }

    fun stringToPlay(input: String): Shape {
        if (input in listOf("A", "X"))
            return Shape.ROCK
        if (input in listOf("B", "Y"))
            return Shape.PAPER
        if (input in listOf("C", "Z"))
            return Shape.SCISSOR

        throw IllegalStateException("Unknown play")
    }

    fun stringToOutcome(input: String): Outcome {
        if (input in listOf("X"))
            return Outcome.LOSE
        if (input in listOf("Y"))
            return Outcome.DRAW
        if (input in listOf("Z"))
            return Outcome.WIN

        throw IllegalStateException("Unknown play")
    }


    fun String.toPlay(): Pair<Shape, Shape> {
        val tokens = this.split(" ")

        return Pair(stringToPlay(tokens[0]), stringToPlay(tokens[1]))
    }

    fun String.toStrategy(): Pair<Shape, Outcome> {
        val tokens = this.split(" ")

        return Pair(stringToPlay(tokens[0]), stringToOutcome(tokens[1]))
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { calculateScore(it.toPlay()) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { calculateScore(it.toStrategy()) }
    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    var input = readInput("Day02")
    part1(input).println()

    check(part2(testInput) == 12)
    part2(input).println()
}
