package examples;

public class Lambda {
    public static void main(String[] args) {
        //Anonymous inner class
        //NOTE: m will be of type String and lambda function should return void.
        GreetingMessage gm = (m) -> System.out.println(m);
        gm.greetMessage("Say Hello");

        //Method reference.
        //NOTE: calculateArea argument and return type should match 
        //      with functional interface method.
        Shape sq1 = Square::calculateArea;
        System.out.println("Area of sq1 = "+sq1.getArea(new Square(4.0f)));
    }

    @FunctionalInterface
    interface  GreetingMessage {
        public abstract  void greetMessage(String message);
    }

    @FunctionalInterface
    interface  Shape {
        public abstract  Float getArea(Square sq);
    }

    static class Square {
        Float sideLength;
        public  Square(Float x) {
            this.sideLength = x;
        }

        public Float calculateArea() {
            return sideLength * sideLength;
        }
    }
}

