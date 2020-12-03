package day2

import getLines

fun main() {
  val regex = Regex("^(\\d+)-(\\d+) (\\w): (\\w+)$")
  val passwords = getLines("day2_input.txt")
  val validPasswords = passwords.filter {
    val (min, max, char, password) = regex.find(it)!!.destructured
    val count = password.count { c -> c == char.toCharArray().first() }
    count >= min.toInt() && count <= max.toInt()
  }
  println(validPasswords.size)

}