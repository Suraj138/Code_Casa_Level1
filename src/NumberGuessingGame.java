import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int rounds = 3;  // Number of rounds
        int totalScore = 0;

        System.out.println("Welcome to the Number Guessing Game..! ");

        for (int round = 1; round <= rounds; round++) {
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;

            System.out.println("\nRound " + round + ": Guess the number between " + lowerBound + " and " + upperBound);

            while (true) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    int roundScore = calculateScore(attempts);
                    totalScore += roundScore;
                    System.out.println("Round Score: " + roundScore);
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                // Check if the maximum number of attempts is reached
                if (attempts == 5) {
                    System.out.println("Sorry, you've reached the maximum number of attempts. The number was: " + targetNumber);
                    break;
                }
            }
        }

        System.out.println("\nGame Over! Total Score: " + totalScore);
        scanner.close();
    }

    private static int calculateScore(int attempts) {
        // Example scoring logic: fewer attempts result in a higher score
        if (attempts <= 3) {
            return 10;
        } else if (attempts <= 5) {
            return 5;
        } else {
            return 2;
        }
    }
}
