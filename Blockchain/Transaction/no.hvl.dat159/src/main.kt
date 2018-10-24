import java.util.Random

fun main(args: Array<String>) {
    val SIZE = 100000000

    var i: Int = 0
    var sum: Int = 0
    var tot = 0

    for(k in 1..SIZE) {
        while (i != 5) {
            sum++
            i = Random().nextInt(10)
        }
        tot+=sum
    }
    print(tot/SIZE)
}

