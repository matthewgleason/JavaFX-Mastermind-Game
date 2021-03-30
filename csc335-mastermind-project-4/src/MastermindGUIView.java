
import java.util.ArrayList;
import java.util.List;

import controller.MastermindController;
import controller.MastermindIllegalColorException;
import controller.MastermindIllegalLengthException;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import javafx.stage.Stage;
import model.MastermindModel;

public class MastermindGUIView extends Application {
	
	final int WIDTH = 400;
	final int HEIGHT = 600;
	GridPane bottomPane;
	VBox vboxHistory;
	Integer counter = 0;
	MastermindModel model = new MastermindModel();
	MastermindController controller = new MastermindController(model);

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane = new BorderPane();
		
		BackgroundFill backgroundFill = new BackgroundFill(Color.TAN, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(backgroundFill);
		pane.setBackground(background);
		vboxHistory = new VBox();
		pane.setCenter(vboxHistory);
		
		bottomPane = new GridPane();
		bottomPane = (GridPane) setBottom(bottomPane);
		pane.setBottom(bottomPane);
		
		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.setTitle("Mastermind");
		stage.show();
		
	}

	private GridPane setBottom(GridPane pane) {
		// TODO Auto-generated method stub
		
		BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(backgroundFill);
		pane.setBackground(background);
		
		for (int i = 0; i < 5; i++) {
			if (i < 4) {
				
				Circle cir = new Circle(20, Color.BLACK);
				cir.setOnMouseClicked((event) -> {
					List<Color> colors = new ArrayList<>();
					colors.add(Color.RED);
					colors.add(Color.ORANGE);
					colors.add(Color.YELLOW);
					colors.add(Color.GREEN);
					colors.add(Color.BLUE);
					colors.add(Color.PURPLE);
					
					Paint currColor = cir.getFill();
					if (currColor.equals(Color.BLACK) || currColor.equals(Color.PURPLE)) {
						cir.setFill(colors.get(0));
					} else {
						cir.setFill(colors.get(colors.indexOf(currColor) + 1));
					}
					
				});
				pane.addColumn(i, cir);
				
			} else {
				Button button = new Button();
				button.setText("Guess");
				button.setOnAction((event) -> {
					GridPane copy = new GridPane();
					
					
					Label label = new Label();
					label.setStyle("-fx-font: 20 arial;");;
					label.setText(Integer.toString(counter));
					
					// creates label for History Panel
					copy.addColumn(0, label);
					ColumnConstraints col = new ColumnConstraints();
					col.setPercentWidth(20);
					col.setHalignment(HPos.CENTER);
					copy.getColumnConstraints().add(col);
					
					// get color from bottom nodes and switch into new Pane
					Color[] colorPos = new Color[4];
					int index = 0;
					for (Node child: bottomPane.getChildren()) {
						if (index == 4) {
							break;
						}
						colorPos[index] = (Color) ((Shape) child).getFill();
						index++;
					}
					int count = 1;
					int colorCount = 0;
					for (int j = 0; j < 4; j++) {
						// creates circles in History Panel
						
						// Sets off alert if one node is black still
						if (colorPos[colorCount].equals(Color.BLACK)) {
							Alert a = new Alert(Alert.AlertType.INFORMATION);
							a.setTitle("Color Error");
							a.setContentText("Please make sure that all circles are colored in.");
							a.setHeaderText("One Color is still Black");
							a.showAndWait();
							return;
						}
						Circle cir = new Circle(20, colorPos[colorCount]);
						
						ColumnConstraints col1 = new ColumnConstraints();
						col1.setPercentWidth(20);
						col1.setHalignment(HPos.CENTER);
						copy.getColumnConstraints().add(col1);
						copy.addColumn(count, cir);
						count++;
						colorCount++;
						
						
					}
					
					GridPane miniPane = new GridPane();
					String guess = controller.convertToString(colorPos);
					
					try {
						if (controller.getRightColorRightPlace(guess) == 0 || controller.getRightColorWrongPlace(guess) == 0) {
							miniPane.addColumn(0, new Label(""));
						}
					} catch (MastermindIllegalLengthException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MastermindIllegalColorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						for (int k = 0; k < controller.getRightColorRightPlace(guess); k++) {
							Circle cir = new Circle(5, Color.BLACK);
							
							ColumnConstraints col1 = new ColumnConstraints();
							col1.setPercentWidth(20);
							col1.setHalignment(HPos.CENTER);
							miniPane.getColumnConstraints().add(col1);
							miniPane.add(cir, k, 0);
						}
					} catch (MastermindIllegalLengthException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MastermindIllegalColorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						for (int k = 0; k < controller.getRightColorWrongPlace(guess); k++) {
							Circle cir = new Circle(5, Color.WHITE);
							
							ColumnConstraints col1 = new ColumnConstraints();
							col1.setPercentWidth(20);
							col1.setHalignment(HPos.CENTER);
							miniPane.getColumnConstraints().add(col1);
							miniPane.add(cir, k, 1);
						}
					} catch (MastermindIllegalLengthException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MastermindIllegalColorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					miniPane.setHgap(5);
					miniPane.setVgap(5);
					
					copy.addColumn(5, miniPane);
					
					copy.setPadding(new Insets(10, 60, 0, 15));
					
					vboxHistory.getChildren().add(copy);
					counter++;
					
					//check to see if win or lose  
					try {
						if (controller.isCorrect(guess)) {
							Alert a = new Alert(Alert.AlertType.INFORMATION);
							a.setTitle("Game Has Ended");
							a.setContentText("You have won the game.");
							a.setHeaderText("You Win!");
							a.showAndWait();
							return;
						} else if (counter == 10) {
							Alert a = new Alert(Alert.AlertType.INFORMATION);
							a.setTitle("Game Has Ended");
							a.setContentText("You have lost the game...");
							a.setHeaderText("You lose...");
							a.showAndWait();
							return;
						}
					} catch (MastermindIllegalLengthException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MastermindIllegalColorException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				});
				pane.addColumn(i, button);
			}
			ColumnConstraints col = new ColumnConstraints();
			col.setPercentWidth(20);
			col.setHalignment(HPos.CENTER);
			pane.getColumnConstraints().add(col);
		}
		pane.setPadding(new Insets(10, 30, 10, 30));
		return pane;
	}

	public static void guiMain(String[] args) {
		launch(args);
		
	}

}