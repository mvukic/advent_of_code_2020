package day8

import getLines

data class ProgramExecutionResult(
  val wasLoop: Boolean,
  val acc: Int
)

enum class InstructionType {
  NOP,
  ACC,
  JMP
}

data class Instruction(
  var type: InstructionType,
  val value: Int
)

fun main() {
  val lines = getLines("day8_input.txt")

  val program = getProgram(lines)

  for (index in program.indices) {
    println("Changing instruction: $index")
    // Change the instruction on index
    val programCopy = changeProgram(program, index)
    // Check how the program was executed
    val (wasLoop, acc) = runProgram(programCopy)
    println()
    if (!wasLoop) {
      println("Acc: $acc")
      break
    }
  }
}

fun runProgram(program: List<Instruction>): ProgramExecutionResult {
  var acc = 0
  var pc = 0

  val instructionCounter = mutableMapOf<Int, Int>()

  while (true) {
    if (pc == program.size) {
      return ProgramExecutionResult(false, acc)
    }
    if (instructionCounter.containsKey(pc)) {
      return ProgramExecutionResult(true, acc)
    }
    instructionCounter[pc] = 1
    val currentInstruction = program[pc]
    when (currentInstruction.type) {
      InstructionType.NOP -> {
        pc++
      }
      InstructionType.ACC -> {
        acc += currentInstruction.value
        pc++
      }
      InstructionType.JMP -> {
        pc += currentInstruction.value
      }
    }
  }
}

fun changeProgram(program: List<Instruction>, index: Int): List<Instruction> {
  val copy = program.map { it.copy() }
  if (copy[index].type == InstructionType.JMP) {
    copy[index].type = InstructionType.NOP
    println("Index: $index JMP -> NOP")
  } else if (copy[index].type == InstructionType.NOP) {
    copy[index].type = InstructionType.JMP
    println("Index: $index NOP -> JMP")
  } else {
    println("Index: $index NO CHANGE")
  }
  return copy
}

fun getProgram(lines: List<String>): List<Instruction> {
  return lines.map {
    val (name, value) = it.split(" ")
    val isPositive = value.startsWith("+")
    val absoluteValue = value.substring(1).toInt()
    Instruction(
      type = InstructionType.valueOf(name.toUpperCase()),
      value = if (isPositive) absoluteValue else -absoluteValue
    )
  }
}