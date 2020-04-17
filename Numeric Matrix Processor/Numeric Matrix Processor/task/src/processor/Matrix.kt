package processor

import java.lang.StringBuilder
import java.util.Scanner

class Matrix {
    private var content: Array<DoubleArray>
    private var rows = 0
    private var cols = 0
    var lastIndex = 0
    var subLastIndex = 0

    constructor() {
        val scanner = Scanner(System.`in`)
        print("Enter size of matrix: > ")
        rows = scanner.nextInt()
        cols = scanner.nextInt()

        print("Enter matrix:\n")
        content = Array(rows) { DoubleArray(cols) { scanner.nextDouble() } }
        lastIndex = content.lastIndex
        subLastIndex = content.first().lastIndex
    }

    constructor(rows: Int, cols: Int) {
        this.rows = rows
        this.cols = cols
        content = Array(rows) { DoubleArray(cols) }
        lastIndex = content.lastIndex
        subLastIndex = content.first().lastIndex
    }

    constructor(array: Array<DoubleArray>) {
        this.rows = array.size
        this.cols = array.first().size
        content = array
        lastIndex = content.lastIndex
        subLastIndex = content.first().lastIndex
    }

    private fun defaultTranspose(): Matrix {
        val result = Matrix(cols, rows)
        for (i in 0..lastIndex) {
            for (j in 0..subLastIndex) {
                result[j, i] = get(i, j)
            }
        }
        return result
    }

    private fun sideTranspose(): Matrix {
        val result = Matrix(cols, rows)
        for (i in lastIndex downTo 0) {
            for (j in subLastIndex downTo 0) {
                result[subLastIndex - j, lastIndex - i] = get(i, j)
            }
        }
        return result
    }

    private fun verticalTranspose(): Matrix {
        val result = Matrix(cols, rows)
        for (i in 0..lastIndex) {
            for (j in 0..subLastIndex) {
                result[i, j] = get(i, subLastIndex - j)
            }
        }
        return result
    }

    private fun horizontalTranspose(): Matrix {
        val result = Matrix(cols, rows)
        for (i in 0..lastIndex) {
            for (j in 0..subLastIndex) {
                result[i, j] = get(lastIndex - i, j)
            }
        }
        return result
    }

    fun transpose(type: String): Matrix {
        return when (type) {
            "main" -> defaultTranspose()
            "side" -> sideTranspose()
            "vertical" -> verticalTranspose()
            else -> horizontalTranspose()
        }
    }

    operator fun plus(other: Matrix): Matrix {
        return Matrix(Array(rows) { row ->
            DoubleArray(cols) { col -> get(row, col) + other[row, col] }
        })
    }

    operator fun times(other: Double): Matrix {
        return Matrix(Array(rows) { row ->
            DoubleArray(cols) { col -> get(row, col) * other }
        })
    }

    operator fun times(other: Matrix): Matrix {
        val result = Matrix(Array(rows) { DoubleArray(other.cols) })

        for (i in 0..lastIndex) {
            for (k in 0..subLastIndex) {
                for (j in 0..other[k].lastIndex) {
                    result[i, j] += get(i, k) * other[k, j]
                }
            }
        }
        return result
    }

    operator fun get(i: Int) = content[i]

    operator fun set(i: Int, row: DoubleArray) {
        content[i] = row
    }

    operator fun get(i: Int, j: Int) = content[i][j]

    operator fun set(i: Int, j: Int, value: Double) {
        content[i][j] = value
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 0..lastIndex) {
            sb.append(get(i).joinToString(" "))
            sb.append('\n')
        }
        return sb.toString()
    }
}