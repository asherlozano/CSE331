package setup;
import java.util.Random;


/** RandomHello selects and prints a random greeting. */
public class RandomHello {

    /**
     * Prints a random greeting to the console.
     *
     * @param args command-line arguments (ignored)
     */
    public static void main(String[] args) {
        RandomHello randomHello = new RandomHello();
        System.out.println(randomHello.getGreeting());
    }

    /** returns a greeting, randomly chosen from five possibilities */
    public String getGreeting() {
        Random randomFive =new Random();
        String[] greeting = new String[5];
        greeting[0] = "Hello World";
        greeting[1] = "Hola Mundo";
        greeting[2] = "Bonjour Monde";
        greeting[3] = "Hallo Welt";
        greeting[4] = "Ciao Mondo";
        return greeting[randomFive.nextInt(5)];

    }
}
