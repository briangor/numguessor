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
		// Generate a random number between 1 and 100
		int number = 1 + (int) (100 * Math.random());

		// Number of attempts
		final int K = 5;

		System.out.println("A number is chosen between 1 and 100.");
		System.out.println("You have " + K + " attempts to guess the correct number");

		// Loop for K attempts
		for (int i = 0; i < K; i++) {
			System.out.print("Enter your guess: ");
			int guess = input.nextInt();

			// Warn if count == 4
			if (i == 3) {
				System.out.println("\n Last chance remaining!");
			}

			// Check conditions
			if (guess == number) {
				System.out.println(" Congratulations! You guessed the correct number.\n");

				// Ask to replay
				// Do you want to play again? (yes/no):

				// Exit if guessed correctly
				return;
			} else if (guess < number) {
				System.out.println(" The number is greater than " + guess);
			} else {
				System.out.println(" The number is less than " + guess);
			}
		}

		System.out.println("\nYou've exhausted all attempts. The correct number was: " + number + "\n");
		// Ask to replay, if yes, go back to the game, if no, end game
		// Do you want to play again? (yes/no):
	}

	public static void guessNumberUnlimitedSteps(Scanner input) {
		// Generate a random number between 1 and 100
		int number = 1 + (int) (100 * Math.random());

		// Track number of attempts
		int attempts = 0;

		// Maximum attempts per round
		int K = 5;
		boolean guessedCorrectly = false;

		System.out.println("A number is chosen between 1 and 100.");
		System.out.println("You have " + K + " attempts per round to guess the correct number.");

		while (!guessedCorrectly) {
			// Give the user K attempts per round
			for (int i = 0; i < K; i++) {
				System.out.print("Enter your guess: ");
				int guess = input.nextInt();
				attempts++; // increment attempt count

				if (guess == number) {
					System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
					guessedCorrectly = true;
					// Ask to replay
					// Do you want to play again? (yes/no):

					break;
				} else if (guess < number) {
					System.out.println("The number is greater than " + guess);
				} else {
					System.out.println("The number is less than " + guess);
				}
			}

			if (!guessedCorrectly) {
				// Ask the user if they want to continue
				// after exhausting K attempts
				System.out.println("You have used all " + K + " attempts.");

				boolean validEndGameInput = false;

				while (!validEndGameInput) {
					System.out.println("Do you want to continue guessing? (yes/no): ");
					String response = input.next();

					if (response.equalsIgnoreCase("yes")) {
						validEndGameInput = true;

					} else if (response.equalsIgnoreCase("no")) {
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