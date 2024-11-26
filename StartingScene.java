
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class StartingScene extends SceneBasic {
	private String hostName = "127.0.0.1";
	private int LISTENING_PORT = 32007;
	private Label messageLabel = new Label();

	public StartingScene() {
		super("Starting Scene");
		ButtonBar bar = new ButtonBar();
		bar.addButton("Start", e -> start());
		bar.addButton("Settings", e -> SceneManager.setScene(SceneManager.SceneType.settings));
		root.getChildren().addAll(bar, messageLabel);
	

	}
	
	private void start() {
		try {
			System.out.println("Started");
			Socket socket = new Socket(hostName, LISTENING_PORT);
			SceneManager.setSocket(socket);
			SceneManager.setScene(SceneManager.SceneType.primary);
		} catch (Exception e) {
			messageLabel.setText("Error connecting to server: " + e);
		}
	}

	public Scene getStartingScene() {
		messageLabel.setText("");
		return this.scene;
	}
}
