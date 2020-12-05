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
    requiredProperties.all { prop -> isValidPart2(prop, it) }
  }

  //
  println("Valid passports count: ${validPassportsCount + 1}")
}

fun isValidPart2(property: String, passport: String): Boolean {
  return when {
    property === "cid" -> true
    property === "byr" -> checkIntValidity(passport, property, 1920, 2002)
    property === "iyr" -> checkIntValidity(passport, property, 2010, 2020)
    property === "eyr" -> checkIntValidity(passport, property, 2020, 2030)
    property === "hgt" -> checkHeightValidity(passport)
    property === "hcl" -> checkHairColorValidity(passport)
    property === "ecl" -> checkEyeColorValidity(passport)
    property === "pid" -> checkPassportIdValidity(passport)
    else -> false
  }
}

fun checkIntValidity(passport: String, property: String, min: Int, max: Int): Boolean {
  val propertyIndex = passport.indexOf("$property:")
  if (propertyIndex != -1) {
    val year = passport.substring(propertyIndex + "$property:".length, propertyIndex + "$property:".length + 4)
    return year.toIntOrNull() in min..max
  }
  return false
}

fun checkHeightValidity(passport: String): Boolean {
  val propertyIndex = passport.indexOf("hgt:")
  if (propertyIndex != -1) {
    val indexOfInUnit = passport.indexOf("in", propertyIndex)
    val indexOfCmUnit = passport.indexOf("cm", propertyIndex)
    return when {
      indexOfInUnit != -1 -> {
        val height = passport.substring(propertyIndex + "hgt:".length, indexOfInUnit)
        height.toIntOrNull() in 59..76
      }
      indexOfCmUnit != -1 -> {
        val height = passport.substring(propertyIndex + "hgt:".length, indexOfCmUnit)
        height.toIntOrNull() in 150..193
      }
      else -> false
    }
  }
  return false
}

fun checkHairColorValidity(passport: String): Boolean {
  val propertyIndex = passport.indexOf("hcl:#")
  if (propertyIndex != -1) {
    val colorHex = passport.substring(propertyIndex + "hcl:#".length, propertyIndex + "hcl:#".length + 6)
    return colorHex.toLongOrNull(16) != null
  }
  return false
}

fun checkEyeColorValidity(passport: String): Boolean {
  val propertyIndex = passport.indexOf("ecl:")
  if (propertyIndex != -1) {
    val allowedValues = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    val color = passport.substring(propertyIndex + "ecl:".length, propertyIndex + "ecl:".length + 3)
    return allowedValues.contains(color)
  }
  return false
}

fun checkPassportIdValidity(passport: String): Boolean {
  val propertyIndex = passport.indexOf("pid:")
  if (propertyIndex != -1) {
    val id = passport.substring(propertyIndex + "pid:".length, propertyIndex + "pid:".length + 9)
    return id.toLongOrNull() != null
  }
  return false
}