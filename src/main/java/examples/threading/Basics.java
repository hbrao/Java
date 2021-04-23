package examples.threading;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.LockSupport;

public class Basics {

    public static void main(String[] args) {
        new Basics().scheduledThread(1000L);
    }

    public void parkUnPark(Long millis) {
        Thread t = new Thread( () -> {
            System.out.println("Before parking thread :"+ Thread.currentThread().getName());
            LockSupport.park();
            System.out.println("Un parked !");
        });

        t.start();

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LockSupport.unpark(t);
    }

    public void scheduledThread(Long millis) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task started at " + DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()));
            }
        };

        Timer t = new Timer();
        t.schedule(task, millis, millis);

    }
}
