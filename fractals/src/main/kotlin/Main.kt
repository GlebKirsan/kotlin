import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import java.util.*
import kotlin.random.Random


val A = Nd4j.create(Array(3) { row -> DoubleArray(3) { col -> if (row == col) 0.5 else 0.0 } })

fun f1(x: INDArray): INDArray {
    return A.mul(x)
}

fun f2(x: INDArray): INDArray {
    val column = Nd4j.create(3, 1)
    column.put(0, 0, 1)
    return A.mul(x).add(column)
}

fun f3(x: INDArray): INDArray {
    val column = Nd4j.create(3, 1)
    column.put(1, 0, 1)
    return A.mul(x).add(column)
}

fun f4(x: INDArray): INDArray {
    val column = Nd4j.create(3, 1)
    column.put(0, 0, 0.5)
    column.put(1, 0, 0.5)
    column.put(2, 0, 1)
    return A.mul(x).add(column)
}

fun f5(x: INDArray): INDArray {
    val column = Nd4j.create(3, 1)
    column.put(0, 0, 0.5)
    column.put(1, 0, 0.5)
    column.put(2, 0, 1)
    return A.mul(x).add(column)
}

fun main() {
    val funcs: Array<(INDArray) -> INDArray> = arrayOf(::f1, ::f2, ::f3, ::f4)
    val x = LinkedList<Double>()
    val y = LinkedList<Double>()
    val z = LinkedList<Double>()
    repeat(10) {
        var point = Nd4j.rand(3, 1)
        repeat(10) {
            repeat(100) {
                point = funcs[Random.nextInt(0, funcs.lastIndex)](point)
            }
            x.add(point.getDouble(0, 0))
            y.add(point.getDouble(1, 0))
            z.add(point.getDouble(2, 0))
        }
    }
}