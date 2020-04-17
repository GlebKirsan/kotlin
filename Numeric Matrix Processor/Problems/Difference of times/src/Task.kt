import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val hours = scanner.nextInt() * 3600
    val minutes = scanner.nextInt() * 60
    val seconds = scanner.nextInt()

    val secondsDiff = scanner.nextInt() * 3600 - hours
    val minutesDiff = scanner.nextInt() * 60 - minutes
    val hoursDiff = scanner.nextInt() - seconds
    print(hoursDiff + minutesDiff + secondsDiff)
}