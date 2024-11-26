import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;

import java.net.Socket;

import javafx.geometry.Insets; 

public class SettingsScene extends SceneBasic {
	private Label messageLabel = new Label();
	private Label errorMessage = new Label();
	private UserInput color;
	private UserInput username;


	public SettingsScene() {
		super("Settings");
		color = new UserInput("Color:");
		username = new UserInput("Username:");
		ButtonBar bar = new ButtonBar();
		bar.addButton("Save", e -> apply());
		bar.addButton("Back", e -> SceneManager.setScene(SceneManager.SceneType.start));
		root.getChildren().addAll(color, username, bar, errorMessage);
	}

	private void apply() {
//		String host = host1.getText();
//		String port = port1.getText();
//		try {
//			Socket connection = new Socket(host, Integer.parseInt(port));
//			SceneManager.setSocket(connection);
//			System.out.println("Connection = " + connection);
//			errorMessage.setText("");
//		}
//		catch (Exception e) {
//			errorMessage.setText("Error trying to connect to server.");
//			System.out.println("Error:  " + e);
//		}
	}

	private void saveSettings() {
		messageLabel.setText("Settings saved");
	}
}
