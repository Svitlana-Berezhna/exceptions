package ua.testing;

public class AppPart2CheckedUnchecked {
    public class App {
        public static void main(String[] args) {
            throw new Exception(); // тут ошибка компиляции
        }
    }
//>> COMPILATION ERROR: unhandled exception: java.lang.Exception

    import java.io.IOException;

    public class App {
        public static void main(String[] args) throws IOException {
            throw new Exception(); // тут ошибка компиляции
        }
    }
//>> COMPILATION ERROR: unhandled exception: java.lang.Exception

    public class App {
        public static void main(String[] args) throws Exception { // предупреждаем о Exception
            throw new Exception(); // и кидаем Exception
        }
    }

    public class App {
        public static void main(String[] args) throws Throwable { // предупреждаем "целом" Throwable
            throw new Exception(); // а кидаем только Exception
        }
    }

    public class App {
        public static void main(String[] args) throws Exception { // пугаем
            // но ничего не бросаем
        }
    }

    public class App {
        public static void main(String[] args) {
            f(); // тут ошибка компиляции
        }

        public static void f() throws Exception {
        }
    }
//>> COMPILATION ERROR: unhandled exception: java.lang.Exception

    public class App {
        // они пугают целым Throwable
        public static void main(String[] args) throws Throwable {
            f();
        }
        // хотя мы пугали всего-лишь Exception
        public static void f() throws Exception {
        }
    }

    public class InternetDownloader {
    public static byte[] (String url) throws IOException {
            return "<html><body>Nothing! It's stub!</body></html>".getBytes();
        }
    }

    public class App {
        public static void main(String[] args) {
            f();
        }
        public static void f() throws RuntimeException {
        }
    }

    package java.lang;

    public final class Integer extends Number implements Comparable<Integer> {
        ...
        /**
         * ...
         *
         * @param s    a {@code String} containing the {@code int}
         *             representation to be parsed
         * @return     the integer value represented by the argument in decimal.
         * @exception  NumberFormatException  if the string does not contain a
         *               parsable integer.
         */
        public static int parseInt(String s) throws NumberFormatException {
            return parseInt(s,10);
        }
        ...
    }

    import java.io.EOFException;
    import java.io.FileNotFoundException;

    public class App {
        // пугаем ОБОИМИ исключениями
        public static void main(String[] args) throws EOFException, FileNotFoundException {
            if (System.currentTimeMillis() % 2 == 0) {
                throw new EOFException();
            } else {
                throw new FileNotFoundException();
            }
        }
    }

    import java.io.EOFException;
    import java.io.FileNotFoundException;

    public class App {
        // пугаем ОБОИМИ исключениями
        public static void main(String[] args) throws EOFException, FileNotFoundException {
            f0();
            f1();
        }
        public static void f0() throws EOFException {...}
        public static void f1() throws FileNotFoundException {...}
    }

    import java.io.EOFException;
    import java.io.FileNotFoundException;
    import java.io.IOException;

    public class App {
        // пугаем ПРЕДКОМ исключений
        public static void main(String[] args) throws IOException {
            if (System.currentTimeMillis() % 2 == 0) {
                throw new EOFException();
            } else {
                throw new FileNotFoundException();
            }
        }
    }

    import java.io.EOFException;
    import java.io.FileNotFoundException;

    public class App {
        // пугаем ПРЕДКОМ исключений
        public static void main(String[] args) throws IOException {
            f0();
            f1();
        }
        public static void f0() throws EOFException {...}
        public static void f1() throws FileNotFoundException {...}
    }

    import java.io.EOFException;
    import java.io.FileNotFoundException;

    public class App {
        public static void main(String[] args) throws IOException, InterruptedException {
            f0();
            f1();
            f2();
        }
        public static void f0() throws EOFException {...}
        public static void f1() throws FileNotFoundException {...}
        public static void f2() throws InterruptedException {...}
    }

    public class App {
        public static void main(String[] args) {
            try {
                throw new Exception();
            } catch (Exception e) {
                // ...
            }
        }
    }

    public class App {
        public static void main(String[] args) {
            try {
                throw new Exception();
            } catch (Throwable e) {
                // ...
            }
        }
    }

    public class App {
        public static void main(String[] args) {
            try {
                throw new Throwable();
            } catch (Exception e) {
                // ...
            }
        }
    }
//>> COMPILATION ERROR: unhandled exception: java.lang.Throwable

    public class App {
        public static void main(String[] args) {
            try {
                throw new Exception();
            } catch (Error e) {
                // ...
            }
        }
    }
//>> COMPILATION ERROR: unhandled exception: java.lang.Exception

    import java.io.EOFException;
    import java.io.FileNotFoundException;

    public class App {
        // EOFException перехватили catch-ом, им не пугаем
        public static void main(String[] args) throws FileNotFoundException {
            try {
                if (System.currentTimeMillis() % 2 == 0) {
                    throw new EOFException();
                } else {
                    throw new FileNotFoundException();
                }
            } catch (EOFException e) {
                // ...
            }
        }
    }

    public class App {
        // пугаем Exception
        public static void main(String[] args) throws Exception {
            Throwable t = new Exception(); // и лететь будет Exception
            throw t; // но тут ошибка компиляции
        }
    }
//>> COMPILATION ERROR: unhandled exception: java.lang.Throwable

    public class App {
        public static void main(String[] args) throws Exception {
            Object ref = "Hello!";  // ref указывает на строку
            char c = ref.charAt(0); // но тут ошибка компиляции
        }
    }
//>> COMPILATION ERROR: Cannot resolve method 'charAt(int)'

    // НЕ КОМПИЛИРУЕТСЯ! ИССЛЕДУЕМ ГИПОТЕТИЧЕСКУЮ СИТУАЦИЮ!
    public class App {
        public static void f0(Throwable t) throws Exception {
            throw t;
        }
        public static void f1(Object ref){
            char c = ref.charAt(0);
        }
    }

    public class App {
        // пугаем Exception
        public static void main(String[] args) throws Exception {
            try {
                Throwable t = new Exception(); // и лететь будет Exception
                throw t; // но тут ошибка компиляции
            } catch (Exception e) {
                System.out.println("Перехвачено!");
            }
        }
    }
//>> COMPILATION ERROR: unhandled exception: java.lang.Throwable

    public class App {
        // ТЕПЕРЬ пугаем Throwable
        public static void main(String[] args) throws Throwable {
            try {
                Throwable t = new Exception(); // а лететь будет Exception
                throw t;
            } catch (Exception e) { // и мы перехватим Exception
                System.out.println("Перехвачено!");
            }
        }
    }
//>> Перехвачено!

    import java.io.FileNotFoundException;
    import java.io.IOException;

    public class Parent {
        // предок пугает IOException и InterruptedException
        public void f() throws IOException, InterruptedException {}
    }

    class Child extends Parent {
        // а потомок пугает только потомком IOException
        @Override
        public void f() throws FileNotFoundException {}
    }

    import java.io.IOException;

    public class Parent {
        public void f() throws IOException, InterruptedException {}
    }

    class ChildB extends Parent {
        @Override
        public void f() throws Exception {}
    }
//>> COMPILATION ERROR: overridden method does not throw 'java.lang.Exception'

    public class Parent {
        // предок пугает Exception
        public void f() throws Exception {}
    }

    // тут ошибка компиляции в Java, но, ДОПУСТИМ, ее нет
    public class Child extends Parent {
        // потомок расширил Exception до Throwable
        public void f() throws Throwable {}
    }

    public class Demo {
        public static void test(Parent ref) {
            // тут все компилируется, Parent.f() пугает Exception и мы его ловим catch
            try {
                ref.f();
            } catch(Exception e) {}
        }
    }

    public class App {
        public static void main(String[] args) {
            // тут все компилируется, Demo.test хотел Parent и мы дали ему подтип - Child
            Demo.test(new Child());
        }
    }
}
