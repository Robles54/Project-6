import java.io.PrintWriter;
import java.net.Socket;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Store extends Application {
    private SceneManager sceneManager;

    @Override
    public void start(Stage stage) {
        sceneManager = new SceneManager();
        sceneManager.setStage(stage);
        stage.setTitle("Store Application");
        stage.setOnCloseRequest(event -> {
            event.consume();
            closeApplication(stage);
        });
        
        SceneManager.setScene(SceneManager.SceneType.start);
        stage.show();
    }

    private void closeApplication(Stage stage) {
        try {
            Socket connection = SceneManager.getSocket();
            if (connection != null && !connection.isClosed()) {
                PrintWriter outgoing = new PrintWriter(connection.getOutputStream());
                outgoing.println("QUIT");
                outgoing.flush();
                connection.close();
            }
            stage.close();
            Platform.exit();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error during shutdown: " + e);
            stage.close();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
