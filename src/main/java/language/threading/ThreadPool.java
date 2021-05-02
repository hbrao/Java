package language.threading;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class ThreadPool {
    private static final AtomicInteger nextGroupId = new AtomicInteger(1);
    private final ThreadGroup grp;
    private final List<Runnable> runQueue = new LinkedList<>();
    private volatile Boolean running = true;

    public ThreadPool(Integer poolSize) {
        Integer groupId ;
        synchronized (ThreadPool.class) {
            groupId = nextGroupId.incrementAndGet();
        } 
        grp = new ThreadGroup("Group "+ groupId);
        IntStream.range(1, poolSize + 1).forEach( workerId -> {
            new Worker(grp, groupId + "-" + workerId).start();
        });
    }

    private Runnable take() throws InterruptedException {
        synchronized (runQueue) {
            while( runQueue.isEmpty() ) runQueue.wait();
            return runQueue.remove(0);
        }
    }

    public void submit(Runnable task) {
        synchronized (runQueue) {
            runQueue.add(task);
            runQueue.notifyAll();
        }
    }

    public void shutdown() {
        //Set running to false to handle case when an alien task submitted swallows the interrupt.
        running = false;
        grp.interrupt();
    }

    public Integer getRunQueueLength() {
        synchronized (runQueue) {
            return runQueue.size();
        }
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup grp, String name) {
            super(grp, name);
        }

        public void run() {
            while(running) {
                try {
                    take().run();
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
}