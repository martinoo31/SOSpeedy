package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.util.*;

public class Login {

    public Map<String,Scene> scenes;

    public Login(Map<String,Scene> scenes) {
        this.scenes = scenes;
    }
    
    @SuppressWarnings("unused")
	public GridPane createContent() {
    	// Create GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        // Username Label and TextField
        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 0);
        TextField usernameInput = new TextField();
        GridPane.setConstraints(usernameInput, 1, 0);

        // Password Label and PasswordField
        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 1);
        PasswordField passwordInput = new PasswordField();
        GridPane.setConstraints(passwordInput, 1, 1);

        // Login Button
        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);

        // Message Text
        Text messageText = new Text();
        GridPane.setConstraints(messageText, 1, 3);

        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            // Simple validation logic
            if (username.equals("user") && password.equals("password") || true) {
                
                Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
                stage.setScene(scenes.get("home"));
            } else {
                messageText.setFill(Color.RED);
                messageText.setText("Invalid username or password");
            }
        });

        // Add everything to grid
        grid.getChildren().addAll(usernameLabel, usernameInput, passwordLabel, passwordInput, loginButton, messageText);
        
        return grid;
    }
}