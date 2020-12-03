package day1

import getLines

fun main() {
  val numbers = getLines("day1_input.txt").map { it.toInt() }
  numbers.forEachIndexed { firstIndex, first ->
    numbers.subList(firstIndex + 1, numbers.size).forEachIndexed { secondIndex, second ->
      if (firstIndex != firstIndex + secondIndex) {
        if (first + second == 2020) {
          println(first * second)
          return
        }
      }
    }
  }
}