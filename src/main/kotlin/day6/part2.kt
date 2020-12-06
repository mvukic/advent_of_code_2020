package day6

import getLines

fun main() {

  val groups = mutableListOf<MutableList<MutableList<Char>>>()
  var group = mutableListOf<MutableList<Char>>()

  val lines = getLines("day6_input.txt")
  lines.forEachIndexed { index, line ->
    when {
      line.isBlank() -> {
        // Save answers for the group
        groups.add(group)
        group = mutableListOf()
      }
      lines.size == index + 1 -> {
        // Add last answers to the current group
        group.add(line.toMutableList())
        groups.add(group)
      }
      else -> {
        // Add answers to the current group
        group.add(line.toMutableList())
      }
    }
  }

  val yesAnswers = groups
    // Pair of group size and each question occurrence count
    .map { singleGroup ->
      Pair(singleGroup.size, singleGroup.flatten().groupingBy { it }.eachCount())
    }
    // Filter only questions that were answered by every person in the group
    .map { (groupSize, answersMap) ->
      answersMap.filterValues { it == groupSize }
    }
    // Get those unique questions
    .map {
      it.keys.size
    }
    .sum()
  println(yesAnswers)

}