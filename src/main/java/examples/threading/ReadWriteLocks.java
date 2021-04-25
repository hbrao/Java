package examples.threading;

import java.util.stream.*;
import java.util.concurrent.locks.*;

import examples.comparator.GenericPoint;

public class ReadWriteLocks {

    public static void main(String[] args) {
        Point p = new Point();
        IntStream.range(1,100)
                 .parallel()
                 .mapToObj( i -> new GenericPoint<Integer,Integer>(i, i))
                 .forEach( gp -> {
                     if ( gp.getX() % 2 == 0 ) p.move(gp.getX(), gp.getY());
                     else System.out.printf(Thread.currentThread().getName() + ": %s \n" , p.distanceFromOrigin());
                 });

        System.out.printf("Distance from origin : %s \n" , p.distanceFromOrigin());
    }

    static class Point {
        private int x;
        private int y;

        StampedLock sl = new StampedLock();

        public void move(Integer dX, Integer dY) {
            Long stamp = sl.writeLock(); //Pessimistic write
            try {
                this.x += dX;
                this.y += dY;
            } finally {
                sl.unlockWrite(stamp);
            }
        }

        public Double distanceFromOrigin() {
            Long stamp = sl.tryOptimisticRead(); //Optimistic read
            Integer lX = this.x, lY = this.y;
            if ( ! sl.validate(stamp) ) {
                stamp = sl.readLock(); // Pessimistic read
                try {
                    lX = this.x ;
                    lY = this.y ;
                } finally {
                    sl.unlockRead(stamp);
                }
            }
            return  Math.sqrt(Math.pow(lX, 2) + Math.pow(lY, 2));
        }
    }
}
