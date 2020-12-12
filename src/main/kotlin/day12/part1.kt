package day12

import getLines
import kotlin.math.absoluteValue

data class Move(
  val orientation: String,
  val steps: Int
)

data class Position(
  var orientation: String,
  var east: Int,
  var north: Int
)

fun main() {
  val moves = getLines("day12_input.txt").map {
    val orientation = it.take(1)
    val steps = it.substring(1).toInt()
    Move(orientation, steps)
  }

  // Current position
  val position = Position(orientation = "E", east = 0, north = 0)
  for (move in moves) {
    println("Current position: $position")
    println("Executing $move")
    val (orientation, steps) = move
    when (orientation) {
      "F" -> moveForward(position, steps)
      "L" -> turnLeft(position, steps)
      "R" -> turnRight(position, steps)
      "E" -> moveEast(position, steps)
      "W" -> moveWest(position, steps)
      "N" -> moveNorth(position, steps)
      "S" -> moveSouth(position, steps)
    }
    println("Result position: $position")
    println()
  }

  println("Result $position")
  val distance = position.east.absoluteValue + position.north.absoluteValue
  println("Manhattan distance $distance")
}

fun moveSouth(position: Position, steps: Int) {
  position.north -= steps
}

fun moveNorth(position: Position, steps: Int) {
  position.north += steps
}

fun moveWest(position: Position, steps: Int) {
  position.east -= steps
}

fun moveEast(position: Position, steps: Int) {
  position.east += steps
}

fun moveForward(position: Position, steps: Int) {
  when (position.orientation) {
    "E" -> position.east += steps
    "W" -> position.east -= steps
    "N" -> position.north += steps
    "S" -> position.north -= steps
  }
}

fun turnLeft(position: Position, degrees: Int) {
  when (position.orientation) {
    "E" -> {
      when (degrees) {
        in listOf(0, 360) -> position.orientation = "E"
        90 -> position.orientation = "N"
        180 -> position.orientation = "W"
        270 -> position.orientation = "S"
      }
    }
    "W" -> {
      when (degrees) {
        in listOf(0, 360) -> position.orientation = "W"
        90 -> position.orientation = "S"
        180 -> position.orientation = "E"
        270 -> position.orientation = "N"
      }
    }
    "N" -> {
      when (degrees) {
        in listOf(0, 360) -> position.orientation = "N"
        90 -> position.orientation = "W"
        180 -> position.orientation = "S"
        270 -> position.orientation = "E"
      }
    }
    "S" -> {
      when (degrees) {
        in listOf(0, 360) -> position.orientation = "S"
        90 -> position.orientation = "E"
        180 -> position.orientation = "N"
        270 -> position.orientation = "W"
      }
    }
  }
}

fun turnRight(position: Position, degrees: Int) {
  when (position.orientation) {
    "E" -> {
      when (degrees) {
        in listOf(0, 360) -> position.orientation = "E"
        90 -> position.orientation = "S"
        180 -> position.orientation = "W"
        270 -> position.orientation = "N"
      }
    }
    "W" -> {
      when (degrees) {
        in listOf(0, 360) -> position.orientation = "W"
        90 -> position.orientation = "N"
        180 -> position.orientation = "E"
        270 -> position.orientation = "S"
      }
    }
    "N" -> {
      when (degrees) {
        in listOf(0, 360) -> position.orientation = "N"
        90 -> position.orientation = "E"
        180 -> position.orientation = "S"
        270 -> position.orientation = "W"
      }
    }
    "S" -> {
      when (degrees) {
        in listOf(0, 360) -> position.orientation = "S"
        90 -> position.orientation = "W"
        180 -> position.orientation = "N"
        270 -> position.orientation = "E"
      }
    }
  }
}
