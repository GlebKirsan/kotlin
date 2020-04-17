package processor

import java.util.Scanner


fun main() {
    val scanner = Scanner(System.`in`)

    var condition = true
    while (condition) {
        printMenu()
        val operation = scanner.nextInt()

        when (operation) {
            1 -> println(Matrix() + Matrix())
            2 -> println(Matrix() * Scanner(System.`in`).nextDouble())
            3 -> println(Matrix() * Matrix())
            4 -> transpose()
            0 -> condition = false
        }
    }
}

fun transpose() {
    println("1. Main diagonal")
    println("2. Side diagonal")
    println("3. Vertical line")
    println("4. Horizontal line")
    print("Your choice: > ")
    val scanner = Scanner(System.`in`)

    val transposeType = when (scanner.nextInt()) {
        1 -> "main"
        2 -> "side"
        3 -> "vertical"
        else -> "horizontal"
    }
    val matrix = Matrix()
    print(matrix.transpose(transposeType))
}

fun printMenu() {
    println("1. Add matrices")
    println("2. Multiply matrix to a constant")
    println("3. Multiply matrices")
    println("4. Transpose matrix")
    println("0. Exit")
    print("Your choice: > ")
}