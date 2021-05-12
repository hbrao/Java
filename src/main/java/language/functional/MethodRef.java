package language.functional;

import java.math.*;

public class MethodRef {

    public static void main(String[] args) {

        Shape shp1 = (obj , rounding) -> obj.calculateArea(rounding); //Lambda
        Shape shp2 = Square::calculateArea;  //Method reference. this, mc are passed as arguments

        System.out.println("Area of sq1 = " + shp1.getArea(new Square(BigDecimal.valueOf(4.02f)), MathContext.UNLIMITED));
        System.out.println("Area of sq1 = " + shp2.getArea(new Square(BigDecimal.valueOf(4.02f)), MathContext.DECIMAL32));
    }

    @FunctionalInterface
    interface  Shape {
       BigDecimal getArea(Square sq, MathContext mc); // By default this will be public abstract
    }

    static class Square {
        private  BigDecimal sideLength;

        public Square(BigDecimal sideLength) {
            this.sideLength = sideLength;
        }

        public BigDecimal calculateArea(MathContext mc) {
            return this.sideLength.multiply(this.sideLength).round(mc);
        }
    }
}
