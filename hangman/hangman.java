import java.util.Scanner;

public class Hangman {
    private String word;
    private StringBuilder guessedWord;
    private int maxAttempts;
    private int remainingAttempts;
    private StringBuilder guessedLetters;

    public Hangman(String word, int maxAttempts) {
        this.word = word.toLowerCase();
        this.maxAttempts = maxAttempts;
        guessedWord = new StringBuilder(word.length());
        guessedLetters = new StringBuilder();
        remainingAttempts = maxAttempts;

        for (int i = 0; i < word.length(); i++) {
            if (Character.isLetter(word.charAt(i))) {
                guessedWord.append("_");
            } else {
                guessedWord.append(word.charAt(i));
            }
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (remainingAttempts > 0) {
            System.out.println("Word: " + guessedWord);
            System.out.println("Guessed Letters: " + guessedLetters);
            System.out.println("Attempts Remaining: " + remainingAttempts);
            System.out.print("Guess a letter or the whole word: ");
            input = scanner.nextLine().toLowerCase();

            if (input.length() == 1) {
                handleLetterGuess(input.charAt(0));
            } else {
                handleWordGuess(input);
            }

            if (guessedWord.toString().equals(word)) {
                System.out.println("Congratulations! You guessed the word correctly.");
                return;
            }
        }

        System.out.println("You ran out of attempts. The word was: " + word);
    }

    private void handleLetterGuess(char letter) {
        if (guessedLetters.indexOf(Character.toString(letter)) != -1) {
            System.out.println("You already guessed that letter.");
            return;
        }

        guessedLetters.append(letter);
        boolean foundLetter = false;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                guessedWord.setCharAt(i, letter);
                foundLetter = true;
            }
        }

        if (!foundLetter) {
            remainingAttempts--;
            System.out.println("Wrong guess.");
        }
    }

    private void handleWordGuess(String guessedWord) {
        if (guessedWord.equals(word)) {
            this.guessedWord = new StringBuilder(guessedWord);
        } else {
            remainingAttempts--;
            System.out.println("Wrong guess.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Player 1, enter a word: ");
        String word = scanner.nextLine();

        System.out.print("Player 2, enter the maximum number of attempts: ");
        int maxAttempts = scanner.nextInt();

        Hangman hangman = new Hangman(word, maxAttempts);
        hangman.play();
        
    }
}
