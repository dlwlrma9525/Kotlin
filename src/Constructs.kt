/** More Language Constructs.
 *
 *  typealias
 *      제네릭 타입을 사용하다 보면 다소 복잡한 형태의 타입을 사용하는 경우가 종종 있다.
 *      코틀린은 타입 별칭 기능을 제공하고 복잡한 구조로 구성된 타입을 typealias 키워드로 간략히 표현.
 *
 *      1. 타입 별칭으로 선언한 타입은 기존의 타입과 완전히 동일하게 사용 가능.
 *         타입 별칭을 사용하여 새로운 타입을 선언한다고 해서, 이 타입에 해당하는 새로운 클래스가 생성되는 것은 아니다.
 *         타입 별칭으로 선언된 타입은 컴파일 시점에 모두 원래 타입으로 변환되므로 실행 시점의 부하가 없다는 또 다른 장점이 있다.
 *      2. 클래스나 함수와 마찬가지로 타입을 인자로 받을 수 있다.
 *      3. 함수형 타입도 타입 별칭을 지정할 수 있다.
 *
 *  destructuring declarations.
 *      복잡한 구성의 자료구조를 사용하다 보면, 때로는 해당 자료구조에 포함된 필드 중 일부만 사용하거나
 *      각 항목을 별도의 변수로 추출하고 사용하는 경우가 종종 있다.
 *
 *      class Developer {
 *          public int getAge() { ... }
 *          public String getName() { ... }
 *      }
 *
 *      Developer person = ...
 *
 *      // 객체에 포함된 필드를 각각 사용하려면 이를 수동으로 각 변수에 할당.
 *      int ageOfPerson = person.getAge();
 *      String nameOfPerson = person.getName();
 *
 *      코틀린은 분해 선언으로 각 프로퍼티가 가진 자료의 값을 한번에 여러개의 값(val) 또는 변수에 할당 지원.
 *      data class Developer(val age: int, val name: String)
 *      val person: Developer = ...
 *
 *      val (ageOfPerson, nameOfPerson) = person
 *
 *      분해 선언의 컴파일 코드
 *      val ageOfPerson: Int = person.component1()
 *      val nameOfPerson: String = person.component2()
 *
 *      분해 선언을 사용하면 내부적으로 각 값에 componentN() 함수의 반환값을 할당.
 *      분해 선언을 사용하려면 클래스에 프로퍼티 수만큼 componentN() 함수가 정의되어 있어야 하고
 *      이 함수들을 포함하고 있는 클래스만 분해 선언을 사용할 수 있다. 분해 선언을 지원하는 클래스는 다음과 같다.
 *
 *      data class
 *      kotlin.Pair
 *      kotlin.Triple
 *      kotlin.collections.Map.Entry
 */

// typealias.
typealias PeopleList = List<People>
typealias PeopleInTags = Map<String, Developer>

// typealias with Generic.
typealias ItemsInTags<T> = Map<String, T>

// typealias of function type
typealias PeopleFilter = (People) -> Boolean

fun sendMessage(people: PeopleList) { // fun sendMessage(people: List<People>)
    people.forEach { }
}

fun sendMessage(people: PeopleList, filter: PeopleFilter) { // fun sendMessage(people: List<People>, filter: (People) -> Boolean)
    people.filter(filter)
            .forEach { }
}

fun main(args: Array<String>) {
    val cities: Map<String, String> = mapOf("02" to "Seoul", "031" to "Gyeonggi-do")

    // destructuring declarations with map.
    for ((cityCode, name) in cities) {
        println("$cityCode=$name")
    }

    // destructuring declarations with lambda expression.
    cities.forEach { cityCode, name ->
        println("$cityCode=$name")
    }

    // 람다 표현식의 매개변수와 마찬가지로, 분해 표현식도 사용하지 않는 값 또는 변수가 있다면
    // 이름 대신 _를 사용하여 별도의 값이나 변수로 선언되지 않도록 지원.
    val member: Members = Members(25, "Kotlin")
    val (_, name) = member
}

class Members(val age: Int, val name: String) {
    // 코틀린은 개발자가 작성한 클래스에서 분해 선언 기능을 사용하려면 해당 클래스 내에
    // 별도로 componentN() 함수를 프로퍼티 선언 순서 및 타입에 맞게 추가.
    // componentN() 함수 정의 시 operator 키워드 추가.
    operator fun component1() = this.age

    operator fun component2() = this.name
}