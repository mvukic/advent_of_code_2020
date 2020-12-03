package day3

import getLines

fun main() {
  val area: List<List<Char>> = getLines("day3_input.txt").map { it.toList() }
  area.forEach {
    println(it)
  }
  var rowIndex = 0
  var columnIndex = 0
  var treeCount = 0
  while (true) {
    val newColumnIndex = columnIndex + 3
    val wrappedNewColumnIndex = if (newColumnIndex >= area[rowIndex].size) newColumnIndex % area[rowIndex].size else newColumnIndex
    columnIndex = wrappedNewColumnIndex
    val newRowIndex = rowIndex + 1
    rowIndex = newRowIndex
    if (rowIndex >= area.size) {
      break
    }
    if (area[rowIndex][columnIndex] == '#') {
      treeCount++
    }
    println("($columnIndex, $rowIndex) = ${area[rowIndex][columnIndex]}")
  }
  println("Trees: $treeCount")
}