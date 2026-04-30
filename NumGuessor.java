import java.util.Scanner;

class NumGuessor {
	public static void main(String[] args) {
		String gameIntroText = """
				-------------------------------------------------------------------------------
								NumGuessor
				-------------------------------------------------------------------------------
				This is a number guessing game.
				A number will be chosen by the Elder gods and your task is to guess it.
				You can select between the two game modes of limited steps or unlimited steps.
				-------------------------------------------------------------------------------
				""";
		System.out.println(gameIntroText);

		System.out.println("Select the game mode (1 or 2. 0 to quit): ");
		System.out.println("\t1) Limited steps (1 round of 5 attempts) ");
		System.out.println("\t2) Unlimited steps (unlimited rounds of 5 attempts each) ");
		System.out.println("\t0) Quit game ");
		System.out.print("> ");

		try (Scanner scan = new Scanner(System.in)) {
			int gameMode = 0;
			boolean isValid = false;

			while (!isValid) {
				if (scan.hasNextInt()) {
					gameMode = scan.nextInt();
					// scan.nextLine(); // consumes leftover "Enter" keypress

					if (gameMode == 1) {
						System.out.println("Game mode selected: Limited steps (5)");
						System.out.println("Please wait ...");
						flushScreen();
						guessNumber(scan);
						isValid = true;
					} else if (gameMode == 2) {
						System.out.println("Game mode selected: Unlimited steps");
						System.out.println("Please wait ...");
						flushScreen();
						guessNumberUnlimitedSteps(scan);
						isValid = true;
					} else if (gameMode == 0) {
						// Quit game
						System.out.println("Bye");
						return;

					} else {
						System.out.println("Invalid Mode: " + gameMode + ". Please choose 1 or 2.");
						System.out.print("> ");
					}

				} else {
					String invalidInput = scan.next();
					System.out.println("Error: '" + invalidInput + "' is not a valid number. Try again.");
					System.out.print("> ");
				}
			}

		}
	}

	public static void flushScreen() {
		try {
			Thread.sleep(3000); // Wait for 3000 milliseconds (3 seconds)
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void guessNumber(Scanner input) {

		boolean playAgain = false;

		do {
			// Reset game state for a new round
			// Generate a random number between 1 and 100
			int number = 1 + (int) (100 * Math.random());

			// Number of attempts
			final int MAX_ATTEMPTS = 5;
			int attemptsUsed = 0;

			boolean won = false;

			System.out.println("A number is chosen between 1 and 100.");
			System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess the correct number");

			// The guessing loop: runs until attempts are exhausted OR the user wins
			while (attemptsUsed < MAX_ATTEMPTS) {
				if (attemptsUsed == MAX_ATTEMPTS - 1)
					System.out.println("Last chance remaining!");

				System.out.print("Enter your guess: ");

				String rawInput = input.next();
				input.nextLine();

				int guess;

				try {
					guess = Integer.parseInt(rawInput);
				} catch (NumberFormatException e) {
					System.out.println(">> '" + rawInput + "' is not a valid number. Try again.\n");
					continue;
				}

				if (guess == number) {
					System.out.println("Congratulations! You guessed it.\n");
					won = true;
					attemptsUsed++;
					break;
				} else if (guess > 100 || guess < 0) {
					System.out.println(">> The number is within the range of 0 to 100. You still have "
							+ (MAX_ATTEMPTS - attemptsUsed) + " attempts.\n");
					continue;
				} else if (guess < number) {
					System.out.println("The number is greater than " + guess + "\n");
				} else {
					System.out.println("The number is less than " + guess + "\n");
				}
				attemptsUsed++;
			}

			if (!won) {
				System.out.println("You've exhausted your attempts. The number was: " + number);
			}

			boolean validEndGameInput = false;

			while (!validEndGameInput) {

				System.out.print("Do you want to play again? (yes/no): ");
				String response = input.nextLine().trim().toLowerCase();

				if (response.equals("yes") || response.equals("y")) {
					System.out.println("Please wait ...");
					flushScreen();
					validEndGameInput = true;
					playAgain = true;
				} else if (response.equals("no") || response.equals("n")) {
					validEndGameInput = true;
					playAgain = false;
				} else {
					System.out.println("Invalid input: '" + response + "'.");
				}
			}
		} while (playAgain);

		System.out.println("Thanks for playing!");

	}

	public static void guessNumberUnlimitedSteps(Scanner input) {
		// Generate a random number between 1 and 100
		int number = 1 + (int) (100 * Math.random());

		// Track number of attempts
		int attempts = 0;

		// Maximum attempts per round
		int K = 5;
		// final int MAX_ATTEMPTS = 5; // use this var
		boolean guessedCorrectly = false;

		System.out.println("A number is chosen between 1 and 100.");
		System.out.println("You have " + K + " attempts per round to guess the correct number.");

		while (!guessedCorrectly) {
			int roundAttempts = 0;

			// Give the user K attempts per round
			while (roundAttempts < K) {
				if (roundAttempts == K - 1)
					System.out.println("Last chance in this round!");

				System.out.print("Enter your guess: ");

				String rawInput = input.next();
				input.nextLine();

				int guess;

				try {
					guess = Integer.parseInt(rawInput);
				} catch (NumberFormatException e) {
					System.out.println(">> '" + rawInput + "' is not a valid number. Try again.\n");
					continue;
				}

				if (guess > 100 || guess < 0) {
					System.out.println(">> The number is within the range of 0 to 100. You still have "
							+ (K - roundAttempts) + " attempts in this round.\n");
					continue;
				}

				attempts++;
				roundAttempts++;

				if (guess == number) {
					System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
					guessedCorrectly = true;
					break;
				} else if (guess < number) {
					System.out.println("The number is greater than " + guess + "\n");
				} else {
					System.out.println("The number is less than " + guess + "\n");
				}
			}

			if (!guessedCorrectly) {
				// Ask the user if they want to continue
				// after exhausting K attempts
				System.out.println("You have used all " + K + " attempts.");

				boolean validEndGameInput = false;

				while (!validEndGameInput) {
					System.out.println("Do you want to continue guessing? (yes/no): ");
					String response = input.nextLine().trim().toLowerCase();

					if (response.equals("yes") || response.equals("y")) {
						validEndGameInput = true;

					} else if (response.equals("no") || response.equals("n")) {
						System.out.println("Game Over! The correct number was: " + number);

						return; // Or use System.exit(0) to stop the program entirely.
					} else {
						System.out.println("Invalid input: '" + response + "'.");
					}
				}
			}

		}
	}
}

/*
 * TODO:
 * - allow the user to navigate between game modes after the game finishes
 * 
 * 
 */

/*
 * # FEATURES TO BE IMPLEMENTED LATER
 * - high score feature that tracks how many rounds the user has won
 * - Add a Score: Count how many attempts it took to win and display a "Rank"
 * (e.g., 1 attempt = "God Mode", 5 attempts = "Close Call").
 * - difficulty setting that changes the value of K/MAX_ATTEMPTS eg. 1-100 or
 * 1-1000.
 * - Implementing a hint system (e.g., "You're getting warmer!").
 * 
 * ++
 * - Adding a timer to see how fast the user can guess.
 * - Saving a high score to a file so it persists even after the program closes.
 */