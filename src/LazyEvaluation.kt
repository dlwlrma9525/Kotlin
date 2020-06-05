fun getVeryExpensiveValue(): String {
    Thread.sleep(3000)
    return "Hello"
}

fun printIfValidIndex(index: Int, value: String) {
    if (index >= 0) {
        println("The value is $value.")
    } else {
        println("Invalid.")
    }
}

fun printIfValidIndexWithLazy(index: Int, lazy: () -> String) {
    if (index >= 0) {
        println("The value is ${lazy()}.") // 람다가 호출되는 순간 인자를 평가.
    } else {
        println("Invalid.")
    }
}

// Lazy Evaluation (늦은 평가)
fun main(args: Array<String>) {
    var start = System.currentTimeMillis()
    printIfValidIndex(0, getVeryExpensiveValue()) // eager, 메소드 호출 전 인자의 평가가 끝나있어야 함. (평가는 인자에 값이 할당되고 검증되는 것을 의미)
    printIfValidIndex(-1, getVeryExpensiveValue())
    printIfValidIndex(-2, getVeryExpensiveValue())
    println("It took ${(System.currentTimeMillis() - start) / 1000.0} seconds.")

    start = System.currentTimeMillis()
    printIfValidIndexWithLazy(0) { getVeryExpensiveValue() }
    printIfValidIndexWithLazy(-1) { getVeryExpensiveValue() }
    printIfValidIndexWithLazy(-2) { getVeryExpensiveValue() }
    println("It took ${(System.currentTimeMillis() - start) / 1000.0} seconds.")
}
