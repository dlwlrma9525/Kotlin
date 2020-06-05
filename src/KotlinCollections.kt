/** Kotlin Collections.
 *  코틀린 표준 라이브러리는 다양한 컬렉션을 간편히 만들 수 있는 함수들 지원.
 *
 *  Array.
 *      fun <T> arrayOf(vararg elements: T): Array<T>   함수의 가변인자로 받은 값으로 구성된 배열 반환.
 *      fun <T> emptyArray(): Array<T>                  특정 타입을 갖는 빈 배열 반환.
 *      fun <T> arrayOfNulls(size: Int): Array<T?>      null 값을 포함할 수 있는 배열 반환. (모든 값이 null 값으로 초기화, 배열의 크기는 size 만큼 할당)
 *
 *  Java primitive type array.
 *      자바의 원시 타입을 포함하는 배열은 코틀린의 배열과 다른 타입으로 취급.
 *      다음은 자바의 원시 타입을 포함하는 배열을 생성하는 함수.
 *
 *      fun booleanArrayOf(vararg elements: Boolean): BooleanArray      boolean[]
 *      fun byteArrayOf(vararg elements: Byte): ByteArray               byte[]
 *      fun charArrayOf(vararg elements: Char): CharArray               char[]
 *      fun doubleArrayOf(vararg elements: Double): DoubleArray         double[]
 *      fun floatArrayOf(vararg elements: Float): FloatArray            float[]
 *      fun intArrayOf(vararg elements: Int): IntArray                  int[]
 *      fun longArrayOf(vararg elements: Long): LongArray               long[]
 *      fun shortArrayOf(vararg elements: Short): ShortArray            short[]
 *
 *  List.
 *      fun <T> listOf(vararg elements: T): List<T>                 인자로 받은 elements 를 포함하는 읽기 전용 리스트 반환.
 *      fun <T> listOf(element: T): List<T>                         인자로 받은 element 하나만을 요소로 갖는 읽기 전용 리스트 반환.
 *      fun <T> listOf(): List<T>                                   비어있는 읽기 전용 리스트 반환.
 *
 *      fun <T: Any> listOfNotNull(vararg elements: T?): List<T?>   인자로 받은 elements 중 null 이 아닌 값들로만 구성된 읽기 전용 리스트 반환. (모든 값이 null 값이면 빈 리스트 반환)
 *      fun <T : Any> listOfNotNull(element: T?): List<T>           인자로 받은 element 의 값이 널이 아닌 경우 이 요소 하나만을 갖는 리스트 반환. (null 값인 경우 빈 리스트 반환)
 *
 *      fun <T> mutableListOf(vararg elements: T): MutableList<T>   인자로 받은 elements 를 요소로 가지며 수정 가능한 리스트 반환.
 *      fun <T> mutableListOf(): MutableList<T>                     비어있는 수정 가능한 리스트를 반환.
 *
 *      fun <T> arrayListOf(vararg elements: T): ArrayList<T>       인자로 받은 elements 를 요소로 하는 ArrayList 반환.
 *      fun <T> arrayListOf(): ArrayList<T>                         비어있는 ArrayList 반환.
 *
 *  Map.
 *      fun <K, V> mapOf(vararg pairs: Pair<K, V>): Map<K, V>                   Pair 형태로 받은 인자들을 포함하는 읽기 전용 맵 반환.
 *      fun <K, V> mapOf(pair: Pair<K, V>): Map<K, V>                           인자로 받은 Pair 하나만을 요소로 갖는 읽기 전용 맵 반환.
 *      fun <K, V> mapOf(): Map<K, V>                                           비어있는 읽기 전용 맵 반환.
 *
 *      fun <K, V> mutableMapOf(vararg pairs: Pair<K, V>): MutableMap<K, V>     Pair 형태로 받은 인자들을 포함하는 수정 가능한 맵 반환.
 *      fun <K, V> mutableMapOf(): MutableMap<K, V>                             비어있는 수정 가능한 맵 반환.
 *
 *      fun <K, V> hashMapOf(vararg pairs: Pair<K, V>): HashMap<K, V>           Pair 형태로 받은 인자들을 포함하는 HashMap 형태의 맵 반환.
 *      fun <K, V> hashMapOf(): HashMap<K, V>                                   비어있는 HashMap 형태의 맵 반환.
 *
 *      fun <K, V> linkedMapOf(vararg pairs: Pair<K, V>): LinkedHashMap<K, V>   Pair 형태로 받은 인자들을 포함하는 LinkedHashMap 형태의 맵 반환.
 *      fun <K, V> linkedMapOf(): LinkedHashMap<K, V>                           비어있는 LinkedHashMap 형태의 맵 반환.
 *
 *      fun <K, V> sortedMapOf(vararg pairs: Pair<K, V>): SortedMap<K, V>       Pair 형태로 받은 인자들을 포함하는 SortedMap 형태의 맵 반환.
 *
 *  to()
 *      mapOf() 함수나 mutableMapOf() 함수는 맵에 들어갈 요소를 모두 Pair 형태로 받는데,
 *      Pair 를 만들 때 사용할 수 있는 표준 라이브러리 내 함수인 to()를 사용하면 이를 직관적으로 표현할 수 있다.
 *
 *  Set.
 *      fun <T> setOf(vararg elements: T): Set<T>                               인자로 받은 elements 를 요소로 하는 읽기 전용 집합 반환.
 *      fun <T> setOf(element: T): Set<T>                                       인자로 받은 element 하나만을 요소로 하는 읽기 전용 집합 반환.
 *      fun <T> setOf(): Set<T>                                                 비어있는 읽기 전용 집합 반환.
 *
 *      fun <T> mutableSetOf(vararg elements: T): MutableSet<T>                 인자로 받은 elements 를 요소로 하는 수정 가능한 집합 반환.
 *      fun <T> mutableSetOf(): MutableSet<T>                                   비어있는 수정 가능한 집합 반환.
 *
 *      fun <T> hashSetOf(vararg elements: T): HashSet<T>                       인자로 받은 elements 를 포함하는 HashSet 형태의 집합 반환.
 *      fun <T> hashSetOf(): HashSet<T>                                         비어있는 HashSet 형태의 집합 반환.
 *
 *      fun <T> linkedSetOf(vararg elements: T): LinkedHashSet<T>               인자로 받은 elements 를 포함하는 LinkedHashSet 형태의 집합 반환.
 *      fun <T> linkedSetOf(): LinkedHashSet<T>                                 비어있는 LinkedHashSet 형태의 집합 반환.
 *
 *      fun <T> sortedSetOf(comparator: Comparator<in T>, vararg elements: T):  인자로 받은 elements 를 포함하는 SortedSet 형태의 집합 반환.
 *              TreeSet<T>                                                      요소의 정렬 기준으로 Comparator 를 사용.
 *      fun <T> sortedSetOf(vararg elements: T): TreeSet<T>                     인자로 받은 elements 를 포함하는 SortedSet 형태의 집합 반환.
 */
fun main(args: Array<String>) {

    // array.
    val cities = arrayOf("Seoul", "Tokyo", "San Francisco") // type inference.
    val emptyStringArray = emptyArray<String>()
    val nullStoreableArray = arrayOfNulls<String>(3)

    // java primitive type array.
    val chars: CharArray = charArrayOf('a', 'b', 'c', 'd', 'e')
    val ints: IntArray = intArrayOf(0, 1, 2, 3, 4)

    val listOfCountries = listOfNotNull(null) // empty list.
    val listOfCities = listOfNotNull("Seoul", null, "Tokyo", null) // ["Seoul", "Tokyo"]

    // map
    val cities1 = mapOf(Pair("SEO", "Seoul"), Pair("TOK", "Tokyo"), Pair("MTV", "Mountain View"))
    val cities2 = mapOf("SEO" to "Seoul", "TOK" to "Tokyo", "MTV" to "Mountain View")
}