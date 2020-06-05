/** Generics.
 *  제네릭은 인자로 사용하는 타입에 따라 구체화되는 클래스 또는 인터페이스를 의미.
 *  제네릭을 사용하면 Any 타입을 사용하는 것보다 타입 안정성을 보장.
 */
open class Car

class Sedan : Car()
class SUV : Car()

interface Container<T : Car> { // 제네릭 타입 T는 Car 타입 또는 Car 의 자식타입으로 한정.
    fun put(item: T)
    fun take(): T
}

class Garage : Container<Car> {
    private var item: Car? = null

    override fun put(item: Car) {
        this.item = item
    }

    override fun take(): Car {
        return item ?: throw IllegalStateException()
    }
}

class SedanGarage : Container<Sedan> {
    private var item: Sedan? = null

    override fun put(item: Sedan) {
        this.item = item
    }

    override fun take(): Sedan {
        return item ?: throw IllegalStateException()
    }
}

// in T     ? super T       T 및 T의 부모 타입
// out T    ? extends T     T 및 T의 자식 타입
fun <T> append(dest: MutableList<in T>, src: List<out T>) {
    dest.addAll(src)
}

fun main(args: Array<String>) {
    val names: List<String>
    val entries: Map<String, String>

    // 제네릭 클래스에 타입을 넣지 않고 선언 가능한 자바와 달리 코틀린은 반드시 <T>의 제네릭 타입을 지정.
    // val list: List

    val sedans: List<Sedan> = listOf()
    val suvs: List<SUV> = listOf()
    val cars: MutableList<Car> = mutableListOf()

    append(cars, sedans)
    append(cars, suvs)
}