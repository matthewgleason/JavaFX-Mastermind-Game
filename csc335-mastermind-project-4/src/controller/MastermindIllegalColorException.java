package controller;
/**
 * 
 * Creates the exception for when the user inputs a wrong color in the string as their guess.
 * The guess can only include "roygbp".
 * This will call the super class's constructor as the error.
 * 
 * @author Matt Gleason
 *  
 */
@SuppressWarnings("serial")
public class MastermindIllegalColorException extends Exception{
	/**
	 * Constructor of the MastermindIllegalColorException class. Calls the super constructor. 
	 */  
	public MastermindIllegalColorException() {
		super();
	}
}
