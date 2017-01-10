import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Perry on 1/9/2017.
 */
public class GuessingGame {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Reader read = new Reader();
        ArrayList<String> words = read.readFile(
                "ENTER-FILE-PATH-HERE"
        );

        WordTracker gameState = new WordTracker(words.get((int) (Math.random() * words.size())));

        System.out.println("Welcome to Guess the Word! Enter anything to begin.");
        scan.nextLine();

        System.out.println("The game is about to start! Remember, you can enter a number");
        System.out.println("to exit the game at anytime.");
        System.out.println();
        gameState.delay(2);

        System.out.format("Your word is %d characters long.", gameState.getWord().length());
        System.out.println();

        while (gameState.gameContinues()) {
            System.out.print("Enter your guess: ");
            String guess = scan.nextLine();
            System.out.println();

            if (gameState.isNumber(guess)) {
                break;
            }
            if (gameState.repeatedGuess(guess)) {
                System.out.println("You already guessed that!");
            } else {
                int index = gameState.isCorrect(guess);
                if (index != -1) {
                    System.out.println("Correct!");
                } else {
                    gameState.increaseIncorrect();
                    if (!gameState.gameContinues()) {
                        break;
                    } else {
                        System.out.println("Try again!");
                    }
                }
                System.out.println(gameState.getSoFar());
            }
        }

        if (gameState.win()) {
            System.out.println("You win!");
        } else if (gameState.lose()) {
            System.out.println("Sorry, you ran out of guesses. Maybe next time!");
            System.out.format("Your word was %s", gameState.getWord());
        } else {
            System.out.println("Come back next time!");
        }
    }
}
