package src;

import java.util.Scanner;

class NumGuessor {
	static final int MIN_NUMBER = 1;
	static final int MAX_NUMBER = 100;
	static final int MAX_ATTEMPTS = 5;

	// Integer.MIN_VALUE used so it can never collide with a real
	// attempt count (positive = win, negative = loss, MIN_VALUE = quit)
	static final int QUIT_SIGNAL = Integer.MIN_VALUE;

	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			boolean running = true;

			while (running) {
				showMainMenu();
				int gameMode = readGameMode(scan);

				switch (gameMode) {
					case 1 -> {
						System.out.println("Game mode selected: Limited steps (" + MAX_ATTEMPTS + ")");
						System.out.print("Please wait ...");
						flushScreen();
						guessNumber(scan);
					}

					case 2 -> {
						System.out.println("Game mode selected: Unlimited steps");
						System.out.print("Please wait ...");
						flushScreen();
						guessNumberUnlimitedSteps(scan);
					}

					case 0 -> {
						System.out.println("Bye!");
						running = false;
					}
				}
			}
		}
	}

	static void showMainMenu() {
		System.out.println("""
				-------------------------------------------------------------------------------
								NumGuessor
				-------------------------------------------------------------------------------
				This is a number guessing game.
				A number will be chosen by the Elder gods and your task is to guess it.
				You can select between the two game modes of limited steps or unlimited steps.
				-------------------------------------------------------------------------------
				""");

		System.out.println("Select the game mode (1 or 2. 0 to quit): ");
		System.out.println("\t1) Limited steps (1 round of " + MAX_ATTEMPTS + " attempts) ");
		System.out.println("\t2) Unlimited steps (unlimited rounds of " + MAX_ATTEMPTS + " attempts each) ");
		System.out.println("\t0) Quit game ");
		System.out.print("> ");
	}

	static int readGameMode(Scanner scan) {
		while (true) {
			if (scan.hasNextInt()) {
				int mode = scan.nextInt();
				scan.nextLine();

				if (mode >= 0 && mode <= 2)
					return mode;
				System.out.println("Invalid Mode: " + mode + ". Please choose 1, 2, or 0.");
			} else {
				System.out.println("Error: '" + scan.next() + "' is not a valid number. Try again.");
			}
			System.out.print("> ");
		}
	}

	static void flushScreen() {
		try {
			Thread.sleep(3000); // Wait for 3000 milliseconds (3 seconds)
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	// ============================================
	// SHARED HELPER
	// ============================================
	/**
	 * Plays one round of up to MAX_ATTEMPTS guesses for the given number
	 * 
	 * @param input  the active Scanner
	 * @param number the secret number the player must guess
	 * @return positive attemptsUsed if the player guessed correctly
	 *         negative attemptsUsed if they exhausted all attempts
	 */
	static int playRound(Scanner input, int number) {
		int attemptsUsed = 0;

		while (attemptsUsed < MAX_ATTEMPTS) {
			if (attemptsUsed == MAX_ATTEMPTS - 1)
				System.out.println("Last chance remaining!");
			System.out.print("Enter your guess: ");

			String rawInput = input.next();
			input.nextLine();

			if (rawInput.equalsIgnoreCase("quit") || rawInput.equalsIgnoreCase("q")) {
				return QUIT_SIGNAL;
			}

			int guess;
			try {
				guess = Integer.parseInt(rawInput);
			} catch (NumberFormatException e) {
				System.out.println(">> '" + rawInput + "' is not a valid number. Try again.\n");
				continue;
			}

			if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
				System.out.println(">> The number is within the range of " + MIN_NUMBER + " to " + MAX_NUMBER
						+ ". You still have " + (MAX_ATTEMPTS - attemptsUsed) + " attempt(s).\n");
				continue;
			}

			attemptsUsed++;

			if (guess == number) {
				return attemptsUsed;
			} else if (guess < number) {
				System.out.println("The number is greater than " + guess + "\n");
			} else {
				System.out.println("The number is less than " + guess + "\n");
			}
		}

		return -attemptsUsed;
	}

	// ==
	// SHARED HELPER: yes/no prompt used by all game modes
	// ==
	static boolean askYesNo(Scanner input, String prompt) {
		while (true) {
			System.out.print(prompt + " (yes/no): ");
			String response = input.nextLine().trim().toLowerCase();
			if (response.equals("yes") || response.equals("y"))
				return true;
			if (response.equals("no") || response.equals("n"))
				return false;
			System.out.println("Invalid input: '" + response + "'. Please enter yes or no.");
		}
	}

	static void guessNumber(Scanner input) {

		boolean playAgain;

		do {
			// Reset game state for a new round
			// Generate a random number between 1 and 100
			int number = MIN_NUMBER + (int) ((MAX_NUMBER - MIN_NUMBER + 1) * Math.random());

			System.out.println("A number is chosen between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
			System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess the correct number");
			System.out.println("To quit and return to the main menu, type 'quit'\n");

			int result = playRound(input, number);

			if (result == QUIT_SIGNAL) {
				System.out.print("\nReturning to the main menu ...");
				flushScreen();
				return;
			}

			if (result > 0) {
				System.out.println("Congratulations! You have guessed it in " + result + " attempt(s).\n");
			} else {
				System.out.println("You've exhausted your attempts. The number was: " + number + "\n");
			}

			playAgain = askYesNo(input, "Do you want to play again?");

			if (playAgain) {
				System.out.print("Please wait ...");
				flushScreen();
			}

		} while (playAgain);

		System.out.print("Thanks for playing! Returning to the main menu ...");
		flushScreen();
	}

	public static void guessNumberUnlimitedSteps(Scanner input) {
		boolean playAgain;

		do {
			int number = MIN_NUMBER + (int) ((MAX_NUMBER - MIN_NUMBER + 1) * Math.random());
			int totalAttempts = 0;
			boolean guessedCorrectly = false;
			boolean playerQuit = false;

			System.out.println("A number is chosen between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
			System.out.println("You have " + MAX_ATTEMPTS + " attempts per round to guess the correct number.");
			System.out.println("To quit and return to the main menu, type 'quit'\n");

			while (!guessedCorrectly) {
				int result = playRound(input, number);

				if (result == QUIT_SIGNAL) {
					System.out.print("\nReturning to the main menu ...");
					playerQuit = true;
					break;
				}

				totalAttempts += Math.abs(result);

				if (result > 0) {
					System.out.println("Congratulations! You have guessed the correct number in "
							+ totalAttempts + " total attempt(s).\n");
					guessedCorrectly = true;
				} else {
					System.out.println("You have used all " + MAX_ATTEMPTS + " attempts in this round.");
					if (!askYesNo(input, "Do you want to continue guessing?")) {
						System.out.println("Game Over! The correct number was: " + number + "\n");
						break;
					}
				}
			}

			if (playerQuit) {
				flushScreen();
				return;
			}

			playAgain = askYesNo(input, "Do you want to play again?");
			if (playAgain) {
				System.out.print("Please wait ...");
				flushScreen();
			}
		} while (playAgain);

		System.out.print("Thanks for playing! Returning to the main menu ...");
		flushScreen();
	}
}