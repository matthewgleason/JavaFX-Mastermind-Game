import java.util.Scanner;

public class Mastermind {
	public static void main(String[] args) {
		Scanner yesNo = new Scanner(System.in);
		System.out.print("Would you like to play (gui) or (text-ui)? ");
		String answer = yesNo.nextLine();
		if (!(answer.equals("gui"))) {
			MastermindTextView.textMain(args);
		} else {
			MastermindGUIView.guiMain(args);
		}
	}
}
