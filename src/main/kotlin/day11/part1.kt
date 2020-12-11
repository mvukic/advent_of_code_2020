package day11

import getLines

fun main() {
  val sittingArea: MutableList<MutableList<String>> = getLines("day11_input.txt")
    .map {
      it.toList().map { char -> char.toString() }.toMutableList()
    }
    .toMutableList()

  var somethingEvolved = true
  var currentEvolution = sittingArea.map { row -> row.map { it }.toMutableList() }.toMutableList()
  while (somethingEvolved) {
    // Reset evolution flag
    somethingEvolved = false
    // Initialize empty next evolution
    val nextEvolution = mutableListOf<MutableList<String>>()

    // Calculate next evolution for all seats
    for (rowIndex in currentEvolution.indices) {
      val newRowSeatingOrder = currentEvolution[rowIndex].indices.map { getNextState1(currentEvolution, rowIndex, it) }
        .toMutableList()
      nextEvolution.add(newRowSeatingOrder)
    }
    // Compare evolutions for difference
    somethingEvolved = areDifferent1(currentEvolution, nextEvolution)
    // Replace old evolution with the new one
    currentEvolution = nextEvolution
  }
  println("Occupied seats count: ${getOccupiedSeatsCount1(currentEvolution)}")
}

fun areDifferent1(first: MutableList<MutableList<String>>, second: MutableList<MutableList<String>>): Boolean{
  for (rowIndex in first.indices) {
    for (columnIndex in first[rowIndex].indices) {
      if (first[rowIndex][columnIndex] != second[rowIndex][columnIndex]) {
        return true
      }
    }
  }
  return false
}

fun getOccupiedSeatsCount1(seats: MutableList<MutableList<String>>): Int {
  return seats.flatten().count { it == "#" }
}

fun getNextState1(seats: MutableList<MutableList<String>>, rowIndex: Int, columnIndex: Int): String {
  val currentSeatState = getSeatState1(seats, Pair(rowIndex, columnIndex))

  // It is a floor, just return it
  if (currentSeatState == ".") {
    return "."
  }

  val seatUpLeft = Pair(rowIndex - 1, columnIndex - 1)
  val seatUp = Pair(rowIndex - 1, columnIndex)
  val seatUpRight = Pair(rowIndex - 1, columnIndex + 1)
  val seatLeft = Pair(rowIndex, columnIndex - 1)
  val seatRight = Pair(rowIndex, columnIndex + 1)
  val seatBottomLeft = Pair(rowIndex + 1, columnIndex - 1)
  val seatBottom = Pair(rowIndex + 1, columnIndex)
  val seatBottomRight = Pair(rowIndex + 1, columnIndex + 1)

  //    [seatUpLeft]         [seatUp]           [seatUpRight]
  //     [seatLeft]     [currentSeatState]       [seatRight]
  //  [seatBottomLeft]    [seatBottom]        [seatBottomRight]

  val adjacentSeats = listOf(
    getSeatState1(seats, seatUpLeft),
    getSeatState1(seats, seatUp),
    getSeatState1(seats, seatUpRight),
    getSeatState1(seats, seatLeft),
    getSeatState1(seats, seatRight),
    getSeatState1(seats, seatBottomLeft),
    getSeatState1(seats, seatBottom),
    getSeatState1(seats, seatBottomRight)
  )

  val adjacentTakenSeatsCount = adjacentSeats.count { it === "#" }

  return if (currentSeatState == "L") {
    // Current seat is empty
    if (adjacentTakenSeatsCount == 0) "#" else "L"
  } else {
    // Current seat is occupied
    if (adjacentTakenSeatsCount >= 4) "L" else "#"
  }
}

fun getSeatState1(seats: MutableList<MutableList<String>>, indexes: Pair<Int, Int>): String {
  val (rowIndex, columnIndex) = indexes
  // if row is outside of the seating area
  if (rowIndex < 0 || rowIndex >= seats.size) {
    return "."
  }
  // if column is outside of the seating area
  if (columnIndex < 0 || columnIndex >= seats[rowIndex].size) {
    return "."
  }
  // Else return the seat state on given indexes
  return seats[rowIndex][columnIndex]
}
