import java.io.IOException

/** if~else, when, while, do~while, Range
 */
fun main(args: Array<String>) {
    val age: Int = 25
    val ageRange: String

    // if~else
    // 자바와 달리 코틀린의 if~else 문은 값을 반환할 수 있다.
    ageRange = if (age >= 10 && age < 20) {
        "10대"
    } else if (age >= 20 && age < 30) {
        "20대"
    } else {
        ""
    }
    println(ageRange)

    // 삼항 연산자
    val number = 20
    val str = if (number % 2 == 0) "Even" else "Odd"

    // when
    // 자바의 switch 문을 대체.
    // 1. when 문은 break 문 대신 중괄호로 구분.
    // 2. 구문이 한 줄이면 중괄호 생략 가능.
    // 3. when 문도 if~else 문 처럼 값을 반환할 수 있다.
    // 4. when 문의 조건의 경우 상수 값뿐만 아니라 표현식 가능.
    val bags: Int = 1
    when (bags) {
        0 -> println("We have no bags")
        1, 2 -> {
            // 여러 개의 조건은 쉼표로 구분.
            println("Extra charge required")
            println("We have $bags bag(s)")
        }
        else -> println("Cannot have more bags")
    }

    var message = when (bags) {
        0 -> "We have no bags"
        1, 2 -> "We have $bags bag(s)"
        else -> "Cannot have more bags"
    }
    println(message)

    val e = IOException()
    message = when (e) {
        is IOException -> "Network Error"
        is IllegalStateException -> "Invalid State"
        else -> "Exception"
    }
    println(message)

    // while 문은 자바와 동일.
    // do~while 문은 자바와 동일.

    // for
    // 코틀린은 Iterator 를 통한 for-each 형태만 지원하며 인자의 타입 생략 가능.
    // 코틀린은 for 문 내에서 현재 항목의 인덱스가 필요한 경우, Collection.indices 프로퍼티 사용.
    val names = arrayOf("Lorem", "ipsum", "dolor", "sit")
    for (name in names) { //type inference.
        println(name)
    }

    for (i in names.indices) { // IntRange class.
        println(names[i])
    }

    // break, continue label
    // 코틀린의 모든 식은 label 을 추가할 수 있다. 라벨은 @ 부호 앞에 식별자가 붙는 형식.
    // 식 앞에 라벨을 위치시켜 라벨을 붙힌다.
    loop@ for (i in 0..100) {
        for (j in 0..100) {
            // 라벨로 break, continue 문을 한정할 수 있고
            // 해당 라벨이 붙은 루프 이후로 실행지점을 점프한다.
            if (j == 50) break@loop
        }
    }
    // Range
    // Range 는 코틀린의 독특한 자료구조료 특정 범위를 순환하거나 해당 범위 내의 특정 항목이 포함되어 있는지 확인할 때 사용.
    // ..                   Range operator
    // if (5 in 0..10)      true
    // if (20 !in 0..10)    true
    // downTo               역순
    // step                 증감폭

    val myRange: IntRange = 0..10           // 0 ~ 10
    val myRange2: IntRange = 0 until 11     // 마지막 값을 포함하지 않는 범위

    for (i in myRange) {
        println(i)
    }

    for (i in 0..10 step 5) {
        println(i)
    }

    for (i in 10 downTo 0 step 2) {
        println(i)
    }

    if (5 in myRange) {
        println("5 in Range")
    }

    if (11 !in myRange) {
        println("11 is not in Range")
    }

    // try~catch
    val valid: Boolean = try {
        checkAge(-1)
        true
    } catch (e: Exception) {
        false
    } finally {
        println("end..")
    }
}

/** Exception.
 *  코틀린의 예외는 자바와 동일.
 *  코틀린의 try 문은 값을 반환할 수 있다.
 *
 *  코틀린은 checked exception 을 따로 검사하지 않으므로
 *  대부분의 예외를 try~catch 문으로 감싸 처리한 자바와 달리 코틀린은 이를 선택적으로 적용.
 */
fun checkAge(age: Int) {
    if (age < 0) {
        throw IllegalArgumentException("Invalid age: $age")
    }
}