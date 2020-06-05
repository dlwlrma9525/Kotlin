import com.example.FOO // package-level field
import com.example.Foo // package-level class
import com.example.foo // package-level function

/** Object Village.
 *  코틀린의 클래스 및 인터페이스는 자바와 유사한 부분이 많지만
 *  새로운 개념이 추가되거나 다른 방식을 사용하는 문법이 있다.
 *
 *  Specification
 *  1. 코틀린은 접근 제한자를 지정하지 않는 경우 public 으로 간주.
 *  2. internal 접근 제한자는 동일한 모듈 내에 있는 클래스들로의 접근 제한.
 *     (intellij IDEA Module, Maven/Gradle Project, 하나의 Ant 태스크 내에서 함께 컴파일되는 파일들)
 *  3. 코틀린은 클래스 및 인터페이스 블록에 선언된 내용이 없으면 이름만으로 선언 가능.
 *  4. 코틀린은 new 키워드 없이 생성자의 호출만으로 클래스의 인스턴스 생성.
 *
 *  package foo.bar
 *  class Baz
 *  interface Foo { fun foo() }
 *  abstract class Bar { abstract fun bar() }
 */
class MacBook(id: String) {
    // property.
    // 자바 클래스 멤버의 Field, Getter, Setter 메서드로 인한 상용구 코드를 위해 코틀린은 프로퍼티를 지원.
    // 클래스 멤버의 프로퍼티는 자료를 저장할 수 있는 Field 와 Getter, Setter 함수를 함께 지원.
    //
    // 1. 프로퍼티는 초기값을 명시적으로 지정하지 않으면 컴파일 에러.
    // 2. 생성자에서 프로퍼티의 값을 할당한다면 선언 시 값을 할당하지 않아도 됨.
    // 3. 프로퍼티 선언이나 생성자 호출 시점에 값을 할당할 수 없으면 lateinit 키워드 추가.
    //    (선언 시점에서 값 할당을 요구하는 val 프로퍼티와 Nullable 프로퍼티는 사용 불가)
    val code: String? = null
    val cpu: String? = null
    val ram: Int = 0
    lateinit var macOsVersion: String

    // primary constructor.
    // class MacBook(code: String)
    //
    // 1. init 블록을 사용하여 기본 생성자 대체.
    // 2. 생성자에 인자가 필요한 경우 주 생성자로 인자를 받을 수 있고 init 블록에서 사용가능.
    init {
        println("Hello $id")
    }

    // constructor.
    // 주 생성자 외 다른 형태의 생성자가 필요한 경우 constructor 키워드로 추가 생성자 지원.
    // 코틀린은 추가 생성자를 정의하는 경우 반드시 주 생성자를 호출.
    constructor(id: String, password: String) : this(id)
    private constructor() : this("kotlin")

    // function
    // 코틀린은 자바 클래스의 메서드를 함수로 표현.
    // fun name(arg: Type): Type {}
    //
    fun on(): Unit { // Unit 타입은 "함수 자체"를 의미하며 생략 가능.
        // 아무것도 반환하지 않는 함수.
    }

    // nested class
    // static nested class      코틀린은 정적 중첩 클래스를 선언할 때 별도의 키워드가 필요 없다.
    // non-static nested class  inner 키워드 추가.
    class StaticNestedClass
    inner class NonStaticNestedClass
}

// 1. 코틀린은 생성자의 인자를 통해 클래스 내부의 프로퍼티에 값을 할당할 수 있다.
// 2. 코틀린 클래스는 생성자에 프로퍼티를 선언할 수 있다.
// 3. 생성자의 가시성을 변경하려면 constructor 키워드 앞에 접근 제한자 추가.
class MacPro internal constructor(val code: String, val macOsVersion: String)

/** package-level static field and method.
 *  코틀린은 클래스 내의 정적 필드와 메서드를 지원하지 않는다.
 *  패키지 레벨로 값이나 함수를 선언할 수 있고, 패키지에 종속되기 때문에 {패키지 이름}.{값 또는 함수 이름}으로 호출 가능.
 *
 *  package com.example
 *
 *  const val FOO = 0   // package-level field.
 *  fun foo() {}        // package-level function.
 *  class Foo           // package-level class.
 */

class User private constructor(val name: String, val registerTime: Long) {

    // companion object (동반 객체)
    // 패키지 단위 함수는 특정 클래스에 속해 있지 않으므로, 클래스 내의 private 멤버에 접근하려면 동반 객체를 선언.
    // 동반 객체는 클래스 내의 모든 멤버에 접근이 가능하고 인스턴스 생성 없이 호출 가능.
    companion object {
        fun create(name: String): User {
            return User(name, System.currentTimeMillis())
        }
    }
}

// singleton.
// 코틀린은 class 키워드 대신 object 키워드를 적용하면 싱글턴 패턴의 객체로 동작.
object Singleton {
    fun foo() {}
    fun bar() {}
}

// enumeration class.
enum class Color(val rgb: Int) {
    // 각각의 열거형은 enum class 의 인스턴스이므로 초기화 가능.
    WHITE(0xFFFFFF),
    BLACK(0x000000)
}

fun main(args: Array<String>) {
    val macBook = MacBook("Catalina")
    macBook.NonStaticNestedClass()
    MacBook.StaticNestedClass()

    // package-level.
    print(FOO)
    val foo = Foo()
    foo()

    // companion object.
    val user = User.create("dlwlrma")
    println(user.name)

    // singleton.
    Singleton.foo()
    Singleton.bar()
    println("object's hashcode: ${Singleton.hashCode()}")
    println("object's hashcode: ${Singleton.hashCode()}")

    // enumeration.
    println("black rgb value: ${String.format("0x%X", Color.WHITE.rgb)}")
}