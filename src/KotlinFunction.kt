/** Kotlin Function.
 *  자바의 메서드는 표현 형태와 사용 방법이 매우 제한적. 코틀린의 함수는 자바의 메서드와 동일한 기능을 수행하지만
 *  표현 형태가 더 자유롭고 자바의 메서드가 제공하지 않는 여러 유용한 기능을 갖추고 있다.
 */

// named parameter.
// 코틀린은 명명된 인자(named parameter)를 사용함으로써 함수를 호출할 때 매개변수의 순서와 상관없이 인자를 전달할 수 있다.
// 또한, 명명된 인자를 사용하면 매개변수의 수가 많아지더라도 각 인자에 어떤 값이 전달되는지 쉽게 구분할 수 있다.
//
// default parameter.
// 코틀린은 함수의 매개변수에 기본값을 지정할 수 있다.
fun drawCircle(x: Int, y: Int, radius: Int = 25) {}

// single expression. (단일 표현식)
// 메서드 내용을 항상 중괄호({})로 감싸야 했던 자바와 달리
// 코틀린은 Unit 타입을 제외한 타입을 반환하는 함수라면 단일 표현식(single expression) 정의 지원.
fun theAnswerToLifeTheUniverseAndEverything(): Int {
    return 21 * 2
}

fun theAnswerToLifeTheUniverseAndExpression(): Int = 21 * 2 // 단일 표현식.
fun theAnswerToLifeTheUniverseAndAnywhere() = 21 * 2        // 반환 타입 생략 가능.

// extension function.
// 자바는 기존의 클래스에 새로운 메서드를 추가하려면 해당 클래스를 상속하는 새로운 클래스를 작성해야 하지만
// 코틀린은 확장 함수(extension function)를 사용하여 상속 없이 기존 클래스에 새로운 함수 추가 가능.
//
// receiver type.
// 코틀린은 확장 함수를 추가할 대상, 타겟 클래스를 리시버 타입(receiver type)으로 명명.
// 리시버 타입 뒤에 (.)을 찍고 그 뒤에 원하는 함수의 형태를 정의하는 방식.
// 1. 확장 함수 구현부는 this 키워드로 리시버 타입 클래스의 인스턴스에 접근할 수 있고, 이를 리시버 객체(receiver object)로 명명.
// 2. 확장 함수는 엄연히 클래스 외부에서 정의하는 함수이므로 리시버 객체는 클래스 내 public 으로 정의된 프로퍼티나 함수만 접근 가능.

// String 클래스에 withPostfix() 함수 추가.
private fun String.withPostfix(postfix: String) = "$this$postfix"
fun String.withBar() = this.withPostfix("Bar")

// operator overloading.
// 자바는 연산자 오버로딩을 일체 허용하지 않지만 코틀린은 사용자 정의 타입에 한해 연산자 오버로딩 지원.
// 각 연산자별로 사전 정의된 함수를 재정의하는 방식이고 연산자 오버로딩을 위한 함수는 함수 정의에 operator 키워드가 추가되며 기존의 연산자를 재정의하는 것만 허용.
// 확장 함수로 연산자를 재정의하는 기능도 지원.
class Volume(var left: Int, var right: Int) {

    // == → equals()
    // 자바에서 equals() 메서드를 재정의하는 방식과 동일.
    // 단, 코틀린은 동일성 비교 연산자를 재정의할 때는 operator 키워드를 추가하지 않는다.
    override fun equals(other: Any?): Boolean {
        if (other == this) {
            return true
        }

        if (other !is Volume) {
            return false
        }

        return other.left == this.left && other.right == this.right
    }
}

// unary -
operator fun Volume.unaryMinus(): Volume {
    this.left = -this.left
    this.right = -this.right
    return this
}

// unary ++
operator fun Volume.inc(): Volume {
    this.left += 1
    this.right += 1
    return this
}

// unary --
operator fun Volume.dec(): Volume {
    this.left -= 1
    this.right -= 1
    return this
}

// binary +
operator fun Volume.plus(other: Volume)
        = Volume(this.left + other.left, this.right + other.right)

// binary -
operator fun Volume.minus(other: Volume)
        = Volume(this.left - other.left, this.right - other.right)

class Rectangle(val width: Int, val height: Int)

// comparison operator.
// 비교 연산자는 각 연산자가 모두 동일한 compareTo() 함수에 할당.
// compareTo() 함수의 반환형은 항상 Int.
operator fun Rectangle.compareTo(other: Rectangle): Int {
    val myDimension = this.width * this.height
    val otherDimension = other.width * other.height

    // 함수가 반환하는 값의 크기에 따라 해당 연산자의 참, 거짓 여부 판별.
    return myDimension - otherDimension
}

// augmented assignment operator.
// +=
operator fun Volume.plusAssign(other: Int) {
    this.left += other
    this.right += other
}

// -=
operator fun Volume.minusAssign(other: Int) {
    this.left -= other
    this.right -= other
}

// index access operator.
class Triple(var first: Int, var second: Int, var third: Int)

operator fun Triple.get(index: Int) = when (index) {
    0 -> this.first
    1 -> this.second
    2 -> this.third
    else -> IllegalArgumentException()
}

operator fun Triple.set(index: Int, value: Int) {
    when (index) {
        0 -> this.first = value
        1 -> this.second = value
        2 -> this.third = value
        else -> IllegalArgumentException()
    }
}

// in operator.
class Line(val start: Int, val end: Int)
operator fun Line.contains(point: Int) = point in start..end

// infix notation.
// 중위 표기법은 두 개의 변수 가운데 오는 함수 표기법.
// infix 함수는 다음 조건을 만족해야 한다.
// 1. 함수 선언에 infix 키워드 추가.
// 2. 확장 함수 혹은 멤버 함수이면서, 매개변수가 하나일 것.
infix fun Volume.increaseBy(amount: Int) {
    this.left += amount
    this.right += amount
}

infix fun Volume.decreaseBy(amount: Int) {
    this.left -= amount
    this.right -= amount
}

fun main(args: Array<String>) {
    drawCircle(x = 10, y = 5, radius = 25)  // named parameter.
    drawCircle(10, 5, radius = 25)   // 대입하는 인자 중 일부에만 명명된 인자 사용 가능.
    drawCircle(10, 5)                // default parameter.

    val foo = "Foo"
    println(foo.withBar())                  // extension function.

    var volume = Volume(50, 50)
    -volume
    volume++
    volume--

    volume += 20
    volume -= 10

    val v1 = Volume(10, 10) + Volume(20, 30)    // v1 == Volume(30, 40)
    val v2 = Volume(50, 30) - Volume(20, 10)    // v2 == Volume(30, 20)

    val a = Rectangle(10, 10)   // 100
    val b = Rectangle(2, 10)    // 20
    println("${a > b} ${a >= b} ${a <= b} ${a < b}")

    val triple = Triple(10, 20, 30)
    println("${triple[0]} ${triple[1]} ${triple[2]}")

    triple[0] = 25
    triple[1] = 50
    println("${triple[0]} ${triple[1]} ${triple[2]}")

    val line = Line(0, 10)
    println("${5 in line} ${-1 in line} ${-1 !in line}")

    val currentVolume = Volume(50, 50)
    currentVolume increaseBy 30
    currentVolume decreaseBy 20
}

// Note.
// 확장 함수는 리시버 타입에 직접 추가되는 함수가 아니다. 리시버 타입과 확장 함수의 인자를
// 인자로 받는 새로운 함수를 만들고, 확장 함수를 호출하면 이 새로운 함수를 대신 호출.
// 확장 함수는 패키지 수준으로도 선언할 수 있고 {정의된 파일 이름}Kt 클래스 내 정적 함수로 변환.