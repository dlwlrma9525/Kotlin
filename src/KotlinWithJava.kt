/** Kotlin with Java.
 *  코틀린은 자바와 완벽히 호환되므로 프로젝트 내 일부 코드만 코틀린으로 작성하고 나머지 자바 코드는 그대로 지원.
 *  하지만 코틀린과 자바는 언어 구성이 엄연히 다른 만큼 일부 기능은 자바와 코틀린 간 특별한 처리 필요.
 *
 *  [Getter/Setter 의 프로퍼티화]
 *      자바로 작성된 클래스 내 Getter/Setter 의 일반적인 규칙을 만족하는 메서드는 코틀린에서 프로퍼티 형태로 지원.
 *
 *      1. Getter/Setter 메서드가 모두 정의되어 있다면, 코틀린에서는 이를 읽고 쓰기 모두가 가능한 프로퍼티처럼 사용.
 *      2. 반면 둘 중 하나만 정의되어 있거나 특정 메서드가 규칙을 만족하지 않는다면 읽기 혹은 쓰기만 가능한 프로퍼티로 취급.
 *      3. Getter/Setter 가 프로퍼티로 취급되는 경우, 해당 프로퍼티의 이름은 get 혹은 set 을 제외한 나머지 부분으로 구성.
 *
 *  [가변인자를 받는 메서드]
 *      자바로 작성된 가변 인자를 받는 메서드를 코틀린 코드에서 호출하는 경우 * (스프레드 연산자)를 사용하여 인자 전달.
 *
 *  [Object 클래스 내 메서드]
 *      자바의 Object 클래스는 코틀린의 Any 클래스로 처리.
 *      하지만 코틀린은 JVM 플랫폼 외 다른 플랫폼도 사용할 수 있도록 설계되어 Any 클래스에 Object 클래스 내 메서드 중 일부만 멤버 함수로 지원.
 *
 *      toString(), hashCode(), equals()
 *
 *      1. 멤버 함수로 지원하고 있지 않은 wait(), notify() 함수는 자바의 Object 클래스의 확장 함수로 지원.
 *      2. 코틀린에서 생성한 클래스에서 자바의 clone() 메서드를 재정의하려면 kotlin.Cloneable 인터페이스를 구현.
 *          class Developer(val name: String, val address: String) : Cloneable {
 *              override fun clone(): Any { ... }
 *          }
 *
 *      3. finalize() 메서드는 단순히 해당 함수를 클래스에 정의. 단 private 선언 금지.
 *          class Developer(val name: String, val address: String) {
 *              protected fun finalize() { ... }
 *          }
 *
 *  [SAM 변환]
 *      코틀린은 자바로 작성된 인터페이스에 대해 Single Abstract Method 변환 지원.
 *      따라서 SAM 변환이 가능한 인터페이스를 인자로 받는 함수를 호출할 때 인터페이스 구현 대신 함수를 전달.
 *
 *      코틀린으로 작성된 인터페이스는 SAM 변환 미 지원.
 *      따라서 하나의 함수만을 포함하는 인터페이스가 필요한 부분에는 인터페이스 대신 함수형 타입을 사용하기를 적극 권장.
 *
 *      button.setOnClickListener {
 *          ...
 *      }
 */
fun main(args: Array<String>) {
    /** public class Developer {
     *      @Nullable
     *      private String name;
     *      private String address;
     *
     *      public Developer(String name, String address) {
     *          this.name = name;
     *          this.address = address;
     *      }
     *
     *      public String getName() {
     *          return this.name;
     *      }
     *
     *      public String getAddress() {
     *          return address;
     *      }
     *
     *      public void setAddress(String address) {
     *          this.address = address;
     *      }
     *  }
     */

    // 코틀린 클래스와 동일하게 객체 생성 가능.
    val person = Person("John Doe", "Somewhere")

    // person.name from getName()
    // person.address from getAddress()
    println("name: ${person.name} address: ${person.address}")

    // not exist setter.
    // person.name="Jane Doe"

    // person.address from setAddress()
    person.address = "Nowhere"

    /** public class ArrayTest {
     *      public void doSomething(int... args) {
     *          // TODO
     *      }
     *
     *      public void doNothing(String... args) {
     *          // TODO
     *      }
     *  }
     */

    val a = ArrayTest()

    // Java primitive type array.
    val intArgs = intArrayOf(1, 2, 3, 4, 5)
    a.doSomething(*intArgs)

    // Object type array.
    val stringArgs = arrayOf("Lorem", "ipsum", "dolor", "sit", "amet")
    a.doNothing(*stringArgs)

    val str = "Lorem ipsum"
    synchronized(str) {
        (str as java.lang.Object).wait()
        (str as java.lang.Object).notify()
    }
}