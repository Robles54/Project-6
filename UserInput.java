import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class UserInput extends HBox{
	private Label label;
	protected TextField textField;
	
	public UserInput(String labelText) {
		setAlignment(Pos.CENTER);
		setMinWidth(400);
		setSpacing(10);
		label=new Label(labelText);
		textField=new TextField();
		getChildren().addAll(label, textField);
	}
	
	public String getText() {
		return textField.getText();
	}
	
	public void setText(String text) {
		textField.setText(text);
	}
}
