package day12

import getLines
import kotlin.math.absoluteValue

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
      "F" -> moveForward2(position, steps)
      "L" -> turnLeft2(position, steps)
      "R" -> turnRight2(position, steps)
      "E" -> moveEast2(position, steps)
      "W" -> moveWest2(position, steps)
      "N" -> moveNorth2(position, steps)
      "S" -> moveSouth2(position, steps)
    }
    println("Result position: $position")
    println()
  }

  println("Result $position")
  val distance = position.east.absoluteValue + position.north.absoluteValue
  println("Manhattan distance $distance")
}

fun moveSouth2(position: Position, steps: Int) {
  position.north -= steps
}

fun moveNorth2(position: Position, steps: Int) {
  position.north += steps
}

fun moveWest2(position: Position, steps: Int) {
  position.east -= steps
}

fun moveEast2(position: Position, steps: Int) {
  position.east += steps
}

fun moveForward2(position: Position, steps: Int) {
  when (position.orientation) {
    "E" -> position.east += steps
    "W" -> position.east -= steps
    "N" -> position.north += steps
    "S" -> position.north -= steps
  }
}

fun turnLeft2(position: Position, degrees: Int) {
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

fun turnRight2(position: Position, degrees: Int) {
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
