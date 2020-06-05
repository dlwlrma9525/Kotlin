import java.net.URL

class Api
class Image
class PostalCode

class Contact {
    var name: String = ""
    val address: Address? = null
}

class Address {
    var line1: String = ""
    var line2: String? = null
}

class Maps {
    /** Null Safety.
     *  자바로 개발하다 겪는 오류는 여러 가지가 있지만 그 중 가장 빈번하게 발생하는 것으로 NullPointerException 이 있다.
     *  안드로이드 서포트 라이브러리의 @Nullable, @NonNull 어노테이션을 사용하여 객체의 널 허용여부를 표시할 수 있지만
     *  어노테이션을 통한 널 허용여부 확인은 IDE 에서 지원하는 플러그인 혹은 Android Lint 와 같은 정적 분석 도구에서만 지원하므로
     *  컴파일 단계에서는 여전히 NullPointerException 예외가 발생할 소지가 있다.
     *
     *  @Nullable String nullableString = null;
     *  @NonNull String nonNullString = "";
     *  변수 nonNullString 은 널 값을 허용하도록 표기하였지만 변수를 초기화하지 않아도 컴파일 단계에서 오류가 발생하지 않으므로 무결성 보장 X.
     *
     *
     *  ? (Nullable operator)
     *      코틀린은 별도 표기가 없으면 널 값을 허용하지 않으므로 널 값을 가지려면 명시적으로 타입 뒤에 ? 를 붙혀주여야 한다.
     *      함수의 파라미터나 반환 값도 적용.
     *
     *  ?: (Elvis operator)
     *      널 값을 허용하지 않는 값, 변수에 널 값을 반환할 수 있는 함수의 결과를 대입하는 경우 널 값에 대한 별도 처리가 필요.
     *      엘비스 연산자는 널 값에 대한 처리를 지원.
     *
     *      foo ?: bar // foo 가 null 이면 bar 반환. foo 가 null 이 아니면 foo 반환.
     *
     *  ?. (safe call operator)
     *      val foo = bar?.baz  // bar 가 null 이 아닐 경우 해당 값을 대입하고 bar 가 null 이면 null 반환.
     *      foo?.bar()          // foo 가 null 이 아닐 경우 bar() 호출
     *
     *  as? (safe cast)
     *      자료형 변환이 실패할 경우 null 반환.
     *
     *  !! (non-null assertions)
     *      상황에 따라 널 값을 포함할 수 있는 타입에 널 값이 아닌 값만 포함되는 경우
     *      널 값을 포함할 수 있는 타입을 널 값을 포함하지 않는 타입으로 변환 가능.
     *
     *      val foo: Foo?
     *      val nonNullFoo: Foo = foo!!     // foo 는 null 값을 포함하지 않음을 보증.
     *      foo!!.bar()                     // foo 가 null 값이 아님을 보장하면서 bar() 호출.
     *      val myBaz = foo!!.baz
     *
     *      1. 비 널 값 보증을 사용하였으나 실제 객체가 널 값이면 예외가 발생하므로 사용 주의.
     *      2. 비 널 값 보증은 중첩 사용 비 권장. 하나라도 널 값이면 예외이고 로그는 라인만 알 수 있을 뿐 어느 요소의 예외인지 알 수 없다.
     *         val line = contact.address!!.line2!!
     *
     *  lateinit
     *      클래스의 프로퍼티는 클래스를 생성할 때 생성자와 함께 값을 할당하는 경우도 많지만
     *      의존성 주입을 사용하거나 설계상 이유로 클래스를 생성하고 추 후 초기화를 수행하는 경우도 있다.
     *      코틀린은 널 값을 허용하지 않는 경우 lateinit 키워드를 사용하면 초기화 없이 선언할 수 있다.
     *      (단, 초기화 작업을 빠뜨리지 않도록 주의)
     */
    val nullableString: String? = null
    val nonNullString: String = "foo"

    // val name: String
    // val address: String = null

    lateinit var api: Api

    init {
        val contact = Contact()
        val line: String? = contact.address?.line1
        println("line: ${line ?: "No address.."}")

        val foo = "foo"
        val bar: Int? = foo as? Int ?: 0
    }

    fun formatAddress(line1: String, line2: String?, city: String): Address {
        return Address()
    }

    fun findPostalCode(address: Address): PostalCode? {
        return null
    }

    fun generateMapImage(address: Address): Image? {
        // val postalCode = findPostalCode(address) ?: return null
        val postalCode = findPostalCode(address) ?: throw IllegalStateException()
        return Image()
    }
}

fun main(args: Array<String>) {
    /** Type.
     *  type check.
     *  코틀린은 비교대상이 객체냐 객체의 값이냐를 구분하지 않고 모두 == 연산자 사용.
     *  ==  값 또는 객체의 값 비교.
     *  === 객체 자체를 비교.
     *
     *  is operator. (type check)
     *  자바의 instanceOf 연산자와 동일.
     *
     *  as operator. (type casting)
     *  val foo: Int = 10.0 as Int
     *
     *  스마트 캐스트.
     *  자료형 추론이 가능한 경우 캐스팅 없이 해당 자료형의 객체를 사용할 수 있도록 스마트 캐스트(smart cast) 기능을 지원.
     *  스마트 캐스트는 값을 검사하는 시점과 사용하는 시점 사이에 값이 변하지 않았다는 것이 보장되는 경우에만 지원.
     *  (var 변수는 지원 X, 자료형 추론이나 is 연산자로 타입을 확인하면 스마트 캐스트 지원)
     */
    val a: Pair<Char, Int> = Pair('A', 65)
    val b = a
    val c: Pair<Char, Int> = Pair('A', 65)

    println("aEqualsToB: ${a == b}")
    println("aEqualsToC: ${a == c}")
    println("aIdenticalToB: ${a === b}")
    println("aIdenticalToC: ${a === c}")

    // is operator.
    printTypeName("String")

    // as operator.
    val photoHolder = CustomRecyclerView().PhotoHolder()
    val holder = photoHolder as RecyclerView.ViewHolder

    onBindViewHolder(holder, 0)
    onBindViewHolder(CustomRecyclerView().TextHolder(), 0)

    Maps()
}

fun printTypeName(obj: Any) { // Any 는 코틀린의 최상위 클래스.
    if (obj is Int) {
        println("Type = Integer")
    } else if (obj is Float) {
        println("Type = Float")
    } else if (obj is String) {
        println("Type = String")
    } else {
        println("Type = Unknown Type")
    }
}

open class RecyclerView {
    open inner class ViewHolder
}

class CustomRecyclerView : RecyclerView() {

    inner class PhotoHolder : RecyclerView.ViewHolder() {
        fun setImageUrl(url: URL) {}
    }

    inner class TextHolder : RecyclerView.ViewHolder() {
        fun setText(text: String) {}
    }
}

fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (holder is CustomRecyclerView.PhotoHolder) {
        // smart cast.
        holder.setImageUrl(URL("http://google.com"))
    } else if (holder is CustomRecyclerView.TextHolder) {
        // smart cast.
        holder.setText("Hello")
    }
}

/** Platform Type.
 *  자바로 작성된 클래스는 코틀린에서 플랫폼 타입이라고 명칭. 플랫폼 타입은 몇 가지 특징이 있다.
 *  1. 기본적으로 널 값 허용.
 *  2. Type! 과 같은 형태로 표시.
 *  3. 코틀린에서 자바로 작성한 클래스를 사용할 때 자동으로 지정되는 타입이고 개발자가 직접 지정하지 않는다.
 *
 *  플랫폼 타입은 코틀린에서 널 허용 여부와 관계 없이 사용이 가능하지만 널 값이면 예외가 발생.
 *  코틀린은 이를 해결하기 위해 @Nullable, @NonNull 과 같은 몇몇 어노테이션을 인식해 널 허용여부를 판단.
 *
 *  단, 코틀린으로 작성되지 않은 곳에서 플랫폼 타입의 객체를 생성하는 경우 컴파일 과정에서 해당 필드에 대한 널 여부를 검증할 수 없다.
 *  (자바로 작성된 코드, JSON 파싱 결과..)
 */
fun platformType() {
    /** Java class.
     *  public class Developer {
     *      @Nullable String name; // String? 프로퍼티로 인식
     *
     *      public String getName() {
     *          return this.name;
     *      }
     *  }
     */
    val person = Person("John Doe", "Somewhere")

    // val n1: String = person.name
    val n2: String? = person.name
}
/** 코틀린이 인식 가능한 자바 어노테이션.
 * 종류         패키지/클래스
 * JetBrains    org.jetbrains.annotations
 * Android      com.android.annotations, android.support.annotations
 * JSR-305      javax.annotation
 * FindBugs     edu.umd.cs.findbugs.annotations
 * Eclipse      org.eclipse.jdk.annotation
 * Lombok       lombok.NonNull
 */