package day14

import getLines
import kotlin.math.pow

@ExperimentalUnsignedTypes
data class Address(
  val pointer: UInt,
  val value: UInt
)

@ExperimentalUnsignedTypes
data class Operation(
  val bitmaskList: List<UInt>,
  val bitmask: UInt,
  val addresses: List<Address>
)

@ExperimentalUnsignedTypes
fun main() {
  val lines = getLines("day14_input.txt")

//  val bitmask = lines.first().split(" = ")[1]
//  val addresses = lines.takeLast(3)

  println(stringToInt("000000000000000000000000000000000101"))

//  val operation = Operation(
//    bitmaskList = bitmask.map { it.toInt().toUInt() },
//    bitmask = bitmask.replace('X', '1').toULong(),
//    addresses = addresses.map {
//      val (_, pointer, _, value) = it.replace(" ", "").split("[", "]", "=")
//      Address(
//        pointer = pointer.toUInt(),
//        value = value.toUInt()
//      )
//    }
//  )

//  println(operation)
}

@ExperimentalUnsignedTypes
fun stringToInt(string: String): UInt {
  var result: UInt = 0U
  val base: Double = 2.toDouble()
  string.forEachIndexed { index, c ->
    println("Index: $index Value: $c")
    println("Binary: ${base.pow(25 - index)}")
    result += if (c == '1') base.pow(25 - index).toUInt() else 0U
  }
  return result
}