/** Kotlin Playground.
 *  JetBrains 의 언어. 2016.02 v1.0 정식버전 출시.
 *  구글 I/O 2017 에서 공식언어로 채택.
 */
fun main(args: Array<String>) {
    // 코틀린 파일을 컴파일하면 ~Kt 클래스에 main 정적 메서드 생성.
    // 코틀린의 간단한 기능들은 메서드만으로 구성이 가능하고 파일명 기준으로 클래스 자동 생성.
    println("Hello Kotlin!")
    dateType()
    array()
    collection()
}

fun dateType() {
    // value and variable.
    val a: String = "foo"
    val b = "bar" // type inference.
    val c: String
    c = "baz"
    var d: Int = 0
    d += 1

    // data type.
    // 코틀린의 자료형은 모두 객체로 표현하고 컴파일 시점에서 자바의 원시 타입 또는 래퍼 타입 결정.

    // Number type.
    // 숫자를 표현하는 모든 자료형의 최상위 클래스.
    val decValue: Int = 100
    val hexValue: Int = 0x100       // 256
    val binaryValue: Int = 0b100    // 8
    val longValue: Long = 100L
    var doubleValue: Double = 100.1 // 1.001e2
    var floatValue: Float = 100.0f

    doubleValue = floatValue.toDouble()
    println(decValue.toChar())

    // 논리 연산자: 자바와 동일.
    // 비트 연산자: and, or, xor, inv, shl, shr, ushr
    val result: Int = (2 or 4) shl 1 // 1100

    // Char and String.
    var character: Char = 'A'
    character = 65.toChar()     // character = 65 X
    val name: String = "Lorem ipsum"
    val c1: Char = name.get(4)  // m
    val c2: Char = name[6]      // i

    // formatted string.
    val length: Int = 3000
    println(String.format("Length: %d meters", length))

    // string template.
    println("TextLength: ${name.length}")
    println("Price: ${'$'}1000")
}

fun array() {
    // Array.
    // 코틀린 배열은 Array 타입의 클래스로 표현.
    val words: Array<String> = arrayOf("Lorem", "ipsum", "dolor", "sit")

    // Java Array.
    // 자바 원시 타입은 코틀린 배열의 타입 인자로 사용 불가.
    // 따라서 자바 원시 타입을 인자로 갖는 배열의 특수 클래스 지원.
    val javaIntArray: IntArray = intArrayOf(1, 2, 3, 4, 5)
    val kotlinIntArray: Array<Int> = arrayOf(1, 2, 3, 4, 5)

    // * (Spread Operator)
    // 1. 자바 코드에서 코틀린 배열 전달.
    // 2. 자바 코드의 가변 인자에 코틀린 배열 전달.
    // 3. 코틀린의 가변인자에 코틀린 배열을 전달.
    fun foo(arr: Array<String>) {}

    fun bar(vararg args: String) {}

    foo(words)
    bar(*words)
}

fun collection() {
    // Collections.
    // JVM 을 기반으로 하는 코틀린의 컬렉션은 자바에서 제공하는 컬렉션들을 그대로 사용.
    // 단, 타입 별칭을 사용하여 컬렉션 내 다른 클래스와 일관성 유지. (java.util. → kotlin.collections.)
    //
    // 1. 불변 타입과 가변 타입 구분. (kotlin.collections.Collection & kotlin.collections.MutableCollection)
    // 2. 자바 코드에서 코틀린 컬렉션은 모두 자바의 List 인터페이스로 변환.
    // 3. 배열과 마찬가지로 코틀린 표준 라이브러리에서 컬렉션을 쉽게 생성하는 함수 제공.
    //    일부 타입은 타입 별칭을 사용하므로 실제 반환되는 타입을 알아야 한다.
    val immutableList: List<String> = listOf("Lorem", "ipsum", "dolor", "sit")
    val firstItem = immutableList.get(0) // immutableList[0]
    // immutableList[0] = "Kotlin" → ERROR

    val mutableList: MutableList<String> = mutableListOf("Lorem", "ipsum", "dolor", "sit")
    mutableList[0] = "Kotlin"

    val immutableMap: Map<String, Int> = mapOf("A" to 65, Pair("B", 66))
    val code = immutableMap["A"]
    // immutableMap["C"] = 67

    val mutableMap: MutableMap<String, Int> = mutableMapOf("A" to 65, "B" to 66)
    mutableMap["C"] = 67
}