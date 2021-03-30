import java.util.Scanner;

import controller.MastermindController;
import controller.MastermindIllegalColorException;
import controller.MastermindIllegalLengthException;
import model.MastermindModel;

/**
 *         
 * View of the Mastermind Game. All prints and interactions with the user are handled here. 
 * Calls to the controller object and model are made in order to store and retieve the data to be used within the game. 
 * The methods that those objects have allow for searching and checking of this data. There are not attributes or methods besides main()
 * 
 * @author Matthew Gleason  
 *   
 * 
 */
public class MastermindTextView {

	public static void textMain(String[] args) {
		// This class represents the view, it should be how uses play the game
		System.out.println("Welcome to Mastermind!");
		// TODO while the user wants to play:
		Scanner yesNo = new Scanner(System.in);
		System.out.print("Would you like to play? ");
		String answer = yesNo.nextLine();
		System.out.println();
		
		Scanner userGuess = null;
		Scanner playAgain = null;
		if (answer.trim().equals("yes")) {
			// TODO Construct the model (whose constructor builds the secret answer)
			
			while (true) {
				MastermindModel model = new MastermindModel();
				// TODO Construct the controller, passing in the model
				MastermindController controller = new MastermindController(model);
				// TODO Read up to ten user inputs
				boolean correct = false;
				int numGuess = 1;
				
				while (!(correct) && numGuess <= 10) {
					System.out.print("Enter guess number " + numGuess +  ": ");
					userGuess = new Scanner(System.in);
					String guess = userGuess.nextLine();
				
				
					// TODO Check whether or not the input is correct (by asking the controller)
					try {
						if (controller.isCorrect(guess)) {
							correct = true;
							continue;
						}
					} catch (MastermindIllegalLengthException e1) {
						// TODO Auto-generated catch block
						System.out.println("Input is not up to length 4, please try again...");
						System.out.println();
						continue;
					} catch (MastermindIllegalColorException e1) {
						// TODO Auto-generated catch block
						System.out.println("Input uses a color not supported by the system (r, o, y, g, b, p), please try again...");
						System.out.println();
						continue;
					}
					// TODO If not, display the relevant statistics  (by asking the controller)
					try {
						System.out.println("Colors in the correct place: " + 
								controller.getRightColorRightPlace(guess));
					} catch (MastermindIllegalLengthException e) {
						// TODO Auto-generated catch block
						System.out.println("Input is not up to length 4, please try again...");
						System.out.println();
						continue;
					} catch (MastermindIllegalColorException e) {
						// TODO Auto-generated catch block
						System.out.println("Input uses a color not supported by the system (r, o, y, g, b, p), please try again...");
						System.out.println();
						continue;
					}
					try {
						System.out.println("Colors correct but in wrong position: " + 
								controller.getRightColorWrongPlace(guess));
					} catch (MastermindIllegalLengthException e) {
						// TODO Auto-generated catch block
						System.out.println("Input is not up to length 4, please try again...");
						System.out.println();
						continue;
					} catch (MastermindIllegalColorException e) {
						// TODO Auto-generated catch block
						System.out.println("Input uses a color not supported by the system (r, o, y, g, b, p), please try again...");
						System.out.println();
						continue;
					}
					System.out.println();
					
					numGuess++;
				}
				// TODO Determine win or loss
				if (correct) {
					System.out.println("You won!");
				} else {
					System.out.println("You lost...");
				}
				System.out.print("Play again?: ");
				playAgain = new Scanner(System.in);
				String wantsToPlay = playAgain.next();
				if (wantsToPlay.trim().equals("no")) {
					break;
				}
				
			}
				
		} else {
			System.out.println("Did not want to play");
		}
		if (userGuess != null) { userGuess.close(); }
		if (playAgain != null) { playAgain.close(); }
		yesNo.close();
		
		

	}

}

