package language.functional;

public class Lambda {
    public static void main(String[] args) {
        //Anonymous inner class
        //NOTE: m will be of type String and lambda function should return void.
        GreetingMessage gm = (m) -> System.out.println(m);
        gm.greetMessage("Say Hello");
    }

    @FunctionalInterface
    interface  GreetingMessage {
        public abstract  void greetMessage(String message);
    }
}

