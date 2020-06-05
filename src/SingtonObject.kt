object SingtonObject {
    @JvmField
    val BAR = "bar"

    const val FOO = "foo"

    @JvmStatic
    fun baz() {
    }
}