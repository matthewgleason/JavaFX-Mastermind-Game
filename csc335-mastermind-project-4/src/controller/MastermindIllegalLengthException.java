package controller;

/**
 *   
 * Creates the exception for when the user inputs the wrong length of a string as their guess.
 * This will call the super class's constructor as the error.
 * 
 * @author Matt Gleason
 *
 */
@SuppressWarnings("serial")
public class MastermindIllegalLengthException extends Exception {
	/**
	 * Constructor of the MastermindIllegalLengthException class. Calls the super constructor.   
	 */
	public MastermindIllegalLengthException() {
		super();
	}
}