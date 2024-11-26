import java.net.Socket;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class PrimaryScene extends SceneBasic{
	private UserInput xCord = new UserInput("X Cordinate Position");
	private UserInput yCord = new UserInput("Y Cordinate Position");
	private Label errorMessage = new Label();
	private Socket connection;
	private String hostName = "127.0.0.1";
	private int LISTENING_PORT = 32007;
	
	public PrimaryScene{
		super("Primary Scene");
		
//		GridPane gridPane = new GridPane();
//		gridPane.setMinSize(400, 200);
//		gridPane.setPadding(new Insets(10, 10, 10, 10));
//		
		
	}
	
	private Canvas makeCanvas() {
		Canvas canvas = new Canvas(600, 600);
		canvas.setOnMousePressed(this::mousePressed);
		canvas.setOnMouseReleased(this::mouseReleased);
		canvas.setOnMouseDragged(this::mouseDragged);
		return canvas;
	}
	
	private HBox makeToolPanel(Canvas canvas) {
		ButtonBar buttonBar = new ButtonBar();
		buttonBar.addButton("Settings", e -> SceneManager.setScene(SceneManager.SceneType.settings));
		buttonBar.addButton("Add Ship", e -> addShip( new shipThing()));
		
		

	}
	
	
	
	
	public Scene getPrimaryScene() {
		errorMessage.setText("");
        return this.scene;
	}

}
