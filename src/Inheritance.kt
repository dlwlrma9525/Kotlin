/** inheritance and implements.
 *  자바는 클래스의 상속과 인터페이스의 구현을 extends 와 implements 키워드로 구분하지만
 *  코틀린은 클래스의 상속과 인터페이스의 구현 모두 : 으로 표기.
 */

// open
// 코틀린은 open 키워드를 붙힌 클래스나 함수가 아니라면 클래스를 상속하거나 함수, 프로퍼티를 재정의할 수 없다.
open class Context

class AttributeSet

open class View() {

    constructor(context: Context) : this()
    constructor(context: Context, attributeSet: AttributeSet) : this()

    fun setOnClickListener(listener: (View) -> Unit) {}
    fun setOnClickListener(listener: View.OnClickListener) {}

    interface OnClickListener {
        fun onClick(v: View)
    }
}

// inheritance
// 클래스를 상속하는 경우 반드시 부모 클래스의 생성자를 호출해야 한다.
// class AppCompatActivity : Context()
open class AppCompatActivity : Context() {
    open val TAG: String = AppCompatActivity::class.toString()
    open fun onCreate() {}
}

class MainActivity : AppCompatActivity() {

    override val TAG: String = MainActivity::class.toString()

    // 자바는 재정의한 메서드나 인터페이스를 구현한 메서드를 @Override 어노테이션으로 구분.
    // 코틀린은 상속받거나 구현한 함수 앞에 무조건 override 키워드를 추가하도록 강제.
    override fun onCreate() {
        super.onCreate()

        val button = Button(this)
        button.setOnClickListener(object : View.OnClickListener { // instantiate anonymous interface
            override fun onClick(v: View) {
                // this
                // this 키워드는 해당 키워드를 사용한 클래스 자신을 지칭.
                // this 키워드를 단독으로 사용한 것은, 해당 위치에서 가장 가까운 범위의 클래스 자신을 의미.
                // 따라서 클래스 내에서 다른 클래스나 인터페이스의 인스턴스를 동적으로 생성하여 사용하는 경우 키워드를 사용하는 위치에 따라 this 가 의미하는 클래스가 달라질 수 있다.
                //
                // 클래스를 명시하려면 this@{클래스 이름}의 형태로 표기.
                Toast.makeText(this@MainActivity, "click!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

class Button : View {

    // 부모 클래스의 생성자가 여러 형태일 경우, 클래스 선언부에 부모 클래스의 생성자를 호출하지 않고
    // 별도의 생성자 선언에서 super 키워드로 부모 클래스의 생성자를 호출하도록 구현.
    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context)
}

class Toast private constructor() {
    companion object {
        const val LENGTH_SHORT: Int = 50
        const val LENGTH_LONG: Int = 100

        fun makeText(context: Context, message: String, duration: Int): Toast {
            return Toast()
        }
    }

    fun show() {}
}

fun main(args: Array<String>) {}