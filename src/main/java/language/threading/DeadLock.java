package language.threading;

import java.util.stream.*;

public class DeadLock {
    private final Object lock = new Object();

    private synchronized  void foo() {
        synchronized (lock) {
            System.out.println("Inside foo .....");
        }
    }

    private void bar() {
        synchronized (lock) {
            System.out.println("Inside bar calling foo...");
            foo();
        }
    }

    public static void main(String[] args) {
        DeadLock sol = new DeadLock();
        IntStream.range(0, 100)
                 .parallel()
                 .forEach( i -> {
                     if ( i % 2 == 0 ) sol.foo(); else sol.bar();
                 });
    }
}
