package day5

import getLines

fun main() {
  val tickets = getLines("day5_input.txt").map {
    Pair(calculateRow(it), calculateColumn(it))
  }
  println("Tickets: ${tickets.size}")

  val seats: Array<CharArray> = (0..127).map { CharArray(8) { '.' } }.toTypedArray()

  // Populate the seats using existing tickets
  tickets.forEach { (row, column) ->
    seats[row][column] = 'X'
  }

  seats.forEachIndexed { rowIndex, row ->
    print("$rowIndex ")
    row.forEach { column ->
      print(column)
    }
    println()
  }

  // Read location from printed image on the console
  println("My id is ${81 * 8 + 3}")
}
