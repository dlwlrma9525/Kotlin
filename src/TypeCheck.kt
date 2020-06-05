package com.example.typecheck
import com.sun.org.apache.xpath.internal.operations.Bool
import java.net.URL

/** Type Check
 *  코틀린에서는 비교 대상이 객체냐 객체의 값이냐를 구분할 필요 없이 모두 == 연산자 사용.
 *
 *  ==  값, 객체의 값 비교
 *  === 객체 자체를 비교
 *
 *  is operator (type check)
 *  자바의 instanceOf 연산자와 동일
 *
 *  as operator (type casting)
 *  자바의 형변환 연산자 대신 코틀린은 as 연산자를 사용하여 자료형을 변환.
 *  val foo: Int = 10.0 as Int
 *
 *  스마트 캐스트
 *  자료형 추론이 가능할 경우 캐스팅 없이 해당하는 자료형으로 객체를 사용할 수 있도록 스마트 캐스트(smart cast) 기능을 지원.
 *  스마트 캐스트는 값을 검사하는 시점과 사용하는 시점 사이에 갑시 변하지 않았다는 것이 보장되는 경우만 지원되기 때문에 var 변수는 스마트 캐스트가 지원되지 않는다.
 *  자료형 추론이나 is 연산자로 타입을 확인하면 스마트 캐스트를 지원.
 */

open class RecyclerView {
    open inner class ViewHolder
}

class CustomRecyclerView : RecyclerView() {
    inner class PhotoHolder : RecyclerView.ViewHolder() {
        fun setImageUrl(url: URL) {

        }
    }

    inner class TextHolder : RecyclerView.ViewHolder() {
        fun setText(message: String) {

        }
    }
}

fun main(args: Array<String>) {
    val a: Pair<Char, Int> = Pair('A', 65)
    val b = a
    val c: Pair<Char, Int> = Pair('A', 65)

    val aEqualsToB: Boolean = a == b
    val aEqualsToC: Boolean = a == c
    println(aEqualsToB)
    println(aEqualsToC)

    val aIdenticalToB: Boolean = a === b
    val aIdenticalToC: Boolean = a === c
    println(aIdenticalToB)
    println(aIdenticalToC)

    printTypeName("Hello Kotlin!")

    val photoHolder = CustomRecyclerView().PhotoHolder()
    val holder = photoHolder as RecyclerView.ViewHolder

    onBindViewHolder(holder, 0)
    onBindViewHolder(CustomRecyclerView().TextHolder(), 0)
}

fun printTypeName(obj: Any) { // Any 는 코틀린의 최상위 클래스 (자바의 Object 클래스와 동일)
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

fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    if (holder is CustomRecyclerView.PhotoHolder) {
        holder.setImageUrl(URL("http://google.com")) // smart cast
        println("smart cast: ViewHolder to PhotoHolder")
    } else if (holder is CustomRecyclerView.TextHolder) {
        holder.setText("Hello") // smart cast
        println("smart cast: ViewHolder to TextHolder")
    }
}