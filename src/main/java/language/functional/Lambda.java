package language.functional;

public class Lambda {
    public static void main(String[] args) {
        //Anonymous inner class
        //NOTE: m will be of type String and lambda function should return void.
        GreetingMessage gm = (m) -> System.out.println(m);
        gm.greetMessage("Say Hello");

        //Method reference.
        Shape1 shp1 = Square::calculateArea;
        System.out.println("Area of sq1 = "+shp1.getArea(new Square(4.0f)));

        Shape2 shp2 = Rectangle::calculateArea;
        System.out.println("Area of rectangle1 = "+ shp2.getArea(new Rectangle(2.5f, 3.3f)));

        Converter c = Square::convert;
        System.out.println("Area of converted Square = "+ c.convertSqToRect(new Square(2.0f)).calculateArea());

    }

    @FunctionalInterface
    interface  GreetingMessage {
        public abstract  void greetMessage(String message);
    }

    @FunctionalInterface
    interface  Shape1 {
        public abstract  Float getArea(Square sq);
    }

    @FunctionalInterface
    interface  Shape2 {
        public abstract  Float getArea(Rectangle rg);
    }

    @FunctionalInterface
    interface  Converter {
        public abstract  Rectangle convertSqToRect(Square sq);
    }


    static class Square {
        Float sideLength;
        public  Square(Float x) {
            this.sideLength = x;
        }

        public Float calculateArea() {
            return sideLength * sideLength;
        }

        public Rectangle convert() {
            return new Rectangle(this.sideLength, this.sideLength);
        }
    }

    static class Rectangle {
        Float length;
        Float width;
        public Rectangle(Float l, Float w) {
            this.length = l;
            this.width = w;
        }

        public Float calculateArea() {
            return this.length * this.width;
        }
    }
}

