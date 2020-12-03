package day2

import getLines

fun main() {
  val regex = Regex("^(\\d+)-(\\d+) (\\w): (\\w+)$")
  val passwords = getLines("day2_input.txt")
  val validPasswords = passwords.filter {
    val (position1, position2, char, password) = regex.find(it)!!.destructured
    val chars = password.toCharArray()
    val firstChar =  chars[position1.toInt() - 1].toString()
    val secondChar = chars[position2.toInt() - 1].toString()
    val firstOnly = firstChar == char && secondChar != char
    val secondOnly = firstChar != char && secondChar == char
    firstOnly || secondOnly
  }
  println(validPasswords.size)

}