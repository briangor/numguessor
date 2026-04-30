import java.util.Scanner;

class NumGuessor {
	public static void main(String[] args) {
		guessNumber();
	}

	public static void guessNumber() {
		try (Scanner scanner = new Scanner(System.in)) {
			// Generate a random number between 1 and 100
			int number = 1 + (int)(100 * Math.random());

			// Number of attempts
			final int K = 5;

			System.out.println("A number is chosen between 1 and 100.");
			System.out.println("You have " + K + " attempts to guess the correct number");

			// Loop for K attempts
			for (int i = 0; i < K; i++) {
				System.out.print("Enter your guess: ");
				int guess = scanner.nextInt();

				// Warn if count == 4
				if (i == 3) {
					System.out.println("\n Last chance remaining!");
				}

				// Check conditions 
				if (guess == number) {
					System.out.println(" Congratulations! You guessed the correct number.\n");

					// Exit if guessed correctly
					return;
				} else if ( guess < number) {
					System.out.println(" The number is greater than " + guess);
				} else {
					System.out.println(" The number is less than " + guess);
				}
			}

			System.out.println("\nYou've exhausted all attempts. The correct number was: " + number + "\n");
		} 
	}
}