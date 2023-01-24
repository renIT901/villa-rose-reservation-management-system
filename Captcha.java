import java.util.Scanner;
import java.util.Random;

public class Captcha {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 6;

    public static void main(String[] args) {
        // Generate the random alphanumeric string
        String captcha = generateRandomString(LENGTH);

        // Display the generated string
        System.out.println("Captcha: " + captcha);

        // Prompt the user to enter the string
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the captcha: ");
        String userInput = scanner.nextLine();

        // Compare the user input with the generated string
        if (captcha.equals(userInput)) {
            System.out.println("Access granted!");
        } else {
            System.out.println("Access denied!");
        }
    }

    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
