import java.io.PrintWriter;
import java.net.Socket;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public abstract class SceneBasic {
    protected Scene scene;
    protected VBox root = new VBox();

    public SceneBasic(String titleText) {
        Label message = new Label(titleText);
        message.setFont(new Font(40));
        root.getChildren().add(message);
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(10);
        scene = new Scene(root, 600, 400);
    }

    public Scene getScene() {
        return scene;
    }
    
    public void addButton(String text, EventHandler<ActionEvent> func) {
    	Button button = new Button(text);
		button.setOnAction(func);
		root.getChildren().addAll(button);
    }

    protected void logout() {
        try {
            Socket connection = SceneManager.getSocket();
            if (connection != null && !connection.isClosed()) {
                PrintWriter outgoing = new PrintWriter(connection.getOutputStream());
                outgoing.println("QUIT");
                outgoing.flush();
                connection.close();
            }
            SceneManager.setSocket(null);
            SceneManager.setScene(SceneManager.SceneType.start);
        } catch (Exception e) {
            System.out.println("Error during logout: " + e);
        }
    }
}
