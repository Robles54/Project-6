import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;

import java.net.Socket;

import javafx.geometry.Insets; 

public class SocketScene extends SceneBasic {
	private Label messageLabel = new Label();
	private Label errorMessage = new Label();
	private UserInput host1;
	private UserInput port1;


	public SocketScene() {
		super("Set Socket");
		host1 = new UserInput("Host:");
		port1 = new UserInput("Port:");
		ButtonBar bar = new ButtonBar();
		bar.addButton("Save", e -> apply());
		bar.addButton("Back", e -> SceneManager.setScene(SceneManager.SceneType.start));
		root.getChildren().addAll(host1, port1, bar, errorMessage);
	}

	private void apply() {
		String host = host1.getText();
		String port = port1.getText();
		try {
			Socket connection = new Socket(host, Integer.parseInt(port));
			SceneManager.setSocket(connection);
			System.out.println("Connection = " + connection);
			errorMessage.setText("");
		}
		catch (Exception e) {
			errorMessage.setText("Error trying to connect to server.");
			System.out.println("Error:  " + e);
		}
	}

	private void saveSettings() {
		messageLabel.setText("Settings saved");
	}
}
