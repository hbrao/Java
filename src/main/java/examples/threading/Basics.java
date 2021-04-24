package examples.threading;

import java.util.*;
import java.util.stream.*;
import java.util.concurrent.locks.*;

public class Basics {

    public static void main(String[] args) {
        Basics sol = new Basics();

        //Simple thread
        sol.startThread();

        //Scheduled task
        sol.scheduledThread(1000L, 2 * 1000L);

        //ThreadGroup park / un park.
        ThreadGroup grp = sol.startThreadGroup();
        Thread[] threads = new Thread[grp.activeCount()];
        grp.enumerate(threads);
        for(Thread t : threads) {
            LockSupport.unpark(t);
        }
    }

    public void startThread() {
        new Thread( new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName() + " started execution..");
            }
        }).start();
    }

    public void scheduledThread(Long delay, Long period) {
        TimerTask task = new TimerTask() { // Factory method run()
            @Override
            public void run() {
                System.out.println("Timed task running at " + System.currentTimeMillis());
            }
        };

        Timer t = new Timer();
        t.schedule(task, delay, period);
    }

    public ThreadGroup startThreadGroup() {
        ThreadGroup grp = new ThreadGroup("TG01");

        IntStream.range(1,6).boxed().forEach( val -> {
            new Thread(grp, () -> {
                System.out.printf("T%s: ..... %n", val);
                LockSupport.park();
                System.out.printf("T%s: un parked ! %n", val);
            }, val.toString()).start();
        });

        return grp;
    }
}
