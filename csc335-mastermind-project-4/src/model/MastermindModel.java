package model;

import java.util.Random;

/**
 * 
 * @author Matthew Gleason
 * 
 *
 */
public class MastermindModel {
	//private variable(s) to store the answer
	private String[] combo;
	
	// Only these methods may be public - you may not create any additional 
	// public methods (and NO public fields)  
    public MastermindModel() { 
    	// TODO Make the answer  
    	combo = new String[4];
    	String[] storage = {"r", "o", "y", "g", "b", "p"};
    	Random rand = new Random();
    	int upperBound = 6;
    	for (int i = 0; i < combo.length; i++) {
    		int int_random = rand.nextInt(upperBound);
    		combo[i] = storage[int_random];
    	}
    }
    
    /**
     * This method is a special constructor to allow us to use JUnit to test our model.
     * 
     * Instead of creating a random solution, it allows us to set the solution from a 
     * String parameter.  
     * 
     * 
     * @param answer A string that represents the four color solution
     */
    public MastermindModel(String answer) {
    	// TODO Take answer and somehow store it as your answer. Make sure the getColorAt method 
    	// still works
    	combo = new String[4];
    	for (int i = 0; i < answer.length(); i++) {
    		combo[i] = Character.toString(answer.charAt(i));
    	}
    }


    public char getColorAt(int index) {
          /* Return color at position index as a char
           (first converted if stored as a number) */
    	char letter = combo[index].charAt(0);
    	return letter; //Just returning something to make sure the code compiles
    }
    
    // Create as many private methods as you like
    

}
