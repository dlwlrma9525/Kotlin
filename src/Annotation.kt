/** Annotation.
 *  코틀린도 자바와 동일하게 어노테이션을 정의하고 사용할 수 있고
 *  선언 형태와 일부 추가된 용법이 있는 것을 제외하면 사용 방법이 대부분 동일.
 *
 *  코틀린의 어노테이션은 멤버를 가질 수 있으며, 주 생성자의 프로퍼티를 정의하는 방식과 유사.
 *  자바의 경우 멤버가 하나이고 이름이 value 이면 값을 바로 대입할 수 있지만 코틀린은 바로 대입 가능.
 *
 *  메타 어노테이션
 *  어노테이션은 부가 정보를 표시하기 위해 별도의 메타 어노테이션 지원.
 *  자바에서 제공하는 메타 어노테이션을 모두 지원하며 표기 방식만 다르며 형태가 유사.
 *
 *  @Foo class Bar @Foo constructor (val param: Any) {
 *
 *      @Foo constructor(param: Any, other: Any) : this(param)
 *      @Foo fun bar(@Foo b: Any) {}
 *  }
 */

@Target(AnnotationTarget.FUNCTION)      // @Target(ElementType.METHOD)
@Retention(AnnotationRetention.RUNTIME) // @Retention(RetentionPolicy.RUNTIME)
@Repeatable                             // @Repeatable(Foo.class)
@MustBeDocumented                       // @Documented
annotation class Label(                 // public @interface Label
        val value: String = "John Doe", // String value() default "John Doe"
        val numbers: IntArray,
        val names: Array<String>
)

/** use-site targets. (어노테이션 적용 대상)
 *  코틀린은 프로퍼티 자체에 어노테이션을 지정하는 것뿐 아니라
 *  자바와 동일하게 프로퍼티를 구성하는 각 요소에 별도로 지정할 수 있도록 어노테이션에 적용 대상을 지정하는 기능을 제공.
 *
 *  어노테이션의 사용 시점 대상은 프로퍼티의 구성요소 외 다른 요소에도 지원.
 *  @file       하나의 소스 파일
 *  @property   하나의 프로퍼티
 *  @get        프로퍼티 내 getter 메서드
 *  @set        프로퍼티 내 setter 메서드
 *  @receiver   리시버
 *  @param      생성자의 매개변수
 *  @setparam   setter 메서드의 매개변수
 *  @delegate   delegate 프로퍼티의 인스턴스를 저장하는 필드
 */
@Target(AnnotationTarget.PROPERTY_SETTER)
annotation class Setter

@Target(AnnotationTarget.PROPERTY_SETTER)
annotation class Wire

@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Param

@Target(AnnotationTarget.FIELD)
annotation class Field

class Object {

    @setparam:Param
    @set:Setter
    @field:Field
    var bar: String = "bar"

    @set:[Setter Wire] // 적용할 어노테이션이 다수이면 대괄호.
    var baz: String = "baz"
}

@Label(
        value = "annotation",
        numbers = [1, 2, 3, 4, 5],
        names = ["Lorem", "ipsum", "dolor", "sit"]
)
fun main(args: Array<String>) {
}