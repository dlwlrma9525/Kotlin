import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty

/** Reflection.
 *  리플렉션은 런타임에 프로그램 구조를 볼 수 있는 라이브러리와 언어 특징.
 *  코틀린의 함수와 프로퍼티는 1급이고 런타임에 이름, 프로퍼티 타입이나 함수를 알아내는 것이
 *  함수형 또는 리액티브를 간단하게 적용하는 것과 밀접한 관련이 있다.
 */
class MyClass(val p: Int)

var x: Int = 1
val y: Int = 2

val String.lastChar: Char
    get() = this[length - 1]

fun main(args: Array<String>) {

    // class reference.
    // 기본적인 리플렉션 특징은 코틀린 클래스의 런타임 레퍼런스를 구하는 것.
    // 정적 코틀린 클래스에 대한 레퍼런스를 구하려면 class 리터럴 구문 사용.
    // 자바 클래스 레퍼런스를 구하려면 KClass 인스턴스의 .java 프로퍼티 사용.
    val kotlinReference: KClass<MyClass> = MyClass::class
    val javaReference: Class<Developer> = Developer::class.java

    // member reference - function.
    // 함수를 값으로 다른 함수로 전달할 수 있고 :: 연산자로 함수 레퍼런스를 구한다.
    // 1. :: 연산자는 문맥으로 오버로딩한 함수도 적용.
    // 2. 변수에 명시적으로 함수 타입을 지정해서 함수 레퍼런스를 저장.
    // 3. 클래스 멤버나 확장 함수를 사용하면 한정자를 추가.
    val list = listOf(0, 1, 2)
    val predicate: (Int) -> Boolean = ::isOdd
    println(list.filter(predicate))
    val toCharArray: String.() -> CharArray = String::toCharArray

    val oddLength = compose(::isOdd, ::length)
    val strings = listOf("a", "ab", "abc")
    println(strings.filter(oddLength))

    // member reference - property.
    // 코틀린의 1급 객체로써 프로퍼티의 접근도 :: 연산자 사용.
    val mutableProperty: KMutableProperty<Int> = ::x
    val property: KProperty<Int> = ::y

    println(::x.get())
    println(::y.get())
    ::x.set(2)
    println(::x.get())

    // 파라미터가 없는 함수가 필요한 곳에 프로퍼티 레퍼런스를 사용.
    val strs = listOf("a", "bc", "def")
    println(strs.map(String::length))

    // 클래스 멤버인 프로퍼티에 접근할 때는 클래스로 한정.
    val prop = MyClass::p
    println(prop.get(MyClass(1)))

    // 확장 프로퍼티.
    println(String::lastChar.get("abc"))
}

fun isOdd(x: Int) = x % 2 !== 0
fun isOdd(s: String) = s == "brillig" || s == "slithy" || s == "tove"
fun length(s: String) = s.length

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}