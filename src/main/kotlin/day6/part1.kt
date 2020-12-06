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
        // Just concatenate last line to the current passport
        group.add(line.toMutableList())
        groups.add(group)
      }
      else -> {
        // Concatenate partial passport data
        group.add(line.toMutableList())
      }
    }
  }

  val yesAnswers = groups
    .map { it.flatten().distinct() }
    .map { it.count() }
    .sum()
  println(yesAnswers)
}