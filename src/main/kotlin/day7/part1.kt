package day7

import getLines

fun main() {
  val bags = getLines("day7_input.txt").map { it.dropLast(1) }.map { line ->
    val (containerString, allowedBagsString) = line.split("contain")
    val (bagDescription, bagColor) = containerString.split(" ")
    val bag = Bag(bagDescription, bagColor)

    val allowedBags = allowedBagsString.split(",").map { it.trim() }
    if ("no other bags" !in allowedBags) {
      allowedBags.forEach { allowedBag ->
        val (allowedBagCount, allowedBagDescription, allowedBagColor, _) = allowedBag.split(" ")
        val bagRule = AllowedRule(allowedBagCount.toInt(), allowedBagDescription, allowedBagColor)
        bag.allowed.add(bagRule)
      }
    }
    bag
  }

  // Start with my bag
  val myBag = Bag(description = "shiny", color = "gold")
  val containers = mutableListOf(myBag)

  // 1) get possible parents for [myBag] (skip [myBag])
  // 2) get possible parents for [myBag, parent-1] (skip [myBag, parent-1])
  // 3) get possible parents for [myBag, parent-1, for] (skip [myBag, parent-1, for])
  // 4) .......
  // n) repeat until we have the same number of containers as before the loop
  while (true) {
    val newContainers = findPossibleParentContainers(bags, containers)
    if (newContainers.isEmpty()) {
      break
    }
    containers.addAll(newContainers)
  }

  println("Possible containers are:")
  containers.forEach { println(it) }
  println("Count is: ${containers.size - 1}")
}

fun findPossibleParentContainers(allContainers: List<Bag>, currentContainers: List<Bag>): List<Bag> {
  return allContainers
    // Skip bags already in the containers
    .filterNot { bag -> currentContainers.find { it.color == bag.color && it.description == bag.description } != null }
    // Check if this bag can contain any of the existing bags
    .filter { bag -> currentContainers.any { container -> bag.canContain(container) } }
    // Remove allowed bags for readability
    .map { bag -> bag.copy(allowed = mutableListOf()) }
}
