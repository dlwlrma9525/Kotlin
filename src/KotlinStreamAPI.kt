/** Kotlin Stream API.
 *  자바 8 은 리스트나 맵과 같은 컬렉션에 포함된 자료들을 다룰 수 있도록 스트림(Stream) API 기능을 지원.
 *  스트림에서 제공하는 여러 연산자들을 사용하면 컬렉션에 포함된 자료들을 다른 타입으로 변경하거나, 새로운 자료를 추가로 생성하는 등의 작업을 간단히 구현 가능.
 *
 *  코틀린 표준 라이브러리는 스트림 역할의 함수들을 확장 함수 형태로 지원.
 *  따라서 항상 stream() 메서드를 호출하는 자바와 달리 컬렉션 객체에서 직접 호출.
 *  엄밀히 말하면 코틀린 표준 라이브러리는 자바 8의 스트림 기능을 사용하지 않는다.
 *
 *  Ⅰ
 *      fun <T, R> map(transform: (T) -> R): List<R>                            컬렉션 내 인자를 다른 값 또는 타입으로 변환하는 함수.
 *      fun <T, R> mapIndexed(transform: (index: Int, T) -> R): List<R>         컬렉션 내 포함된 인자의 인덱스 값을 변환 함수로 전달.
 *      fun <T, R : Any> mapNotNull(transform: (T) -> R?): List<R>              컬렉션 내 각 인자를 변환함과 동시에, 변환한 결과가 null 값인 경우 이를 무시.
 *      fun <T, R> flatMap(transform: (T) -> Iterable<R>): List<R>              flatMap() 함수는 변환 함수의 반환형이 Iterable. 하나의 인자를 여러 개의 인자로 매핑.
 *      fun <T, K> Iterable<T>.groupBy(keySelector: (T) -> K): Map<K, List<T>>  컬렉션 내의 인자들을 지정한 기준에 따라 분류하고 각 인자들의 리스트를 포함하는 맵 반환.
 *
 *  Ⅱ
 *      fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T>          컬렉션 내 인자들 중 주어진 조건과 일치하는 인자만 걸러주는 함수.
 *
 *      fun <T> Iterable<T>.take(n: Int): List<T>                               take() 함수의 인자로 받은 개수의 리스트 반환.
 *      fun <T> List<T>.takeLast(n: Int): List<T>                               take() 함수와 반대로 뒤에서부터 이 함수의 인자로 받은 개수의 리스트 반환.
 *      fun <T> Iterable<T>.takeWhile(predicate: (T) -> Boolean): List<T>       첫 번째 인자부터 시작하여 주어진 조건을 만족하는 인자까지를 포함하는 리스트 반환.
 *      fun <T> List<T>.takeLastWhile(predicate: (T) -> Boolean): List<T>       takeWhile() 함수와 반대로 뒤에서부터 주어진 조건을 만족하는 인자까지를 포함하는 리스트 반환.
 *
 *      fun <T> Iterable<T>.drop(n: Int): List<T>                               take() 함수의 반대 역할, 조건을 만족하는 항목을 컬렉션에서 제외한 결과를 반환.
 *      fun <T> List<T>.dropLast(n: Int): List<T>                               takeLast() 함수의 반대 역할.
 *      fun <T> Iterable<T>.dropWhile(predicate: (T) -> Boolean): List<T>       takeWhile() 함수의 반대 역할.
 *      fun <T> List<T>.dropLastWhile(predicate: (T) -> Boolean): List<T>       takeLastWhile() 함수의 반대 역할.
 *
 *      fun <T> Iterable<T>.first(): T                                          컬렉션 내의 첫번째 인자 반환.
 *      fun <T> List<T>.first(): T
 *      fun <T> Iterable<T>.first(predicate: (T) -> Boolean): T                 특정 조건을 만족하는 첫 번째 인자 반환. (조건을 만족하는 인자가 없는 경우 NoSuchElementException 예외 발생)
 *      fun <T> Iterable<T>.firstOrNull(predicate: (T) -> Boolean): T?          특정 조건을 만족하는 첫 번째 인자 반환. (조건을 만족하는 인자가 없는 경우 null 반환)
 *
 *      fun <T> Iterable<T>.distinct(): List<T>                                 컬렉션 내에 포함된 항목 중 중복된 항목을 걸러낸 결과를 반환. (항목의 중복 여부는 equals()로 판단)
 *      fun <T, K> Iterable<T>.distinctBy(sel                                                                                                                                                                                                                 ector: (T) -> K): List<T>          비교에 사용할 키 값을 직접 설정하는 함수.
 *
 *  Ⅲ
 *      infix fun <T, R> Iterable<T>.zip(other: Array<out R>): List<Pair<T, R>>
 *      fun <T, R, V> Iterable<T>.zip(other: Array<out R>, transform: (a: T, b: R) -> V): List<V>
 *
 *      두 컬렉션 내의 자료들을 조합하여 새로운 자료를 만들 때 사용.
 *      1. 두 컬렉션 간 자료의 개수가 달라도 사용할 수 있으며, 이 경우에 반환되는 컬렉션의 자료 수는 조합에 사용하는 컬렉션의 자료의 수 중 더 적은 쪽 기준.
 *      2. 기본값으로 조합된 결과를 Pair 로 만들어주며, 원하는 경우 조합 규칙을 사용자가 정의하여 사용할 수도 있다.
 *
 *      fun <T> Iterable<T>.joinToString(               컬렉션 내 자료를 문자열 형태로 변환함과 동시에, 이를 조합하여 하나의 문자열로 생성.
 *          separator: CharSequence = ", ",
 *          prefix: CharSequence = "",
 *          postfix: CharSequence = "",
 *          limit: Int = -1,                            // 컬렉션 요소의 리미트.
 *          truncated: CharSequence = "...",            // 생략 문자열.
 *          transform: ((T) -> CharSequence)? = null    // T 가 문자열이 아닌 다른 자료형의 리스트를 표현하는 경우 요소를 문자열로 변환하는 함수.
 *      ): String
 *
 *      fun <T> Iterable<T>.count(): Int                                                    컬렉션 내 포함된 자료의 개수를 반환.
 *      inline fun <T> Collection<T>.count(): Int
 *      inline fun <T> Iterable<T>.count(predicate: (T) -> Boolean): Int                    해당 조건을 만족하는 자료의 개수를 반환.
 *
 *      inline fun <S, T: S> Iterable<T>.reduce(operation: (acc: S, T) -> S): S             컬렉션 내 자료들을 모두 합쳐 하나의 값으로 만들어주는 함수. (acc → 지금까지 조합한 결과, T → 다음 요소)
 *      inline fun <S, T: S> List<T>.reduceRight(operation: (T, acc: S) -> S): S            reduce() 함수의 작업을 컬렉션 내 마지막 자료부터 시작.
 *
 *      inline fun <T, R> Iterable<T>.fold(initial: R, operation: (acc: R, T) -> R): R      reduce() 함수와 거의 동일한 역할을 하나, 초깃값 지정 가능.
 *      inline fun <T, R> List<T>.foldRight(initial: R, operation: (T, acc: R) -> R): R     fold() 함수의 작업을 컬렉션 내 마지막 자료부터 시작.
 *
 *  Ⅳ
 *      fun <T> Iterable<T>.any(): Boolean                                      컬렉션 내 단 하나의 자료라도 존재하면 true 반환.
 *      fun <T> Iterable<T>.none(): Boolean                                     컬렉션 내 자료가 없으면 true 반환.
 *      inline fun <T> Iterable<T>.any(predicate: (T) -> Boolean): Boolean      조건식을 만족하는 자료가 단 하나라도 존재하면 true 반환.
 *      inline fun <T> Iterable<T>.none(predicate: (T) -> Boolean): Boolean     조건식을 만족하는 자료가 단 하나라도 없으면 true 반환.
 *
 *      fun <T : Comparable<T>> Iterable<T>.max(): T?   숫자 타입의 자료를 갖는 컬렉션 내 최댓값을 찾아 반환.
 *      fun <T : Comparable<T>> Iterable<T>.min(): T?   숫자 타입의 자료를 갖는 컬렉션 내 최솟값을 찾아 반환.
 *      fun Iterable<Primitive>.average(): Double       숫자 타입의 자료를 갖는 컬렉션 내 지료들의 평균 반환.
 *
 *  Ⅴ   Scope function.
 *      fun <T, R> T.let(block: (T) -> R): R                let() 함수를 호출하는 객체를 함수형 인자 block 의 인자로 전달하고 block 함수의 결과 반환.
 *      T?.let()                                            let() + safe call → null check and execute.
 *
 *      fun <T> T.apply(block: T.() -> Unit): T             apply() 함수를 호출한 객체를, 이어지는 함수 블록의 리시버(receiver)로 전달.
 *                                                          1. apply() 함수가 전달받는 block 함수를 확장 함수로 추가.
 *                                                          2. block 내에서는 해당 객체 내의 프로퍼티나 함수를 직접 호출할 수 있다.
 *
 *      fun <T, R> with(receiver: T, block: T.() -> R): R   인자로 받은 객체를 이어지는 함수 블록의 리시버로 전달.
 *                                                          null 값이 아닌 것으로 확인된 객체에 with() 함수를 사용하는 것을 권장.
 *
 *      fun <R> run(block: () -> R): R          함수형 인자 block 을 호출하고 그 결과를 반환.
 *                                              1. run() 함수는 인자가 없는 익명 함수처럼 복잡한 계산을 위해 여러 임시 변수가 필요할 때 유용하다.
 *                                              2. run() 함수 내부의 변수들은 블록 외부에 노출되지 않으므로 변수 선언 영역을 확실히 분리.
 *
 *      fun <T, R> T.run(block: T.() -> R): R   run() 함수를 호출한 객체를 함수형 인자 block 의 리시버로 전달하고 그 결과를 반환.
 *                                              with() 함수처럼 사용할 수 있고 safe call 을 사용할 수 있으므로 nullable 객체의 프로퍼티나 함수에 연속적으로 접근해야 할 때 유용.
 */
fun main(args: Array<String>) {
    // Transform.
    var cities = listOf("Seoul", "Tokyo", "Mountain View")
    cities.map { city -> city.toUpperCase() }.forEach { println(it) }       // List<String> → List<String>
    cities.map { city -> city.length }.forEach { println("length=$it") }    // List<String> → List<Int>

    var numbers = 0..10
    numbers.mapIndexed { idx, element -> idx * element }.forEach { print("$it ") }
    cities.mapNotNull { city -> if (city.length <= 5) city else null }.forEach { println(it) }

    numbers = 1..6
    numbers.flatMap { number -> 1..number }.forEach { println("$it") }
    cities.groupBy { city -> if (city.length <= 5) "A" else "B" }.forEach { key, cities -> println("key=$key cities=$cities") }

    // Filter.
    cities.filter { city -> city.length <= 5 }.forEach { println("$it") }

    cities = listOf("Seoul", "Tokyo", "Mountain View", "NYC", "Singapore")

    cities.take(1).forEach { println(it) }                                  // ["Seoul"]
    cities.takeLast(2).forEach { println(it) }                              // ["NYC", "Singapore"]
    cities.takeWhile { city -> city.length <= 5 }.forEach { println(it) }       // Mountain View 가 조건을 만족하지 않으므로 이후의 인자들은 모두 무시.
    cities.takeLastWhile { city -> city.length < 13 }.forEach { println(it) }   // ["NYC", "Singapore"]

    cities.drop(1).forEach { println(it) }                                  // "Seoul" 제외.
    cities.dropLast(2).forEach { println(it) }                              // "NYC", "Singapore" 제외.
    cities.dropWhile { city -> city.length <= 5 }.forEach { println(it) }       // "Seoul", "Tokyo" 제외
    cities.dropLastWhile { city -> city.length < 13 }.forEach { println(it) }   // "NYC", "Singapore" 제외.

    println(cities.first())
    println(cities.last())
    println(cities.first { city -> city.length > 5 })   // "Mountain View"
    println(cities.last { city -> city.length > 5 })    // "Singapore"

    try {
        cities.first { city -> city.isEmpty() }
    } catch (e: NoSuchElementException) {
        println("Not found")
    }

    try {
        cities.last { city -> city.isEmpty() }
    } catch (e: NoSuchElementException) {
        println("Not found")
    }

    println(cities.firstOrNull { city -> city.isEmpty() })
    println(cities.lastOrNull { city -> city.isEmpty() })

    cities = listOf("Seoul", "Tokyo", "Mountain View", "Seoul", "Tokyo")

    cities.distinct().forEach { println(it) } // ["Seoul", "Tokyo", "Mountain View"]
    cities.distinctBy { city -> city.length }.forEach { println(it) } // ["Seoul", "Mountain View"]

    // Zip
    val cityCodes = listOf("SEO", "TOK", "MTV", "NYC")
    val cityNames = listOf("Seoul", "Tokyo", "Mountain View")

    cityCodes.zip(cityNames).forEach { pair -> println("${pair.first}:${pair.second}") }    // [SEO to Seoul, TOK to Tokyo, MTV to Mountain View]
    (cityCodes zip cityNames).forEach { pair -> println("${pair.first}:${pair.second}") }   // infix operator.

    cityCodes.zip(cityNames) { code, name -> "$code ($name)" }.forEach { println(it) }      // V is "$code ($name)"

    cities = listOf("Seoul", "Tokyo", "Mountain View", "NYC", "Singapore")

    println(cities.joinToString())
    println(cities.joinToString(", ", "[", "]", 3, "..."))
    println(cityCodes.zip(cityNames) { code, name -> code to name }.joinToString { "${it.first}: ${it.second}" })

    println(cities.count())
    println(cities.count { city -> city.length <= 5 })

    println(cities.reduce { acc, next -> "$acc, $next" })
    println(cities.reduceRight { previous, acc -> "$acc, $previous" })

    println(cities.fold("Initial") { acc, next -> "$acc, $next" })
    println(cities.foldRight("Initial") { previous, acc -> "$acc, $previous" })

    println(cities.any())
    println(cities.any { city -> city.length <= 5 })

    println(cities.none())
    println(cities.none { city -> city.isEmpty() })

    val numberList = listOf(4, 2, 5, 3, 2, 0, 8)
    println(numberList.min())
    println(numberList.max())
    println(numberList.average())

    fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {}
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, DisplayMetrics()).toInt()
            .let { setPadding(it, 0, it, 0) }

    fun doSomething(message: String?) {
        message?.let {
            println(message)
        }
    }
    doSomething("Hello! Kotlin")
    doSomething(null)

    LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            .apply {
                gravity = Gravity.CENTER_HORIZONTAL
                weight = 1f
                topMargin = 100
                bottomMargin = 100
            }

    val textView = TextView()
    with(textView) {
        // receiver is must be not null.
        text = "Hello! Kotlin"
        gravity = Gravity.CENTER_HORIZONTAL
    }

    val padding = run {
        val defaultPadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, DisplayMetrics())
        val extraPadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, DisplayMetrics())

        defaultPadding + extraPadding
    }

    fun onCreate(savedInstanceState: Bundle?) {
        savedInstanceState?.run {
            val selection = getInt("last_selection")
            val text = getString("last_text")
            // UI Restore..
        }
    }
}

class Bundle {
    fun getInt(key: String): Int {
        return 0
    }

    fun getString(key: String): String {
        return ""
    }
}

class DisplayMetrics
class TypedValue {
    companion object {
        const val COMPLEX_UNIT_DIP: Int = 0
        fun applyDimension(unit: Int, value: Float, metrics: DisplayMetrics): Float {
            return 0.toFloat()
        }
    }
}

class Gravity {
    companion object {
        const val CENTER_HORIZONTAL: Int = 1
        const val CENTER_VERTICAL: Int = 16
    }
}

class LinearLayout {
    class LayoutParams(width: Int, height: Int) {
        var gravity: Int = 0
        var weight: Float = 0f
        var topMargin: Int = 0
        var bottomMargin: Int = 0

        companion object {
            const val MATCH_PARENT: Int = -1
            const val WRAP_CONTENT: Int = -2
        }
    }
}

class TextView {
    var gravity: Int = 0
    var text: String = ""
}