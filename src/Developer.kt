import java.io.IOException

class Developer @JvmOverloads constructor(
        val name: String,
        var address: String = "",
        var isAdult: Boolean = false) {

    @JvmOverloads
    fun doSomething(a: String, b: Int = 0, c: Boolean = false) {
        // TODO
    }

    @Throws(IOException::class)
    fun doSomething() {
        throw IOException()
    }

    companion object {
        @JvmField
        val BAR = "bar"

        const val FOO = "foo"

        @JvmStatic
        fun baz() {
        }
    }
}