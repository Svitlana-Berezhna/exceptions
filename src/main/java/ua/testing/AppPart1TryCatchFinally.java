package ua.testing;

public class AppPart1TryCatchFinally {
    /*    throws:
        Годится*/
    public class App {
        public static void main(String[] args) throws Throwable {
        }
    }

    //    Не годится
    public class App {
        public static void main(String[] args) throws String {
        }
    }

    /*    catch:
        Годится*/
    public class App {
        public static void main(String[] args) {
            try {
            } catch (Throwable t) {
            }
        }
    }

    //    Не годится
    public class App {
        public static void main(String[] args) {
            try {
            } catch (String s) {
            }
        }
    }

    /*    throw:
        Годится*/
    public class App {
        public static void main(String[] args) {
            // Error - потомок Throwable
            throw new Error();
        }
    }

    //    Не годится
    public class App {
        public static void main(String[] args) {
            throw new String("Hello!");
        }
    }

    //    Кроме того, throw требуется не-null аргумент, иначе NullPointerException в момент выполнения
    public class App {
        public static void main(String[] args) {
            throw null;
        }
    }

    public class App {
        public static void main(String[] args) {
            Error ref = new Error(); // создаем экземпляр
            throw ref;               // "бросаем" его
        }
    }

    public class App {
        public static void main(String[] args) {
            f(null);
        }

        public static void f(NullPointerException e) {
            try {
                throw e;
            } catch (NullPointerException npe) {
                f(npe);
            }
        }
    }
//>> RUNTIME ERROR: Exception in thread "main" java.lang.StackOverflowError

    public class App {
        public static void main(String[] args) {
            System.out.println("sout");
            throw new Error();
        }
    }
/*>> RUNTIME ERROR: Exception in thread "main" java.lang.Error
>> sout*/

    public class App {
        public static void main(String[] args) {
            System.out.println("sout");
            throw new Error();
        }
    }
/*>> sout
>> RUNTIME ERROR: Exception in thread "main" java.lang.Error*/

    public class App {
        public double sqr(double arg) { // надо double
            return arg * arg;           // double * double - это double
        }
    }

    public class App {
        public double sqr(double arg) { // надо double
            int k = 1;                  // есть int
            return k;                   // можно неявно преобразовать int в double
        }
    }

    // на самом деле, компилятор сгенерирует байт-код для следующих исходников
    public class App {
        public double sqr(double arg) { // надо double
            int k = 1;                  // есть int
            return (double) k;          // явное преобразование int в double
        }
    }

    public class App {
        public static double sqr(double arg) {
            return "hello!";
        }
    }
//>> COMPILATION ERROR: Incompatible types. Required: double. Found: java.lang.String

    public class App {
        public static double sqr(double arg) {
        }
    }
//>> COMPILATION ERROR: Missing return statement

    public class App {
        public static double sqr(double arg) {
            if (System.currentTimeMillis() % 2 == 0) {
                return arg * arg; // если currentTimeMillis() - четное число, то все ОК
            }
            // а если нечетное, что нам возвращать?
        }
    }
//>> COMPILATION ERROR: Missing return statement

    public class App {
        public static void main(String[] args) {
            double d = sqr(10.0); // ну, и чему равно d?
            System.out.println(d);
        }

        public static double sqr(double arg) {
            // nothing
        }
    }
//>> COMPILATION ERROR: Missing return statement

    public class App {
        public static double sqr(double arg) {
            while (true) ; // Удивительно, но КОМПИЛИРУЕТСЯ!
        }
    }

    public class App {
        public static void main(String[] args) {
            double d = sqr(10.0);  // sqr - навсегда "повиснет", и
            System.out.println(d); // d - НИКОГДА НИЧЕГО НЕ БУДЕТ ПРИСВОЕНО!
        }

        public static double sqr(double arg) {
            while (true) ; // Вот тут мы на века "повисли"
        }
    }

    public class App {
        public static double sqr(double arg) {
            if (System.currentTimeMillis() % 2 == 0) {
                return arg * arg; // ну ладно, вот твой double
            } else {
                while (true) ;    // а тут "виснем" навсегда
            }
        }
    }

    public class App {
        public static double sqr(double arg) {
            throw new RuntimeException();
        }
    }

    public class App {
        public static double sqr(double arg) { // согласно объявлению метода ты должен вернуть double
            long time = System.currentTimeMillis();
            if (time % 2 == 0) {
                return arg * arg;              // ок, вот твой double
            } else if (time % 2 == 1) {
                {
                    while (true) ;                // не, я решил "повиснуть"
                } else{
                    throw new RuntimeException(); // или бросить исключение
                }
            }
        }
    }

    public class App {
        public static void main(String[] args) {
            // sqr - "сломается" (из него "выскочит" исключение),
            double d = sqr(10.0);  // выполнение метода main() прервется в этой строчке и
            // d - НИКОГДА НИЧЕГО НЕ БУДЕТ ПРИСВОЕНО!
            System.out.println(d); // и печатать нам ничего не придется!
        }
        public static double sqr(double arg) {
            throw new RuntimeException(); // "бросаем" исключение
        }
    }
//>> RUNTIME ERROR: Exception in thread "main" java.lang.RuntimeException

//    Задача: реализовать функцию, вычисляющую площадь прямоугольника
    public static int area(int width, int height) {...}

    public static int area(int width, int height) {
        return width * height; // тут просто перемножаем
    }

    public static int area(int width, int height) {
        if (width < 0 || height < 0) {
            // у вас плохие аргументы, извините
        } else {
            return width * height;
        }
    }
//>> COMPILATION ERROR: Missing return statement

    public static int area(int width, int height) {
        if (width < 0 || height < 0) {
            System.out.println("Bad ...");
        }
        return width * height;
    }

    public static int area(int width, int height) {
        if (width < 0 || height < 0) {
            return -1; // специальное "неправильное" значение площади
        }
        return width * height;
    }

    public static int area(int width, int height) {
        if (width < 0 || height < 0) {
            System.exit(0);
        }
        return width * height;
    }

    public static int area(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Negative sizes: w = " + width + ", h = " + height);
        }
        return width * height;
    }

    public class App {
        public static void main(String[] args) {
            // Пример: ОПЕРАТОР ПОСЛЕДОВАТЕЛЬНОСТИ
            int x = 42;    // первый шаг
            int y = x * x; // второй шаг
            x = x * y;     // третий шаг
            ...
        }
    }

    public class App {
        public static void main(String[] args) {
            // Пример: ОПЕРАТОР ВЕТВЛЕНИЯ
            if (args.length > 2) { первый шаг
                // второй шаг или тут
                ...
            } else {
                // или тут
                ...
            }
            // третий шаг
            ...
        }
    }

    public class App {
        public static void main(String[] args) {
            // Пример: ОПЕРАТОР ЦИКЛА do..while
            int x = 1;
            do {
                ...
            } while (x++ < 10);
                ...
        }
    }

    public class App {
        public static void main(String[] args) {
            System.err.println("#1.in");
            f(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println("#1.out"); // вернулись
        } // выходим из текущего фрейма, кончились инструкции

        public static void f() {
            System.err.println(".   #2.in");
            g(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   #2.out");  //вернулись
        } // выходим из текущего фрейма, кончились инструкции

        public static void g() {
            System.err.println(".   .   #3.in");
            h(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   .   #3.out"); // вернулись
        } // выходим из текущего фрейма, кончились инструкции

        public static void h() {
            System.err.println(".   .   .   #4.in");
            if (true) {
                System.err.println(".   .   .   #4.RETURN");
                return; // выходим из текущего фрейма по 'return'
            }
            System.err.println(".   .   .   #4.out"); // ПРОПУСКАЕМ
        }
    }

    public class App {
        public static void main(String[] args) {
            System.err.println("#1.in");
            f(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println("#1.out"); // ПРОПУСТИЛИ!
        }

        public static void f() {
            System.err.println(".   #2.in");
            g(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   #2.out"); // ПРОПУСТИЛИ!
        }

        public static void g() {
            System.err.println(".   .   #3.in");
            h(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
        }

        public static void h() {
            System.err.println(".   .   .   #4.in");
            if (true) {
                System.err.println(".   .   .   #4.THROW");
                throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
            }
            System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
        }
    }

    public class App {
        public static void main(String[] args) {
            System.err.println("#1.in");
            try {
                f(); // создаем фрейм, помещаем в стек, передаем в него управление
            } catch (Error e) { // "перехватили" "летящее" исключение
                System.err.println("#1.CATCH");  // и работаем
            }
            System.err.println("#1.out");  // работаем дальше
        }

        public static void f() {
            System.err.println(".   #2.in");
            g(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   #2.out"); // ПРОПУСТИЛИ!
        }

        public static void g() {
            System.err.println(".   .   #3.in");
            h(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
        }

        public static void h() {
            System.err.println(".   .   .   #4.in");
            if (true) {
                System.err.println(".   .   .   #4.THROW");
                throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
            }
            System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
        }
    }

    public class App {
        public static void main(String[] args) {
            System.err.println("#1.in");
            f(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println("#1.out"); // вернулись и работаем
        }

        public static void f() {
            System.err.println(".   #2.in");
            try {
                g(); // создаем фрейм, помещаем в стек, передаем в него управление
            } catch (Error e) { // "перехватили" "летящее" исключение
                System.err.println(".   #2.CATCH");  // и работаем
            }
            System.err.println(".   #2.out");  // работаем дальше
        }

        public static void g() {
            System.err.println(".   .   #3.in");
            h(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
        }

        public static void h() {
            System.err.println(".   .   .   #4.in");
            if (true) {
                System.err.println(".   .   .   #4.THROW");
                throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
            }
            System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
        }
    }

    public class App {
        public static void main(String[] args) {
            System.err.println("#1.in");
            f(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println("#1.out"); // вернулись и работаем
        }

        public static void f() {
            System.err.println(".   #2.in");
            g(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   #2.out"); // вернулись и работаем
        }

        public static void g() {
            System.err.println(".   .   #3.in");
            try {
                h(); // создаем фрейм, помещаем в стек, передаем в него управление
            } catch (Error e) { // "перехватили" "летящее" исключение
                System.err.println(".   .   #3.CATCH");  // и работаем
            }
            System.err.println(".   .   #3.out");  // работаем дальше
        }

        public static void h() {
            System.err.println(".   .   .   #4.in");
            if (true) {
                System.err.println(".   .   .   #4.THROW");
                throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
            }
            System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
        }
    }

    public class App {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                if (true) {throw new RuntimeException();}
                System.err.print(" 1");
            } catch (Exception e) { // catch по Exception ПЕРЕХВАТЫВАЕТ RuntimeException
                System.err.print(" 2");
            }
            System.err.println(" 3");
        }
    }

    public class App {
        public static void main(String[] args) {
            try {
                throw new RuntimeException();
            } catch (Exception e) {
                if (e instanceof RuntimeException) {
                    RuntimeException re = (RuntimeException) e;
                    System.err.print("Это RuntimeException на самом деле!!!");
                } else {
                    System.err.print("В каком смысле не RuntimeException???");
                }
            }
        }
    }
//>> Это RuntimeException на самом деле!!!

    public class App {
        public static void main(String[] args) throws Exception { // пока игнорируйте 'throws'
            try {
                System.err.print(" 0");
                if (true) {throw new Exception();}
                System.err.print(" 1");
            } catch (RuntimeException e) {
                System.err.print(" 2");
            }
            System.err.print(" 3");
        }
    }
/*>> 0
        >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Exception*/

    public class App {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                if (true) {throw new Error();}
                System.err.print(" 1");
            } catch (Exception e) {
                System.err.print(" 2");
            }
            System.err.print(" 3");
        }
    }
/*>> 0
        >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error*/

    public class App {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                if (true) {throw new RuntimeException();}
                System.err.print(" 1");
            } catch (RuntimeException e) {     // перехватили RuntimeException
                System.err.print(" 2");
                if (true) {throw new Error();} // но бросили Error
            }
            System.err.println(" 3");          // пропускаем - уже летит Error
        }
    }
/*>> 0 2
        >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error*/

    public class App {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                if (true) {throw new RuntimeException();}
                System.err.print(" 1");
            } catch (RuntimeException e) { // перехватили RuntimeException
                System.err.print(" 2");
                if (true) {throw e;}       // и бросили ВТОРОЙ раз ЕГО ЖЕ
            }
            System.err.println(" 3");      // пропускаем - опять летит RuntimeException
        }
    }
/*>> 0 2
        >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.RuntimeException*/

    public class App {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                if (true) {throw new RuntimeException();}
                System.err.print(" 1");
            } catch (RuntimeException e) {     // перехватили RuntimeException
                System.err.print(" 2");
                if (true) {throw new Error();} // и бросили новый Error
            } catch (Error e) { // хотя есть cath по Error "ниже", но мы в него не попадаем
                System.err.print(" 3");
            }
            System.err.println(" 4");
        }
    }
/*>> 0 2
        >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error*/

    public class App {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                if (true) {throw new RuntimeException();}
                System.err.print(" 1");
            } catch (RuntimeException e) { // перехватили RuntimeException
                System.err.print(" 2.1");
                try {
                    System.err.print(" 2.2");
                    if (true) {throw new Error();} // и бросили новый Error
                    System.err.print(" 2.3");
                } catch (Throwable t) {            // перехватили Error
                    System.err.print(" 2.4");
                }
                System.err.print(" 2.5");
            } catch (Error e) { // хотя есть cath по Error "ниже", но мы в него не попадаем
                System.err.print(" 3");
            }
            System.err.println(" 4");
        }
    }
//>> 0 2.1 2.2 2.4 2.5 4

    public class App {
        public static void main(String[] args) {
            try {
            } catch (Exception e) {
            } catch (RuntimeException e) {
            }
        }
    }
//>> COMPILATION ERROR: Exception 'java.lang.RuntimeException' has alredy been caught

    public class App {
        public static void main(String[] args) {
            try {
            } catch (Error e) {
            } catch (RuntimeException e) {
            }
        }
    }

    public class App {
        public static void main(String[] args) {
            try {
                throw new Exception();
            } catch (RuntimeException e) {
                System.err.println("catch RuntimeException");
            } catch (Exception e) {
                System.err.println("catch Exception");
            } catch (Throwable e) {
                System.err.println("catch Throwable");
            }
            System.err.println("next statement");
        }
    }
/*>> catch Exception
>> next statement*/

    public class App {
        public static void main(String[] args) {
            try {
                Throwable t = new Exception(); // ссылка типа Throwable указывает на объект типа Exception
                throw t;
            } catch (RuntimeException e) {
                System.err.println("catch RuntimeException");
            } catch (Exception e) {
                System.err.println("catch Exception");
            } catch (Throwable e) {
                System.err.println("catch Throwable");
            }
            System.err.println("next statement");
        }
    }
/*>> catch Exception
>> next statement*/

    public class App {
        public static void main(String[] args) {
            try {
                System.err.println("try");
            } finally {
                System.err.println("finally");
            }
        }
    }
/*>> try
        >> finally*/

    public class App {
        public static void main(String[] args) {
            try {
                throw new RuntimeException();
            } finally {
                System.err.println("finally");
            }
        }
    }
/*>> finally
        >> Exception in thread "main" java.lang.RuntimeException*/

    public class App {
        public static void main(String[] args) {
            try {
                return;
            } finally {
                System.err.println("finally");
            }
        }
    }
//>> finally

    public class App {
        public static void main(String[] args) {
            try {
                System.exit(42);
            } finally {
                System.err.println("finally");
            }
        }
    }
//>> Process finished with exit code 42

    public class App {
        public static void main(String[] args) {
            try {
                Runtime.getRuntime().exit(42);
            } finally {
                System.err.println("finally");
            }
        }
    }
//>> Process finished with exit code 42

    public class App {
        public static void main(String[] args) {
            try {
                Runtime.getRuntime().halt(42);
            } finally {
                System.err.println("finally");
            }
        }
    }
//>> Process finished with exit code 42

    public class App {
        public static void main(String[] args) {
            try {
                System.err.println("try");
                if (true) {throw new RuntimeException();}
            } finally {
                System.err.println("finally");
            }
            System.err.println("more");
        }
    }
/*>> try
        >> finally
        >> Exception in thread "main" java.lang.RuntimeException*/

    public class App {
        public static void main(String[] args) {
            try {
                System.err.println("try");
                throw new RuntimeException();
            } finally {
                System.err.println("finally");
            }
            System.err.println("more");
        }
    }
//>> COMPILER ERROR: Unrechable statement

    public class App {
        public static void main(String[] args) {
            try {
                System.err.println("try");
                if (true) {return;}
            } finally {
                System.err.println("finally");
            }
            System.err.println("more");
        }
    }
/*>> try
        >> finally*/

    public class App {
        public static void main(String[] args) {
            System.err.println(f());
        }
        public static int f() {
            try {
                return 0;
            } finally {
                return 1;
            }
        }
    }
//>> 1

    public class App {
        public static void main(String[] args) {
            System.err.println(f());
        }
        public static int f() {
            try {
                throw new RuntimeException();
            } finally {
                return 1;
            }
        }
    }
//>> 1

    public class App {
        public static void main(String[] args) {
            System.err.println(f());
        }
        public static int f() {
            try {
                return 0;
            } finally {
                throw new RuntimeException();
            }
        }
    }
//>> Exception in thread "main" java.lang.RuntimeException

    public class App {
        public static void main(String[] args) {
            System.err.println(f());
        }
        public static int f() {
            try {
                throw new Error();
            } finally {
                throw new RuntimeException();
            }
        }
    }
//>> Exception in thread "main" java.lang.RuntimeException

    // open some resource
    try {
        // use resource
    } finally {
        // close resource
    }

    Lock lock = new ReentrantLock();
    ...
    lock.lock();
    try {
        // some code
    } finally {
        lock.unlock();
    }

    public class App {
        public static void main(String[] args) {
            System.err.println(f());
        }
        public static int f() {
            long rnd = System.currenttimeMillis();
            boolean finished = false;
            try {
                if (rnd % 3 == 0) {
                    throw new Error();
                } else if (rnd % 3 == 1) {
                    throw new RuntimeException();
                } else {
                    // nothing
                }
                finished = true;
            } finally {
                if (finished) {
                    // не было исключений
                } else {
                    // было исключение, но какое?
                }
            }
        }
    }

    public class App {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                // nothing
                System.err.print(" 1");
            } catch(Error e) {
                System.err.print(" 2");
            } finally {
                System.err.print(" 3");
            }
            System.err.print(" 4");
        }
    }
//>> 0 1 3 4

    public class App {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                if (true) {throw new Error();}
                System.err.print(" 1");
            } catch(Error e) {
                System.err.print(" 2");
            } finally {
                System.err.print(" 3");
            }
            System.err.print(" 4");
        }
    }
//>> 0 2 3 4

    public class App {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                if (true) {throw new RuntimeException();}
                System.err.print(" 1");
            } catch(Error e) {
                System.err.print(" 2");
            } finally {
                System.err.print(" 3");
            }
            System.err.print(" 4");
        }
    }
/*>> 0 3
        >> RUNTIME ERROR: Exception in thread "main" java.lang.RuntimeException*/

    public class App {
        public static void main(String[] args) {
            if (args.length > 1) {
                if (args.length > 2) {
                    if (args.length > 3) {
                        ...
                    }
                }
            }
        }
    }

    public class App {
        public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; i++) {
                    for (int k = 0; k < 10; k++) {
                        ...
                    }
                }
            }
        }
    }

    public class App {
        public static void main(String[] args) {
            try {
                try {
                    try {
                        ...
                    } catch (Exception e) {
                    } finally {}
                } catch (Exception e) {
                } finally {}
            } catch (Exception e) {
            } finally {}
        }
    }

    public class App {
        public static void main(String[] args) {
            try {
                try {
                    ...
                } catch (Exception e) {
                    ...
                } finally {
                    ...
                }
            } catch (Exception e) {
                try {
                    ...
                } catch (Exception e) {
                    ...
                } finally {
                    ...
                }
            } finally {
                try {
                    ...
                } catch (Exception e) {
                    ...
                } finally {
                    ...
                }
            }
        }
    }

    public class App {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                try {
                    System.err.print(" 1");
                    // НИЧЕГО
                    System.err.print(" 2");
                } catch (RuntimeException e) {
                    System.err.print(" 3"); // НЕ заходим - нет исключения
                } finally {
                    System.err.print(" 4"); // заходим всегда
                }
                System.err.print(" 5");     // заходим - выполнение в норме
            } catch (Exception e) {
                System.err.print(" 6");     // НЕ заходим - нет исключения
            } finally {
                System.err.print(" 7");     // заходим всегда
            }
            System.err.print(" 8");         // заходим - выполнение в норме
        }
    }
//>> 0 1 2 4 5 7 8

    public class App {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                try {
                    System.err.print(" 1");
                    if (true) {throw new RuntimeException();}
                    System.err.print(" 2");
                } catch (RuntimeException e) {
                    System.err.print(" 3"); // ЗАХОДИМ - есть исключение
                } finally {
                    System.err.print(" 4"); // заходим всегда
                }
                System.err.print(" 5");     // заходим - выполнение УЖЕ в норме
            } catch (Exception e) {
                System.err.print(" 6");     // не заходим - нет исключения, УЖЕ перехвачено
            } finally {
                System.err.print(" 7");     // заходим всегда
            }
            System.err.print(" 8");         // заходим - выполнение УЖЕ в норме
        }
    }
//>> 0 1 3 4 5 7 8

    public class App {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                try {
                    System.err.print(" 1");
                    if (true) {throw new Exception();}
                    System.err.print(" 2");
                } catch (RuntimeException e) {
                    System.err.print(" 3"); // НЕ заходим - есть исключение, но НЕПОДХОДЯЩЕГО ТИПА
                } finally {
                    System.err.print(" 4"); // заходим всегда
                }
                System.err.print(" 5");     // не заходим - выполнение НЕ в норме
            } catch (Exception e) {
                System.err.print(" 6");     // ЗАХОДИМ - есть подходящее исключение
            } finally {
                System.err.print(" 7");     // заходим всегда
            }
            System.err.print(" 8");         // заходим - выполнение УЖЕ в норме
        }
    }
//>> 0 1 4 6 7 8

    public class App {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                try {
                    System.err.print(" 1");
                    if (true) {throw new Error();}
                    System.err.print(" 2");
                } catch (RuntimeException e) {
                    System.err.print(" 3"); // НЕ заходим - есть исключение, но НЕПОДХОДЯЩЕГО ТИПА
                } finally {
                    System.err.print(" 4"); // заходим всегда
                }
                System.err.print(" 5");     // НЕ заходим - выполнение НЕ в норме
            } catch (Exception e) {
                System.err.print(" 6");     // не заходим - есть исключение, но НЕПОДХОДЯЩЕГО ТИПА
            } finally {
                System.err.print(" 7");     // заходим всегда
            }
            System.err.print(" 8");         // не заходим - выполнение НЕ в норме
        }
    }
/*>> 0 1 4 7
        >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error*/
}
