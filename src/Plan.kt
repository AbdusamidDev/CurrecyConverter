interface Plan {
    fun viewCurrency()
    fun korish()
    fun converteCurrnecy()
    fun exit(): Int


    fun readFile(): ArrayList<String>
    fun writeFile(list: List<String>)
}