package day1
import getLines

fun main() {
  val numbers = getLines("day1_input.txt").map { it.toInt() }
  numbers.forEachIndexed { firstIndex, first ->
//    println("First: $first ($firstIndex)")

    numbers.forEachIndexed { secondIndex, second ->

      if (firstIndex != secondIndex) {
//        println("\tSecond: $second (${firstIndex + secondIndex})")

        numbers.forEachIndexed { thirdIndex, third ->
          if (thirdIndex != secondIndex) {

//            println("\t\tThird: $third (${firstIndex + secondIndex + thirdIndex})")
            if (first + second + third == 2020) {
              println(first * second * third)
              return
            }

          }
        }

      }
    }

  }
}