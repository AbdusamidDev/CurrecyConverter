import java.util.*


fun main() {
    var scanner = Scanner(System.`in`)
    var service = Service()
    do {
        println(
            """
            1.View Currency
            2.Converte Currency
            3.View Converted Currency
            0.Exit
            Which Function You What To Use: 
        """.trimIndent()
        )
        var number = scanner.nextInt()
        when (number) {
            1 -> service.viewCurrency()
            2 -> service.converteCurrnecy()
            3 -> service.korish()
            0 -> service.exit()

        }
    } while (number == 0)
}   