package day4

import getLines

fun main() {
  val passports = mutableListOf<String>()
  val requiredProperties = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid")

  var passport = ""
  val lines = getLines("day4_input.txt")
  lines.forEachIndexed { index, line ->
    when {
      line.isBlank() -> {
        // Save passport data
        passports.add(passport)
        passport = ""
      }
      lines.size == index + 1 -> {
        // Just concatenate last line to the current passport
        passport += " $line"
        passports.add(passport)
      }
      else -> {
        // Concatenate partial passport data
        passport += " $line"
      }
    }
  }

  val validPassportsCount = passports.count {
    requiredProperties.all { prop -> isPassportValid1(prop, it) }
  }

  println("Valid passports count: $validPassportsCount")
}

fun isPassportValid1(property: String, passport: String): Boolean {
  return when {
    property === "cid" -> true
    else -> passport.contains(" $property:")
  }
}