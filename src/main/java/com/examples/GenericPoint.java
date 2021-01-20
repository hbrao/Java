package com.examples;

class GenericPoint<X extends Comparable, Y extends Comparable> implements Comparable {
    X x;
    Y y;

    public GenericPoint(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Object o) {
        return x.compareTo(((GenericPoint<X, Y>) o).x);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }
}
