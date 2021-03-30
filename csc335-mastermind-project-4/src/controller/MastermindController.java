package controller;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.scene.paint.Color;
import model.MastermindModel;

/**
 * 
 * @author Matthew Gleason
 * Project: Mastermind
 *
 */
public class MastermindController {
	
	private MastermindModel myModel;
	// Only these methods may be public - you may not create any additional 
	// public methods (and NO public fields)  
	
	public MastermindController(MastermindModel model) {
		myModel = model;
	}
 

    public boolean isCorrect(String guess) throws MastermindIllegalLengthException, MastermindIllegalColorException {
    	Set<String> chars = new HashSet<>(Arrays.asList("r", "o", "y", "g", "b", "p"));
    	if (guess.length() != 4) {
    		throw new MastermindIllegalLengthException();
    	}
    	
    	for (int i = 0; i < guess.length(); i++) {
    		if (!(chars.contains(Character.toString(guess.charAt(i))))) {
    			throw new MastermindIllegalColorException();
    		}
    	}
    	
    	for (int i = 0; i < 4; i++) {
    		if (!(myModel.getColorAt(i) == guess.charAt(i))) {
    			return false;
    		}
    	}
    	return true; //Just something for now to make sure the code compiles
    }


    public int getRightColorRightPlace(String guess) throws MastermindIllegalLengthException, MastermindIllegalColorException {
    	Set<String> chars = new HashSet<>(Arrays.asList("r", "o", "y", "g", "b", "p"));
    	if (guess.length() != 4) {
    		throw new MastermindIllegalLengthException();
    	}
    	
    	for (int i = 0; i < guess.length(); i++) {
    		if (!(chars.contains(Character.toString(guess.charAt(i))))) {
    			throw new MastermindIllegalColorException();
    		}
    	}
    	
    	int count = 0;
    	for (int i = 0; i < guess.length(); i++) {
    		if (guess.charAt(i) == myModel.getColorAt(i)) {
    			count++;
    		}
    	}
    	return count; //Just something for now to make sure the code compiles
    }


    public int getRightColorWrongPlace(String guess) throws MastermindIllegalLengthException, MastermindIllegalColorException {
    	Set<String> chars = new HashSet<>(Arrays.asList("r", "o", "y", "g", "b", "p"));
    	if (guess.length() != 4) {
    		throw new MastermindIllegalLengthException();
    	}
    	
    	for (int i = 0; i < guess.length(); i++) {
    		if (!(chars.contains(Character.toString(guess.charAt(i))))) {
    			throw new MastermindIllegalColorException();
    		}
    	}
    	
    	int count = 0;
    	for (int i = 0; i < 4; i++) {
    		char c = myModel.getColorAt(i);
    		if (guess.contains("" + c) && (guess.charAt(i) != c)) {
    			count++;
    		}
    	}
    	return count; //Just something for now to make sure the code compiles  
    } 


	public String convertToString(Color[] colorPos) {
		// TODO Auto-generated method stub
		String retval = "";
		for (Color color: colorPos) {
			if (color.equals(Color.RED)) {
				retval+="r";
			} else if (color.equals(Color.ORANGE)) {
				retval+="o";
			} else if (color.equals(Color.YELLOW)) {
				retval+="y";
			} else if (color.equals(Color.GREEN)) {
				retval+="g";
			} else if (color.equals(Color.BLUE)) {
				retval+="b";
			} else if (color.equals(Color.PURPLE)) {
				retval+="p";
			}
		}
		return retval;
	}
    

}
