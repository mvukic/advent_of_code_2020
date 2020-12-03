package day3

import getLines

fun main() {
  val area: List<List<Char>> = getLines("day3_input.txt").map { it.toList() }
  val paths = listOf(
    1 to 1,
    3 to 1,
    5 to 1,
    7 to 1,
    1 to 2
  )
  var result = 1L
  paths.forEach { (columnStep, rowStep) ->
    var rowIndex = 0
    var columnIndex = 0
    var treeCount = 0
    while (true) {
      val newColumnIndex = columnIndex + columnStep
      val wrappedNewColumnIndex =
        if (newColumnIndex >= area[rowIndex].size) newColumnIndex % area[rowIndex].size else newColumnIndex
      columnIndex = wrappedNewColumnIndex
      val newRowIndex = rowIndex + rowStep
      rowIndex = newRowIndex
      if (rowIndex >= area.size) {
        break
      }
      if (area[rowIndex][columnIndex] == '#') {
        treeCount++
      }
    }
    result *= treeCount
    println("Trees for ($columnStep, $rowStep): $treeCount")
  }
  println(result)
}