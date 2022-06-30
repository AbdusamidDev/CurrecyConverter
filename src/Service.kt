import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class Service : Plan {
    private var scanner = Scanner(System.`in`)

    override fun viewCurrency() {
        try {
            var url = URL("http://cbu.uz/uzc/arkhiv-kursov-valyut/json/")
            var connection = url.openConnection() as HttpURLConnection
            connection.connect()
            var inputStream = connection.inputStream
            var bufferedReader = inputStream.bufferedReader()
            var list = bufferedReader.readLines()
            var stringBuilder = StringBuilder()
            list.forEach {
                stringBuilder.append(it)
            }
            var gson = Gson()
            var list1: ArrayList<Valyuta>
            var type = object : TypeToken<ArrayList<Valyuta>>() {}.type
            list1 = gson.fromJson(stringBuilder.toString(), type)
            for (valyuta in list1) {
                println(valyuta.CcyNm_EN + " = " + valyuta.Rate.toDouble() + " sums ||" + " date: " + valyuta.Date)
            }
        } catch (e: Exception) {
            println("Error")
        }

    }

    override fun korish() {
        var list = readFile()
        list.forEach {
            println("{$it}")
        }
    }

    override fun converteCurrnecy() {
        var listred = readFile()
        var input = Scanner(System.`in`)
        var url = URL("http://cbu.uz/uzc/arkhiv-kursov-valyut/json/")
        var connection = url.openConnection() as HttpURLConnection
        connection.connect()
        var inputStream = connection.inputStream
        var bufferedReader = inputStream.bufferedReader()
        var listString = bufferedReader.readLines()
        var stringBuilder = StringBuilder()
        listString.forEach {
            stringBuilder.append(it)
        }

        var gson = Gson()

        var list: ArrayList<Valyuta>

        var type = object : TypeToken<ArrayList<Valyuta>>() {}.type
        list = gson.fromJson(stringBuilder.toString(), type)

        println("\nEnter Currency CCY(example: USD;UZS) :")
        var nom = input.next()
        println("Enter Currency: ")
        var currency = scanner.nextInt()
        for (valyuta in list) {
            if (valyuta.Ccy == nom) {
                var a = "$currency ${valyuta.CcyNm_EN} ${valyuta.Rate.toDouble() * currency} Equals Sums"
                println(a)
                listred.add(a)
                writeFile(listred)
            }
        }
    }

    override fun exit(): Int {
        return (-1)
    }

    override fun readFile(): ArrayList<String> {
        var list = ArrayList<String>()
        val file = File("Bank.txt")
        file.createNewFile()
        val lines = file.readLines()
        val type = object : TypeToken<ArrayList<String>>() {}.type
        if (lines.isNotEmpty())
            list = Gson().fromJson(lines[0], type)

        return list
    }

    override fun writeFile(list: List<String>) {
        val liststr = Gson().toJson(list)
        val filewriter = FileWriter("Bank.txt")
        filewriter.write(liststr)
        filewriter.close()
    }

}