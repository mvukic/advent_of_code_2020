package day13

import getLines

fun main() {
  val (timestampData, bussesData) = getLines("day13_input.txt")

  // Start time to check
  val start = timestampData.toInt()
  // End time
  val end = start + 15

  // List of busses
  val busses = bussesData.split(",").filter { it != "x" }.map { it.toInt() }

  val possibleDepartures: MutableMap<Int, List<Pair<Int, String>>> = mutableMapOf()

  (start..end).forEach { time ->
    possibleDepartures[time] = busses.map { bus ->
      Pair(bus, if (time % bus == 0) "D" else ".")
    }
  }

  print("    ")
  println(busses.joinToString(" ").padStart(3))
  possibleDepartures.toSortedMap().forEach { (timestamp, departures) ->
    print("$timestamp ")
    println(departures.joinToString(separator = " ") { it.second })
  }

  println()

  for ((timestamp, departures) in possibleDepartures.toSortedMap()) {
    val hasDepartingBus = departures.firstOrNull { it.second == "D" }
    if (hasDepartingBus != null) {
      println("Timestamp: $timestamp")
      println(departures.joinToString(separator = ",") { "${it.first}" })
      println(departures.joinToString(separator = ",") { it.second })
      println((timestamp - start) * hasDepartingBus.first)
      break
    }
  }

}
