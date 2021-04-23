package examples.threading;

import java.util.Deque;
import java.util.LinkedList;

class MyThreadPool {
    private Deque<Thread> queue = new LinkedList<>();

    public  MyThreadPool(Integer poolSize) {

    }

    private Runnable take() {
        return null;
    }

    public void submit(Runnable task) {

    }
}