import com.example.FooUtils;

import java.io.IOException;

/**
 * Java with Kotlin.
 * 자바 코드에서 코틀린 코드로 작성된 클래스, 라이브러리 적용 방법.
 * <p>
 * [프로퍼티의 Getter/Setter 변환]
 * 코틀린에서 생성한 클래스를 자바에서 사용하는 경우, 클래스 내 정의된 프로퍼티는 Getter/Setter 형태로 값을 읽거나 설정.
 * <p>
 * [@JvmOverloads]
 * 코틀린은 함수의 매개변수에 기본 매개변수를 설정할 수 있고 별다른 처리 없이 기본 매개변수가 있는 함수를 자바에서 사용하려면 함수에 모든 인자를 전달해야 한다.
 * 코틀린의 기본 매개변수를 사용하는 함수에 @JvmOverloads 어노테이션을 추가하면 매개변수에 맞게 함수를 여러 벌 생성.(생성자도 동일하게 지원)
 * <p>
 * [@JvmName]
 * 코틀린의 패키지 단위로 선언된 함수 혹은 변수가 자바 코드에서 필요한 경우 해당 함수 및 변수가 선언된 파일의 이름을 사용하여 접근.
 * 패키지 단위 함수 및 변수가 정의된 파일에 @JvmName 어노테이션을 사용하면 코틀린 파일 이름 대신 원하는 이름을 사용할 수 있다.
 * <p>
 * [@JvmField, @JvmStatic, INSTANCE]
 * 자바 코드에서 코틀린 클래스의 동반 객체 내의 항목들에 접근하려면 Class.Companion 과 같이 동반 객체를 명시적으로 호출.
 * 1. 동반 객체 내 항목들을 코틀린 코드에서 사용하는 것과 동일하게 사용하려면 @JvmField, @JvmStatic 어노테이션 적용.(자바의 정적 멤버로 취급)
 * 2. 자바 원시 타입 혹은 문자열 타입의 값이나 변수는 @JvmField 어노테이션 대신 const 키워드를 사용하면 자바 코드에서 정적 필드처럼 취급.
 * <p>
 * 코틀린의 싱글톤 클래스를 자바 코드에서 사용하는 경우, 해당 클래스 내의 변수 및 함수에 접근할 때 INSTANCE 사용.
 * 1. 동반 객체와 마찬가지로 변수는 Getter/Setter 함수를 사용하여 값에 접근.
 * 2. 동반 객체와 마찬가지로 값이나 변수는 const 혹은 @JvmField 어노테이션을, 함수는 @JvmStatic 어노테이션 지원.
 * <p>
 * [@Throws]
 * 코틀린은 Checked exception 을 검사하지 않고 자바로 이러한 예외를 발생시키는 메서드의 throws 문법 미 지원.
 * 코틀린 함수는 예외를 발생시킨다는 것을 명시할 수 없으므로 try~catch 문 처리의 오류가 발생.
 * <p>
 * 따라서 자바 코드에서 코틀린 함수를 호출하면서 예외 처리를 수행하려면 @Throws 어노테이션을 사용하여
 * 이 함수에서 발생시키는 예외의 종류를 자바 컴파일러로 통지.
 */
public class JavaWithKotlin {
    public static void main(String[] args) {
        /**
         * class Developer(val name: String, var address: String, var isAdult: Boolean)
         */
        Developer developer = new Developer("John Doe", "Somewhere", false);

        System.out.println("name: " + developer.getName()
                + " address: " + developer.getAddress()
                + " adult: " + developer.isAdult());

        // name is val (read only)
        // developer.setName("Jane Doe");

        developer.setAddress("Nowhere");
        developer.setAdult(true);

        /**
         * class Developer @JvmOverloads constructor(
         *         val name: String,
         *         var address: String = "",
         *         var isAdult: Boolean = false) {
         *
         *     @JvmOverloads
         *     fun doSomething(a: String, b: Int = 0, c: Boolean = false) {
         *         // TODO
         *     }
         * }
         */

        Developer d1 = new Developer("Lorem ipsum");
        Developer d2 = new Developer("Lorem ipsum", "Somewhere");
        Developer d3 = new Developer("Lorem ipsum", "Somewhere", true);

        d1.doSomething("foo");
        d1.doSomething("foo", 1);
        d1.doSomething("foo", 1, true);

        /**
         * @file:JvmName("FooUtils")
         * package com.example
         *
         * // package-level field
         * const val FOO = 123
         *
         * // package-level function
         * fun foo() {}
         *
         * // package-level class
         * class Foo {
         *     fun bar() {}
         * }
         */
        // int foo = FooKt.FOO;
        // FooKt.foo();

        int foo = FooUtils.FOO;
        FooUtils.foo();

        /**
         * class Developer {
         *     companion object {
         *         @JvmField
         *         val BAR = "bar"
         *
         *         const val foo = "foo"
         *
         *         @JvmStatic
         *         fun baz() {
         *         }
         *     }
         * }
         */
        // String b = Developer.Companion.getBAR();
        // Developer.Companion.baz();

        String b = Developer.BAR;
        String f = Developer.FOO;
        Developer.baz();

        /**
         * object SingtonObject {
         *     @JvmField
         *     val BAR = "bar"
         *
         *     const val FOO = "foo"
         *
         *     @JvmStatic
         *     fun baz() {
         *     }
         * }
         */
        // b = SingtonObject.INSTANCE.getBAR();
        // SingtonObject.INSTANCE.baz();
        b = SingtonObject.FOO;
        f = SingtonObject.FOO;
        SingtonObject.baz();

        /**
         * import java.io.IOException
         *
         * class Developer {
         *     @Throws(IOException::class)
         *     fun doSomething() {
         *         throw IOException()
         *     }
         * }
         */
        try {
            developer.doSomething();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
