package day5

import getLines
import kotlin.math.floor

fun main() {
  val tickets = getLines("day5_input.txt")
  val id = tickets.map {
    val row = calculateRow(it)
    val column = calculateColumn(it)
    row * 8 + column
  }.maxOrNull()
  println("Max id is $id")
}

fun calculateRow(ticket: String): Int {
  var rowRange = Pair(0, 127)
  ticket.take(7).forEach {
    val (lower, upper) = rowRange
    rowRange = if (it == 'F')
      Pair(lower, lower + floor(((upper - lower) / 2).toDouble()).toInt())
    else
      Pair(upper - floor(((upper - lower) / 2).toDouble()).toInt(), upper)
  }
  return rowRange.first
}

fun calculateColumn(ticket: String): Int {
  var columnRange = Pair(0, 7)
  ticket.takeLast(3).forEach {
    val (lower, upper) = columnRange
    columnRange = if (it == 'L')
      Pair(lower, lower + floor(((upper - lower) / 2).toDouble()).toInt())
    else
      Pair(upper - floor(((upper - lower) / 2).toDouble()).toInt(), upper)
  }
  return columnRange.first
}