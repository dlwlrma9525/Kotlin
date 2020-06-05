/** Kotlin Class.
 */

// data class
// 자료를 저장하는 클래스를 만드는 과정을 단순화 한 코틀린은 데이터 클래스를 지원.
// 데이터 클래스는 자료를 구성하는 프로퍼티만 선언하면 컴파일러가 equals(), hashCode(), toString() 함수를 자동으로 생성.
data class Member(val name: String, val address: String)

// sealed class
// 한정 클래스는 enum 클래스를 확장한 개념을 가진 클래스로 각 종류별로 하나의 인스턴스만 생성되어 있는 enum 클래스와 달리 다수의 인스턴스 생성 가능.
// 한정 클래스는 enum 클래스의 특징을 그대로 가지고 있다.
// 한정 클래스를 상속하는 클래스는 한정 클래스로 정의된 여러 타입 중 하나로 취급.
sealed class MobileApp(val os: String) {
    class Android(os: String, val packageName: String) : MobileApp(os)  // MobileApp 한정 클래스를 상속받은 Android 클래스.
    class IOS(os: String, val bundleId: String) : MobileApp(os)         // MobileApp 한정 클래스를 상속받은 IOS 클래스.
    class WindowsMobile(os: String, val packageId: String) : MobileApp(os)
}

// 한정 클래스는, 한정 클래스로 정의된 클래스의 종류에 따라 다른 작업을 처리해야 할 때 유용.
// 1. when 문의 else 절이 없을 때, 한정 클래스를 상속받은 모든 클래스를 처리하지 않으면 컴파일 에러.
// 2. when 문으로 한정 클래스를 상속하는 모든 클래스의 처리 여부를 알수 있고 모두 처리했다면 else 문이 필요 없다.
fun whoami(app: MobileApp) = when (app) {
    is MobileApp.Android -> println("${app.os} / ${app.packageName}")
    is MobileApp.IOS -> println("${app.os} / ${app.bundleId}")

// 새로 추가된 WindowsMobile 유형을 처리하지 않는다면
// 'add necessary 'is WindowsMobile' branch or 'else' branch instead' 메시지와 함께 컴파일 에러.
    is MobileApp.WindowsMobile -> println("${app.os} / ${app.packageId}")
}

// 프로퍼티의 사용자 지정 Getter/Setter
// 프로퍼티에서 Getter 및 Setter 의 구현을 원하는 대로 변경 가능.
// [<getter>], [<setter>]는 각각 사용자 지정 Getter 및 Setter 이고 get() 및 set(value)로 선언.
//
// property syntax.
// var <propertyName>[: <propertyType>] [=<property_initializer>]
//      [<getter>]
//      [<setter>]
class People(val age: Int, val name: String) {
    val adult: Boolean
        get() = age >= 19

    var address: String = ""
        set(value) {
            // 사용자 지정 Setter 를 사용하면 프로퍼티 내 필드의 설정 값을 제어할 수 있으나
            // 읽고 쓰기가 모두 가능한 var 프로퍼티만 사용 가능.
            field = value.substring(0..9)
        }
}

fun main(args: Array<String>) {
    // data class
    val john = Member("John Doe", "Somewhere")
    val johnDoe = Member("John Doe", "Somewhere")
    val jane = Member("Jane Doe", "Anywhere")

    println("John == JohnDoe? = ${john == johnDoe}")
    println("John == Jane? = ${john == jane}")

    println("John.hashCode() = ${john.hashCode()}")
    println("John = $john") // john.toString()
    println("Jane = $jane") // jane.toString()

    // sealed class
    whoami(MobileApp.Android("Android 10", "com.kotlin.android"))
    whoami(MobileApp.Android("IOS 13", "com.kotlin.ios"))
    whoami(MobileApp.Android("Windows 10", "com.kotlin.windows"))

    // custom getter/setter
    val people = People(20, "dlwlrma")
    people.address = "Gangnam-gu, Seoul, Republic of Korea"
    println("${people.name}, ${if (people.adult) "adult" else "child"}, ${people.address}")
}

// Note.
// 한정 클래스를 상속하는 클래스는 일반적으로 한정 클래스 내에 중첩하여 선언.
// 하지만 같은 파일 내에 정의한다면 클래스 외부에 선언할 수도 있다.
//
// sealed class MobileApp(val os: String)
// class Android(os: String, val packageName: String) : MobileApp(os)
// class IOS(os: String, val bundleId: String) : MobileApp(os)