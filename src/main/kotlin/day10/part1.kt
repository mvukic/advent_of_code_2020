package day10

import getLines

fun main() {
  val unorderedAdapters = getLines("day10_input.txt").map { it.toInt() }

  val orderedAdapters = mutableListOf<Int>()
  var diffOne = 0
  var diffTwo = 0
  var diffThree = 0

  var lastAdapterJoltage = 0
  while (orderedAdapters.size < unorderedAdapters.size) {
    println("Last adapter: $lastAdapterJoltage")
    val uncheckedAdapters = unorderedAdapters.filterNot { adapter -> orderedAdapters.contains(adapter) }
    println("Adapters left to check: $unorderedAdapters")
    val differenceOfOne = uncheckedAdapters.filter { it == lastAdapterJoltage + 1 }.sorted()
    val differenceOfTwo = uncheckedAdapters.filter { it == lastAdapterJoltage + 2 }.sorted()
    val differenceOfThree = uncheckedAdapters.filter { it == lastAdapterJoltage + 3 }.sorted()
    println("By one:   $differenceOfOne")
    println("By two:   $differenceOfTwo")
    println("By three: $differenceOfThree")

    // Find the adapter with the smallest joltage margin from the last adapter
    val smallest = when {
      differenceOfOne.isNotEmpty() -> {
        println("one")
        diffOne++
        differenceOfOne.first()
      }
      differenceOfTwo.isNotEmpty() -> {
        println("two")
        diffTwo++
        differenceOfTwo.first()
      }
      else -> {
        println("three")
        diffThree++
        differenceOfThree.first()
      }
    }
    // Add the adapter with the smallest
    orderedAdapters.add(smallest)
    lastAdapterJoltage = smallest
    println()
  }

  println(diffOne)
  println(diffTwo)
  println(diffThree + 1)
  println(diffOne * (diffThree + 1))

}