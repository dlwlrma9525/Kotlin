/** Kotlin Library.
 *  코틀린 표준 라이브러리는 개발 시 유용하게 사용할 수 있는 여러 함수들 지원.
 *
 *  check(), require()
 *      특정 값의 상태를 확인하거나 의도하지 않는 상태에서 프로그램이 계속 실행되는 것을 방지하는 함수.
 *      함수 또는 생성자의 인자로 전달받은 값을 사용하기 전, 그 값의 유효성을 검사하고 명시적으로 에러 발생 사실을 알리고 처리.
 *
 *      fun check(value: Boolean)
 *      fun check(value: Boolean, lazyMessage: () -> Any)
 *      인자로 받은 value 값이 참이 아니라면 IllegalStateException 을 발생시키고, lazyMessage 로 넘겨진 함수 실행.
 *
 *      fun require(value: Boolean)
 *      fun require(value: Boolean, lazyMessage: () -> Any)
 *      인자로 받은 value 값이 참이 아니라면 IllegalArgumentException 을 발생시키고, lazyMessage 로 넘겨진 함수 실행.
 *
 *  checkNotNull(), requireNotNull()
 *      특정 값의 null 여부를 확인하고 null 이 아닌 값을 반환하는 함수.
 *
 *      fun <T: Any> checkNotNull(value: T?) : T
 *      fun <T: Any> checkNotNull(value: T?, lazyMessage: () -> Any) : T
 *      인자로 받은 value 값이 널 값이라면 IllegalStateException 을 발생시키고 lazyMessage 로 넘겨진 함수를 실행시키며, null 이 아니라면 값 반환.
 *
 *      fun <T: Any> requireNotNull(value: T?) : T
 *      fun <T: Any> requireNotNull(value: T?, lazyMessage: () -> Any) : T
 *      인자로 받은 value 값이 널 값이라면 IllegalArgumentException 을 발생시키고 lazyMessage 로 넘겨진 함수를 실행시키며, null 이 아니라면 값 반환.
 *
 *  error()
 *      호출될 가능성이 없는 영역에 진입하게 되는 경우 임의로 예외를 발생시켜 프로그램의 실행을 막는 방법을 주로 사용.
 *      코틀린에서는 임의로 예외를 발생시킬 때 error() 함수를 사용하여 이를 간편하게 구현할 수 있습니다.
 *
 *      fun error(message: String) : Nothing
 *      인자로 받은 message 와 함께 IllegalStateException 발생.
 *
 *  TODO()
 *      fun TODO() : Nothing
 *      fun TODO(reason: String) : Nothing
 *      NotImplementedError 예외를 발생시켜 이 부분이 아직 완성되지 않았음을 통지. 에러 메시지에 표시될 상세 내용을 reason 매개변수를 통해 전달.
 */
class BMW {
    fun drive() {}
    fun stop() {
        TODO("Brake is not implemented")
    }
}

fun showMessage(isPrepared: Boolean, message: String) {
    check(isPrepared)                      // if (!isPrepared) throw IllegalArgumentException()
    require(message.length > 10)    // if (!message.length > 10) throw IllegalArgumentException()

    println(message)
}

fun showMessage(message: String?) {
    val msg = requireNotNull(message) { println("message is null") }

    if (msg.isEmpty()) {
        error("not prepared yet")
    }
    println(message)
}

fun main(args: Array<String>) {
    showMessage(true, "Hello! Kotlin")
    showMessage(false, "Hello! Kotlin")
    showMessage(null)
    showMessage("")

    val bmw = BMW()
    bmw.stop()
}