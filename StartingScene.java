
import java.net.Socket;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class StartingScene extends SceneBasic {
	private Socket connection;
	private String hostName = "127.0.0.1";
	private int LISTENING_PORT = 32007;
	private Label messageLabel = new Label();
	
	public StartingScene() {
		super("Starting Scene");
		
//		GridPane gridPane = new GridPane();
//		gridPane.setMinSize(400, 200);
//		gridPane.setPadding(new Insets(10, 10, 10, 10));
//		gridPane.setVgap(5);
//		gridPane.setHgap(5);
//		
//		ButtonBar buttonBar = new ButtonBar();
//		buttonBar.addButton("Start Game", e -> SceneManager.setScene(SceneManager.SceneType.primary));
//		buttonBar.addButton("Settings", e -> SceneManager.setScene(SceneManager.SceneType.settings));
//		
//		gridPane.add(buttonBar, 0, 2);
//		gridPane.setAlignment(Pos.TOP_CENTER);
//		root.getChildren().addAll(gridPane);
		ButtonBar bar = new ButtonBar();
		bar.addButton("Start", e -> SceneManager.setScene(SceneManager.SceneType.primary));
		bar.addButton("Settings", e -> SceneManager.setScene(SceneManager.SceneType.settings));
		root.getChildren().addAll(bar, messageLabel);
		
	}
	
	public Scene getStartingScene() {
		messageLabel.setText("");
        return this.scene;
	}
}
