

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.*;

public class PrimaryScene extends Application {
    protected Scene scene;
    Button xCoor = new Button("X Coor:");
    Button yCoor = new Button("Y Coor:");
    TextField xField = new TextField();
    TextField yField = new TextField();

    public void drawPicture(GraphicsContext g, int width, int height) {
        g.strokeLine(50, 200, 550, 200);
        g.strokeLine(50, 400, 550, 400);
        g.strokeLine(200, 50, 200, 550);
        g.strokeLine(400, 50, 400, 550);
        g.strokeLine(50, 100, 550, 100);
        g.strokeLine(100, 50, 100, 550);
        g.strokeLine(50, 300, 550, 300);
        g.strokeLine(300, 50, 300, 550);
        g.strokeLine(500, 50, 500, 550);
        g.strokeLine(50, 500, 550, 500);
    }

    public PrimaryScene() {
        initializeScene();
    }

    public void initializeScene() {
    	//Need getSocket to continue connection
        int width = 600;
        int height = 600;
        Canvas canvas = new Canvas(width, height);
        drawPicture(canvas.getGraphicsContext2D(), width, height);
        HBox controlBar = new HBox(xCoor, yCoor, xField, yField);
        controlBar.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setBottom(controlBar);
        root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
        scene = new Scene(root);
    }

    public Scene getScene() {
        if (scene == null) {
            initializeScene();
        }
        return scene;
    }
    
    @Override
    public void start(Stage stage) {
        stage.setScene(getScene());
        stage.setTitle("Tic Tac Toe");
        stage.show();
        stage.setResizable(false);
    }
}

