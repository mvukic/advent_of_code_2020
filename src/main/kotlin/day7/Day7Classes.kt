package day7

data class AllowedRule(
  val count: Int,
  val description: String,
  val color: String,
)

data class Bag(
  val description: String,
  val color: String,
  val allowed: MutableList<AllowedRule> = mutableListOf()
) {

  fun canContain(bag: Bag): Boolean {
    return allowed.any { it.description == bag.description && it.color == bag.color }
  }

}