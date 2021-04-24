package examples.threading;

import java.util.*;
import java.util.stream.*;

public class ThreadPool {
    private static Integer nextGroupId = 1;
    final private List<Runnable> runQueue = new LinkedList<>();
    final private ThreadGroup grp;

    public ThreadPool(Integer poolSize) {
        Integer groupId ;
        synchronized (ThreadPool.class) {
            groupId = nextGroupId ++;
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
        grp.stop();
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
            while(true) {
                try {
                    take().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}