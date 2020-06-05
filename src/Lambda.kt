import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/** Lambda Expression.
 *  코틀린의 람다 표현식은 하나의 함수를 표현하는 리터럴.
 *  { x: Any, y: Any -> x + y }
 *
 *  1. 람다 식은 항상 중괄호로 감싼다.
 *  2. 파라미터 선언은 중괄호 안에 위치하고 선택적으로 타입을 생략한다.
 *  3. 몸체는 -> 뒤에 정의한다.
 *  4. 람다의 추정한 리턴 타입이 Unit 이 아니면 람다 몸체의 마지막 식을 리턴 값으로 처리한다.
 *  5. 함수의 마지막 파라미터가 함수 타입이고, 람다 식을 대입한다면 람다 식을 함수의 파리미터 선언 괄호 뒤 선언 가능.
 *     파라미터가 함수 타입 하나이면 괄호 생략 가능.
 *  6. 람다 식의 사용하지 않는 매개변수는 _ 를 사용하여 명시.
 *
 *      val dialog = AlertDialog.Builder(this)
 *          .setNegativeButton("Cancel") { dialog, which -> doOnCancel(which) }
 *
 *      button.setOnClickListener { v -> doSomething() }
 *
 *  high-order function.
 *      고차함수는 함수를 파라미터로 받거나 함수를 리턴하는 함수.
 *
 *  function type.
 *      함수가 다른 함수를 파라미터로 받거나 리턴할 때, 함수 타입을 지정해야 한다.
 *      :: 연산자로 함수 레퍼런스를 구하고 전달.
 *      (T) -> T
 *      (x: T, y: T) -> T                   // parameter name.
 *      val sum((Int, Int) -> Int)? = null  // nullable.
 *
 *  anonymous function.
 *      일반 함수이지만 이름이 없는 함수.
 *      fun(x: Int, y: Int): Int {
 *          return x + y
 *      }
 *
 *      문맥에서 파라미터 타입과 리턴 타입 추론이 가능하면 생략 가능.
 *      단, 몸체가 있으면 반환 타입을 무조건 지정.
 *      ints.filter(fun(item) = item > 0)
 *
 *  it: 단일 파라미터의 암묵적 이름.
 *      함수 리터럴의 파라미터가 한 개면 파라미터를 선언하지 않는 것이 가능하며
 *      코틀린이 시그니처 자체를 알아낼 수 있다면 암묵적으로 파라미터 이름을 it 으로 선언.
 *
 *  inline function.
 *      고차 함수를 사용하면 런타임에 일부 불이익이 발생한다.
 *
 *      1. 각 함수의 객체가 생성되고 함수의 몸체에 접근하는 변수인 클로저를 캡처한다. (함수의 호출 분기로 저장)
 *      2. 메모리 할당과 가상 호출로 런타임 오버헤드 증가.
 *
 *      인라인 선언은 전달 받은 람다 식의 몸체 코드를 해당 람다 식을 사용하는 부분에 컴파일러가 그대로 복사.
 *
 *      1. inline 수식어는 함수 자체와 함수에 전달되는 람다에 영향을 주고 모두 호출 위치에서 인라인된다.
 *      2. 인라인은 생성한 코드의 크기를 증가시킨다. 하지만 작은 코드를 복사하는 등의 합리적인 방법으로 처리하면 성능에서 큰 성과를 낼수 있다.
 *         (특히 루프의 "변형" 호출 위치)
 *
 *  noinline
 *      인라인 가능한 람다는 인라인 함수 안에서만 호출할 수 있고 또는 인라인 가능한 인자로만 전달할 수 있지만,
 *      noinline 은 필드에 저장하거나 다른 곳에 전달하는 등 원하는 방식으로 다룰 수 있다.
 *
 *      inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {}
 *
 *  non-local return.
 *      코틀린은 이름을 가진 함수는 익명 함수에서 나가려면 return 문 사용.
 *      코틀린의 람다를 나가려면 라벨을 사용해야 한다. 람다는 순수 return 을 금지하는데 람다를 둘러싼 함수를 리턴할 수 없기 때문.
 *
 *      람다를 전달한 함수가 인라인되면, 리턴도 인라인 되기 때문에 return 문을 선언 할 수 있다.
 *      하지만 return 문은 람다에 위치하지만 둘러싼 함수를 나가며 이를 비 로컬 리턴이라고 명칭.
 *
 *      fun foo() {s
 *          inlineFunction {
 *              return
 *          }
 *      }
 *
 *  crossinline
 *      비 로컬 리턴 을 명시적으로 금지시키려면 람다 파라미터에 crossinline 키워드 추가.
 *      crossinline 키워드를 추가하면 인라인된 람다의 return 문이 있으면 컴파일 에러 발생.
 *      inline fun f(crossinline body: () -> Unit) {
 *          val f = object: Runnable {
 *              override fun run() = body()
 *          }
 *      }
 */
class SharedResource {
    companion object {
        fun operation(): String = "Kotlin"
    }
}

class Graph(val vertices: List<Vertex>)
class Vertex(val neighbors: List<Vertex>)

fun main(args: Array<String>) {

    // high-order function.
    val result = lock(ReentrantLock(), ::toBeSynchronized)
    println(result)

    // lambda expression.
    val sum: (Int, Int) -> Int = { x, y -> x + y }

    // it.
    listOf(0, 1, 2).filter { it > 0 } // (it: Int) -> Boolean

    // non-local return
    inlineFunction(1) {
        println("First call: $it")
        // return
    }
}

fun toBeSynchronized() = SharedResource.operation()
fun <T> lock(lock: Lock, body: () -> T): T {
    lock.lock()
    try {
        return body()
    } finally {
        lock.unlock()
    }
}

inline fun inlineFunction(a:Int, crossinline f:(Int)->Unit) {
    println("Before calling out")
    f(a)
    println("After calling out")
}

/** function scope.
 *  코틀린은 함수를 파일에서 최상위 수준으로 선언할 수 있다. 이는 자바, C#, 스칼라와 달리 함수를 포함할 클래스를 만들 필요가 없다는 것을 의미.
 *  최상위 수준 함수뿐만 아니라 함수를 로컬로, 멤버 함수로, 확장 함수로 선언할 수 있다.
 *
 *  local function.
 *      코틀린은 다른 함수 안에 선언한 로컬 함수를 지원한다.
 *
 *  closure.
 *      "The function named closure is said to “close over” the variable named x."
 *      function add(x) {
 *          return function closure(y) {
 *              return x + y;
 *          };
 *      }
 *
 *      클로저는 outer scope(상위 함수의 영역)의 변수를 접근할 수 있는 함수.
 *      코틀린은 클로저를 지원하며 로컬 함수, 익명 함수는 함수 밖에서 정의된 변수에 접근할 수 있고 클로저에 캡처한 변수를 수정 할 수 있다.
 *
 *  return at labels. (local return)
 *      코틀린은 함수 리터럴, 로컬 함수, 오브젝트 식에서 함수를 중첩할 수 있는데 한정된 return 을 사용하면 바깥 함수로 부터 리턴할 수 있다.
 *      return 문은 가장 가깝게 둘러싼 함수에서 리턴한다. 람다 식에서 리턴하고 싶다면 람다 식에 라벨을 붙혀 return 문을 한정해야 한다.
 *
 *      fun foo() {
 *          // 라벨 이름을 지정하지 않으면 암묵적으로 람다를 전달한 함수와 같은 이름으로 지정.
 *          ints.forEach lit@ {
 *              // 값을 리턴할 때 파서는 한정한 리턴에 우선순위를 준다. (@lit 에 1 리턴)
 *              if (it == 0) return@lit
 *              print(it)
 *          }
 *      }
 */
fun dfs(graph: Graph) {
    val visited = HashSet<Vertex>()

    fun dfs(current: Vertex) {
        if (!visited.add(current)) return
        for (v in current.neighbors)
            dfs(v)
    }

    dfs(graph.vertices[0])
}

fun nonLocalReturn() {
    listOf(0, 1, 2).forEach {
        if (it == 0) return
        print(it)
    }
}

fun localReturn() {
    listOf(0, 1, 2).forEach {
        if (it == 0) return@forEach
        print(it)
    }
}